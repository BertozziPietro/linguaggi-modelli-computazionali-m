// Generic trampoline
function trampoline(fn) {
    while (fn && typeof fn === 'function') fn = fn()
    return fn
}

// Normal recursive function
function specialSumRaw(n, partial = 0) {
    if (n === 0) return partial
    return specialSumRaw(n - 1, partial + n)
}

// Tail recursive function
function specialSumJumping(n, partial = 0) {
    if (n === 0) return partial
    return () => specialSumJumping(n - 1, partial + n)
}

// Entrypoint
function specialSumTrampoline(n) {
    return trampoline(() => specialSumJumping(n))
}

// Usage
console.log(specialSumRaw(5))      // 15
console.log(specialSumRaw(500))    // 125250
console.log(specialSumRaw(100000)) // RangeError: Maximum call stack size exceeded

console.log(specialSumTrampoline(5))      // 15
console.log(specialSumTrampoline(500))    // 125250
console.log(specialSumTrampoline(100000)) // 5000050000
