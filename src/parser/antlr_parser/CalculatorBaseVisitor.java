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

/**
 * This class provides an empty implementation of {@link CalculatorVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * operations with no return type.
 */
public class CalculatorBaseVisitor extends AbstractParseTreeVisitor<ReturnValue> implements CalculatorVisitor<ReturnValue> {

	private My_math math;
	private TableOfVariables tableOfVariables;
	private TableOfFunctions tableOfFunctions;
	private Function actualFunction;
	private boolean addVariable;

	public CalculatorBaseVisitor(TableOfVariables tableOfVariables, TableOfFunctions tableOfFunctions) {
		this.math = new My_math();
		this.tableOfVariables = tableOfVariables;
		this.tableOfFunctions = tableOfFunctions;
		this.addVariable = true;

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

		String name = ctx.getChild(0).getText();
		String content = ctx.getChild(2).getText();

		Variable variable = new Variable(name, content);

		if(addVariable) {

			if(tableOfVariables.isVariableExists(variable)) {


				tableOfVariables.getVariables().remove(variable);


			}

			tableOfVariables.addVariable(variable);

			tableOfVariables.getVariables().sort(new Comparator<Variable>() {

				@Override
				public int compare(Variable o1, Variable o2) {
					return o1.getName().compareTo(o2.getName());
				}

			});
		}


		MyParser tempParser = new MyParser(tableOfVariables, tableOfFunctions);
		tempParser.sedAddVariable(addVariable);
		ReturnValue variableValue = tempParser.parse(content);

		return new ReturnValue(
				variableValue.getValue(),
				name
		);
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

		actualFunction = new Function(ctx.getChild(0).getText(), ctx.getChild(2).getText());

		visit(ctx.getChild(0));

		if(!tableOfFunctions.isFunctionDeclared(actualFunction)) {

			tableOfFunctions.addFunction(
					actualFunction
			);
		}
		else {

			tableOfFunctions.removeFunction(actualFunction);
			tableOfFunctions.addFunction(actualFunction);

		}

		return new ReturnValue(
			0.0,
			""
		);
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
	@Override public ReturnValue visitIdentifier(CalculatorParser.IdentifierContext ctx) {

		String content = ctx.getText();

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


		MyParser localParser = new MyParser(tableOfVariables, tableOfFunctions);
		localParser.sedAddVariable(addVariable);

		return localParser.parse(tempVariable.getContent());
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public ReturnValue visitBrackets(CalculatorParser.BracketsContext ctx) {

		ReturnValue child = visit(ctx.getChild(1));

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

		String functionName = ctx.getChild(0).getText();

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

		System.err.println("Bad function");
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

	public void setAddVariable(boolean value) {
		addVariable = value;
	}
}