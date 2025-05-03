import java.util.*;

public class CYKParser {

    static class Grammar {
        Map<String, List<List<String>>> rules = new HashMap<>();

        void addRule(String lhs, String... rhs) {
            rules.computeIfAbsent(lhs, k -> new ArrayList<>()).add(Arrays.asList(rhs));
        }

        Set<String> getUnaryProducers(String terminal) {
            Set<String> result = new HashSet<>();
            for (var entry : rules.entrySet())
                for (var prod : entry.getValue())
                    if (prod.size() == 1 && prod.get(0).equals(terminal))
                        result.add(entry.getKey());
            return result;
        }

        Set<String> getBinaryProducers(String a, String b) {
            Set<String> result = new HashSet<>();
            for (var entry : rules.entrySet())
                for (var prod : entry.getValue())
                    if (prod.size() == 2 && prod.get(0).equals(a) && prod.get(1).equals(b))
                        result.add(entry.getKey());
            return result;
        }

        Set<String> closure(Set<String> symbols) {
            Set<String> closure = new HashSet<>(symbols);
            boolean changed;
            do {
                changed = false;
                for (var entry : rules.entrySet()) {
                    for (var prod : entry.getValue()) {
                        if (prod.size() == 1 && closure.contains(prod.get(0)) && closure.add(entry.getKey()))
                            changed = true;
                    }
                }
            } while (changed);
            return closure;
        }
    }

    public static void main(String[] args) {
        Grammar grammar = new Grammar();

        // CNF grammar
        grammar.addRule("S", "A");
        grammar.addRule("A", "C");
        grammar.addRule("A", "A", "X");
        grammar.addRule("X", "B", "A");
        grammar.addRule("B", "+");
        grammar.addRule("C", "a");

        String[] input = {"a", "+", "a", "+", "a"};
        int n = input.length;

        @SuppressWarnings("unchecked")
        Set<String>[][] table = new HashSet[n][n + 1];

        System.out.println("\n--- Derivazioni CYK ---");

        // Inizializzazione per terminali
        for (int i = 0; i < n; i++) {
            Set<String> initial = grammar.getUnaryProducers(input[i]);
            table[i][i + 1] = grammar.closure(initial);
            System.out.println("table[" + i + "][" + (i + 1) + "] ← " + table[i][i + 1] + " (per '" + input[i] + "')");
        }

        // Riempimento tabella CYK
        for (int span = 2; span <= n; span++) {
            for (int start = 0; start <= n - span; start++) {
                int end = start + span;
                table[start][end] = new HashSet<>();
                for (int split = start + 1; split < end; split++) {
                    for (String left : table[start][split])
                        for (String right : table[split][end]) {
                            Set<String> combined = grammar.closure(grammar.getBinaryProducers(left, right));
                            if (!combined.isEmpty()) {
                                System.out.println("table[" + start + "][" + end + "] ← " + combined + " (da " + left + "+" + right + ")");
                                table[start][end].addAll(combined);
                            }
                        }
                }
            }
        }

        // Output tabella
        System.out.println("\n--- Tabella CYK ---");
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                System.out.println("table[" + i + "][" + j + "] = " + table[i][j]);

        boolean accepted = table[0][n].contains("S");
        int derivations = countDerivations(grammar, table, 0, n, "S");
        System.out.println("\nAccepted: " + accepted);
        System.out.println("Number of derivations for 'S': " + derivations);
    }

    static int countDerivations(Grammar grammar, Set<String>[][] table, int i, int j, String symbol) {
        if (!table[i][j].contains(symbol)) return 0;
        if (j == i + 1) return 1;

        int count = 0;

        // Produzioni unarie
        for (var prod : grammar.rules.getOrDefault(symbol, List.of())) {
            if (prod.size() == 1) {
                String next = prod.get(0);
                if (table[i][j].contains(next)) {
                    count += countDerivations(grammar, table, i, j, next);
                }
            }
        }

        // Produzioni binarie
        for (int k = i + 1; k < j; k++) {
            for (String B : table[i][k])
                for (String C : table[k][j])
                    for (var prod : grammar.rules.getOrDefault(symbol, List.of()))
                        if (prod.size() == 2 && prod.get(0).equals(B) && prod.get(1).equals(C))
                            count += countDerivations(grammar, table, i, k, B) * countDerivations(grammar, table, k, j, C);
        }

        return count;
    }
}
