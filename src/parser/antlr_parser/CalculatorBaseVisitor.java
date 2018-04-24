
// Generated from Calculator.g4 by ANTLR 4.7.1
package parser.antlr_parser;
import my_math.MathException;
import my_math.MathExceptionType;
import my_math.My_math;
import my_math.Operation;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import parser.MyParser;
import parser.symbol_table.Function;
import parser.symbol_table.TableOfFunctions;
import parser.symbol_table.TableOfVariables;
import parser.symbol_table.Variable;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import static parser.antlr_parser.TypeReturnValue.OK;

/**
 * This class provides an empty implementation of {@link CalculatorVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * operations with no return type.
 */
public class CalculatorBaseVisitor extends AbstractParseTreeVisitor<ReturnValue> implements CalculatorVisitor<ReturnValue> {

	/**
	 * Math library
	 */
	private My_math math;

	/**
	 * Table of variables for this parser
	 */
	private TableOfVariables tableOfVariables;

	/**
	 * Function arguments
	 */
	private TableOfVariables functionArgumentsVariables;

	/**
	 * Table of functions for this parser
	 */
	private TableOfFunctions tableOfFunctions;

	/**
	 * Acltual function
	 */
	private Function actualFunction;

	/**
	 * Flag which tell us if variable can be added
	 */
	private boolean addVariable;

	/**
	 * List of parent variables (for cyclical dependence)
	 */
    private Set<String> parentVariables;

	/**
	 * List of parent variables (for cyclical dependence)
	 */
    private Set<String> actualVariables;

	/**
	 * Actual functions
	 */
	private Set<String> actualFunctions;

	/**
	 * Parent functions
	 */
	private Set<String> parentFunctions;

	/**
	 * Flag which tell us if variables can be expand
	 */
	private boolean expandVariables;


	/**
	 * Flag which tell us if functions can be expand
	 */
	private boolean expandFunctions;

	/**
	 * Flag which tell us if we are in function
	 */
	private boolean parseFunction;

	/**
	 * Flag which tell us that function call function
	 */
	private boolean functionCallFunction;

	/**
	 * Create new Visitor for calculator
	 *
	 * @param tableOfVariables actual table of variables
	 * @param tableOfFunctions actual table of functions
	 */
	public CalculatorBaseVisitor(TableOfVariables tableOfVariables, TableOfFunctions tableOfFunctions) {
		this.math = new My_math();
		this.tableOfVariables = tableOfVariables;
		this.tableOfFunctions = tableOfFunctions;
		this.addVariable = true;
		this.expandVariables = true;
		parentVariables = null;
		parentVariables = null;
		actualVariables = new HashSet<>();
		actualFunctions = new HashSet<>();
		parseFunction = false;
		expandFunctions = false;

	}

	public void setFunctionCallFunction(boolean functionCallFunction) {
		this.functionCallFunction = functionCallFunction;
	}


	/**
	 * Setter for parent variables
	 *
	 * @param parentVariables
	 */
	public void setParentVariables(Set<String> parentVariables) {
		this.parentVariables = parentVariables;
	}

