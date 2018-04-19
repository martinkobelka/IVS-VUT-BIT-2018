
// Generated from Calculator.g4 by ANTLR 4.7.1
package parser.antlr_parser;
import my_math.MathException;
import my_math.My_math;
import my_math.Operation;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import parser.MyParser;
import parser.symbol_table.Function;
import parser.symbol_table.TableOfFunctions;
import parser.symbol_table.TableOfVariables;
import parser.symbol_table.Variable;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

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
	 * Flag which tell us if variables can be expand
	 */
	private boolean expandVariables;

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
		actualVariables = new HashSet<>();

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

		ReturnValue returnValue =  visitChildren(ctx);

		if(returnValue.getTypeReturnValue() == TypeReturnValue.CYCLE) {
			return emptyValeuFactory();
		}

		return returnValue;
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

		// If additable of variable is active
		if(addVariable) {

			// If variable exists, remove it
			if(tableOfVariables.isVariableExists(variable)) {
				tableOfVariables.getVariables().remove(variable);
			}

			// Add new variable && sort it
			tableOfVariables.addVariable(variable);
		}

		MyParser tempParser = new MyParser(tableOfVariables, tableOfFunctions);
		tempParser.setAddVariable(addVariable);
		actualVariables.addAll(parentVariables);
		tempParser.setParentVariables(actualVariables);
		tempParser.setExpandVariables(expandVariables);
		ReturnValue variableValue = tempParser.parse(content);

		// If there are variables in cycle, cancel it
		if(variableValue.getTypeReturnValue() == TypeReturnValue.CYCLE) {
			variable.setContent("0.0");
		}

		sortVariables();

		return new ReturnValue(
				variableValue.getValue(),
				name
		);

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
		actualFunction = new Function(ctx.getChild(0).getText(), ctx.getChild(2).getText());

		visit(ctx.getChild(0));

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

		if(parentVariables.contains(content)) {
			return emptyValeuFactory();
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

		if (child.getTypeReturnValue() == TypeReturnValue.CYCLE) {
			return emptyValeuFactory();
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

		// If there is cycle, return error
		if (first.getTypeReturnValue() == TypeReturnValue.CYCLE || first.getTypeReturnValue() == TypeReturnValue.CYCLE ) {
			return emptyValeuFactory();
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
			else {
				textRepresentation = first.getTextRepresentation() + textOperation + second.getTextRepresentation();
			}

			return new ReturnValue(
					math.run_operate(operands, operation),
					textRepresentation
			);

		}
		catch(Exception ex) {
			System.err.println("Illegal operation");
		}

		return new ReturnValue();

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

		// If it is builtInFunction
		if(Transformator.isBuiltInFunction(functionName)) {

			ReturnValue arguments = visit(ctx.getChild(2));

			try {

				Operation operation = Transformator.mapOperation(functionName);

				ReturnValue returnValue = new ReturnValue(
						math.run_operate(new double[]{
								arguments.getValue()}, operation),
						functionName + "(" + arguments.getTextRepresentation() + ")"
				);

				ReturnValue next = arguments.getNext();

				while(next != null) {

					returnValue.setNext(
							new ReturnValue(
								math.run_operate(new double[]{next.getValue()}, operation),
									functionName + "(" + next.getTextRepresentation() + ")"
							)
					);

					next = next.getNext();
				}

				return returnValue;
			}
			catch (MathException ex) {
				System.err.println("Bad operation");
			}

		}

		// If it is not built in function
		else {
			ReturnValue returnValue = new ReturnValue(
				0.0,
				ctx.getText()
			);

			return returnValue;
		}

		return new ReturnValue();

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

			if(child.getTypeReturnValue() == TypeReturnValue.CYCLE) {
				return emptyValeuFactory();
			}

			switch (operation) {
				case ADD:
					return new ReturnValue(
						child.getValue(),
						"+" + child.getTextRepresentation()
					);
				case SUBTRACT:
					return new ReturnValue(
							child.getValue(),
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

		try {

			Operation operation = Transformator.mapOperation(ctx.getChild(1).getText());
			ReturnValue child = visit(ctx.getChild(0));

			if(child.getTypeReturnValue() == TypeReturnValue.CYCLE) {
				return emptyValeuFactory();
			}

			if(operation == Operation.FACTORIAL) {
				return new ReturnValue(
						math.run_operate(new double[]{child.getValue()}, operation),
						child.getTextRepresentation() + "!"
				);
			}



		}
		catch(MathException ex) {
			System.err.println("Error operation");
		}

		return new ReturnValue();
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
	private ReturnValue emptyValeuFactory() {
		ReturnValue returnValue = new ReturnValue(
				0.0,
				"0.0"
		);

		returnValue.setTypeReturnValue(TypeReturnValue.CYCLE);

		return returnValue;
	}
}