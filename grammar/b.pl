final('C').

f('B', b, 'B').
f('B', c, 'C').

accept([], State) :- final(State).
accept([Char | Others], State) :-
    f(State, Char, NewState),
    accept(Others, NewState).

% ?- accept([b,b,c], 'B').
