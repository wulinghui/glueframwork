package com.glueframework.complier;

import javax.tools.*;

import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;
import com.glueframework.log.LogUtils;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

public class Eval {
	protected static ILogger logger = LogMSG.getLogger();

    private static WeakReference<Eval> mWeakReference = new WeakReference<>(new Eval());

    public static Object eval() {
        Eval eval = mWeakReference.get();

        String method = "main";

        String codes = "public static void main(String[]args){" +
                "System.out.print(\"hello world\"); }";

        eval.run(method,codes);
        return null;
    }    


    public static void main(String[] args) {
    	eval();   
    }

    private Object run(String method,String codes){

        String className = "com.aaa.Eval3";
        StringBuilder sb = new StringBuilder();
        sb.append("package com.aaa;");
//        sb.append("\n import com.compiler.lgx.A;");
        sb.append("\n public class Eval3{\n ");
        sb.append(codes);
        sb.append("\n}");
        logger.debug("className=[{}] \n src=[{}]" , className , sb );
        Class<?> clazz = compile(className, sb.toString());
        
        try {
            // 生成对象
            Object obj = clazz.newInstance();
            Class<? extends Object> cls = obj.getClass();
            // 调用sayHello方法
            Method m = clazz.getMethod(method,String[].class);
            Object invoke = m.invoke(obj, new Object[] { new String[] {} });
            return invoke;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 装载字符串成为java可执行文件
     * @param className className
     * @param javaCodes javaCodes
     * @return Class
     */
    private  Class<?> compile(String className, String javaCodes) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        StrSrcJavaObject srcObject = new StrSrcJavaObject(className, javaCodes);
        Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(srcObject);
        String flag = "-d";
        String outDir = "";
        try {
            File classPath = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
            outDir = classPath.getAbsolutePath() + File.separator;
            logger.debug("classPath={}",outDir);
        } catch (URISyntaxException e1) {
        	logger.warn(e1);
        }
        Iterable<String> options = Arrays.asList(flag, outDir);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, options, null, fileObjects);
        boolean result = task.call();
        if (result == true) {
            try {
                return Class.forName(className);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private static class StrSrcJavaObject extends SimpleJavaFileObject {
        private String content;
        StrSrcJavaObject(String name, String content) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.content = content;
        }
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return content;
        }
    }




}
