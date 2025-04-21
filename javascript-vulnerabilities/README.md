# JavaScript: vulnerabilità

##  Type coercion e JSFuck con Function

### Definizione di Type Coersion

In JavaScript, le variabili non hanno tipi fissi; ma i valori assegnati a una variabile a hanno comunque un tipo. Questo significa che una variabile può contenere valori di tipo diverso durante l'esecuzione del programma. Questo fa di JavaScript linguaggio a tipizzazione debole (weakly typed) in cui i valori non necessitano di essere esplicitamente contrassegnati con un tipo.  
La type coercion si riferisce alla conversione automatica o implicita di valori da un tipo di dato a un altro, e si verifica a runtime, quando JavaScript converte i valori in tipi diversi in base all'operatore che li utilizza.  
Alcune di alcune di queste [decisioni](type-coersion.js), automatiche e implicite, sono poco intuitive...

### JSFuck e Function

JSFuck l amassima tecnica di offuscamento del codice JavaScript e consente di scrivere [programmi validi](js-fuck.js) utilizzando solo sei caratteri: \[\]\(\)+!.  
Nonostante sembri incomprensibile a prima vista, tutto il codice JSFuck è perfettamente valido e viene eseguito normalmente dai motori JavaScript. Questa tecnica si basa in gran parte proprio sulla type coercion e sul comportamento "creativo" del linguaggio quando si tratta di convertire tipi e interpretare espressioni in modo implicito.  
Gli ingredienti fondamentali di JSFuck sono: la coercizione implicita dei tipi, l'accesso dinamico alle proprietà degli oggetti, la costruzione di stringhe a partire da espressioni booleane o numeriche, e l'uso della funzione Function() per eseguire codice generato dinamicamente.

##  Modello a prototipi e prototype pollution

[exemple](prototype-pollution.js)