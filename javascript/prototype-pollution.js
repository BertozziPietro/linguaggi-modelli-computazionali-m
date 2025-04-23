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