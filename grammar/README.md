# JavaScript: Linguaggio Dinamico ma Vulnerabile
##  Type Coercion e JSFuck con Function
### 🔄 Type Coercion
### 🧩 JSFuck con Function
##  Modello a Prototipi e Prototype Pollution
### 🧬 Modello a Prototipi
### ⚠️ Prototype Pollution

# Grammatiche: 

S → d S b | A B  
A → A a | ε  
B → b B | c

## Pumping Lemma

🧠 Dimostrazione che il linguaggio non è regolare (Tipo 3)
Ipotesi: Il linguaggio L generato dalla grammatica

S → d S b | A B
A → A a | ε
B → b B | c

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

regolari se presi da soli
A → A a | ε  A=a*
B → b B | c  B=b*c

adesso devo eliminare la ricorsione sinistra che c'è in 
A → A a | ε

in generale
S -> vS | l
diventa
S -> l S'
S' -> vS' | ε

nel nostro caso
A  → ε A'
A' → a A' | ε
o più semplicemente
A -> a A | ε


S  → d S b | A B  
A  → a A | ε  
B  → b B | c

adesso calcoliamo i first e i floow e i dss della grammatica candidata ad essere ll1

FIRST(S) = { d, a, b, c }
FIRST(A) = { a, ε }
FIRST(B) = { b, c }


FOLLOW(S) = { $, b }
FOLLOW(A) = { b, c }
FOLLOW(B) = { $, b }

DSS(S → d S b) = { d }
DSS(S → A B) = FIRST(A) ∪ (FIRST(B) se ε ∈ FIRST(A)) = { a } ∪ { b, c } = { a, b, c }

DSS(A → a A) = { a }0
DSS(A → ε) = FOLLOW(A) = { b, c }

DSS(B → b B) = { b }
DSS(B → c) = { c }

adesso analisi lr0 della grammatica iniziale di partenza

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