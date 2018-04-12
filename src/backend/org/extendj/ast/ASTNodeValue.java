package org.extendj.ast;

public class ASTNodeValue {
	
	private final ASTNode<ASTNode> node;
	
	private final String value;
	
	private final String msg;

	public ASTNodeValue(ASTNode<ASTNode> node, String value, String msg) {
		this.node = node;
		this.value = value;
		this.msg = msg;
	}

	/**
	 * @return the node
	 */
	public ASTNode<ASTNode> getNode() {
		return node;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	@Override
	public String toString() {
		return "\n{\n\t\"start\": " + node.getColumn(node.start())+ " ,\n\t\"end\": " + node.getColumn(node.getEnd()) + " ,\n\t\"value\": \"" + value +"\"\n}";
	}

	
}
