package org.extendj.ast;

import java.util.ArrayList;
import java.util.Iterator;

public class NodeValueList implements Iterable<ASTNodeValue>{
	
	protected final java.util.List<ASTNodeValue> list = new ArrayList<ASTNodeValue>();

	public void add(ASTNodeValue nodeValue){
		list.add(nodeValue);
	}
	
	public void addAll(NodeValueList nodeValues){
		list.addAll(nodeValues.list);
	}

	@Override
	public String toString() {
		return list.toString();
	}

	@Override
	public Iterator<ASTNodeValue> iterator() {
		return list.iterator();
	}
	
	public String explanationToString(){
		String explanation = "";
		for (ASTNodeValue nodeValue : this){
			explanation += nodeValue.getMsg();
		}
		return explanation;
	}
	
	public int size(){
		return list.size();
	}
}
