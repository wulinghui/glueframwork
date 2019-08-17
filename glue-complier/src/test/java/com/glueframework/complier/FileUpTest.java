package com.glueframework.complier;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.eclipse.core.resources.IFile;
import org.eclipse.jdt.core.IClassFile;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.junit.After;
import org.junit.Test;

import com.glueframework.boilerplate.common.IJdtConvert;
import com.glueframework.boilerplate.common.JdtConvertDefault;
import com.glueframework.common.util.DebugClass;
import com.glueframework.complier.CompilerChangeMonitor;
import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

/**
 * 测试文件变更
 * <p>
 * Created by yihui on 2017/4/28.
 */
public class FileUpTest {
	protected static ILogger logger = LogMSG.getLogger();
		@After
	    public void onStop() throws InterruptedException {
			Thread.sleep(TimeUnit.HOURS.toMillis(1));
		}
	  
	@Test
	public void main11()  {
		logger.info("============ c://tmo");
		String property = System.getProperty("user.dir");
		String path = this.getClass().getClassLoader().getResource("").getPath();
		String pathStr = "Y://Virtual-Machines-Files-Writer//workpace//glueframwork//glue-complier//src//main//java";
		File file = new File(pathStr);
		logger.info(file.exists() + "======file======"+path);
		CompilerChangeMonitor changeMonitor = new CompilerChangeMonitor( new File(pathStr) );
		Path path2 = file.toPath();
		logger.info("======toPath======"+path2);
		logger.info("========path===="+path);
		logger.info("========path2===="+path2.toAbsolutePath());
		logger.info("======property======"+property);
		try {
			changeMonitor.fileUpWather();
			logger.info("============");      
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		JdtConvertDefault jdtConvertDefault = new JdtConvertDefault();

		 String src =  FileUtils.readFileToString( new File("Z:\\Virtual-Machines-Files-Writer\\workpace\\glueframwork\\glue-boilerplate\\src\\main\\java\\com\\glueframework\\boilerplate\\common\\JdtConvertDefault.java") ,"UTF-8"); 
		 //"class A { void method1(int b){;} }";
		CompilationUnit createCompilationUnit = jdtConvertDefault.createCompilationUnit(src);
		 DebugClass.printAllGetMethod(createCompilationUnit);
	 
		 
		// jdt无法这样直接解析class文件。需要先反编译一下。
		ASTParser parser = jdtConvertDefault.createAstParser();
		Class<?> forName = Class.forName("com.glueframework.boilerplate.common.JdtChangeMonitor");
		// org.eclipse.jdt.internal.compiler.env.IModule
		IFile file = null; 
		IClassFile source =  JavaCore.createClassFileFrom( file );
		parser.setSource(source);
		logger.info("=====1111111111=======");
		
		String pathStr = "Y://Virtual-Machines-Files-Writer//workpace//glueframwork//glue-complier//src//main//java";
		CompilerChangeMonitor changeMonitor = new CompilerChangeMonitor( new File(pathStr) );
		try {
			changeMonitor.fileUpWather();
			logger.info("============");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 @Test
	    public void testFileUpWather() throws IOException {
	        Path path = Paths.get("c://tmo");
	        WatchService watcher = FileSystems.getDefault().newWatchService();
	        path.register(watcher, ENTRY_MODIFY);

	        new Thread(() -> {
	            try {
	                while (true) {
	                    WatchKey key = watcher.take();
	                    for (WatchEvent<?> event : key.pollEvents()) {
	                        if (event.kind() == OVERFLOW) {//事件可能lost or discarded
	                            continue;
	                        }
	                        
	                        Kind<?> kind = event.kind();
	                        Path fileName = (Path) event.context();
	                        
	                        System.out.println("文件更新: " + fileName);
	                    }
	                    if (!key.reset()) {
	                        break;
	                    } 
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }).start();

	        try { 
	        	 System.out.println("正在监听文件的变更");
	            Thread.sleep(1000 * 60 * 10);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
}
