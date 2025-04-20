// 1. Esecuzione di codice dinamico
eval("console.log('Eval says hi')"); // Eval says hi
(new Function("console.log('Function says hi')"))(); // Function says hi

// 2. Scope locale vs globale
let localVar = 42;

eval("console.log('Eval access:', localVar)"); // Eval access: 42

const fn = new Function("try { console.log('Function access:', localVar); } catch(e) { console.log('Function access error:', e.message); }");
fn(); // Function access error: localVar is not defined

// 3. Modifica variabili locali
eval("localVar = 99");
console.log("After eval, localVar:", localVar); // 99

const fn2 = new Function("localVar = 123"); // Non modifica nulla
fn2();
console.log("After Function, localVar still:", localVar); // 99

// 4. Valutazione dinamica
const expression = "2 + 3 * 4";
console.log("Eval result:", eval(expression)); // 14
const func = new Function("return " + expression);
console.log("Function result:", func()); // 14

// 5. Pericoli reali
const userInput = "alert('You got hacked!')"; // Simula input utente
// DO NOT EXECUTE in browser, just for reference
// eval(userInput); // ← questo può essere una falla enorme

// 6. Safe vs unsafe execution
function safeEval(str) {
  // Un modo per controllare cosa eseguire
  if (/^[0-9+\-*/ ().]+$/.test(str)) {
    return eval(str);
  } else {
    return "Rejected!";
  }
}

console.log(safeEval("3 + 3")); // 6
console.log(safeEval("alert('boom')")); // Rejected!

// ====================================
// ⚠️ EVAL: CHIAMATA DIRETTA VS INDIRETTA
// ====================================

function esempioEval() {
  let locale = "Sono locale";

  // ✅ Chiamata diretta: eval vede lo scope locale
  eval("console.log('Diretto:', locale)"); // ✅ "Sono locale"

  // ❌ Chiamata indiretta: eval non vede lo scope locale
  (1, eval)("try { console.log('Indiretto:', locale) } catch(e) { console.log('Indiretto: ERRORE') }");
}

esempioEval();


// ====================================
// 🔥 EVAL + USE STRICT
// ====================================

function esempioStrict() {
  "use strict";

  try {
    eval("undeclared = 42"); // ❌ Errore in strict mode
  } catch (e) {
    console.log("Strict eval: non puoi creare variabili globali implicitamente!");
  }

  // Senza strict mode questo avrebbe creato una variabile globale
}

esempioStrict();


// ====================================
// 🛠️ FUNCTION CONSTRUCTOR
// ====================================

function esempioFunctionConstructor() {
  const f = new Function("x", "return x * x;");
  console.log("Function: f(5) =", f(5)); // 25

  // ⚠️ Function NON ha accesso allo scope locale
  let y = 10;
  const g = new Function("return typeof y;");

  console.log("Function scope:", g()); // "undefined"
}

esempioFunctionConstructor();


// ====================================
// 🌐 GLOBALTHIS USO
// ====================================

function esempioGlobalThis() {
  // Definizione globale tramite globalThis
  globalThis.foo = "Sono globale";
  console.log("globalThis.foo =", globalThis.foo); // "Sono globale"

  // Anche eval indiretta opera su globalThis
  (1, eval)("console.log('eval indiretta su globalThis.foo:', foo)"); // "Sono globale"
}

esempioGlobalThis();


// ====================================
// 💡 RIDEFINIRE FUNZIONI GLOBALI (es. alert)
// ====================================

function esempioOverrideAlert() {
  globalThis.alert = function(msg) {
    console.log("ALERT OVERRIDED:", msg);
  };

  alert("Non mostro più un popup, ma vado in console!");
}

esempioOverrideAlert();
