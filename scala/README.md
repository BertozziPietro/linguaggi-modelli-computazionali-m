# Scala: Costrutti e Comportamenti Estensibili

## 👨‍🍳 Ingredienti e Ricetta per Nuovi Costrutti

Scala fornisce gli strumenti per definire nuovi costrutti anziché definirne a priori un insieme fisso e prestabilito.  
Gli ingredienti principali utilizzati per offrire questa possibilità sono:
- funzioni come first class objects e lambda;  
- supporto per il passaggio dei parametri con call-by-name;  
- currying, ovvero la possibilità di applicare ad una funziona a più argomenti una serie di argomenti in sequenza;  
- block-like syntax, come zucchero sintattico;  

Le funzioni sono oggetti a tutti gli effetti: possono essere assegnate a variabili, passate come parametri, e restituite da altre funzioni.  
La call-by-name è un meccanismo che permette di ritardare l’esecuzione di un parametro fino a quando (e se) viene effettivamente utilizzato.  
Il currying è una tecnica che consiste nel trasformare una funzione che prende più argomenti in una serie di funzioni, ciascuna con un singolo argomento.  
In ogni situaizone in cui vi sia solo un argomento, si può usare la sintassi con le graffe { ... } invece delle parentesi tonde (...), rendendo il codice più simile a un blocco.  

Seguono la [definizione di nuovi costrutti](dslFiles.scala) che semplificano le operazioni di lettura e scrittura su file, e un  semplice [esempio del loro utilizzo](collatzFiles.scala).  

L'espressività dei nuovi costrutti così definiti può essere ulteriormente potenziata dalle chiusure e dalle funzioni parzialmente specificate.  
Le chiusure permettono a una funzione di "catturare" e utilizzare variabili dal contesto in cui è stata definita.  
Questo rende i blocchi più potenti e flessibili, perchè possono accedere allo stato esterno senza che venga loro passato esplicitamente.  
Le funzioni parzialmente specificate permettono di creare nuove funzioni a partire da altre, fissando alcuni parametri.
È utile perché rende il codice più modulare, componibile e leggibile, specialmente in contesti come DSL o flussi di operazioni dove una parte si ripete.  

## Tratti e Stackable Behaviour

### 🧩 Tratti

Scala supera le interfacce classiche, introducendo i tratti che sono più simili a classi che a interfacce, possono contenere codice, definiscono un tipo e supportano una nuova forma di composizionalità: il MIX-IN.  
Una classe estende una superclasse (ereditarietà singola) ma può mixarsi con un numero arbitrario di tratti, che vengono
composti assieme mediante linearizzazione e il risultato è uno stackable behaviour.

### 📚 Stackable Behaviour

Normalmente la composizione è raggiunta risolvendo dinamicamente a runtime i riferimenti super.x a dati o metodi di un tratto.  
Il concetto di stackable behaviour è reinterpretato: non si usa super, ma si costruisce manualmente uno stack di comportamenti.  
Sebbene non venga utilizzato il classico meccanismo di stackable traits tramite abstract override e super (tipico della stackable modification pattern), l'[esempio proposto](realStackable.scala) ne conserva lo spirito compositivo: più tratti possono essere mischiati per sommare effetti, senza che uno sovrascriva l'altro.  

Questo approccio potrebbe avere anche utilizzi in scenari applicativi concreti come nei Middleware nei Web Framework dove è comune avere pipeline di funzioni che processano una richiesta in sequenza (logging, autenticazione, validazione).  
Un altro scenario possibile è rappresentato dagli engine per giochi o sistemi a eventi, dove si potrebbe voler registrare azioni da eseguire in sequenza, in risposta a un input: ogni modulo potrebbe scrivere su una coda di eventi di risposta.