grammar ChangeTraversal;

g  : G p | g T R t ;
n  : C o g ;
o  : H A N ;
p  : A | E | O | ;
t  : A u A L ;
u  : V v S ;
v  : E A | H O | E R ;

A : 'a';
C : 'c';
E : 'e';
G : 'g';
H : 'h';
L : 'l';
N : 'n';
O : 'o';
R : 'r';
S : 's';
T : 't';
V : 'v';
i : 'i';
WS : [ \t\r\n]+ -> skip ;
