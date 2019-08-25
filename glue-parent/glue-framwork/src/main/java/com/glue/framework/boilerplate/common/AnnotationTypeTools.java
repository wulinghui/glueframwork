package com.glue.framework.boilerplate.common;

import java.util.List;

import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class AnnotationTypeTools extends CompilationUnitTools{

	public AnnotationTypeTools(CompilationUnit createCompilationUnit,
			int indexClass) {
		super(createCompilationUnit, indexClass);
	}
	
	public AnnotationTypeDeclaration getTypeDeclaration() {
		List types = createCompilationUnit.types();
		AnnotationTypeDeclaration classType = (AnnotationTypeDeclaration) types.get(indexClass);
		return classType;
	}
}
