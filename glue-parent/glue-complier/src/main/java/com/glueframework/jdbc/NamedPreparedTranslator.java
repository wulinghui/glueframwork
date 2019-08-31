package com.glueframework.jdbc;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.translate.CharSequenceTranslator;

import com.glueframework.commons.DBTools;

public class NamedPreparedTranslator  extends CharSequenceTranslator {
//	public  final char prefix = '&';
//	public  final char suffix = ' ';
//	public  final char out = '?';
	
	public  final String prefix ;
	public  final String suffix ;
	public  final String out;
	public NamedPreparedTranslator() {
		this("&"," ","?");
	}
	/**
	 * @param prefix0
	 * @param suffix
	 * @param out
	 */
	public NamedPreparedTranslator(String prefix, String suffix, String out) {
		super();
		this.prefix = prefix;
		this.suffix = suffix;
		this.out = out;
	}
	
	private final List<String> namedPrepared = new ArrayList<String>(10);
	private int currentIndex = 0;
	
	
	public List<String> getNamedPrepared() {
		return namedPrepared;
	}
	@Override
	public int translate(CharSequence input, int index, Writer out)
			throws IOException {
		if(input.charAt(index)==prefix.charAt(0))
		{
			Boolean flag=true;
			int count=0;
			while(count<prefix.length()-1) {
				count++;
				index++;
				if(prefix.charAt(count)!=input.charAt(index))
					{
					flag=false;return 0;
					}
				
			}
			if(flag) {
				count=0;
				Boolean flag2=false,flag3=true;
				StringBuffer buffer=new StringBuffer();
				do{
					index++;				
				if(suffix.charAt(0)==input.charAt(index))
				{
					flag2=true;
					break;
				}
				else {
					buffer.append(input.charAt(index));
				}
				}while(index<input.length());
				if(flag2) {
					  do {
						   count++;
						   index++;
						   System.out.println(index+"--"+count);
						   if(suffix.charAt(count)!=input.charAt(index))
						   {
								flag3=false;return 0;  
						   }
					   }while(count<suffix.length()-1);
					  if(flag3)
					  {
						  namedPrepared.add(buffer.toString());
						  out.write("??");
						  return buffer.toString().length()+suffix.length()+prefix.length();
					  }
				}
			   
			}
		}
//		if(input.charAt(index) == this.out ){
//			namedPrepared.add(  currentIndex++, null  );
//			return 0;
//		}
//		if( input.charAt(index) == prefix ){
//			List<Character> list = new ArrayList<Character>(10);
//			char charAt;
//			do{
//				index++;
//				charAt = input.charAt(index);
//				if(input.charAt(index) != suffix){
//					list.add(charAt);
//				}else{
//					namedPrepared.add(  currentIndex++, StringUtils.join(list,"")  );
//					out.write(this.out); 
//					return list.size()+2;
//				}
//			}while(index < input.length());
//		}
		return 0;
	}
	public static void main(String[] args) throws Exception {
//		String str = "insert into student values(&name , ?? ,&password ,&password ,?,?)";
		String str = "insert into student values( &{name}} , ? , &{password}} ,&{password}} ,?,?)";
//		String str = "insert into student values( ?? , ? , ?? , ?? ,?,?)";
		// input args
		Map map = null;
		NamedPreparedTranslator namedPreparedTranslator = new NamedPreparedTranslator("&{" , "}}" ,"??");
		// sql
		String translate = namedPreparedTranslator.translate(str);
		System.out.println(translate);
		System.out.println(namedPreparedTranslator.namedPrepared);
		// names
		List<String> namedPrepared2 = namedPreparedTranslator.getNamedPrepared();
		// values
		List<Object> value = null;
		for (String string : namedPrepared2) {
			value.add( map.get(namedPrepared2) );
		}
		// get jdbc
		QueryRunner queryRunner = DBTools.getInstance().getQueryRunner();
		// execute
		queryRunner.update(translate, value.toArray());
		// 
//insert into student values(?, ? ,?,?,?,?)
//		[name, null, password, password, null, null]
	}
}
