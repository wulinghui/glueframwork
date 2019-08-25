package com.glue.framework.complier;

import java.io.File;
import java.io.FileFilter;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import com.glue.framework.boilerplate.IJdtConvert;
import com.glue.framework.boilerplate.JdtChangeMonitor;
import com.glue.framework.common.tools.CompileTool;

public class CompilerChangeMonitor extends JdtChangeMonitor {
	 String[] jar , target;
	 
	/**
	 * @param directory
	 * @param fileFilter
	 * @param jdtConvert
	 */
	public CompilerChangeMonitor(File directory, FileFilter fileFilter,
			IJdtConvert jdtConvert) {  
		super(directory, fileFilter, jdtConvert); 
	}         

	/**  
	 * @param directory  
	 */ 
	public CompilerChangeMonitor(File directory) {   
		super(directory);  
	}                            
         
	
	@Override
	protected void afterDo(File file , String srcInner) {
		// 把srcInner,内容编译class，生成到指定目录。
		CompilationUnit createCompilationUnit = this.jdtConvert
				.getCreateCompilationUnit();
		String className;
		TypeDeclaration classType = (TypeDeclaration) createCompilationUnit
				.types().get(0);
		
		className = createCompilationUnit.getPackage().getName().toString()
				+ "."+ classType.getName().toString();
		CompileTool tool = new CompileTool(className);
		if( tool.compile( srcInner ) ){
			this.logger.debug("user javac compile javaFile seccuess=[{}] ",file );
		}
	}
	 
	public void setJar(String[] jar) {
		this.jar = jar;
	}

	public void setTarget(String[] target) {
		this.target = target;
	}
}
