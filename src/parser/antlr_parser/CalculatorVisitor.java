// Generated from Calculator.g4 by ANTLR 4.7.1
package parser.antlr_parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CalculatorParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CalculatorVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code exprProg}
	 * labeled alternative in {@link CalculatorParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprProg(CalculatorParser.ExprProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignmentProg}
	 * labeled alternative in {@link CalculatorParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentProg(CalculatorParser.AssignmentProgContext ctx);
	/**
	 * Visit a parse tree produced by the {@code declareFunction}
	 * labeled alternative in {@link CalculatorParser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclareFunction(CalculatorParser.DeclareFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalculatorParser#funcid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncid(CalculatorParser.FuncidContext ctx);
	/**
	 * Visit a parse tree produced by the {@code twoParams}
	 * labeled alternative in {@link CalculatorParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTwoParams(CalculatorParser.TwoParamsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code paramIdentifier}
	 * labeled alternative in {@link CalculatorParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamIdentifier(CalculatorParser.ParamIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Identifier}
	 * labeled alternative in {@link CalculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(CalculatorParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Brackets}
	 * labeled alternative in {@link CalculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBrackets(CalculatorParser.BracketsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DoubleValue}
	 * labeled alternative in {@link CalculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleValue(CalculatorParser.DoubleValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BinaryOperation}
	 * labeled alternative in {@link CalculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryOperation(CalculatorParser.BinaryOperationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code TwoOperands}
	 * labeled alternative in {@link CalculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTwoOperands(CalculatorParser.TwoOperandsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallFunction}
	 * labeled alternative in {@link CalculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallFunction(CalculatorParser.CallFunctionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryOperationBefore}
	 * labeled alternative in {@link CalculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperationBefore(CalculatorParser.UnaryOperationBeforeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryOperationAfter}
	 * labeled alternative in {@link CalculatorParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryOperationAfter(CalculatorParser.UnaryOperationAfterContext ctx);
}