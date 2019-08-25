package com.glue.framework.boilerplate.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeMemberDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public abstract class CompilationUnitTools {
	final CompilationUnit createCompilationUnit;
	final int indexClass;
	private final List<AnnotationTypeMemberDeclaration> annotationList = new ArrayList<>();
	private final List<EnumConstantDeclaration> enumConstantList = new ArrayList<>();
	private final List<FieldDeclaration> fieldDeclarationList = new ArrayList<>();
	private final List<Initializer> initializerList = new ArrayList<>();
	private final List<MethodDeclaration> methodDeclarationList = new ArrayList<>();
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


}
