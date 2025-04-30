accept(LIn, LOut) :- 
    'S'(LIn, LOut), 
    !.

'S'([d | LIn], LOut) :-
    'S'(LIn, L1),
    'B'(L1, LOut).

'S'(LIn, LOut) :-
    'A'(LIn, L1),
    'B'(L1, LOut).

'A'([a | LIn], LOut) :-
    'A'(LIn, LOut).

'A'(L, L).

'B'([b | LIn], LOut) :-
    'B'(LIn, LOut).

'B'([b | L], L).

% ?- accept([d,d,a,a,b,b,b], []).     true
% ?- accept([a,a,b,b], []).           true
% ?- accept([d,b], []).               false