package org.extendj.ast;

public class Messages {

	public static final String IFSTMT_ENTRY = "Entering if statement block. First, evaluating the condition. ";
	public static final String IFSTMT_COND_RET = "Condition of IF evaluated to ?. ";
	public static final String IFSTMT_THEN_ENTRY = "Entering the then block of if statement. ";
	public static final String IFSTMT_ELSE_ENTRY = "Entering the else block of if statement. ";
	public static final String IFSTMT_RET = "Leaving the if statement block. ";
	
	public static final String VAR_DECL_STMT_ENTRY = "Declaring ? of type ?. ";
	
	public static final String VAR_DECLARATOR_NOT_HASINIT = "Variable '?' declared, but not initialized! ";
	public static final String VAR_DECLARATOR_HASINIT = "Initializing variable '?' with the right hand side value! ";
	public static final String VAR_DECLARATOR_STORE_LOCAL = "Initializing the local variable '?' with the value ?. ";
	
	public static final String FOR_STMT_ENTRY= "Entering the For-Loop block. First evaluate the initializer. ";
	
	public static final String LITERAL_LOAD = "The literal value ? loaded. ";
	
	public static final String VARACSESS_LOAD = "The local variable '?' loaded with value ?. ";
	
	public static final String EXPR_STMT_NEEDSPOP = "The expression value ? is not used, so ignored! ";
	
	public static final String METHOD_DECL_ENTRY = "Starting to execute the body of the method '?'. ";
	
	public static final String ASSIGN_SIMPLE_EXPR_ENTRY = "Executing the assignment statement. first evaluating the destination address of the left hand side. ";
	public static final String ASSIGN_SIMPLE_EXPR_LHS_EVALUATED = "The left hand side of the assignment statement evaluated, now evaluating the right hand side value. ";
	public static final String ASSIGN_SIMPLE_EXPR_RET= "Assigning the value ? to the address denoted by the left hand side of the assignment";
	
	public static final String ACCESS_LOAD_LHS_FIELD = "The left hand side is a field access. Computing the receiver expression. ";
	public static final String ACCESS_LOAD_LHS_THIS = "The left hand side is an instance variable. Computing the this variable. ";
	public static final String ACCESS_LOAD_LHS_VAR = "The left hand side is the local variable '?'. ";
	
	public static final String METHODACESS_ENTRY = "Invoking method '?'. First, loading the qualifier. ";
	public static final String METHODACCESS_LOAD_QUALIFIER_THIS = "Loaded variable \"this\". It points to the object with ID ?. ";
	public static final String METHODACCESS_LOAD_QUALIFIER_STATIC = "Method '?' is static, so it does not need to load any quailifier object. ";
	public static final String METHODACCESS_ARGS_EVAL = "Evaluating actual arguments of the method ?. It has ? actual arguments. Starting with the first one. ";
	public static final String METHODACCESS_ARG_N = "Argument number ? evaluated, now evaluating arugment number ?. ";
	public static final String METHODACCESS_INVOKE = "All pieces of information for invoking the method ? are ready. Invocation happens at this point. ";
	public static final String METHODACCESS_CHECKCAST= "Checking if the casting is legitimate. The answer is ?. ";
	public static final String ADD_BINARY_EXPR_ENTRY = "Performing the ? operation. First, evaluate the left operand. ";
	public static final String ADD_BINARY_RETURN_VISIT = "The left operand evaluated, now evaluating the right operand. ";
	public static final String ADD_BINARY_RETURN_RETURN = "The right operand evaluated, now performing the operation on the evaluated operands. ";
	public static final String ADD_BINARY_RETURN = "The operation result evaluated to ?. ";
	
	
	/**
	 * Replaces the ? with the replacements given. The replacement
	 * happens in the order of the occurrene in the string.
	 * @param source
	 * @param replacements
	 * @return
	 */
	public static String replace(String source, String[] replacements){
		int start = 0;
		for (String rep : replacements) {
			int newIndex = source.indexOf('?', start);
			source = source.substring(0, newIndex) + rep + source.substring(newIndex + 1);
			start = newIndex;
		}
		return source;
	}

}
