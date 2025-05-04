# Grammatica: analisi formale

## Presentazione della Grammatica in Esame

```
S → d S b | A B  
A → A a | ε  
B → b B | c
```

## Pumping Lemma per Dimostrare che la Grammatica Non è Regolare

Se L è un linguaggio di Tipo 3, esiste un intero N tale che, per ogni stringa z di lunghezza almeno pari a N:
- z può essere riscritta come: z = xyw |z| >= N
- la parte centrale xy ha lunghezza limitata: |xy| <= N
- y non è nulla: |y| >= 1
- la parte centrale può essere pompata quanto si vuole ottenendo sempre altre frasi del linguaggio; ovvero, xy^{i}w con i naturale.

Supponiamo per assurdo che il linguaggio L generato dalla grammatica sia regolare e proviamo ad applicare il Pumping Lemma.

Sia z = dⁿ aᵐ bᵖ c bⁿ ∈ L, con n, m, p ≥ N  
Dato che |xy| ≤ N, y è una sottostringa formata solo da simboli d.

Supponiamo di pompare con i = 0: otteniamo z' = xw, con meno d ma lo stesso numero di b.  
Quindi z' non appartiene al linguaggio.  
La contraddizione indica che il pumping lemma non è soddisfatto e che quindi L non è regolare.  

## Esempio in Prolog di Riconoscitore a Stati Finiti

Due delle produzioni, se prese singolarmente, sono regolari.  
Non sono regolari se prese assieme, poiché la prima è regolare a sinistra e la seconda è regolare a destra.
```
A → A a | ε     A = a*
B → b B | c     B = b*c
```

Usiamo la prima produzione per mostrare come si possa realizzare un [riconoscitore a stati finiti scritto in prolog](a.pl).  
L'implementazione è logicamente più vicina all'idea di automa e meno all'idea di linguaggio.

## Analisi LL(1) Dopo Opportune Trasformazioni

### Eliminazione della Ricorsione Sinistra

Un linguaggio definito da una grammatica con ricorsione sinistra non può essere analizzato con successo da un analizzatore LL(1).  
Procediamo quindi con l’eliminazione della ricorsione sinistra.

```
# generalmente si può trasformare questa
S  → v S | l
# in questo modo
S  → S' l
S' → v S' | ε

# nel nostro caso
A  → a A | ε
# procediamo allo stesso modo
A  → A' ε
A' → a A' | ε
# e semplifichiamo ulteriormente perchè dalla prima produzioni A e A' risultano uguali
A  → a A | ε
```

### Analisi LL(1)

```
S  → d S b | A B  
A  → a A | ε  
B  → b B | c

FIRST(S) = { d, a, b, c }
FIRST(A) = { a, ε }
FIRST(B) = { b, c }

FOLLOW(S) = { $, b }
FOLLOW(A) = { b, c }
FOLLOW(B) = { $, b }

DSS(S → d S b) = { d }
DSS(S → A B) = SS(AB) = { a, b, c }
DSS(A → a A) = { a }
DSS(A → ε) = FOLLOW(A) = { b, c }
DSS(B → b B) = { b }
DSS(B → c) = { c }
```

Una condizione necessaria perché una grammatica sia LL(1) è che gli insiemi di starter symbols relativi alle parti destre di uno stesso metasimbolo siano mutuamente disgiunti.
Nel nostro caso, tale condizione è soddisfatta: l’analisi LL(1) ha quindi avuto successo.

Tuttavia, per raggiungere questo risultato, è stato necessario eliminare la ricorsione sinistra.
Se l’obiettivo è costruire un semplice riconoscitore, questa trasformazione è accettabile.
Al contrario, se si intende realizzare un interprete o un compilatore, la trasformazione può risultare problematica, in quanto modifica la struttura delle derivazioni e quindi la semantica del linguaggio.

Proseguiamo, prima con un riconoscitore in prolog, e poi con l’analisi LR.

## Esempio in Prolog di Push-Down Automaton

Usiamo la grammatica senza ricorsione sinistra in un [PDA deterministico scritto in Prolog](s.pl).

## Analisi LR(0) e Identificazione dei Conflitti

adesso analisi LR(0) della grammatica iniziale di partenza

Z -> S
S  → d S b | A B  
A  → a A | ε  
B  → b B | c

LEFTCTXLR(0)(Z) = { ε }
LEFTCTXLR(0)(S) <- LEFTCTXLR(0)(Z) * { ε }
LEFTCTXLR(0)(S) <- LEFTCTXLR(0)(S) * { d }
LEFTCTXLR(0)(A) <- LEFTCTXLR(0)(S) * { ε }
LEFTCTXLR(0)(A) <- LEFTCTXLR(0)(A) * { a }
LEFTCTXLR(0)(B) <- LEFTCTXLR(0)(S) * { A }
LEFTCTXLR(0)(B) <- LEFTCTXLR(0)(B) * { b }

LEFTCTXLR(0)(Z) = { ε }
LEFTCTXLR(0)(S) = { d* }
LEFTCTXLR(0)(A) = { d*, a* }
LEFTCTXLR(0)(B) = { d*A, b* }

CTXLR(0)(Z->S) = { S }
CTXLR(0)(S->dSb) = { d*dSb } = { d⁺ S b }
CTXLR(0)(S->AB) = { d* A B }
CTXLR(0)(A->aA) = { d* a A, a* a A }
CTXLR(0)(A->ε) = { d*, a* }
CTXLR(0)(B->bB) = { d*A b B, b* b B }
CTXLR(0)(B->c) = { d*A c, b* c }

ci sono due conflitti shift reduce in A.

## Analisi SRL

adesso analisi srl 

Z -> S
S  → d S b | A B  
A  → a A | ε  
B  → b B | c

FOLLOW(Z) = { $ }
FOLLOW(S) = { b, $ }
FOLLOW(A) = { b, c }
FOLLOW(B) = { b, $ }

SLR(1)CTX(P → α) = CTXLR(0)(P → α) × FOLLOW(P)

Z → S
CTXLR(0) = S
FOLLOW(Z) = { $ }
SLR(1)CTX = S ($)

S → d S b
CTXLR(0) = d⁺ S b
FOLLOW(S) = { b, $ }
SLR(1)CTX = d⁺ S b (b, $)

S → A B
CTXLR(0) = d* A B
FOLLOW(S) = { b, $ }
SLR(1)CTX = d* A B (b, $)

A → a A
CTXLR(0) = (d* ∪ a*) a A
FOLLOW(A) = { b, c }
SLR(1)CTX = (d* ∪ a*) a A (b, c)

A → ε
CTXLR(0) = d* ∪ a*
FOLLOW(A) = { b, c }
SLR(1)CTX = (d* ∪ a*) (b, c)

B → b B
CTXLR(0) = (d* A ∪ b*) b B
FOLLOW(B) = { b, $ }
SLR(1)CTX = (d* A ∪ b*) b B (b, $)

B → c
CTXLR(0) = (d* A ∪ b*) c
FOLLOW(B) = { b, $ }
SLR(1)CTX = (d* A ∪ b*) c (b, $)