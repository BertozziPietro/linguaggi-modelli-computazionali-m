# Ricorsione: Tail-Recursion, TRO e Trampolini

## 🧠 Costrutti Linguistici e Processi Computazionali Sottostanti e Tail Recursion

Ogni processo computazionale ricorsivo si esprime con funzioni ricorsive, ma è vero anche il viceversa?

Il caso interessante è quello della tail recursion: ricorsione in cui la chiamata ricorsiva è l’ultima operazione eseguita dalla funzione.

Tail recursion e iterazione sono quindi, di fatto, lo stesso processo computazionale espresso con due forme diverse e di conseguenza il risultato a runtime è il medesimo.

La ricorsione in coda è un costrutto sintatticamente ricorsivo che dà luogo a un processo computazionale iterativo, che come tale computa in avanti; mentre il processo computazionale ricorsivo computa all'indietro.

## ⚙️ I Limiti della TRO e i Trampolini

La Tail Recursion Optimization (TRO) consente al compilatore di trasformare una chiamata ricorsiva in coda in una forma iterativa, evitando l’allocazione di un nuovo frame nello stack.  
In pratica, la chiamata ricorsiva viene sostituita con un salto diretto (goto) verso l’inizio della funzione, rendendo l’esecuzione equivalente a un ciclo, sia in termini di memoria che di prestazioni.

Tuttavia, non tutti i linguaggi supportano TRO:
- I linguaggi imperativi classici (C, Java, C#): difficilmente supportano TRO perché sono già presenti costrutti iterativi espliciti (for, while). Qui la ricorsione è usata solo in casi realmente ricorsivi.
- I linguaggi funzionali o logici (Lisp, Scheme, Prolog): la ricorsione è il principale strumento espressivo, quindi la TRO è essenziale e quasi sempre implementata.
- I linguaggi blended (come Scala, Kotlin): offrono sia i convenzionali cicli che l'ottimizzazione della tail recursion.

## 💻 Esempi in Javascript, Kotlin e Scala

Per prima cosa, ecco un [trampolino in Javascript](trampoline.js), un linguaggio che non dispone di TRO.  
Si noti come l'implementazione è particolarmente semplice perché il linguaggio è loosely-typed.

Il trampolino ha un altro scenario applicativo: è utile per catturare i casi di ricorsione indiretta; ovvero quando due o più funzioni si richiamano a vicenda in catena.  
Nel caso di linguaggi strongly typed l'implementaizone è più complessa; il caso base della ricorsione differisce in tipo dal caso in cui la ricorsione continua.

Segue ora un [esempio in Kotlin](trampoline.kt), linguaggio in cui la TRO è disponibile su richiesta.  
Si utilizza `tailrec` ottimizzare con TRO il trampolino stesso, che alterna le diverse funzioni che si susseguono l'una all'altra.

Infine, un ultimo [esempio in Scala](trampoline.scala) simile al precedente.  
Si noti come, in Scala, non è necessario essere a conoscenza di tutte le considerazioni fatte fino ad ora per beneficiarne.
