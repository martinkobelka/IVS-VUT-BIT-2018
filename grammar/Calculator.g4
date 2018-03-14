grammar Calculator;


prog:   expr;

expr:   NUMBER #DoubleValue
    |   IDENTIFIER #Identifier
    |   expr '!' #UnaryOperationAfter
    |   ('-'|'+') expr #UnaryOperationBefore
    |   expr ('%'|'*'|'/') expr #BinaryOperation
    |   expr ('+'|'-') expr #PlusMinus
    |   expr ',' expr #TwoOperands
    |   IDENTIFIER '(' expr ')' #CallFunction
    |   '(' expr ')' #Brackets
    ;

fragment Digit
    : ('0'..'9')
    ;

fragment IdentifierStart
    : ('a'..'z' | 'A'..'Z' | '_')
    ;

fragment Exponent
    : ('e' | 'E')
    ;

fragment PLusMinus
    : ('+' | '-')
    ;

NUMBER: Digit ( '.' Digit ( Exponent PLusMinus? Digit+ )? )? ;

IDENTIFIER: IdentifierStart (IdentifierStart | Digit)*;