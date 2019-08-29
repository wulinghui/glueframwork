package com.glueframework.jdbc;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.translate.CharSequenceTranslator;

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
	 * @param prefix
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
	
	boolean 
	
	@Override
	public int translate(CharSequence input, int index, Writer out)
			throws IOException {
		if(input.charAt(index) == this.out ){
			namedPrepared.add(  currentIndex++, null  );
			return 0;
		}
		if( input.charAt(index) == prefix ){
			List<Character> list = new ArrayList<Character>(10);
			char charAt;
			do{
				index++;
				charAt = input.charAt(index);
				if(input.charAt(index) != suffix){
					list.add(charAt);
				}else{
					namedPrepared.add(  currentIndex++, StringUtils.join(list,"")  );
					out.write(this.out); 
					return list.size()+2;
				}
			}while(index < input.length());
		}
		return 0;
	}
	public static void main(String[] args) {
		String str = "insert into student values(&name , ? ,&password ,&password ,?,?)";
		NamedPreparedTranslator namedPreparedTranslator = new NamedPreparedTranslator();
		String translate = namedPreparedTranslator.translate(str);
//insert into student values(?, ? ,?,?,?,?)
//		[name, null, password, password, null, null]
		System.out.println(translate);
		System.out.println(namedPreparedTranslator.namedPrepared);
	}
}
