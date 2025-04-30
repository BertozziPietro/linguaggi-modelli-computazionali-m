final('B').

f('I', b, 'B').
f('B', b, 'B').

accept([], State) :- final(State).
accept([Char | Others], State) :-
    f(State, Char, NewState),
    accept(Others, NewState).

% ?- accept([b,b,b], 'I').
