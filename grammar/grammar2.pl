e(LI,LO):-
    t(LI,LO).

e(LI,LO):-
    t(LI,LT1),
    piu(LT1,LT2),
    e(LT2,LO).

t(LI,LO):-
    a(LI,LO).

a([a|L],L).
piu([+|L],L).

%?- e([a,+,a,+,a],[]).
%true .
%?- e([a,+,+,a],[]).
%false.
