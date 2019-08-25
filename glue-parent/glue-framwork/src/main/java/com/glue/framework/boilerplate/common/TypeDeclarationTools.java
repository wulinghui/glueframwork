package com.glue.framework.boilerplate.common;

import java.util.List;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
/*
 *		{@link AnnotationTypeMemberDeclaration}
 *		{@link EnumConstantDeclaration}
 * 		{@link FieldDeclaration}
 * 		{@link Initializer}
 *		{@link MethodDeclaration}
 */
public class TypeDeclarationTools extends CompilationUnitTools{
	
	public TypeDeclarationTools(CompilationUnit createCompilationUnit,
			int indexClass) {
		super(createCompilationUnit, indexClass);
	}
	/**
	 * @return size==0 not interface。
	 */
	public List<SimpleType> getInterfaceTypes(){
		return getTypeDeclaration().superInterfaceTypes();
	}
	public String getSuperclassName(){
		Type superclassType = getTypeDeclaration().getSuperclassType();
		if( superclassType ==null ){
			return Object.class.getName();
		}else{
			SimpleType type = (SimpleType) superclassType;
			return getImportClassName(type.getName().toString());
		}
	}
	public List<Statement> getBodys(MethodDeclaration methodDeclaration){
		Block body = methodDeclaration.getBody();
		
		return body.statements();
	}
	public TypeDeclaration getTypeDeclaration() {
		List types = createCompilationUnit.types();
		TypeDeclaration classType = (TypeDeclaration) types.get(indexClass);
		return classType;
	}
}
