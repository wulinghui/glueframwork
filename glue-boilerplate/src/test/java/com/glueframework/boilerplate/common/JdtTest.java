package com.glueframework.boilerplate.common;

import org.eclipse.core.internal.resources.Workspace;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.osgi.framework.util.SecureAction;
import org.eclipse.text.edits.TextEdit;
import org.junit.Test;

import com.glueframework.common.util.DebugClass;

import java.security.PrivilegedAction;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class JdtTest {
    String src = "package com.glueframework.boilerplate.common;class A { void method1(int b){;} }";
    Workspace workspace;
    public JdtTest(){}
//    public JdtTest(String src, Workspace workspace) {
//		super();
//		this.src = src;
//		this.workspace = workspace;
//	}

    @Test
    public  void test() {
    	Properties properties = System.getProperties();
    	System.out.println( properties );
    }
    @Test
    public  void test0() {
        // https://www.cnblogs.com/SEC-fsq/p/7845222.html
        ASTParser parser = ASTParser.newParser(AST.JLS8); //设置Java语言规范版本
        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        parser.setCompilerOptions(null);
        parser.setResolveBindings(true);     

        Map<String, String> compilerOptions = JavaCore.getOptions();
        compilerOptions.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_8); //设置Java语言版本
        compilerOptions.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_8);
        compilerOptions.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);
        parser.setCompilerOptions(compilerOptions); //设置编译选项

