package com.glueframework.commons;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;



public class Test {
	   
	    /**   
	     * 对目录srcFile下的所有文件目录进行先压缩后加密,然后保存为destfile   
	     *    
	     * @param srcFile   
	     *            要操作的文件或文件夹   
	     * @param destfile   
	     *            压缩加密后存放的文件   
	     * @param keyfile   
	     *            密钥   
	     */    
	    public  void encryptZip(String srcFile, String destfile, String keyStr) throws Exception {     
	        File temp = new File(UUID.randomUUID().toString() + ".zip");     
	        temp.deleteOnExit();     
	        // 先压缩文件     
	        ZipTool.compressFile(new File("temp"), new File(srcFile));    
	        // 对文件加密      
	        new AESTool().encrypt(temp.getAbsolutePath(), destfile, keyStr);     
	        temp.delete();     
	    }     
	    
	    /**   
	     * 对文件srcfile进行先解密后解压缩,然后解压缩到目录destfile下   
	     *    
	     * @param srcfile   
	     *            要解密和解压缩的文件名    
	     * @param destfile   
	     *            解压缩后的目录   
	     * @param publicKey   
	     *            密钥   
	     */    
	    public void decryptUnzip(String srcfile, String destfile, String keyStr) throws Exception {     
	        File temp = new File(UUID.randomUUID().toString() + ".zip");     
	        temp.deleteOnExit();     
	        // 先对文件解密      
	        new AESTool().decrypt(srcfile, temp.getAbsolutePath(), keyStr);     
	        // 解压缩      
	       ZipTool.decompressZip2Files(temp.getAbsolutePath(), destfile);
	        temp.delete();     
	    }     
	         
	   public static void main(String[] args) throws Exception {
		        File f1 = new File("C:\\Users\\Administrator\\Desktop\\encode.zip");
		        File f2 = new File("C:\\Users\\Administrator\\Desktop\\log");
		        System.out.println(File.separator);
		       // f1.mkdir();
		       ZipTool.unZip(f1, "C:\\Users\\Administrator\\Desktop");
		       //System.out.println(f1.isDirectory());
		      //  f2.getParentFile().mkdirs();
		       // ZipTool.compressFile(f1, f2);
		     //  ZipTool.decompressZip2Files("C:\\Users\\Administrator\\Desktop\\encode.zip", "C:/Users/Administrator/Desktop/");
		        
	} 
	   
}
