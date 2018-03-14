grammar Calculator;


prog:   expr;

expr:   expr '!' #UnaryOperationAfter
    |   expr ('%'|'*'|'/') expr #BinaryOperation
    |   expr ('+'|'-') expr #BinaryOperation
    |   ('-'|'+') expr #UnaryOperationBefore
    |   expr ',' expr #TwoOperands
    |   IDENTIFIER '(' expr ')' #CallFunction
    |   '(' expr ')' #Brackets
    |   NUMBER #DoubleValue
    |   IDENTIFIER #Identifier
    ;

fragment Digit
    : [0-9]
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

NUMBER: (Digit+ ( '.' Digit+ ( Exponent PLusMinus? Digit+ )? )?) ;

IDENTIFIER: (IdentifierStart (IdentifierStart | Digit)*);