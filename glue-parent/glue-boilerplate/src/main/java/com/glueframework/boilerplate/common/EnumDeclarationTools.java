package com.glueframework.boilerplate.common;

import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.SimpleType;

public class EnumDeclarationTools extends CompilationUnitTools{

	public EnumDeclarationTools(CompilationUnit createCompilationUnit,
			int indexClass) {
		super(createCompilationUnit, indexClass);
	}
	/**
	 * @return size==0 not interface。
	 */
	public List<SimpleType> getInterfaceTypes(){
		return getTypeDeclaration().superInterfaceTypes();
	}
	
	public EnumDeclaration getTypeDeclaration() {
		List types = createCompilationUnit.types();
		EnumDeclaration classType = (EnumDeclaration) types.get(indexClass);
		return classType;
	}
}
