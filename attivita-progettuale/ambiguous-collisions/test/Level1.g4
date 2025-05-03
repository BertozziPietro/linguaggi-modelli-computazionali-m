grammar Level1;

s : a ;
a : c | a b a ;
b : PLUS ;
c : A ;

PLUS : '+' ;
A : 'a' ;
WS : [ \t\r\n]+ -> skip ;