accept(LIn, LOut) :- 
    'S'(LIn, LOut), 
    !.

'S'([d | LIn], LOut) :-
    'S'(LIn, [b | LOut]).

'S'(LIn, LOut) :-
    'A'(LIn, L1),
    'B'(L1, LOut).

'A'([a | LIn], LOut) :-
    'A'(LIn, LOut).

'A'(L, L).

'B'([b | LIn], LOut) :-
    'B'(LIn, LOut).

'B'([c | L], L).

% ?- accept([d,d,a,a,c,b,b], []).     true
% ?- accept([a,a,b,c], []).           true
% ?- accept([d,b], []).               false