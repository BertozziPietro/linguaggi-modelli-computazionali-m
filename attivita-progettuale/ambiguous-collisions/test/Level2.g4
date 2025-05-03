grammar Level2;

s  : a ;
a  : c | b c ;
b  : NOT ;
c  : e | c d e ;
d  : AND | OR ;
e  : f | b f ;
f  : h | f g h ;
g  : LT | GT | EQ ;
h  : j | h i j ;
i  : STAR | SLASH ;
j  : l | j k l ;
k  : PLUS | MINUS ;
l  : B ;

NOT   : '!' ;
AND   : '&' ;
OR    : '|' ;
LT    : '<' ;
GT    : '>' ;
EQ    : '=' ;
STAR  : '*' ;
SLASH : '/' ;
PLUS  : '+' ;
MINUS : '-' ;
B     : 'b' ;
WS    : [ \t\r\n]+ -> skip ;
