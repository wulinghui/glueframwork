package com.glueframework.boilerplate.common;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TextElement;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.junit.Test;

import com.glueframework.common.util.DebugClass;
import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

public class TestCompilationUnitTools extends JdtConvertDefault{
	String src =  null;
	TypeDeclarationTools tools;
	private static ILogger logger = LogMSG.getLogger();
	{
		try {
			src = FileUtils.readFileToString( new File("Z:\\Virtual-Machines-Files-Writer\\workpace\\glueframwork1\\glue-parent\\glue-boilerplate\\src\\main\\java\\com\\glueframework\\boilerplate\\common\\JdtConvertDefault.java") ,"UTF-8");
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
		 
		tools = new TypeDeclarationTools(createCompilationUnit , 0);
	}
	@Test
	public void testGetClassName() {
		List<MethodDeclaration> methodDeclarationList = tools.getMethodDeclarationList();
		for (MethodDeclaration methodDeclaration : methodDeclarationList) {
			if( methodDeclaration.getName().toString().equals("createAstParser") ){
				// 获得方法内部的代码。
				System.out.println( methodDeclaration.getLength() );
				
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
		List<MethodDeclaration> methodDeclarationList = tools.getMethodDeclarationList();
		for (MethodDeclaration methodDeclaration : methodDeclarationList) {
			if( methodDeclaration.getName().toString().equals("createAstParser") ){
				// 获得方法内部的代码。
				String string = methodDeclaration.getBody().toString().replace("  ", "    ");
				string = string.replace("}", "  }");
//				ASTNode root = methodDeclaration.getRoot();
				CompilationUnit createCompilationUnit = super.createCompilationUnit(methodDeclaration.getRoot().toString());
				ASTNode root = createCompilationUnit.getRoot();
//				methodDeclaration.
				String replace = root.toString().replace(string, "{}");
				logger.trace("root=[%s],body=[%s],replace=[%s]" , root , string , replace);
				createCompilationUnit = super.createCompilationUnit(replace);
				DebugClass.printNoAgeMethod( createCompilationUnit );
				break;
			}
		}
	}

	@Test
	public void testGetUnit() {
		List<MethodDeclaration> methodDeclarationList = tools.getMethodDeclarationList();
		for (MethodDeclaration methodDeclaration : methodDeclarationList) {
			if( methodDeclaration.getName().toString().equals("createAstParser") ){
				tools.replacemethod(methodDeclaration.getBody().toString(), "{}");
			}
		}
		logger.trace("root=[%s]" , tools.getUnit().getRoot());
		
	}

	@Test
	public void testGetTypeDeclaration() {
		List<FieldDeclaration> methodDeclarationList = tools.getFieldDeclarationList();
		for (FieldDeclaration methodDeclaration : methodDeclarationList) {
			tools.replacemethod( methodDeclaration.toString(), "");
		}
		logger.trace("root=[%s]" , tools.getUnit().getRoot());
	}

}
