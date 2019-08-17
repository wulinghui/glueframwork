package com.glueframework.agent;

import java.io.File;
import java.io.InputStream;
import java.lang.instrument.ClassDefinition;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

/**
 * 文件变更
 * <p>
 * Created by yihui on 2017/4/28.
 */
public class FileChangeMonitor extends FileAlterationListenerAdaptor{
	String directory;
	private static  Logger logger = Logger.getLogger(FileChangeMonitor.class.getName());
	/**
	 * @param directory
	 */
	public FileChangeMonitor(File directory) {
		this.directory = directory.getAbsolutePath();
	}

	@Override
	public void onFileCreate(File file) {
		doHandleFile(file);
	}

	@Override
	public void onFileChange(File file) {
		doHandleFile(file);
	}
	
	@Override
	public void onFileDelete(File file) {
		doHandleFile(file);
	}
	
	private void doHandleFile(File file) {
		String absolutePath = file.getAbsolutePath();
		String substring = absolutePath.substring(directory.length()+1 , absolutePath.length() - 6 );
		substring = substring.replace(File.separator, ".");
		Class  cls =  null; //  HotAgent.CLS_NAME_MAP.get(substring); 
		Class[]  classes=HotAgent.INST.getAllLoadedClasses();
		for (Class class1 : classes) {
			logger.finest( "INST.getAllLoadedClasses()=="+class1);      
			if( class1 != null && class1.getName().equals(substring)){
				cls = class1;
				break;
			}
		}  
		logger.finer( String.format("file = [%s] key = [%s]  class = [%s]  ,  HotAgent.flag = [%s]",absolutePath , substring , cls , HotAgent.flag )  );
		try {
			if( HotAgent.flag ){
				// 还有一个类似的方法 redefineClasses ，注意，这个方法是在类加载前使用的。类加载后需要使用 retransformClasses 方法。
				// 这个不好用，没有用。不重新加载。
//				HotAgent.INST.retransformClasses(Class.forName(substring));
				loadClass(cls);    
			}else{
				loadClass(cls);  
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// finest 
		logger.setLevel(Level.ALL);
		logger.finest( "INST.getAllLoadedClasses()==");   
		
		logger.finest( File.class.getName() );
		logger.finest("2222222222"); 
		String str = "/Z:/Virtual-Machines-Files-Writer/workpace/glueframwork1/glue-agent/target/classes/";
		String str2 = str + "com/tets/";  
		String substring = str2.substring(str.length());
		logger.finest(substring);
		logger.finest(File.pathSeparator);
		logger.finest(File.separator);
		logger.finest( substring.replace(File.separator, ".") );
		logger.finest( substring.replace("/", ".") );
	}   
	
	
	protected void loadClass(Class cls) throws Exception  {
		if( cls ==null) return;
			ClassDefinition[]  cd=new ClassDefinition[1];
			String  name=cls.getName().replaceAll("\\.","/");
			cd[0]=new ClassDefinition(cls,loadClassBytes(cls,name+".class"));
			HotAgent.INST.redefineClasses(cd);    
	}
    private  byte[]  loadClassBytes(Class  cls,String  clsname) throws  Exception{
//      logger.finest(clsname+":"+cls);
      InputStream  is=cls.getClassLoader().getSystemClassLoader().getResourceAsStream(clsname);
      if(is==null)return  null;
      byte[]  bt=new  byte[is.available()];
      is.read(bt);
      is.close();
      return  bt;
  }
}
