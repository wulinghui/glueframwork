package com.glueframework.boilerplate.jdcore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.jd.core.v1.ClassFileToJavaSourceDecompiler;
import org.jd.core.v1.api.loader.Loader;
import org.jd.core.v1.api.loader.LoaderException;
import org.jd.core.v1.api.printer.Printer;

import com.glueframework.boilerplate.common.JdtConvertDefault;

/**
 * @author Administrator
 * class文件反编译成java文件。
 */
public class TestJdCore {
	static Loader loader = new Loader() {
	    @Override
	    public byte[] load(String internalName) throws LoaderException {
	        InputStream is = null;
//			try {
//				is = new FileInputStream(internalName);
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			is = this.getClass().getResourceAsStream("/" + internalName + ".class");

	        if (is == null) {
	            return null;
	        } else {
	            try (InputStream in=is; ByteArrayOutputStream out=new ByteArrayOutputStream()) {
	                byte[] buffer = new byte[1024];
	                int read = in.read(buffer);

	                while (read > 0) {
	                    out.write(buffer, 0, read);
	                    read = in.read(buffer);
	                }

	                return out.toByteArray();
	            } catch (IOException e) {
	                throw new LoaderException(e);
	            }
	        }
	    }

	    @Override
	    public boolean canLoad(String internalName) {
	        return this.getClass().getResource("/" + internalName + ".class") != null;
	    }
	};
	
	static Printer printer = new Printer() {
	    protected static final String TAB = "  ";
	    protected static final String NEWLINE = "\\\\n";

	    protected int indentationCount = 0;
	    protected StringBuilder sb = new StringBuilder();

	    @Override public String toString() { return sb.toString(); }

	    @Override public void start(int maxLineNumber, int majorVersion, int minorVersion) {}
	    @Override public void end() {}

	    @Override public void printText(String text) { sb.append(text); }
	    @Override public void printNumericConstant(String constant) { sb.append(constant); }
	    @Override public void printStringConstant(String constant, String ownerInternalName) { sb.append(constant); }
	    @Override public void printKeyword(String keyword) { sb.append(keyword); }
	    @Override public void printDeclaration(int type, String internalTypeName, String name, String descriptor) { sb.append(name); }
	    @Override public void printReference(int type, String internalTypeName, String name, String descriptor, String ownerInternalName) { sb.append(name); }

	    @Override public void indent() { this.indentationCount++; }
	    @Override public void unindent() { this.indentationCount--; }

	    @Override public void startLine(int lineNumber) { for (int i=0; i<indentationCount; i++) sb.append(TAB); }
	    @Override public void endLine() { sb.append(NEWLINE); }
	    @Override public void extraLine(int count) { while (count-- > 0) sb.append(NEWLINE); }

	    @Override public void startMarker(int type) {}
	    @Override public void endMarker(int type) {}
	};
	public static void main(String[] args) throws Exception {
		ClassFileToJavaSourceDecompiler decompiler = new ClassFileToJavaSourceDecompiler();
		String internalName =  "javassist/ByteArrayClassPath";
				// "com/glueframework/boilerplate/common/IJdtConvert";
				// "C:\\Users\\Administrator\\Desktop\\html2chm\\FileChangeMonitor.class";
		File file = new File(internalName);   
		System.out.println( file.exists() ); 
		decompiler.decompile(loader, printer, internalName);

		String source = printer.toString();
		System.out.println( source );  
		System.out.println( source.replace("\\\\n", "") );
		
		JdtConvertDefault convertDefault = new JdtConvertDefault();
		CompilationUnit createCompilationUnit = 
				convertDefault.createCompilationUnit( source.replace("\\\\n", "") );
		System.out.println(createCompilationUnit.getRoot().toString());
		
		System.out.println( source.replaceAll("\\s*|\t|\r|\n", "") );
		
		String tmp = "C:\\Users\\Administrator\\Desktop\\html2chm\\FileChangeMonitor.class";
		
		file = new File(tmp);
		FileUtils.writeStringToFile( file, source);
		source = FileUtils.readFileToString(file);
		System.out.println( source );
	}
}	
