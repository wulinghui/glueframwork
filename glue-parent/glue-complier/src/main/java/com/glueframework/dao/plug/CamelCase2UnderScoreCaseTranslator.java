package com.glueframework.dao.plug;

import java.io.IOException;
import java.io.Writer;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.text.translate.CharSequenceTranslator;

/**
 * @author wulinghui
 * 驼峰命名法(CamelCase)转--下划线命名法(UnderScoreCase)
 */
public class CamelCase2UnderScoreCaseTranslator extends CharSequenceTranslator {

	@Override
	public int translate(CharSequence input, int index, Writer out)
			throws IOException {
		//是大写
		char charAt = input.charAt(index);
		if( CharUtils.isAsciiAlphaUpper(charAt) ){
			charAt = Character.toLowerCase(charAt);
			if(index !=0 ) out.append('_');
			out.append(charAt);
			return Character.charCount(Character.codePointAt(input, index));
		}
		return 0;
	}
	public static void main(String[] args) {
		CamelCase2UnderScoreCaseTranslator translator = new CamelCase2UnderScoreCaseTranslator();
		String translate = translator.translate("userName");
		System.out.println(translate); 
		translate = translator.translate("U_serName");
		System.out.println(translate); 
		translate = translator.translate("U_UerName");
		System.out.println(translate); 
		translate = translator.translate("findByNameAndPassword");
		// select * from user where name = ? and password = ?
		System.out.println(translate.split("_").length); 
		for (String string : translate.split("_")) {
			System.out.println(string);  
		}
	}
}
