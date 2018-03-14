// Generated from Calculator.g4 by ANTLR 4.7.1
package parser.antlr_parser;
import com.sun.javaws.exceptions.InvalidArgumentException;
import my_math.MathException;
import my_math.My_math;
import my_math.Operation;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link CalculatorVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * operations with no return type.
 */
public class CalculatorBaseVisitor extends AbstractParseTreeVisitor<ReturnValue> implements CalculatorVisitor<ReturnValue> {

	private My_math math;

	public CalculatorBaseVisitor() {
		this.math = new My_math();
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public ReturnValue visitProg(CalculatorParser.ProgContext ctx) {

		return visitChildren(ctx);

	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public ReturnValue visitIdentifier(CalculatorParser.IdentifierContext ctx) {
		return new ReturnValue(
				0.0,
				""
		);
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

			if(operation != Operation.DIVIDE) {
				textRepresentation = first.getTextRepresentation() + textOperation + second.getTextRepresentation();
			}
			else {
				textRepresentation = "\\frac{"+first.getTextRepresentation() + "}{" + second.getTextRepresentation() + "}";
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
			catch (InvalidArgumentException ex) {
				System.err.println("Bad argument");
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

		try {

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

		}
		catch(InvalidArgumentException ex) {
			System.err.println("Error operation");
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

			else throw new InvalidArgumentException(new String[]{});

		}
		catch(InvalidArgumentException ex) {
			System.err.println("Error operation");
		}
		catch(MathException ex) {
			System.err.println("Error operation");
		}

		return new ReturnValue();
	}
}