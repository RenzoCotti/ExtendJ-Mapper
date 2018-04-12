package org.extendj.ast;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;

public class BodyRootNode {

	private static Map<String, ASTNode<ASTNode>> roots = new HashMap<String, ASTNode<ASTNode>>();

	public static void add(ConstructorDecl root){
		add(root, root.hostType().typeName() , root.name() , root.descName());
	}

	public static void add(MethodDecl root){
		add(root, root.hostType().typeName() , root.name() , root.descName());
	}

	public static void add(TypeDecl root){
		add(root, root.hostType().typeName() , root.name(), "");
	}

	private static void add(ASTNode<ASTNode> root, String hostName, String methodName, String methodDesc){
		String key = hostName + methodName + methodDesc;
		// System.out.println("Added key:\t"+key);
		roots.put(key , root);
		// root.dumpTree(System.out);
	}

	public static ASTNode<ASTNode> findRootFor(String hostName, String methodName, String methodDesc){
		hostName = hostName.replace('/', '.');
		if (methodName.equals("<init>")){
			int classNameStartIndex = hostName.lastIndexOf('/');
			methodName = hostName.substring(classNameStartIndex + 1, hostName.length());
		}

		String key = hostName + methodName + methodDesc;
		System.out.println("Key used for root finding:\t"+key);
		ASTNode<ASTNode> root = roots.get(key);
		if (root == null){
			throw new IllegalStateException("No AST found for method "+ hostName+"."+methodName+ methodDesc);
		}
		return root;
	}

	public static Set<String> getRootIDs(){
		return roots.keySet();
	}

}
