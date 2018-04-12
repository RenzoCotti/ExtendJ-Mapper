package org.extendj.ast;

public interface TraceIterator<T extends TraceElement<E>, E> {
	
	/**
	 * Moves the cursor to the next non-artificial executed bytecode.
	 * @return
	 */
	public TraceIterator<T,E> fwd();

	/**
	 * Moves the cursor to the next non-artificial executed bytecode.
	 * @return the next non-artificial non-executed bytecode
	 */
	public T next();
	
	/**
	 * Provides the next non-artificial executed bytecode without
	 * moving the cursor.
	 * @return
	 */
	public T lookAhead();
	
	/**
	 * The bytecode event currently pointed to by the cursor.
	 * If the cursor is pointing to an artificial bytecode 
	 * event, the cursor is moved forward to the first non-
	 * artificial bytecode event.
	 * @return
	 */
	public T current();
	
	/**
	 * Retrieve the value of the current trace element
	 * and move the cursor forward
	 * @return
	 */
	public T checkAndFwd();
	
	/**
	 * Returns the index of the element currently pointed to by
	 * the cursor
	 * @return
	 */
	public int index();
	
}
