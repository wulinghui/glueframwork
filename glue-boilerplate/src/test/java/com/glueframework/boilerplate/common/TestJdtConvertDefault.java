package com.glueframework.boilerplate.common;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.junit.Test;

import com.glueframework.common.util.DebugClass;

public class TestJdtConvertDefault extends JdtConvertDefault{
	String src =  null;
	{
		try {
			src = FileUtils.readFileToString( new File("Z:\\Virtual-Machines-Files-Writer\\workpace\\glueframwork1\\glue-boilerplate\\src\\main\\java\\com\\glueframework\\boilerplate\\common\\JdtConvertDefault.java") ,"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	//"class A { void method1(int b){;} }";
	CompilationUnit createCompilationUnit = super.createCompilationUnit(src);
	@Test
	public void testCreateCompilationUnit() throws Exception {
		 DebugClass.printAllGetMethod(createCompilationUnit);
	}
	@Test
	public void testUpdateNew() throws Exception {
		
		
		List types = createCompilationUnit.types();
		
		System.out.println(types.size());  
		TypeDeclaration classType = (TypeDeclaration) types.get(0);
		DebugClass.printNoAgeMethod(classType.getBodyDeclarationsProperty());	
		types = classType.bodyDeclarations();
		for (Object object : types) {   
			DebugClass.printNoAgeMethod(object);	
			System.out.println("======================"); 
		}
	}
	@Test
	public void testUpdateNew1() throws Exception {
//		createCompilationUnit.getPackage();  包名
//		createCompilationUnit.imports();   导入的包名
		List types = createCompilationUnit.types(); // 获取的多个类。
		System.out.println(types.size());  
		// 类为TypeDeclaration  。   TypeDeclaration 为  ClassDeclaration  或者 InterfaceDeclaration
		TypeDeclaration classType = (TypeDeclaration) types.get(0);
		//     获得Body                                          
//		DebugClass.printNoAgeMethod(classType.getBodyDeclarationsProperty());	
		// 这里执行了删除操作，就没了
//		DebugClass.printNoAgeMethod(classType.bodyDeclarations());
		// 
		types = classType.bodyDeclarations();
		for (Object object : types) {
			if(object instanceof FieldDeclaration){
				FieldDeclaration fieldDeclaration = (FieldDeclaration) object;
				// 这里也不能获取类的完全限定名。所以需要自己定义。
				Type type = fieldDeclaration.getType();
//				DebugClass.printNoAgeMethod(type);
			}else if( object instanceof MethodDeclaration){
				MethodDeclaration methodDeclaration = (MethodDeclaration) object;
				if( methodDeclaration.getName().toString().equals("createAstParser") ){
					// 获得方法内部的代码。
					Block body = methodDeclaration.getBody();
					//
//					DebugClass.printNoAgeMethod(body);
					List statements = body.statements();
					System.out.println("====11111111111111======" + statements.size() );
//					DebugClass.printNoAgeMethod( statements.get(0)); 
					
					for (Object object2 : statements) {
						if( object2 instanceof VariableDeclarationStatement){
							VariableDeclarationStatement variableDeclarationStatement
								= (VariableDeclarationStatement) object2;
							variableDeclarationStatement.fragments();
						}else{
							
						}
						DebugClass.printNoAgeMethod(object2); 
//						DebugClass.printgetClassMethod(object2);	
					}  
					break;          
				}
			}else{
				continue;
			}
			DebugClass.printNoAgeMethod(new Object());	
//			DebugClass.printgetClassMethod(object);	
		}
	}
	
//	public static void main(String[] args) throws Exception {
//		new TestJdtConvertDefault().testCreateCompilationUnit(); 
//	}  
}
