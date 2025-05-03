# Grammatica: analisi formale

## Presentazione della Grammatica in Esame

```
S → d S b | A B  
A → A a | ε  
B → b B | c
```

## Pumping Lemma per Dimostrare che la Grammatica Non è Regolare

🧠 Dimostrazione che il linguaggio non è regolare (Tipo 3)
Ipotesi: Il linguaggio L generato dalla grammatica
è regolare.
Allora, per il Pumping Lemma per i linguaggi regolari,
esiste un intero N (pumping length) tale che:
Per ogni stringa z ∈ L con |z| ≥ N,
esiste una decomposizione z = x y w tale che:
|xy| ≤ N
|y| ≥ 1
Per ogni i ≥ 0, x yⁱ w ∈ L
🔍 Scelta della stringa
Sia z = dⁿ aⁿ cⁿ bⁿ ∈ L, con n ≥ N
Questa stringa è derivabile da:
S → d S b → d d ... d A B b ... b
                → dⁿ A B bⁿ
                → dⁿ aⁿ cⁿ bⁿ
🧩 Decomposizione z = x y w
Poiché |xy| ≤ N, e i primi N simboli di z sono solo d,
abbiamo: y ∈ d⁺
🚫 Pompiamo con i = 0
Otteniamo la stringa x y⁰ w = x w
Questa ha meno d, ma ancora aⁿ cⁿ bⁿ
Quindi la struttura dⁿ aⁿ bⁿ bⁿ è distrutta
⇒ x y⁰ w ∉ L
❌ Contraddizione
Esiste una stringa in L che non può essere pompata
⇒ Il pumping lemma non è soddisfatto
✅ Conclusione
Il linguaggio non è regolare ⇒ non è di Tipo 3

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
## Analisi SRL e Classificazione della Grammatica

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