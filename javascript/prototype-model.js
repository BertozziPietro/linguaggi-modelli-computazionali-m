// Constructor function
function User(username, password) {
    this.getUsername = function () { return username; };
    this.getPassword = function () { return password; };
}

const u0 = { username: "Alice", password: "1234" };  // literal object
const u1 = new User("BoB", "qwerty");                // object created with constructor
const u2 = new User("Carol", "password");            // another object from constructor

// Check relationship between __proto__ and constructor's prototype
console.log(u0.__proto__ === Object.prototype); // true
console.log(u1.__proto__ === User.prototype);   // true

// Check that constructors are functions
console.log(u0.constructor.__proto__ === Function.prototype); // true

// Prototype chain checks
console.log(Object.prototype.isPrototypeOf(u0)); // true
console.log(Object.getPrototypeOf(u1) === Object.getPrototypeOf(u2)); // true

// Check for own properties
console.log(u1.hasOwnProperty("getUsername")); // true
console.log(u1.hasOwnProperty("username"));    // false
