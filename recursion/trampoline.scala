// Generic trampoline
sealed trait Trampoline[T]

case class Done[T](result: T) extends Trampoline[T]
case class More[T](next: () => Trampoline[T]) extends Trampoline[T]

@annotation.tailrec
def runTrampoline[T](trampoline: Trampoline[T]): T = trampoline match {
    case Done(result) => result
    case More(next)   => runTrampoline(next())
}

// Tail recursive function
def collatzEven(n: Long, steps: Long): Trampoline[Long] =
    More(() => collatz(n / 2, steps + 1))

// Tail recursive function
def collatzOdd(n: Long, steps: Long): Trampoline[Long] =
    More(() => collatz(3 * n + 1, steps + 1))

// Tail recursive function
def collatz(n: Long, steps: Long = 0): Trampoline[Long] =
    if (n == 1L) Done(steps)
    else if (n % 2 == 0L) More(() => collatzEven(n, steps))
    else More(() => collatzOdd(n, steps))

// Usage
def main(): Unit = {
    val start1 = 27L
    println(s"Collatz($start1) ha terminato in ${runTrampoline(collatz(start1))} passi.")

    val start2 = 63728127L
    println(s"Collatz($start2) ha terminato in ${runTrampoline(collatz(start2))} passi.")
}