	/**
	 * Visit a parse tree produced by the {@code exprProg}
	 * labeled alternative in {@link CalculatorParser#prog}.
	 *
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	@Override
	public ReturnValue visitExprProg(CalculatorParser.ExprProgContext ctx) {

		return visitChildren(ctx);
	}

	/**
	 * Visit a parse tree produced by the {@code assignmentProg}
	 * labeled alternative in {@link CalculatorParser#prog}.
	 *
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	@Override
	public ReturnValue visitAssignmentProg(CalculatorParser.AssignmentProgContext ctx) {

		// Get name && content of variable
		String name = ctx.getChild(0).getText();
		String content = ctx.getChild(2).getText();

		// Set actual variable
		actualVariables.add(name);
		Variable variable = new Variable(name, content);

		MyParser tempParser = new MyParser(tableOfVariables, tableOfFunctions);
		tempParser.setAddVariable(addVariable);
		actualVariables.addAll(parentVariables);
		actualFunctions.addAll(parentFunctions);
		tempParser.setParentVariables(actualVariables);
		tempParser.setParentFunctions(actualFunctions);
		tempParser.setExpandVariables(expandVariables);
		ReturnValue variableValue = tempParser.parse(content);

		ReturnValue returnValue = new ReturnValue(
				variableValue.getValue(),
				name
		);

		// If additable of variable is active
		if(addVariable && variableValue.getTypeReturnValue() == OK) {

			// If variable exists, remove it
			if(tableOfVariables.isVariableExists(variable)) {
				tableOfVariables.getVariables().remove(variable);
			}

			// Add new variable && sort it
			tableOfVariables.addVariable(variable);
		}

		// If there are variables in cycle, cancel it
		if(variableValue.getTypeReturnValue() == TypeReturnValue.CYCLE) {
			variable.setContent("0.0");
		}

		returnValue.setTypeReturnValue(variableValue.getTypeReturnValue());

		sortVariables();

		return returnValue;

	}

	public boolean isExpandFunctions() {
		return expandFunctions;
	}

	public void setExpandFunctions(boolean expandFunctions) {
		this.expandFunctions = expandFunctions;
	}

	private void sortVariables() {

		tableOfVariables.getVariables().sort(new Comparator<Variable>() {

			@Override
			public int compare(Variable o1, Variable o2) {
				return o1.getName().compareTo(o2.getName());
			}

		});
	}

	/**
	 * Visit a parse tree produced by the {@code declareFunction}
	 * labeled alternative in {@link CalculatorParser#prog}.
	 *
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	@Override
	public ReturnValue visitDeclareFunction(CalculatorParser.DeclareFunctionContext ctx) {

		// Create function from first parameter

		String header = ctx.getChild(0).getText();
		int positionBracket = getPositionOfBracket(header);
		actualFunction = new Function(header, ctx.getChild(2).getText());
		actualFunction.setName(header.substring(0, positionBracket));

		String[] arguments = header.substring(positionBracket + 1, header.length() - 1).split(",");

		actualFunction.setArguments(arguments);
		actualFunction.setCountOfArguments(arguments.length);

		// If there is flag of function
		if(addVariable) {

			addOrActualizeFunction(actualFunction);

		}

		return new ReturnValue(
			0.0,
			ctx.getText(),
			TypeReturnValue.FUNCITON_DECLARATION
		);
	}

	private int getPositionOfBracket(String string) {

		for(int i = 0; i < string.length(); i++) {

			if(string.charAt(i) == '(') {
				return i;
			}

		}

		return -1;

	}

	private void addOrActualizeFunction(Function function){

		if (!tableOfFunctions.isFunctionDeclared(function)) {

			tableOfFunctions.addFunction(
					function
			);
		} else {

			tableOfFunctions.removeFunction(function);
			tableOfFunctions.addFunction(function);

		}
	}

	/**
	 * Visit a parse tree produced by {@link CalculatorParser#funcid}.
	 *
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	@Override
	public ReturnValue visitFuncid(CalculatorParser.FuncidContext ctx) {

		actualFunction.setName(ctx.getChild(0).getText());

		return null;
	}

	/**
	 * Visit a parse tree produced by the {@code twoParams}
	 * labeled alternative in {@link CalculatorParser#params}.
	 *
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	@Override
	public ReturnValue visitTwoParams(CalculatorParser.TwoParamsContext ctx) {
		return null;
	}

	/**
	 * Visit a parse tree produced by the {@code paramIdentifier}
	 * labeled alternative in {@link CalculatorParser#params}.
	 *
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	@Override
	public ReturnValue visitParamIdentifier(CalculatorParser.ParamIdentifierContext ctx) {
		return null;
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public ReturnValue visitIdentifier(CalculatorParser.IdentifierContext ctx){

		String content = ctx.getText();

		if(parseFunction) {

			Variable variable = new Variable(content, "");

			if(functionArgumentsVariables.getVariables().contains(variable)) {

				String valueString = functionArgumentsVariables.returnByContent(content).getContent();
				double value = Double.valueOf(valueString);

				if(expandVariables || functionCallFunction) {
					return new ReturnValue(
							value, valueString
					);
				}
				else {
					return new ReturnValue(
							value, content
					);
				}

			}

		}

		if(parentVariables.contains(content)) {
			return cycleValueFactory();
		}

		actualVariables.add(content);


		Variable tempVariable = new Variable(content, "0.0");

		if(tableOfVariables.isVariableExists(tempVariable)) {

			tempVariable = tableOfVariables.returnByContent(content);

		}
		else {

			if(addVariable) {

				tableOfVariables.addVariable(tempVariable);

				tableOfVariables.getVariables().sort(new Comparator<Variable>() {

					@Override
					public int compare(Variable o1, Variable o2) {
						return o1.getName().compareTo(o2.getName());
					}

				});

			}
		}

		// Parse variable && get return value
		MyParser localParser = new MyParser(tableOfVariables, tableOfFunctions);
		localParser.setAddVariable(addVariable);
		localParser.setParentVariables(actualVariables);

		actualVariables.addAll(parentVariables);
		actualFunctions.addAll(parentFunctions);
		localParser.setParentFunctions(actualFunctions);
		localParser.setParentVariables(actualVariables);
		localParser.setExpandVariables(expandVariables);
		ReturnValue returnValue = localParser.parse(tempVariable.getContent());

		// If the name should not be expand, get real name
		if(!expandVariables) {
			returnValue.setTextRepresentation(getRealVariableName(content));
		}
		else{
			returnValue.setTextRepresentation("(" + returnValue.getTextRepresentation() + ")");
		}

		return returnValue;

	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public ReturnValue visitBrackets(CalculatorParser.BracketsContext ctx) {

		ReturnValue child = visit(ctx.getChild(1));

		if (child.getTypeReturnValue() != OK) {
			return child;
		}

		return new ReturnValue(
				child.getValue(),
				"(" + child.getTextRepresentation() + ")"
		);

	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public ReturnValue visitDoubleValue(CalculatorParser.DoubleValueContext ctx) {

		return new ReturnValue(
			Double.valueOf(ctx.getText()),
			ctx.getText()
		);

	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public ReturnValue visitBinaryOperation(CalculatorParser.BinaryOperationContext ctx){

		ReturnValue first = visit(ctx.getChild(0));

		ReturnValue second = visit(ctx.getChild(2));

		ReturnValue empty = cycleValueFactory();

		// If there is cycle, return error
		if (first.getTypeReturnValue() != OK) {
			empty.setTypeReturnValue(first.getTypeReturnValue());
			return empty;
		}
		else if(second.getTypeReturnValue() != OK ) {
			empty.setTypeReturnValue(second.getTypeReturnValue());
			return empty;
		}

		String textOperation = ctx.getChild(1).getText();

		double operands[] = {
				first.getValue(),
				second.getValue()
		};

		try {

			Operation operation = Transformator.mapOperation(textOperation);

			String textRepresentation;

			if(operation == Operation.DIVIDE) {
				textRepresentation = "\\\\frac{"+first.getTextRepresentation() + "}{" + second.getTextRepresentation() + "}";
			}
			else if(operation == Operation.MODULO) {
				textRepresentation = first.getTextRepresentation() + " \\\\% " + second.getTextRepresentation();
			}
			else if(operation == Operation.POWER) {
				textRepresentation = "{(" + first.getTextRepresentation() + ")}" + textOperation + "{" + second.getTextRepresentation() + "}";
			}
			else {
				textRepresentation = first.getTextRepresentation() + textOperation + second.getTextRepresentation();
			}

			return new ReturnValue(
					math.run_operate(operands, operation),
					textRepresentation
			);

		}
		catch(MathException ex) {

			empty.setTypeReturnValue(mapExceptionToReturnError(ex.getType()));

			return empty;

		}

	}

	private TypeReturnValue mapExceptionToReturnError(MathExceptionType type) {

		switch(type) {
			case ERR_ZERO:
				return TypeReturnValue.DIVIDE_BY_NULL;
			case ERR_NG_ZERO:
				return TypeReturnValue.ERR_NG_ZERO;
			case ERR_FACT_OVERFLOW:
				return TypeReturnValue.TOO_BIG_FACT;
			case ERR_NOT_INT:
				return TypeReturnValue.ERR_NOT_INT;
			default:
				return OK;
		}

	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public ReturnValue visitTwoOperands(CalculatorParser.TwoOperandsContext ctx) {
		ReturnValue first = visit(ctx.getChild(0));
		ReturnValue second = visit(ctx.getChild(2));

		ReturnValue returnValue = first;

		returnValue.setNext(second);

		if(returnValue.getNext() == null)
			System.err.println("Fuck");

		return returnValue;
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public ReturnValue visitCallFunction(CalculatorParser.CallFunctionContext ctx) {

		// Get function name
		String functionName = ctx.getChild(0).getText();

		if(parentFunctions.contains(functionName)) {
			return cycleValueFactory();
		}

		actualFunctions.add(functionName);

		// If it is builtInFunction

		ReturnValue arguments = visit(ctx.getChild(2));

		if(testResult(arguments) != TypeReturnValue.OK) {

			ReturnValue returnValue1 = new ReturnValue();
			returnValue1.setTypeReturnValue(testResult(arguments));
			return returnValue1;

		}


		Operation operation = Transformator.mapOperation(functionName);

		Boolean isBuiltInFunction = Transformator.isBuiltInFunction(functionName);

		Function actualFunction = null;

		if(!isBuiltInFunction) {
			actualFunction = tableOfFunctions.returnByName(functionName);
		}

		if(!isBuiltInFunction && actualFunction == null) {

			ReturnValue returnValue = new ReturnValue();
			returnValue.setTypeReturnValue(TypeReturnValue.FUNCTION_NOT_DECLARED);
			return returnValue;

		}

		try {

			ReturnValue returnValue = null;
			ReturnValue actual = null;

			ReturnValue argument = arguments;

			do {

				double[] operands;

				// Get count of operands
				if(isBuiltInFunction) {
					operands = new double[Transformator.getCountOfOperands(operation)];
				}
				else {
					operands = new double[actualFunction.getCountOfArguments()];
				}

				String funcTextRepr = functionName + "(";

				for(int i = 0; i < operands.length; i++) {

					if(i != 0) {
						funcTextRepr += ", ";
					}

					if(argument != null) {

						operands[i] = argument.getValue();
						funcTextRepr += arguments.getTextRepresentation();

					}
					else {

						operands[i] = 0.0;
						funcTextRepr += "0.0";

					}

					if(arguments != null) {
						argument = argument.getNext();
					}
					arguments = argument;

				}

				funcTextRepr += ")";

				ReturnValue functionResult;
				if(isBuiltInFunction) {
					functionResult = new ReturnValue(
							math.run_operate(operands, operation),
							funcTextRepr
					);
				}
				else {
					MyParser functionParser = new MyParser(tableOfVariables, tableOfFunctions);
					functionParser.setExpandVariables(expandVariables);
					functionParser.setExpandFunctions(expandFunctions);
					functionParser.setVisitCallFunction(true);
					functionParser.setFunctionArgumentsVariables(actualFunction.getArgumentsAsTable(operands));
					actualVariables.addAll(parentVariables);
					actualFunctions.addAll(parentFunctions);
					functionParser.setParentFunctions(actualFunctions);
					functionParser.setParentVariables(actualVariables);
					functionResult = functionParser.parse(actualFunction.getContent());

					if(testResult(functionResult) != TypeReturnValue.OK) {

						ReturnValue returnValue1 = new ReturnValue();
						returnValue1.setTypeReturnValue(testResult(functionResult));
						return returnValue1;

					}

					if(!expandFunctions) {
						functionResult.setTextRepresentation(funcTextRepr);
					}
					else {
						ReturnValue next = functionResult;
						while(next != null) {
							next.setTextRepresentation("(" + next.getTextRepresentation() + ")");
							next = next.getNext();
						}
					}
				}

				if(returnValue == null) {
					returnValue = functionResult;
					actual = returnValue;
				}
				else {

					actual.next = functionResult;
					actual = actual.next;

				}

			}
			while(arguments != null);

			return returnValue;

		}
		catch (MathException ex) {
			System.err.println("Bad operation");
		}

		return new ReturnValue();

	}

	private TypeReturnValue testResult(ReturnValue arguments) {

		ReturnValue actual = arguments;

		while(actual != null) {

			if(actual.getTypeReturnValue() != TypeReturnValue.OK) {

				return actual.getTypeReturnValue();

			}

			actual = actual.getNext();

		}

		return TypeReturnValue.OK;

	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public ReturnValue visitUnaryOperationBefore(CalculatorParser.UnaryOperationBeforeContext ctx) {

			Operation operation = Transformator.mapOperation(ctx.getChild(0).getText());
			ReturnValue child = visit(ctx.getChild(1));

			if(child.getTypeReturnValue() != OK) {
				return child;
			}

			switch (operation) {
				case ADD:
					return new ReturnValue(
						child.getValue(),
						"+" + child.getTextRepresentation()
					);
				case SUBTRACT:
					return new ReturnValue(
							-child.getValue(),
							"-" + child.getTextRepresentation()
					);
			}



		return new ReturnValue();

	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public ReturnValue visitUnaryOperationAfter(CalculatorParser.UnaryOperationAfterContext ctx) {

		Operation operation = Transformator.mapOperation(ctx.getChild(1).getText());
		ReturnValue child = visit(ctx.getChild(0));

		if(child.getTypeReturnValue() != OK) {
			return child;
		}

		ReturnValue returnValue = new ReturnValue();
		if(operation == Operation.FACTORIAL) {

			try {
				Double result = math.run_operate(new double[]{child.getValue()}, operation);
				returnValue.setValue(result);
				returnValue.setTextRepresentation(child.getTextRepresentation() + "!");
				return returnValue;
			}
			catch (MathException ex) {

				returnValue.setTypeReturnValue(mapExceptionToReturnError(ex.getType()));
				return returnValue;

			}
		}

		return returnValue;

	}

	/**
	 * Setter for {@code addVariable}
	 *
	 * @param value
	 */
	public void setAddVariable(boolean value) {
		addVariable = value;
	}

	public boolean isExpandVariables() {
		return expandVariables;
	}

	public void setExpandVariables(boolean expandVariables) {
		this.expandVariables = expandVariables;
	}

	/**
	 * Get real variable name from string (pi to latex pi...)
	 * @param variableName
	 * @return
	 */
	private String getRealVariableName(String variableName) {

		switch (variableName) {
			case "pi":
				return "\\\\pi";
			default:
				return variableName;
		}
	}

	/**
	 * Return empty value with cycle error
	 * @return
	 */
	private ReturnValue cycleValueFactory() {
		ReturnValue returnValue = new ReturnValue(
				0.0,
				"0.0"
		);

		returnValue.setTypeReturnValue(TypeReturnValue.CYCLE);

		return returnValue;
	}

	public TableOfVariables getFunctionArgumentsVariables() {
		return functionArgumentsVariables;
	}

	public void setFunctionArgumentsVariables(TableOfVariables functionArgumentsVariables) {
		this.functionArgumentsVariables = functionArgumentsVariables;
		parseFunction = true;
	}

	public void setparentFunctions(Set<String> parentFunctions) {
		this.parentFunctions = parentFunctions;
	}
}