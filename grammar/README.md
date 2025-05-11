# Grammatica: Analisi

## 🧑‍🏫 Presentazione della Grammatica in Esame

```
S → d S b | A B  
A → A a | ε  
B → b B | c
```

## 💪 Pumping Lemma per Dimostrare che la Grammatica Non è Regolare

Se L è un linguaggio di Tipo 3, esiste un intero N tale che, per ogni stringa z di lunghezza almeno pari a N:

- z può essere riscritta come: z = xyw |z| >= N
- la parte centrale xy ha lunghezza limitata: |xy| <= N
- y non è nulla: |y| >= 1
- xy^{i}w con i naturale ∈ L.

Supponiamo per assurdo che il linguaggio L generato dalla grammatica sia regolare appichiamo il Pumping Lemma.
Sia z = dⁿ aᵐ bᵖ c bⁿ ∈ L, con n, m, p ≥ N  
Dato che |xy| ≤ N, y è una sottostringa formata solo da simboli d.

Supponiamo di pompare con i = 0: otteniamo z', con meno d iniziali ma lo stesso numero di b finali.  
Quindi z' non appartiene al linguaggio.  
La contraddizione indica che il pumping lemma non è soddisfatto e quindi L non è regolare.  

## 📊 Analisi LL(1) Dopo Opportune Trasformazioni

```
S  → d S b | A B  
A  → a A | ε  
B  → b B | c

FIRST(S) = { d, a, b, c }
FIRST(A) = { a, ε }
FIRST(B) = { b, c }

FOLLOW(S) = { $, b }
FOLLOW(A) = { b, c }
FOLLOW(B) = FOLLOW(S) = { $, b }

DSS(S → d S b) = { d }
DSS(S → A B) = SS(AB) = { a, b, c }
DSS(A → a A) = { a }
DSS(A → ε) = FOLLOW(A) = { b, c }
DSS(B → b B) = { b }
DSS(B → c) = { c }
```

Una condizione necessaria perché una grammatica sia LL(1) è che gli insiemi di direct symbols relativi alle parti destre di uno stesso metasimbolo siano mutuamente disgiunti.
Nel nostro caso, tale condizione è soddisfatta: l’analisi LL(1) ha quindi avuto successo.

Tuttavia, per raggiungere questo risultato, è stato necessario eliminare la ricorsione sinistra.
Se l’obiettivo è costruire un semplice riconoscitore, questa trasformazione è accettabile.
Al contrario, se si intende realizzare un interprete o un compilatore, la trasformazione può risultare problematica, in quanto modifica la struttura delle derivazioni e quindi la semantica del linguaggio.  
Proseguiamo con l’analisi LR.

## 📊 Analisi LR(0) e Identificazione dei Conflitti

L'aggiunta della produzione Z → S in una grammatica per l'analisi LR è prassi standard per due motivi.
In primo luogo, garantisce che la riduzione a Z coincida con l'accept della frase.  
In secondo luogo, fornisce un caso base nel calcolo dei contesti sinistri, che è tipicamente ricorsivo.

```
Z → S
S → d S b | A B  
A → A a | ε  
B → b B | c

LEFTCTX(Z) = { ε }
LEFTCTX(S) = LEFTCTX(S) • { d } = { d* }
LEFTCTX(A) = (LEFTCTX(S) • { ε }) ∪ (LEFTCTX(A) • { ε }) = { d* }
LEFTCTX(B) = (LEFTCTX(S) • { A }) ∪ (LEFTCTX(B) • { b }) = { d* A b* }

LR(0)CTX(Z → S)     = { S }
LR(0)CTX(S → d S b) = { d* d S b } = { d⁺ S b }
LR(0)CTX(S → AB)    = { d* A B }
LR(0)CTX(A → a A)   = { d* a A }
LR(0)CTX(A → ε)     = { d* }
LR(0)CTX(B → b B)   = { d* A b* b B } = { d* A b⁺ B }
LR(0)CTX(B → c)     = { d* A b* c }
```

Dalle produzione di A → ε risultano numerosi conflitti: il contesto in questione è prefisso proprio del contesto di molte altre produzioni.  
La grammatica non è LR(0).

## 📊 Analisi SRL(1)

```
Z → S $
S → d S b | A B  
A → A a | ε  
B → b B | c

FOLLOW1(Z) = { ε }
FOLLOW1(S) = { $, b }
FOLLOW1(A) = { b, c, a }
FOLLOW1(B) = { $, b }

CTXSLR(1)(Z → S)     = { S $ }
CTXSLR(1)(S → d S b) = { d⁺ S b } • { $, b } = { d⁺ S b $ + d⁺ S b b } 
CTXSLR(1)(S → A B)   = { d* A B } • { $, b } = { d* A B $ + d* A B b }
CTXSLR(1)(A → A a)   = { d* A a} • { b, c, a } = { d* A a b + d* A a c + d* A a a}
CTXSLR(1)(A → ε)     = { d* } • { b, c, a } = { d* b + d* c + d* a }
CTXSLR(1)(B → b B)   = { d* A b⁺ B } • { $, b } = { d* A b⁺ B $ + d* A b⁺ B b }
CTXSLR(1)(B → c)     = { d* A b* c } • { $, b } = { d* A b⁺ c $ + d* A b⁺ c b }
```

La grammatica è SLR(1).

## 📌 Possibili Ulteriori Approfondimenti

- [RSF per A](a.pl)
- [RSF per B](b.pl)
- [PDA per S](s.pl)
