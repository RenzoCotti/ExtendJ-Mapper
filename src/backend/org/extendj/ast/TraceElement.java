package org.extendj.ast;

public interface TraceElement<E> {

	public int getEventIndex();
	
	/**
	 * @param index The index of the value among all the values 
	 * defined by the current trace element
	 * @return
	 */
	public String value(int index);
	
	public void generate();
	
	public E getNativeTraceElement();
}
