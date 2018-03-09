grammar Calculator;

prog:   expr;

expr:   expr ('*'|'/') expr
    |   expr ('+'|'-') expr
    |   NUMBER
    |   '(' expr ')'
    ;
NUMBER: [0-9]+ ;