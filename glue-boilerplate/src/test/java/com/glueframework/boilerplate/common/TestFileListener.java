package com.glueframework.boilerplate.common;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import java.io.File;

/**
 * 
 * public class TestFileEnvent {
 * 
 * Title:文件监听器 在Apache的Commons-IO中有关于文件的监控功能的代码. 文件监控的原理如下：
 * 由文件监控类FileAlterationMonitor中的线程不停的扫描文件观察器FileAlterationObserver，
 * 如果有文件的变化，则根据相关的文件比较器，判断文件时新增，还是删除，还是更改。（默认为1000毫秒执行一次扫描）
 * 
 * @Author xg.chen
 * @Date 2016/9/8.
 */
public class TestFileListener extends FileAlterationListenerAdaptor {
    /**
     * 文件创建执行
     */
    @Override
    public void onFileCreate(File file) {
        System.out.println("create file:" + file.getAbsolutePath());
    }
    
    /**
     * 文件创建修改
     */
    @Override
    public void onFileChange(File file) {
        System.out.println("modify file:" + file.getAbsolutePath());
    }

    /**
     * 文件删除
     */
    @Override
    public void onFileDelete(File file) {
        System.out.println("delete file:" + file.getAbsolutePath());
    }

    /**
     * 目录创建
     */
    @Override
    public void onDirectoryCreate(File directory) {
        System.out.println("create dir:" + directory.getAbsolutePath());
    }

    /**
     * 目录修改
     */
    @Override
    public void onDirectoryChange(File directory) {
        System.out.println("modify dir:" + directory.getAbsolutePath());
    }

    /**
     * 目录删除
     */
    @Override
    public void onDirectoryDelete(File directory) {
        System.out.println("delete dir:" + directory.getAbsolutePath());
    }

    @Override
    public void onStart(FileAlterationObserver observer) {
        super.onStart(observer);
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        super.onStop(observer);
    }
}
