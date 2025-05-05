// Generic trampoline
sealed interface Trampoline<T>

data class Done<T>(val result: T) : Trampoline<T>
data class More<T>(val next: () -> Trampoline<T>) : Trampoline<T>

tailrec fun <T> runTrampoline(trampoline: Trampoline<T>): T = when (trampoline) {
    is Done -> trampoline.result
    is More -> runTrampoline(trampoline.next())
}

// Tail recursive function
fun collatzEven(n: Long, steps: Long): Trampoline<Long> =
    More { collatz(n / 2, steps + 1) }

// Tail recursive function
fun collatzOdd(n: Long, steps: Long): Trampoline<Long> =
    More { collatz(3 * n + 1, steps + 1) }

// Tail recursive function
fun collatz(n: Long, steps: Long = 0): Trampoline<Long> =
    if (n == 1L) Done(steps)
    else if (n % 2 == 0L) More { collatzEven(n, steps) }
    else More { collatzOdd(n, steps) }

// Usage
fun main() {
    println(runTrampoline(collatz(27L))) //111
    println(runTrampoline(collatz(63728127L))) //949
}

//java -Xss136k -jar trampoline-kotlin.jar