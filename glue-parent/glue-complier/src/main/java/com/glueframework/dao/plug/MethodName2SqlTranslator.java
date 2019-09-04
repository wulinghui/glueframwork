package com.glueframework.dao.plug;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.text.translate.CharSequenceTranslator;

public class MethodName2SqlTranslator extends CharSequenceTranslator {
	private StringBuilder sqlTemp = new StringBuilder();
	private StringBuilder sql = new StringBuilder();
	boolean tableNameInit;
	char sqlFalg;
	@Override
	public int translate(CharSequence input, int index, Writer out)
			throws IOException {
		String str = null;
		// select * from user where name = ? and password = ?
		// UPDATE user SET password = ? WHERE name = ?
		// DELETE FROM user WHERE name = ?
		// INSERT INTO user (name,password) VALUES (?,?) 
		if( index == 0 ){
			sqlTemp.append( input.subSequence(0, 3) );
			str = sqlTemp.toString();
			if( "sel".equalsIgnoreCase(str) ){
				out.append("SELECT * FROM ");
				sqlFalg = 's';
			}else if( "ins".equalsIgnoreCase(str)){
				out.append("INSERT INTO ");
				sqlFalg = 'i';
			}else if( "del".equalsIgnoreCase(str)){
				out.append("DELETE ");
				sqlFalg = 'd';
			}else if( "upd".equalsIgnoreCase(str)){
				out.append("UPDATE ");
				sqlFalg = 'u';
			}else{
				return input.length();// 不翻译了。
			}
			return 3; // 往后3个不翻译了。
		}else if(  !tableNameInit  ){  // 表名还没初始化。
			
		}
		return 0;
	}

}
