# Ambiguous Collisions Writeup

## Italiano

- [Panoramica della Challenge](#panoramica-della-challenge)
- [Livello 1](#livello-1)
- [Livello 2](#livello-2)
- [Livello 3](#livello-3)
- [Conclusione](#conclusione)

## English

- [Challenge Overview](#challenge-overview)
- [Level 1](#level-1)
- [Level 2](#level-2)
- [Level 3](#level-3)
- [Conclusion](#conclusion)

## Panoramica della Challenge

La challenge Ambiguous Collisions presenta una sequenza di grammatiche ambigue, richiedendo al partecipante di trovare frasi ambigue. Per chi non ha familiarità con i concetti di linguaggi di programmazione, una breve ricerca online potrebbe essere necessaria per comprendere il significato di produzione, grammatica e ambiguità.

La sfida utilizza una funzione di verifica per controllare:
- Se tutti i caratteri nel tentativo appartengono all'insieme consentito.
- Se la lunghezza della stringa rientra nel limite stabilito.

Un partecipante astuto può utilizzare il controllo della lunghezza per dedurre la lunghezza della risposta corretta, ma questo trucco non è necessario per risolvere la sfida.

## Soluzione Passo-Passo

### Livello 1

```
S -> A
A -> C | ABA
B -> +
C -> a
```

**Derivazioni**

```
S -> A -> ABA -> CBA -> aBA -> a+A -> a+ABA
S -> A -> ABA -> ABABA -> aBABA -> a+ABA

a+ABA -> a+aBA -> a+a+A -> a+a+a
```

**Soluzione:** `a+a+a`

### Livello 2

```
S -> A  
A -> C | BC  
B -> !
C -> E | CDE  
D -> & | | 
E -> F | BF
F -> H | FGH  
G -> < | > | =  
H -> J | HIJ  
I -> * | /  
J -> L | JKL  
K -> + | -  
L -> b
```

**Derivazioni**

```
S -> A -> BC -> !C -> !E -> !F
S -> A -> C -> E -> BF -> !F

!F -> !L -> !b
```

**Soluzione:** `!b`

### Livello 3

```
S -> A
A -> B | C | D
B -> f D t A | f D t A e A
C -> w D d A
D -> F | EF
E -> !
F -> H | FGH
G -> & | |
H -> J | HIJ
I -> < | > | =
J -> L | JKL
K -> * | /
L -> N | LMN
M -> + | -
N -> c
```

**Derivazioni**

```
S -> A -> B -> fDtA -> fFtA -> fHtA -> fJtA -> fLtA -> fNtA -> fctA -> fctB -> fctfDtAeA
S -> A -> B -> fDtAeA -> fFtAeA -> fHtAeA -> fJtAeA -> fLtAeA -> fNtAeA -> fctAeA -> fctAeA -> fctBeA -> fctfDtAeA

fctfDtAeA -> fctfFtAeA -> fctfHtAeA -> fctfJtAeA -> fctfLtAeA -> fctfNtAeA -> fctfctAeA -> fctfctDeA -> fctfctFeA -> fctfctHeA -> fctfctJeA -> fctfctLeA -> fctfctNeA -> fctfctceA -> fctfctceD -> fctfctceF -> fctfctceH -> fctfctceJ -> fctfctceL -> fctfctceN -> fctfctcec
```

**Soluzione:** `fctfctcec`

## Conclusione
Questa sfida è un'introduzione semplice al tema dei linguaggi di programmazione. I tre livelli dimostrano diversi tipi di ambiguità comunemente riscontrati nella definizione delle grammatiche:
1. [Ambiguità commutativa](#livello-1): differenti ordini di analisi per operazioni commutative.
2. [Ambiguità nella precedenza degli operatori](#livello-2): più interpretazioni valide dovute alle regole di precedenza.
3. [Ambiguità del dangling else](#livello-3): associazioni non chiare tra istruzioni condizionali e clausole else.

## Challenge Overview

The Ambiguous Collisions challenge presents a sequence of ambiguous grammars, requiring the participant to find ambiguous phrases. For those unfamiliar with programming language concepts, a brief online search will be necessary to understand the meanings of production, grammar and ambiguity.

The challenge uses a verification function to check:
- If all characters in the attempt belong to the allowed set.
- If the string length is within the limit.

A clever partecipant can use the lenght check to deduce the lenght of che correct response, but this trick is not needed to solve the challenge.

## Step-by-Step Solution

### Level 1

```
S -> A
A -> C | ABA
B -> +
C -> a
```

**Derivations**

```
S -> A -> ABA -> CBA -> aBA -> a+A -> a+ABA
S -> A -> ABA -> ABABA -> aBABA -> a+ABA

a+ABA -> a+aBA -> a+a+A -> a+a+a
```

**Solution:** `a+a+a`

### Level 2

```
S -> A  
A -> C | BC  
B -> !
C -> E | ADE  
D -> & | | 
E -> F | BF
F -> H | FGH  
G -> < | > | =  
H -> J | HIJ  
I -> * | /  
J -> L | JKL  
K -> + | -  
L -> b
```

**Derivations**

```
S -> A -> BC -> !C -> !E -> !F
S -> A -> C -> E -> BF -> !F

!F -> !L -> !b
```

**Solution:** `!b`

### Level 3

```
S -> A
A -> B | C | D
B -> f D t A | f D t A e A
C -> w D d A
D -> F | EF
E -> !
F -> H | FGH
G -> & | |
H -> J | HIJ
I -> < | > | =
J -> L | JKL
K -> * | /
L -> N | LMN
M -> + | -
N -> c
```

**Derivations**

```
S -> A -> B -> fDtA -> fFtA -> fHtA -> fJtA -> fLtA -> fNtA -> fctA -> fctB -> fctfDtAeA
S -> A -> B -> fDtAeA -> fFtAeA -> fHtAeA -> fJtAeA -> fLtAeA -> fNtAeA -> fctAeA -> fctAeA -> fctBeA -> fctfDtAeA

fctfDtAeA -> fctfFtAeA -> fctfHtAeA -> fctfJtAeA -> fctfLtAeA -> fctfNtAeA -> fctfctAeA -> fctfctDeA -> fctfctFeA -> fctfctHeA -> fctfctJeA -> fctfctLeA -> fctfctNeA -> fctfctceA -> fctfctceD -> fctfctceF -> fctfctceH -> fctfctceJ -> fctfctceL -> fctfctceN -> fctfctcec
```

**Solution:** `fctfctcec`

## Conclusion

This challenge is a simple introduction to the topic of programming languages. The three levels demonstrate different types of ambiguities commonly found in grammar definitions:
1. [Commutative ambiguity](#level-1): different parsing orders for commutative operations.
2. [Operator precedence ambiguity](#level-2): multiple valid interpretations due to precedence rules.
3. [Dangling else ambiguity](#level-3): unclear associations between conditional statements and else clauses.
