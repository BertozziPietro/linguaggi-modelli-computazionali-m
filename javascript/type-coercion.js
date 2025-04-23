// Maybe understandable
console.log(10 / "5")                 // 2
console.log("number" + 10 + 5)        // 'number105'
console.log(10 + 5 + "number")        // '15number'
console.log(null == '')               // false
console.log(['x'] == 'x')             // true

// Not understandable
console.log(!!"false" == !!"true")    // true
console.log(+[])                      // 0
console.log(!+[]+[]+![])              // 'truefalse'
console.log(+ "bla" + "bla")          // 'NaNbla'
console.log([1] > null)               // true