//        try {
//            src = FileUtils.readFileToString(new File("JdtTest.java"),"UTF-8");  //要解析的文件
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        parser.setSource(src.toCharArray());
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);  //下个断点可以看看cu的types成员就是整个语法树
        System.out.println(cu);
        DebugClass.printAllMethod(cu);
        DebugClass.printAllMethod(cu.getPackage());
        TypeDeclaration obj = (TypeDeclaration) cu.types().get(0);
		DebugClass.printAllMethod(obj);
    }
	
	

    /**
     sorra  780 发布于 编程妙法  的 系列。  https://segmentfault.com/u/sorra/articles?page=2
     Java代码分析器(一): JDT入门
     https://segmentfault.com/a/1190000000609246
     *
     */
    @Test
    public void test1() {
        ASTParser parser = ASTParser.newParser(AST.JLS4); //设置Java语言规范版本
        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        Map<String, String> compilerOptions = JavaCore.getOptions();
        compilerOptions.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_7); //设置Java语言版本
        compilerOptions.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_7);
        compilerOptions.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_7);
        parser.setCompilerOptions(compilerOptions); //设置编译选项

        char[] src = this.src.toCharArray();
        parser.setSource(src);

        CompilationUnit cu = (CompilationUnit) parser.createAST(null); //这个参数是IProgessMonitor,用于GUI的进度显示,我们不需要，填个null. 返回值是AST的根结点



        System.out.println(cu); //把AST直接输出看看啥样
    }
    /**
     Java代码分析器(二): 使用DOM API操作抽象语法树
     https://segmentfault.com/a/1190000000638838
     */
    @Test
    public void test2() throws Exception {
    	PrivilegedAction<SecureAction> createSecureAction = org.eclipse.osgi.framework.util.SecureAction.createSecureAction();
    	SecureAction run = createSecureAction.run();
  
        Workspace workspace = new Workspace();      
        IWorkspaceRoot root = workspace.getRoot();
        IProject project = root.getProject("");
        //
        IClasspathEntry IClasspathEntry = null;
  
        // IJavaModel
        IJavaModel iJavaModel = JavaCore.create(workspace.getRoot());

        //这里一定要导一手。
        IJavaProject iJavaProject = JavaCore.create(project);
        iJavaProject = iJavaModel.getJavaProject();

        Path iPath = new Path("C:\\Users\\Administrator\\workspace\\jdt\\src\\test\\java");

        IPackageFragmentRoot packageFragmentRoot = iJavaProject.findPackageFragmentRoot(iPath);
        // 这里获取包的2种方式
        IPackageFragment packageFragment = packageFragmentRoot.getPackageFragment("");
        packageFragment = iJavaProject.findPackageFragment(iPath);
        // 获取编译体的2种方式
        ICompilationUnit[] compilationUnits = packageFragment.getCompilationUnits();
        ICompilationUnit helloWord = packageFragment.getCompilationUnit("HelloWord");
        helloWord = compilationUnits[0];
    }

    private void createJavaElementsFrom(IProject myProject, IFolder myFolder, IFile myFile) throws Exception {
        IJavaProject myJavaProject= JavaCore.create(myProject);
        if (myJavaProject == null)
        //项目未为Java配置（没有Java性质）
        // the project is not configured for Java (has no Java nature)
            return;

        // get a package fragment or package fragment root
        //获取包片段或包片段根
        IJavaElement myPackageFragment= JavaCore.create(myFolder);

        // get a .java (compilation unit), .class (class file), or
        // .jar (package fragment root)
        //获取.java（编译单元）、类（类文件）或
//.jar（包碎片根）
        IJavaElement myJavaFile = JavaCore.create(myFile);

        // get the non Java resources contained in my project.
        // 获取项目中包含的非Java资源。
        Object[] nonJavaChildren = myJavaProject.getNonJavaResources();


        //
        // Get original compilation unit
//获取原始编译单元
        ICompilationUnit originalUnit = null;

        // Get working copy owner //获取工作副本所有者

        WorkingCopyOwner owner = null;

        // Create working copy  //创建工作副本

        ICompilationUnit workingCopy = originalUnit.getWorkingCopy(owner, null);
        //////////////////////////////////////
        // 1.
        //////////////////////////////////
        // Modify buffer and reconcile  //修改缓冲区和协调

        IBuffer buffer = ((IOpenable)workingCopy).getBuffer();
        buffer.append("class X {}");
        workingCopy.reconcile(ICompilationUnit.NO_AST, false, null, null);

        /* 第2种方式修改。
        // Get text edits  //获取文本编辑
        TextEdit edit = null;
        // Modify buffer and reconcile  //修改缓冲区和协调
        workingCopy.applyTextEdit(edit, null);
        workingCopy.reconcile(ICompilationUnit.NO_AST, false, null, null);
        */

        //////////////////////////////////////////////////
        ////////////////////////////////////////

        // Commit changes  //提交更改
        // TODO  将覆盖之前代码
        workingCopy.commitWorkingCopy(false, null);
        // Destroy working copy  //销毁工作副本
        workingCopy.discardWorkingCopy();

    }

    @Test
    public void createHellword(){
        /** 生产以下代码。
         package example;
         import java.util.*;
         public class HelloWorld {
             public static void main(String[] args) {
                System.out.println("Hello" + " world");
             }
         }
         */
        AST ast = AST.newAST(AST.JLS3);
        CompilationUnit unit = ast.newCompilationUnit();
        PackageDeclaration packageDeclaration = ast.newPackageDeclaration();
        packageDeclaration.setName(ast.newSimpleName("example"));
        unit.setPackage(packageDeclaration);
        ImportDeclaration importDeclaration = ast.newImportDeclaration();
        QualifiedName name =
                ast.newQualifiedName(
                        ast.newSimpleName("java"),
                        ast.newSimpleName("util"));
        importDeclaration.setName(name);
        importDeclaration.setOnDemand(true);
        unit.imports().add(importDeclaration);
        TypeDeclaration type = ast.newTypeDeclaration();
        type.setInterface(false);
        type.modifiers().add(ast.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD));
        type.setName(ast.newSimpleName("HelloWorld"));
        MethodDeclaration methodDeclaration = ast.newMethodDeclaration();
        methodDeclaration.setConstructor(false);
        List modifiers = methodDeclaration.modifiers();
        modifiers.add(ast.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD));
        modifiers.add(ast.newModifier(Modifier.ModifierKeyword.STATIC_KEYWORD));
        methodDeclaration.setName(ast.newSimpleName("main"));
        methodDeclaration.setReturnType2(ast.newPrimitiveType(PrimitiveType.VOID));
        SingleVariableDeclaration variableDeclaration = ast.newSingleVariableDeclaration();
        variableDeclaration.setType(ast.newArrayType(ast.newSimpleType(ast.newSimpleName("String"))));
        variableDeclaration.setName(ast.newSimpleName("args"));
        methodDeclaration.parameters().add(variableDeclaration);
        org.eclipse.jdt.core.dom.Block block = ast.newBlock();
        MethodInvocation methodInvocation = ast.newMethodInvocation();
        name =
                ast.newQualifiedName(
                        ast.newSimpleName("System"),
                        ast.newSimpleName("out"));
        methodInvocation.setExpression(name);
        methodInvocation.setName(ast.newSimpleName("println"));
        InfixExpression infixExpression = ast.newInfixExpression();
        infixExpression.setOperator(InfixExpression.Operator.PLUS);
        StringLiteral literal = ast.newStringLiteral();
        literal.setLiteralValue("Hello");
        infixExpression.setLeftOperand(literal);
        literal = ast.newStringLiteral();
        literal.setLiteralValue(" world");
        infixExpression.setRightOperand(literal);
        methodInvocation.arguments().add(infixExpression);
        ExpressionStatement expressionStatement = ast.newExpressionStatement(methodInvocation);
        block.statements().add(expressionStatement);
        methodDeclaration.setBody(block);
        type.bodyDeclarations().add(methodDeclaration);
        unit.types().add(type);

    }

    @Test
    /**
    将 ` public class X {\n} `  改为  public class Y {\n}
     */
    public void rewriteTest() throws JavaModelException, BadLocationException {
        // creation of a Document //创建文档
        ICompilationUnit cu = null ; // content is "public class X {\n}"  //内容为“public class X {\n}”

//        String source = cu.getSource();
        String source = src;
        Document document= new Document(source);
        // creation of DOM/AST from a ICompilationUnit
//从ICompilationUnit创建dom/ast

        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setSource(cu);
        CompilationUnit astRoot = (CompilationUnit) parser.createAST(null);
        /////////////////////////
        // 1.
        ////
        // creation of ASTRewrite //创建astrewrite
        ASTRewrite rewrite = ASTRewrite.create(astRoot.getAST());

        // description of the change  //变更说明
        SimpleName oldName = ((TypeDeclaration)astRoot.types().get(0)).getName();
        SimpleName newName = astRoot.getAST().newSimpleName("Y");
        rewrite.replace(oldName, newName, null);

        // computation of the text edits  //文本编辑的计算
        TextEdit edits = rewrite.rewriteAST(document, cu.getJavaProject().getOptions(true));

        //////////////////////////////////////////
        // 2.
        /////////////////////////////////////////
        // start record of the modifications//修改的开始记录
/*
        astRoot.recordModifications();
        // modify the AST  //修改ast
        TypeDeclaration typeDeclaration = (TypeDeclaration)astRoot.types().get(0);
        SimpleName newName = astRoot.getAST().newSimpleName("Y");
        typeDeclaration.setName(newName);
        // computation of the text edits //文本编辑的计算
        TextEdit edits = astRoot.rewrite(document, cu.getJavaProject().getOptions(true));
*/

        /////////////////////////////////////////
        /////////////////////////////////////////
        // computation of the new source code //计算新的源代码
        edits.apply(document);
        String newSource = document.get();

        // update of the compilation unit   //编译单元更新
        cu.getBuffer().setContents(newSource);

    }

    @Test
    public void createCompilationUnit() throws JavaModelException {
        // Get package  //获取软件包
        IPackageFragment pkg = null;
        // Create 2 compilation units  //创建2个编译单元
        ICompilationUnit unitA = pkg.createCompilationUnit ("A.java", "public class A {}", false, null);
        ICompilationUnit unitB = pkg.createCompilationUnit("B.java", "public class B {}", false, null);
    }


}
