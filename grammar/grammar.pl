% Stato finale
finale(z).

% Transizioni (f)
f(u, 0, z).
f(z, 0, v).
f(i, 0, v).
f(i, 1, u).
f(v, 1, z).
f(z, 1, u).

% Riconoscimento della sequenza di simboli
accept([], Stato) :- finale(Stato).
accept([Simbolo | Input], Stato) :-
    f(Stato, Simbolo, NuovoStato),
    accept(Input, NuovoStato).

%?- accept([0,1,0,1],i). true