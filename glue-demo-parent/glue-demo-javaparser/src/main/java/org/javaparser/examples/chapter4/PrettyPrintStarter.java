package org.javaparser.examples.chapter4;

import java.util.List;
import java.util.Optional;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.LineComment;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;

public class PrettyPrintStarter {

    public static void main(String[] args) {
        ClassOrInterfaceDeclaration myClass = new ClassOrInterfaceDeclaration();
        myClass.setComment(new LineComment("A very cool class!"));
        myClass.setName("MyClass");
        myClass.addField("String", "foo");
        myClass.addMethod("runTest");
         MethodDeclaration methodDeclaration = myClass.getMethods().get(0);
         methodDeclaration.setBlockComment(getInner(0));
         BlockStmt blockStmt = new BlockStmt();
         Optional<BlockStmt> body = methodDeclaration.getBody();
         blockStmt.setBlockComment(getInner(1));
         blockStmt.setLineComment(getInner(2));  
         
//         blockStmt.setStatement();
		methodDeclaration.setBody(blockStmt);
        System.out.println(myClass);
    }  
    
	protected static String getInner(int i) {
		return "String str"+i+" = null;";
	}

}
