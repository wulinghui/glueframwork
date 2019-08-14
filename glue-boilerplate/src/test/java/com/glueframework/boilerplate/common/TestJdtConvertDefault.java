package com.glueframework.boilerplate.common;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.Test;

import com.glueframework.common.util.DebugClass;

public class TestJdtConvertDefault extends JdtConvertDefault{
	@Test
	public void testCreateCompilationUnit() throws Exception {
		 String src =  FileUtils.readFileToString( new File("Z:\\Virtual-Machines-Files-Writer\\workpace\\glueframwork\\glue-boilerplate\\src\\main\\java\\com\\glueframework\\boilerplate\\common\\JdtConvertDefault.java") ,"UTF-8"); 
		 //"class A { void method1(int b){;} }";
		CompilationUnit createCompilationUnit = super.createCompilationUnit(src);
		 DebugClass.printAllGetMethod(createCompilationUnit);
	}
	
	public static void main(String[] args) throws Exception {
		new TestJdtConvertDefault().testCreateCompilationUnit(); 
	}  
}
