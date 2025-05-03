grammar ShowLog;

g  : G ;
i  : W m g | L ;
m  : L O ;
o  : H A N | q i ;
q  : s O | G ;
s  : S H ;

A  : 'a';
G  : 'g';
H  : 'h';
L  : 'l';
N  : 'n';
O  : 'o';
S  : 's';
W  : 'w';
WS : [ \t\r\n]+ -> skip ;
