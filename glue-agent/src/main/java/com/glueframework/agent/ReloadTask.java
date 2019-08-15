package com.glueframework.agent;
import java.io.InputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.util.TimerTask;
import java.util.logging.Logger;
 
public  class  ReloadTask  extends  TimerTask {
	private static  Logger logger = Logger.getLogger(ReloadTask.class.getName());
	
    private  Instrumentation  inst;
 
    protected  ReloadTask(Instrumentation  inst){
        this.inst=inst;
    }
    
 
    @Override
    public  void  run() {
       try{
           ClassDefinition[]  cd=new ClassDefinition[1];
           Class[]  classes=inst.getAllLoadedClasses();
           for(Class  cls:classes){
                ClassLoader classLoader = cls.getClassLoader();
				if(classLoader==null||!classLoader.getClass().getName().equals("sun.misc.Launcher$AppClassLoader"))
                    continue;
				String path = classLoader.getResource("").getPath();
				System.out.println( path ); 
                String  name=cls.getName().replaceAll("\\.","/");
                cd[0]=new ClassDefinition(cls,loadClassBytes(cls,name+".class"));
                inst.redefineClasses(cd);    
           }
       }catch(Exception ex){
            ex.printStackTrace();
       }
    }
//    public static void main(String[] args) {
//		LogUtil.debug("zzzzzzz");
//	}

    private  byte[]  loadClassBytes(Class  cls,String  clsname) throws  Exception{
        logger.finest(clsname+":"+cls);
        InputStream  is=cls.getClassLoader().getSystemClassLoader().getResourceAsStream(clsname);
        if(is==null)return  null;
        byte[]  bt=new  byte[is.available()];
        is.read(bt);
        is.close();
        return  bt;
    }
}