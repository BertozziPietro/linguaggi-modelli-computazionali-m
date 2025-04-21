console.log(![]) //false
console.log(!![]) //true

console.log([][[]]) //undefined
console.log(+[![]]) //NaN
console.log(+[]) //0
console.log(+!+[]) //1
console.log(!+[]+!+[]) //2
console.log([+!+[]]+[+[]]) //10

console.log([]) //Array
console.log(+[]) //Number
console.log(![]) //Boolean
console.log([]+[]) //String

console.log((false+"")[1]);   // "a"

console.log([]["filter"]) //Function
console.log([]["map"]["constructor"]("x", "return x")(1)) //1
console.log([]["find"]["constructor"]("return this")()) //globalThis

// Full type coercion madness
/*console.log(true + false)             // 1
console.log(12 / "6")                 // 2
console.log("number" + 15 + 3)        // 'number153'
console.log(15 + 3 + "number")        // '18number'
console.log([1] > null)               // true
console.log("foo" + + "bar")          // 'fooNaN'
console.log('true' == true)           // false
console.log(false == 'false')         // false
console.log(null == '')               // false
console.log(!!"false" == !!"true")    // true
console.log(['x'] == 'x')             // true 
console.log([] + null + 1)            // 'null1'
console.log([1,2,3] == [1,2,3])       // false
console.log({}+[]+{}+[1])             // '0[object Object]1'
console.log(!+[]+[]+![])              // 'truefalse'
console.log(new Date(0) - 0)          // 0
console.log(new Date(0) + 0)          // 'Thu Jan 01 1970 02:00:00(EET)0'

console.log(+ '2') //2
console.log(+ true) //1
console.log(+ false) //0
console.log(+ []) //0
console.log(+ {}) //NaN
console.log(+ 'we') //NaN
console.log('10' + 2) //102
console.log(5 + '3') //53

console.log({}) //{} blocco di codice/oggetto
console.log(({})) //{} oggetto
console.log([] + {}) //[object Object]

console.log(!!0) //false
console.log(!!-0) //false
console.log(!!0n) //false
console.log(!!"") //false
console.log(!!null) //false
console.log(!!undefined) //false
console.log(!!NaN) //false
console.log(!!false) //false
//console.log(!!document.all) //false su browserc
console.log(!!{}) //true
console.log(!![]) //true
console.log(!!"text") //true

//assegnazione nullish
console.log(null ?? 'default')   // → 'default'
console.log(0 ?? 42)             // → 0  ✅ (perché 0 non è null né undefined)
console.log(false ?? true)       // → false

//nullish coalescing 
let a;
a ??= 5; // a diventa 5
let b = 0;
b ??= 10; // b resta 0 perché 0 non è nullish

console.log(null == undefined) //true
console.log("0" == 0) //true
console.log([] == "") //true
console.log(null === undefined) //false
console.log("0" === 0) //false

console.log(typeof(1)) //number
console.log(typeof(undefined)) //undefined
console.log(typeof(null)) //object
console.log(typeof(NaN)) //number
console.log(typeof(-Infinity)) //number
console.log(typeof({})) //object
console.log(typeof([])) //object
console.log(typeof("")) //string
console.log(typeof(true)) //boolean

//altri tipi function bugint e symbol

console.log(NaN == NaN) //false
console.log(undefined == null) //true
console.log(undefined === null) //false
*/
