package com.glueframework.boilerplate;
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

import org.apache.commons.io.monitor.FileAlterationObserver;
import org.junit.After;
import org.junit.Test;

import com.glueframework.boilerplate.common.IJdtConvert;
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
