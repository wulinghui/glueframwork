package com.glueframework.boilerplate.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.jd.core.v1.ClassFileToJavaSourceDecompiler;
import org.jd.core.v1.api.loader.Loader;
import org.jd.core.v1.api.loader.LoaderException;
import org.jd.core.v1.api.printer.Printer;

/**
 * @author Administrator
 * class文件反编译成java文件。
 */
public class JdCoreUtil {

	static Loader loader = new Loader() {
	    @Override
	    public byte[] load(String internalName) throws LoaderException {
	        InputStream is = null;
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
	
	public static String decompile(String resourceAsStreamName) throws Exception {
		return decompile(resourceAsStreamName , "", "    ");
	}

	public static String decompile(String resourceAsStreamName,String newLine , String tab) throws Exception {
		ClassFileToJavaSourceDecompiler decompiler = new ClassFileToJavaSourceDecompiler();
		PrinterImp printer = new PrinterImp(tab,newLine);
		decompiler.decompile(loader, printer , resourceAsStreamName);
		return printer.toString();
	}
	
	static class  PrinterImp implements Printer{

	    protected final String TAB ;
	    protected final String NEWLINE;
	    
		public PrinterImp(String tAB, String nEWLINE) {
			super();
			TAB = tAB;
			NEWLINE = nEWLINE;
		}
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
	
	}
}
