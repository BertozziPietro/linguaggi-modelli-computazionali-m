grammar PleaseHint;

a  : P l | A G ;
h  : I N | H w ;
l  : L E A s ;
s  : S H | S E h T ;
w  : I N;

A  : 'a';
E  : 'e';
G  : 'g';
H  : 'h';
I  : 'i';
L  : 'l';
N  : 'n';
P  : 'p';
S  : 's';
T  : 't';
W  : 'w';
WS : [ \t\r\n]+ -> skip ;