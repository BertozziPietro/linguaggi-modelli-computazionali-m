% Fatti

uomo(alberto).
uomo(claudio).
uomo(enrico).
donna(beatrice).
donna(daniela).

genitore(claudio, alberto).
genitore(beatrice, daniela).
genitore(claudio, enrico).
genitore(daniela, enrico).

marito(alberto, beatrice).
marito(claudio, daniela).
moglie(beatrice, alberto).
moglie(daniela, claudio).

% Regole

fratello(F1, F2) :- uomo(F2), genitore(X, F1), genitore(X, F2).
sorella(F1, F2) :- donna(F1), genitore(X, F1), genitore(X, F2).

quasigenitore(G, F) :- genitore(G, F).
quasigenitore(G, F) :- genitore(X, F), marito(G, X).
quasigenitore(G, F) :- genitore(X, F), moglie(G, X).

padre(P, F) :- uomo(P), quasigenitore(P, F).
madre(M, F) :- donna(M), quasigenitore(M, F).
figlio(F, G) :- uomo(F), quasigenitore(G, F).
figlia(F, G) :- donna(F), quasigenitore(G, F).

zio(Z, N) :- fratello(X, Z), quasigenitore(X, N).
zia(Z, N) :- sorella(X, Z), quasigenitore(X, N).

nonno(NO, NI) :- uomo(NO), quasigenitore(NO, X), quasigenitore(X, NI).
nonna(NO, NI) :- donna(NO), quasigenitore(NO, X), quasigenitore(X, NI).

nipote(N, Z) :- zio(Z, N).
nipote(N, Z) :- zia(Z, N).
nipote(NI, NO) :- nonno(NO, NI).
nipote(NI, NO) :- nonna(NO, NI).

genero(G, S) :- uomo(G), marito(G, X), quasigenitore(S, X).
nuora(N, S) :- donna(N), moglie(N, X), quasigenitore(S, X).
suocero(S, G) :- uomo(G), genero(G, S).
suocero(S, G) :- uomo(G), nuora(G, S).
suocera(S, G) :- donna(G), genero(G, S).
suocera(S, G) :- donna(G), nuora(G, S).