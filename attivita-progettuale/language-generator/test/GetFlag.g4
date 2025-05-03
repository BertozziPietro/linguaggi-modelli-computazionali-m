grammar GetFlag;

a  : A G ;
d  : F L ;
g  : G p ;
p  : A | E | O | ;
q  : T d | G ;
y  : g q y | a ;

A  : 'a' ;
E  : 'e' ;
F  : 'f' ;
G  : 'g' ;
L  : 'l' ;
O  : 'o' ;
P  : 'p' ;
T  : 't' ;
WS : [ \t\r\n]+ -> skip ;
