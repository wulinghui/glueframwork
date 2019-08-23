package com.glueframework.boilerplate.javassist;

import javassist.ClassClassPath;
import javassist.ClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.URLClassPath;

import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.Test;

import com.glueframework.common.util.DebugClass;
import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

public class JavassistDemo {
	
	protected static ILogger logger = LogMSG.getLogger();
	
	ClassPool cp = ClassPool.getDefault();   // new ClassPool(true);
	CtClass cc ;
	public JavassistDemo(){
		try {
			cc = cp.get("com.glueframework.boilerplate.javassist.Hello");
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
	}
	@Test 
    public  void hello() throws Exception {
//        DebugClass.printAllMethod(cc);      
		cc.getMethods();
        CtMethod m = cc.getDeclaredMethod("say");
         CtField field = cc.getField("");
         
//         field.equals(obj);
         
        DebugClass.printAllMethod(m);  
        String src = "{ System.out.println(\"dsdasda.say():\"); }";
        String file = this.getClass().getResource("").getFile();
//        new Hello().say(); 
        m.setBody(src);  
//        new Hello().say(); 
//		m.insertBefore(src);
        Class c = cc.toClass();
        Hello h = (Hello)c.newInstance();
        h.say();
        new Hello().say();
    }
	@Test
    public  void writeFile() throws Exception {
		hello();
		// 写到文件中
//		cc.writeFile();   // 这个落到项目根目录上面了。而不是bin里面所以我们需要下面的操作。
//		cc.writeFile("C:\\new\\tmp.txt");
		String file = this.getClass().getResource("/").getFile();
		logger.info(file); 
		// writeFile(), toClass(), toBytecode() 被转换成一个类文件，
		// 此 CtClass 对象会被冻结起来，不允许再修改。
		cc.writeFile(file);
		// 解冻
		cc.defrost();
	}
	@Test
    public  void writeFile2() throws Exception {
		Object invokeConstructor = 
				ConstructorUtils.invokeConstructor( Class.forName("com.glueframework.boilerplate.javassist.Hello"));
		MethodUtils.invokeMethod(invokeConstructor, "say");
	}
	
	@Test // 类搜索路径
    public  void insertClassPath() throws Exception {
		cp.insertClassPath(new ClassClassPath(this.getClass()));
		cp.insertClassPath("/usr/local/javalib");
		/*
		 * 上述代码将 http://www.javassist.org:80/java/ 
		 * 添加到类搜索路径。并且这个URL只能搜索 org.javassist 
		 * 包里面的类。例如，为了加载 org.javassist.test.Main，
		 * 它的类文件会从获取 http://www.javassist.org:80/java/org/javassist/test/Main.class 获取。
		 */
		ClassPath urlClassPath = new URLClassPath("www.javassist.org", 80, "/java/", "org.javassist.");
		cp.insertClassPath( urlClassPath);
		//
//		byte[] b = a byte array;
//		String name = class name;
//		new ByteArrayClassPath(name, classfile);
	}
	@Test // 方法可以定义一个新类。
    public  void makeClass() throws Exception {
		/*
		 * 这段代码定义了一个空的 Point 类。Point 类的成员方法可以通过 CtNewMethod 类的工厂方法来创建，然后使用 CtClass 的 addMethod() 方法将其添加到 Point 中。

使用 ClassPool 中的 makeInterface() 方法可以创建新接口。接口中的方法可以使用 CtNewMethod 的 abstractMethod() 方法创建。
		 */
		CtClass cc = cp.makeClass("JavassistDemo");
//		cc.addMethod(m);
		logger.info(cc.toClass().toString() );   
	}
	@Test // 
    public  void getClassPool() throws Exception {
		cp = ClassPool.getDefault();   // 单例
		cp = new ClassPool(true);   // 多例  , 避免内存溢出
		
		/*
		 * 如果程序正在 Web 应用程序服务器上运行，则可能需要创建多个 ClassPool 实例; 应为每一个 ClassLoader 创建一个 ClassPool 的实例。 程序应该通过 ClassPool 的构造函数，而不是调用 getDefault() 来创建一个 ClassPool 对象。
多个 ClassPool 对象可以像 java.lang.ClassLoader 一样级联
		 */
		ClassPool parent = ClassPool.getDefault();
		ClassPool child = new ClassPool(parent);
//		child.appendSystemPath();         // the same class path as the default one.
		/*
		 * 如果调用 child.get()，子 ClassPool 首先委托给父 ClassPool。如果父 ClassPool 找不到类文件，那么子 ClassPool 会尝试在 ./classes 目录下查找类文件。
如果 child.childFirstLookup 返回 true，那么子类 ClassPool 会在委托给父 ClassPool 之前尝试查找类文件。
		 */
		child.childFirstLookup = true;    // changes the behavior of the child.
	}
	@Test // 方法可以定义一个新类。
    public  void copy() throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.get("Point");
		CtClass cc1 = pool.get("Point");   // cc1 is identical to cc.
		cc.setName("Pair");
		CtClass cc2 = pool.get("Pair");    // cc2 is identical to cc.
		CtClass cc3 = pool.get("Point");   // cc3 is not identical to cc.
	}
	@Test // 修改系统类
    public  void hiddenValue() throws Exception {
		ClassPool pool = ClassPool.getDefault();
		CtClass cc = pool.get("java.lang.String");
		CtField f = new CtField(CtClass.intType, "hiddenValue", cc);
		f.setModifiers(Modifier.PUBLIC);
		cc.addField(f);
		cc.writeFile(".");
		Class<?> class1 = cc.toClass();
		System.out.println(class1.getField("hiddenValue").getName());
	}
	
	
}
	