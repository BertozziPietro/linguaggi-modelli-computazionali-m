using System;

public interface ITrampoline<T> { T Run(); }

public class Done<T> : ITrampoline<T> {
    public T Value { get; }
    public Done(T value) => Value = value;
    public T Run() => Value;
}

public class More<T> : ITrampoline<T> {
    public Func<ITrampoline<T>> Next { get; }
    public More(Func<ITrampoline<T>> next) => Next = next;
    public T Run() => Next().Run();
}

public static class TrampolineExample {
    // Generic trampoline
    public static T RunTrampoline<T>(ITrampoline<T> trampoline) => trampoline.Run();

    // Normal recursive function
    public static long SpecialSumRaw(long n, long partial = 0) => n == 0 ? partial : SpecialSumRaw(n - 1, partial + n);

    // Tail recursive function 
    public static ITrampoline<long> SpecialSumJumping(long n, long partial = 0) =>
        n == 0 ? new Done<long>(partial) : new More<long>(() => SpecialSumJumping(n - 1, partial + n));

    // Entrypoint
    public static long SpecialSumTrampoline(long n) => RunTrampoline(SpecialSumJumping(n));

    // Usage
    public static void Execute() {
        Console.WriteLine(SpecialSumRaw(5));       // 15
        Console.WriteLine(SpecialSumRaw(500));     // 125250
        Console.WriteLine(SpecialSumRaw(150000)); // Stack overflow.
        Console.WriteLine(SpecialSumTrampoline(5));       // 15
        Console.WriteLine(SpecialSumTrampoline(500));     // 125250
        Console.WriteLine(SpecialSumTrampoline(150000)); // 11250075000
    }
}

// Usage
TrampolineExample.Execute();
