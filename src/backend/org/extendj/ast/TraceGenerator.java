package org.extendj.ast;

public interface TraceGenerator<T extends TraceElement<E>, E> {
	
	public void generate(TraceIterator<T, E> trace, 
							String msg, 
							NodeValueList nodeValues, 
							ASTNode<ASTNode> current, 
							boolean couldBeLast);

}
