package com.glueframework.boilerplate;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.junit.Test;

/**
 * 测试文件变更
 * <p>
 * Created by yihui on 2017/4/28.
 */
public class FileUpTest {
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
