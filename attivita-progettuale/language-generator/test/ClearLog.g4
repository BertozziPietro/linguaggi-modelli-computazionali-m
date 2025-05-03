grammar ClearLog;

b  : C l k | S h ;
g  : G ;
h  : I N | H w ;
k  : L O g ;
l  : L v R ;
v  : E A | H O | E R ;
w  : I N | O W k ;

A  : 'a';
C  : 'c';
E  : 'e';
G  : 'g';
H  : 'h';
I  : 'i';
L  : 'l';
N  : 'n';
O  : 'o';
R  : 'r';
S  : 's';
W  : 'w';
WS : [ \t\r\n]+ -> skip ;