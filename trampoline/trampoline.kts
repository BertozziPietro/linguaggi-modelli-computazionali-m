// Trampoline

interface Trampoline<Long>

data class Done(val value: Long) : Trampoline<Long>
data class MoreEven(val next: () -> Trampoline<Long>) : Trampoline<Long>
data class MoreOdd(val next: () -> Trampoline<Long>) : Trampoline<Long>

tailrec fun Long.run(trampoline: Trampoline<Long>): Long =
    when (trampoline) {
        is Done -> trampoline.value
        is MoreEven -> run(trampoline.next())
        is MoreOdd -> run(trampoline.next())
        else -> throw UnsupportedOperationException()
    }

// Tail recursive function
fun collatzEven(n: Long, steps: Long = 0): Trampoline<Long> =
    if (n == 1L) Done(steps)
    else MoreOdd { collatzOdd(3 * n + 1, steps + 1) }

// Tail recursive function
fun collatzOdd(n: Long, steps: Long = 0): Trampoline<Long> =
    if (n == 1L) Done(steps)
    else MoreEven { collatzEven(n / 2, steps + 1) }

// Sugar Wrapper
fun collatz(n: Long): Trampoline<Long> =
    if (n == 1L) Done(0L)
    else if (n % 2L == 0L) MoreEven { collatzEven(n, 1L) }
    else MoreOdd { collatzOdd(n, 1L) }

// Usage
fun main() {
    val start = 2L
    val result = run(collatz(start))
    println("Collatz($start) ha raggiunto 1 in $result passi.")
}
