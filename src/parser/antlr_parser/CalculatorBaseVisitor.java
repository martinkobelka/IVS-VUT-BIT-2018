// Generated from Calculator.g4 by ANTLR 4.7.1
package parser.antlr_parser;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link CalculatorVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 */
public class CalculatorBaseVisitor extends AbstractParseTreeVisitor<ReturnValue> implements CalculatorVisitor<ReturnValue> {

	private String latexRepresentation;

	public CalculatorBaseVisitor() {
		this.latexRepresentation = "";
	}

	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override public ReturnValue visitProg(CalculatorParser.ProgContext ctx) {

		ReturnValue value = visitChildren(ctx);

		System.out.println(value.getTextRepresentation());
		System.out.println(value.getValue());

		return value;
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation returns the result of calling
	 * {@link #visitChildren} on {@code ctx}.</p>
	 */
	@Override
	public ReturnValue visitExpr(CalculatorParser.ExprContext ctx) {

		if(ctx.getChildCount() == 3) {

			ReturnValue value = new ReturnValue();

			if(ctx.getChild(1).getText().equals("*")) {

				ReturnValue first = visit(ctx.getChild(0));
				ReturnValue second = visit(ctx.getChild(2));

				value.setValue(
						first.getValue() *
						second.getValue()
				);

				value.setTextRepresentation(
						first.getTextRepresentation() + "\\ddot" + second.getTextRepresentation()
				);

				return value;
			}
			else if(ctx.getChild(1).getText().equals("+")) {

				ReturnValue first = visit(ctx.getChild(0));
				ReturnValue second = visit(ctx.getChild(2));

				value.setValue(
						first.getValue() +
								second.getValue()
				);

				value.setTextRepresentation(
					first.getTextRepresentation() + "+" + second.getTextRepresentation()
				);

				return value;
			}

			else if(ctx.getChild(1).getText().equals("/")) {

				ReturnValue first = visit(ctx.getChild(0));
				ReturnValue second = visit(ctx.getChild(2));

				value.setValue(
						first.getValue() /
								second.getValue()
				);

				value.setTextRepresentation(
						"\\frac{"+first.getTextRepresentation() + "}{" + second.getTextRepresentation() + "}"
				);

				return value;
			}

			else if(ctx.getChild(1).getText().equals("-")) {

				ReturnValue first = visit(ctx.getChild(0));
				ReturnValue second = visit(ctx.getChild(2));

				value.setValue(
						first.getValue() -
								second.getValue()
				);

				value.setTextRepresentation(
						first.getTextRepresentation() + "-" + second.getTextRepresentation()
				);

				return value;
			}

		}
		else if(ctx.getChildCount() == 1) {

			ReturnValue value = new ReturnValue(
				Double.valueOf(ctx.getText()),
				ctx.getText()
			);

			return value;
		}

		return null;
	}
}