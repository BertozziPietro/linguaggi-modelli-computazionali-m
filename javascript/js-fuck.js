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