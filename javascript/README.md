# JavaScript: Linguaggio Dinamico ma Vulnerabile

##  Type Coercion e JSFuck con Function

### 🔄 Type Coercion

In JavaScript, le variabili non hanno tipi fissi; ma i valori assegnati a una variabile hanno comunque un tipo.  
Questo significa che una variabile può contenere valori di tipo diverso durante l'esecuzione del programma. Questo fa di JavaScript linguaggio a tipizzazione debole (weakly typed) in cui i valori non necessitano di essere esplicitamente contrassegnati con un tipo.  
La type coercion si riferisce alla conversione automatica o implicita di valori da un tipo di dato a un altro, e si verifica a runtime, quando JavaScript converte i valori in tipi diversi in base all'operatore che li utilizza.  
Alcune di queste [decisioni](type-coercion.js), automatiche e implicite, sono poco intuitive...

### 🧩 JSFuck con Function

JSFuck è la massima tecnica di offuscamento del codice JavaScript e consente di scrivere [programmi validi](js-fuck.js) utilizzando solo sei caratteri: \[\]\(\)+!.  
Nonostante sembri incomprensibile a prima vista, tutto il codice JSFuck è valido e viene eseguito normalmente dai motori JavaScript.  
Questa tecnica si basa in gran parte proprio sulla type coercion e sul comportamento "creativo" del linguaggio quando si tratta di convertire tipi e interpretare espressioni in modo implicito.  
Gli ingredienti fondamentali di JSFuck sono: la coercizione implicita dei tipi, l'accesso dinamico alle proprietà degli oggetti, la costruzione di stringhe a partire da espressioni booleane o numeriche, e l'uso della funzione Function() per eseguire codice generato dinamicamente.

##  Modello a Prototipi e Prototype Pollution

### 🧬 Modello a Prototipi

JavaScript adotta un modello di tipo prototype-based: gli oggetti non sono istanze di una classe, ma ereditano direttamente da altri oggetti.  
Si parla di ereditarietà basata sui prototipi (prototype-based inheritance).  
E' quindi un modello object-based, ma non propriamente object-oriented nel senso classico.

Ogni oggetto in JavaScript è creato da una funzione costruttrice.   
Questa funzione, al momento della costruzione di un oggetto, gli assegna un prototipo, ovvero un altro oggetto da cui erediterà proprietà e metodi, che in un secondo momento possono però essere soggetti a modifiche.
In un modello così dinamico è bene che ogni oggetto tenga traccia del costruttore che lo ha creato; e la proprietà in questione è `constructor`.  
In questo senso, la funzione costruttrice ricopre il ruolo che, nei linguaggi class-based, spetta alla classe: definisce le caratteristiche comuni ad oggetti della stessa categoria.  
La proprietà `prototype` è detta prototipo di costruzione e punta all’oggetto che il costruttore affibbierà agli oggetti costruiti.

Tutti i prototipi in JavaScript condividono un antenato comune: `Object.prototype`, detto anche prototipo capostipite. In `__proto__` si trova il riferimento al prototipo che precede nella catena prototipale.  
Il `__proto__` di `Object.prototype` è null, e questo segna il termine della catena prototipale.  
Utilizzando quindi `prototype` e `__proto__` si può [toccare con mano](prototype-model.js) il modello sottostante.

### ⚠️ Prototype Pollution

In un modello così dinamico sono quindi possibili due tipi particolarmente interessanti di modifiche:
1. Il type augmenting che aggiungere/togliere proprietà a un prototipo già in uso con effetto immediato e retroattivo, che ci interessa maggiormente in questa analisi.
2. La sostituzione del prototipo di costruzione che altera le catene di ereditarietà padre-figlio future, che non approfondiamo in questa analisi.

Il type augmenting introduce la vulnerabilità di prototype pollution.  
La prototype pollution consente a un attaccante di manipolare il comportamento di un’applicazione JavaScript modificando oggetti globali come `Object.prototype`.  
Si sfrutta la capacità degli oggetti JavaScript di ereditare le proprietà lungo la prototype chain.  
Se un attaccante riesce a [iniettare o sovrascrivere](prototype-pollution.js) proprietà nel prototype globale, può alterare il comportamento di tutti gli oggetti dell'applicazione, anche quelli creati in modo legittimo.

Il funzionamento della catena di prototipi è il seguente: ogni oggetto in JavaScript ha una propria serie di proprietà, ma se non trova una determinata proprietà al suo interno, la cerca nel prototipo a cui è legato.  
Se non la trova, continua a salire lungo la catena dei prototipi fino a raggiungere `Object.prototype`.  
Se la proprietà non esiste nemmeno lì, il processo si ferma e il risultato è undefined.