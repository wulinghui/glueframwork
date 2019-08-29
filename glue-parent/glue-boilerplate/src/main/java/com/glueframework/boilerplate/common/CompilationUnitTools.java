package com.glueframework.boilerplate.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeMemberDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

public abstract class CompilationUnitTools {
	protected static ILogger logger = LogMSG.getLogger();
	
	CompilationUnit createCompilationUnit;
	final int indexClass;
	private  List<AnnotationTypeMemberDeclaration> annotationList ;
	private  List<EnumConstantDeclaration> enumConstantList;
	private  List<FieldDeclaration> fieldDeclarationList ;
	private  List<Initializer> initializerList;
	private  List<MethodDeclaration> methodDeclarationList;
	/**
	 * @param createCompilationUnit
	 * @param indexClass
	 */
	public CompilationUnitTools(CompilationUnit createCompilationUnit,
			int indexClass) {
		super();
		this.createCompilationUnit = createCompilationUnit;
		this.indexClass = indexClass;
		init();
	}
	protected void init(){
		annotationList = new ArrayList<>();
		enumConstantList = new ArrayList<>();
		fieldDeclarationList = new ArrayList<>();
		initializerList = new ArrayList<>();
		methodDeclarationList = new ArrayList<>();
		for (Object object : getTypeDeclaration().bodyDeclarations()) {
			if(object instanceof FieldDeclaration){
				fieldDeclarationList.add((FieldDeclaration) object);
			}else if( object instanceof AnnotationTypeMemberDeclaration){
				annotationList.add((AnnotationTypeMemberDeclaration) object);
			}else if( object instanceof EnumConstantDeclaration){
				enumConstantList.add((EnumConstantDeclaration) object);
			}else if( object instanceof Initializer){
				initializerList.add((Initializer) object);
			}else if( object instanceof MethodDeclaration){
				methodDeclarationList.add((MethodDeclaration) object);
			}
		}
	}
	
	/**
	 * @param indexClass 一个文件第几个类。
	 * @return 获得本身类的完全限定名
	 */
	public String getClassName(){
		AbstractTypeDeclaration classType = getTypeDeclaration();
		return createCompilationUnit.getPackage().getName() + "." + classType.getName();
	}
	/**
	 * @param simpleClassName
	 * @return
	 */
	public String getImportClassName(String simpleClassName){
		// 本身是com.AA
		if( simpleClassName.contains(".")) return simpleClassName;
		// AA
		for (Object element : createCompilationUnit.imports()) {
			if( element.toString().endsWith( "."+ simpleClassName + ";") ){
				ImportDeclaration declaration = (ImportDeclaration) element;
				return declaration.getName().toString();
			}
		}
		// java.lang.String  的 String
		return simpleClassName;
	}
	
	
	
	public int getIndexClass() {
		return indexClass;
	}
	public CompilationUnit getUnit() {
		return createCompilationUnit;
	}
	public CompilationUnit getCreateCompilationUnit() {
		return createCompilationUnit;
	}
	public List<AnnotationTypeMemberDeclaration> getAnnotationList() {
		return annotationList;
	}
	public List<EnumConstantDeclaration> getEnumConstantList() {
		return enumConstantList;
	}
	public List<FieldDeclaration> getFieldDeclarationList() {
		return fieldDeclarationList;
	}
	public List<Initializer> getInitializerList() {
		return initializerList;
	}
	public List<MethodDeclaration> getMethodDeclarationList() {
		return methodDeclarationList;
	}
	public AbstractTypeDeclaration getTypeDeclaration() {
		List types = createCompilationUnit.types();
		TypeDeclaration classType = (TypeDeclaration) types.get(indexClass);
		return classType;
	}

	public void replacemethod(String oldInner,String methodInner) {
		replaceCompilationUnit(oldInner.replace("  ", "    ").replace("}", "  }") , methodInner );
	}
	public void replaceCompilationUnit(String oldInner,String methodInner) {
		// 获得方法内部的代码。
		String string = oldInner;
		//oldInner.replace("  ", "    ");
//		string = string.replace("}", "  }");
		ASTNode root = createCompilationUnit.getRoot();
		String replace = root.toString().replace(string, methodInner);
		logger.trace("root=[%s],body=[%s],replace=[%s]" , root , string , replace);
		createCompilationUnit = IJdtConvert.DEFAULT
				.createCompilationUnit(replace);
		init();
	}

}
