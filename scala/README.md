# Scala: Costruire Nuovi Costrutti

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

## 🛠️ Esempio ed Utilizzo del Nuovo Costrutto WriteFile

Seguono la [definizione di nuovi costrutti](dslFiles.scala) che semplificano le operazioni di lettura e scrittura su file, e un  semplice [esempio del loro utilizzo](collatzFiles.scala).  

L'espressività dei nuovi costrutti così definiti può essere ulteriormente potenziata dalle chiusure e dalle funzioni parzialmente specificate.  
Le chiusure permettono a una funzione di "catturare" e utilizzare variabili dal contesto in cui è stata definita.  
Questo rende i blocchi più potenti e flessibili, perchè possono accedere allo stato esterno senza che venga loro passato esplicitamente.  
Le funzioni parzialmente specificate permettono di creare nuove funzioni a partire da altre, fissando alcuni parametri.
È utile perché rende il codice più modulare, componibile e leggibile, specialmente in contesti come DSL o flussi di operazioni dove una parte si ripete.  

## 📌 Possibili Ulteriori Approfondimenti

- Matching di variabili e oggetti (analisi del [trampolino in kotlin](../recursion/trampoline.kt) sotto una nuova luce)
- Tratti e [stackable behaviour](realStackable.scala)