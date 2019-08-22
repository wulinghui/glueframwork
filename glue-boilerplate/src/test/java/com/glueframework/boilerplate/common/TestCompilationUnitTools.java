package com.glueframework.boilerplate.common;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TextElement;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.junit.Test;

import com.glueframework.common.util.DebugClass;

public class TestCompilationUnitTools extends JdtConvertDefault{
	String src =  null;
	TypeDeclarationTools tools;
	{
		try {
			src = FileUtils.readFileToString( new File("Z:\\Virtual-Machines-Files-Writer\\workpace\\glueframwork1\\glue-boilerplate\\src\\main\\java\\com\\glueframework\\boilerplate\\common\\CopyOfJdtConvertDefault.java") ,"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		} 
		//"class A { void method1(int b){;} }";
		CompilationUnit createCompilationUnit = super.createCompilationUnit(src);
//		List types = createCompilationUnit.types();
//		System.out.println(createCompilationUnit+"====11111111111111======" + types.size() );
//		for (Object object : types) {    
//			DebugClass.printgetClassMethod(object);
//		}   
		
//		tools = new TypeDeclarationTools(createCompilationUnit , 0);
	}
	@Test
	public void testGetClassName() {
		List<MethodDeclaration> methodDeclarationList = tools.getMethodDeclarationList();
		for (MethodDeclaration methodDeclaration : methodDeclarationList) {
			if( methodDeclaration.getName().toString().equals("createAstParser") ){
				// 获得方法内部的代码。
				Block body = methodDeclaration.getBody();
				body.statements();
				//  
				System.out.println(body.properties());
				System.out.println( body.structuralPropertiesForType().get(0).getClass() );
				List statements = body.statements();
				System.out.println("====11111111111111======" + statements.size() );
				DebugClass.printNoAgeMethod( body );
				System.out.println("====11111111111111======" + statements.size() );
//				statements.add(e)
				for (Object object2 : statements) {
					if( object2 instanceof VariableDeclarationStatement){
						VariableDeclarationStatement variableDeclarationStatement
							= (VariableDeclarationStatement) object2;
						variableDeclarationStatement.fragments();
					}else{
						
					}
					DebugClass.printNoAgeMethod(object2); 
				}  
				break;          
			}
		}
	}

	@Test
	public void testGetImportClassName() {
		AST ast = tools.getUnit().getAST();
		MethodInvocation methodInvocation = ast.newMethodInvocation();
		SimpleName newSimpleName = ast.newSimpleName("System.out.println(1111111);");
		methodInvocation.setExpression(newSimpleName);
		DebugClass.printNoAgeMethod( methodInvocation );
		
	}

	@Test
	public void testGetClassName11() {

		AST ast = tools.getUnit().getAST();
		MethodInvocation methodInvocation = ast.newMethodInvocation();
		SimpleName newSimpleName = ast.newSimpleName("System.out.println(1111111);");
		
		methodInvocation.setExpression(newSimpleName);
		
		DebugClass.printNoAgeMethod( methodInvocation );
		
	
		
		CompilationUnit parseCompilationUnit = ast.parseCompilationUnit(src.toCharArray());
		TextElement newTextElement = ast.newTextElement();
		newTextElement.setText("System.out.println(1111111);");
		
		System.out.println(parseCompilationUnit.types());  
		DebugClass.printNoAgeMethod( parseCompilationUnit );
	}

	@Test
	public void testGetIndexClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUnit() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTypeDeclaration() {
		fail("Not yet implemented");
	}

}
