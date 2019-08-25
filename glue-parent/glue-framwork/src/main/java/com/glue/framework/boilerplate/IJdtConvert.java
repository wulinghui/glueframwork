package com.glue.framework.boilerplate;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent.Kind;

import org.eclipse.jdt.core.dom.CompilationUnit;

public interface IJdtConvert {
	IJdtConvert DEFAULT = new JdtConvertDefault();
	String doHandle(File file);
	public CompilationUnit getCreateCompilationUnit();
	public CompilationUnit createCompilationUnit(String src);
	public String doHandle(String src) ;
}
     