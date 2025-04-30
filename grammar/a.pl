final('A').

f('A', a, 'A').


accept([], State) :- final(State).
accept([Char | Others], State) :-
    f(State, Char, NewState),
    accept(Others, NewState).

% ?- accept([a,a,a], 'A').
