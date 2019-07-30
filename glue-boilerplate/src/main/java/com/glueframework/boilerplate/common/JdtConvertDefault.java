package com.glueframework.boilerplate.common;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent.Kind;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class JdtConvertDefault implements IJdtConvert {
	ICompilationUnitHandle [] handles = null;
	CompilationUnit createCompilationUnit = null;
	
	public CompilationUnit getCreateCompilationUnit() {
		return createCompilationUnit;
	}

	public JdtConvertDefault() {
		super();
		init();
	}
	
	protected void init() {
		handles = (ICompilationUnitHandle[]) System.getProperties()
				.get(JdtConvertDefault.class.getName());
	}

	@Override
	public String doHandle(File file) {
		try {
			String src = FileUtils.readFileToString( file ,"UTF-8");
			createCompilationUnit = createCompilationUnit(src);
			for (ICompilationUnitHandle handle : handles) {
				handle.doHandle( createCompilationUnit );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//要解析的文件
		return createCompilationUnit.getRoot().toString();
	}
	
	public CompilationUnit createCompilationUnit(String src){

        // https://www.cnblogs.com/SEC-fsq/p/7845222.html
        ASTParser parser = createAstParser();
        IClassFile source = null;
        
//        try {
//            src = FileUtils.readFileToString(new File("JdtTest.java"),"UTF-8");  //要解析的文件
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        parser.setSource(src.toCharArray());
//        parser.setSource( );
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);  //下个断点可以看看cu的types成员就是整个语法树
        return cu;
	}

	protected ASTParser createAstParser() {
		ASTParser parser = ASTParser.newParser(AST.JLS8); //设置Java语言规范版本
        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        parser.setCompilerOptions(null);
        parser.setResolveBindings(true);     

        Map<String, String> compilerOptions = JavaCore.getOptions();
        compilerOptions.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_8); //设置Java语言版本
        compilerOptions.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_8);
        compilerOptions.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);
        parser.setCompilerOptions(compilerOptions); //设置编译选项
		return parser;
	}
	
	public static void main(String[] args) throws Exception {
		JdtConvertDefault jdtConvertDefault = new JdtConvertDefault();
		// jdt无法这样直接解析class文件。需要先反编译一下。
		ASTParser parser = jdtConvertDefault.createAstParser();
		Class<?> forName = Class.forName("com.glueframework.boilerplate.common.JdtChangeMonitor");
		IFile file = null;
		IClassFile source =  JavaCore.createClassFileFrom( file );
		parser.setSource(source);
	}
}
