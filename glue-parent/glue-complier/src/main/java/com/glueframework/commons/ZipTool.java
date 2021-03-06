package com.glueframework.commons;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

public class ZipTool {
	protected static ILogger log = LogMSG.getLogger();
	public static int BUFFER_SIZE = 2048;
    public static void compressFile(File targetFile, File sourceFile) {
      ZipOutputStream zipOutput = null;
         try {
          zipOutput = new ZipOutputStream(new FileOutputStream(targetFile));
            compress(zipOutput, sourceFile, sourceFile.getName());
        } catch (Exception e) {
          e.printStackTrace();
         } finally {
            if (zipOutput != null) {
                try {
                    zipOutput.closeEntry();
                    zipOutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
           }
       }
   }

    private static void compress(ZipOutputStream zipOutput, File sourceFile, String base) throws IOException {
        if (sourceFile.isDirectory()) {
           File[] files = sourceFile.listFiles();
             if (files.length == 0) {
                 System.out.println(base + "/");
                 zipOutput.putNextEntry(new ZipArchiveEntry(base + "/"));
            } else {
                for (File file : files) {
                    compress(zipOutput, file, base + "/" + file.getName());
               }
           }
        } else {
            zipOutput.putNextEntry(new ZipArchiveEntry(base));
             FileInputStream fis = new FileInputStream(sourceFile);
            BufferedInputStream bis = new BufferedInputStream(fis);

            int tag;
             System.out.println(base);
             while ((tag = bis.read()) != -1) {
               zipOutput.write(tag);
           }
            fis.close();
            bis.close();
        }
        }
    public static List<String> unZip(File zipfile, String destDir)
            throws Exception {
        if (StringUtils.isBlank(destDir)) {
            destDir = zipfile.getParent();
        }
        destDir = destDir.endsWith(File.separator) ? destDir : destDir
                + File.separator;
        ZipArchiveInputStream is = null;
        List<String> fileNames = new ArrayList<String>();

        try {
            is = new ZipArchiveInputStream(new BufferedInputStream(
                    new FileInputStream(zipfile), BUFFER_SIZE));
            ZipArchiveEntry entry = null;
            while ((entry = is.getNextZipEntry()) != null) {
            	System.out.println(entry.getName());
                fileNames.add(entry.getName());
                if (entry.isDirectory()) {
                    File directory = new File(destDir, entry.getName());
                    directory.mkdirs();
                } else {
                    OutputStream os = null;
                    try {
                        os = new BufferedOutputStream(new FileOutputStream(
                                new File(destDir, entry.getName())),
                                BUFFER_SIZE);
                        IOUtils.copy(is, os);
                    } finally {
                        IOUtils.closeQuietly(os);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            IOUtils.closeQuietly(is);
        }

        return fileNames;
    }
    public static void decompress(ZipInputStream srcFile,File desFile,String name) {
    	try {
			ZipEntry entry=srcFile.getNextEntry();
			String entryName=entry.getName();
			if(entryName.substring(entryName.length()-1, entryName.length()).equals("/")){
				File file =new File(name);
				if(!file.exists()) {
					file.mkdir();
				}
			}
			else {
				File file =new File(name);
				if(!file.exists()) {
					file.createNewFile();
				}	
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	 public static boolean decompressZip2Files(String zipFilePath, String targetDirPath) {

	        InputStream inputStream = null;
	        OutputStream outputStream = null;
	        //zip文件输入流
	        ZipArchiveInputStream zipArchiveInputStream = null;
	        ArchiveEntry archiveEntry = null;
	        try {
	            File zipFile = new File(zipFilePath);
	            inputStream = new FileInputStream(zipFile);
	            zipArchiveInputStream = new ZipArchiveInputStream(inputStream, "UTF-8");

	            while (null != (archiveEntry = zipArchiveInputStream.getNextEntry())) {
	                //获取文件名
	                String archiveEntryFileName = archiveEntry.getName();
	             
	                //构造解压后文件的存放路径
	                String archiveEntryPath = targetDirPath + archiveEntryFileName.substring(0, archiveEntryFileName.length()-1);
	                //把解压出来的文件写到指定路径
	                System.out.println(archiveEntryPath);
	                File entryFile = new File(archiveEntryPath);
	                if(!entryFile.isDirectory()) {
	                	 if (!entryFile.exists()) {
	 	                    boolean mkdirs = entryFile.createNewFile();
	                             System.out.println(entryFile.getName()+"创建文件"+mkdirs);
	 	                }
	 	                byte[] buffer = new byte[1024 * 5];
	 	                outputStream = new FileOutputStream(entryFile);
	 	                int len = -1;
	 	                while ((len = zipArchiveInputStream.read(buffer)) != -1) {
	 	                    outputStream.write(buffer, 0, len);
	 	                }
	 	               outputStream.flush();
	                }
	                else {
	                	entryFile.mkdir();
					}
	               
	               
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        } finally {
	            try {
	                if (null != outputStream) {
	                    outputStream.close();
	                }
	                if (null != zipArchiveInputStream) {
	                    zipArchiveInputStream.close();
	                }
	                if (null != inputStream) {
	                    inputStream.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return true;
	    }

	    /**
	     * 
	     * 
	     * 
	     *  创建临时文件
	     */
	    public static File createTempFile(String fullFileName) throws IOException {
	        String tempDirectoryPath = FileUtils.getTempDirectoryPath();
	        File file = new File(tempDirectoryPath + File.separator + fullFileName);
	        file.deleteOnExit();
	        boolean newFile = file.createNewFile();
	        log.debug("newFile {} => {}", fullFileName, newFile);
	        return file;
	        
	        
	        
	    }
	public static void main(String[] args) throws Exception {
		 File f1 = new File("C:\\Users\\Administrator\\Desktop\\新建文件夹");
	        File f2 = new File("C:\\Users\\Administrator\\Desktop\\新建文本文档.txt");
	        File f3 = new File("/Users/my/Desktop/aaaaa.docx");
	        File des = new File("C:\\Users\\Administrator\\Desktop\\ah.zip");
         List<File> files=new ArrayList<File>();
         files.add(f1);
       
	        // 压缩文件 
	     
	     //  decompressZip2Files("C:\\Users\\Administrator\\Desktop\\ha.zip","C:\\Users\\Administrator\\Desktop\\fa\\");
	      //  log.info("compressFiles2Zip={}", b);
	    //    System.out.println(Zip64Mode.AsNeeded);
	}
}
