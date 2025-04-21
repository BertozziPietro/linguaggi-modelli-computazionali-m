// introspection -> user.isAdmin === true;
// intercession -> Object.prototype.isAdmin = true;

function weTrust(user) { 
  return user.isAdmin === true
}

function doDamage(user) {
    if (weTrust(user)) console.log("✅ Admin access granted...")
    else console.log("⛔ Admin access denied: no harm done.")
}

let anyOtherUser = { intentions: "I want to login as Admin..." }
console.log(anyOtherUser.intentions) //I want to login as Admin...
doDamage(anyOtherUser) // Admin access denied: no harm done.

Object.prototype.intentions = "I give up!"
Object.prototype.isAdmin = true
console.log(anyOtherUser.intentions) //I want to login as Admin...
doDamage(anyOtherUser) // Admin access granted...

/*
// Funzione costruttore
function Point(i, j) {
  this.getX = function () { return i; };
  this.getY = function () { return j; };
}

p0 = { x: 6, y: 7 }; // oggetto letterale
p1 = new Point(3, 4); // oggetto creato con "new"
p2 = new Point(0, 1); // oggetto creati con "new"

console.log(p0.constructor.prototype == p0.__proto__); // true
console.log(p1.constructor.prototype == p1.__proto__); // true
  
console.log(p0.__proto__ == Object.prototype); // true
console.log(p1.__proto__ == Point.prototype);   // true
  
console.log(p0.constructor.__proto__ == Function.prototype); // true
console.log(p1.constructor.__proto__ == Function.prototype); // true

// === PROTOTYPE CHAIN BASE ===
protoOfP0 = Object.getPrototypeOf(p0);
protoOfP1 = Object.getPrototypeOf(p1);
protoOfP2 = Object.getPrototypeOf(p2);

console.log(protoOfP1 == protoOfP2); // true
console.log(protoOfP0 == Object.prototype); // true
console.log(protoOfP1 == Point.prototype); // true
console.log(protoOfP1.isPrototypeOf(p1)); // true

// === PROPRIETÀ ===
console.log(p0.hasOwnProperty("x")); // true
console.log(p1.hasOwnProperty("x")); // false
console.log(Object.getOwnPropertyNames(p1)); // [ 'getX', 'getY' ]
*/