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

import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

/**
 * 测试文件变更
 * <p>
 * Created by yihui on 2017/4/28.
 */
public class FileChangeMonitor {
	protected static ILogger logger = LogMSG.getLogger();
	protected Path path;
	
	public FileChangeMonitor() {
		super();
	}

	public FileChangeMonitor(String pathStr) {
		super();
		// path = Paths.get("c://tmo");
		path = Paths.get(pathStr);
	}

	public void fileUpWather() throws IOException {

		WatchService watcher = FileSystems.getDefault().newWatchService();
		path.register(watcher, ENTRY_MODIFY);
		new Thread(() -> {
			try {
				while (true) {
					WatchKey key = watcher.take();
					for (WatchEvent<?> event : key.pollEvents()) {
						if (event.kind() == OVERFLOW) {// 事件可能lost or discarded
							continue;
						}
					Kind<?> kind = event.kind();
					Path fileName = (Path) event.context();
					logger.info("文件更新: " + fileName);
					doHandle( kind,fileName );
				}
				if (!key.reset()) {
					break;
				}
		}
	} catch (Exception e) {
		logger.info(e);
	}
		}).start();

		try {
			logger.info("正在监听文件的变更");
			Thread.sleep(1000 * 60 * 10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/** 子类不满足，重写该方法。
	 * @param kind
	 * @param fileName
	 */
	protected void doHandle(Kind<?> kind,Path fileName){
		
	}
}
