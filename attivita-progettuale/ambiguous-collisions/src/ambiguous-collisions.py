#!/usr/bin/env python3

import random
import os

grammars = {
    1: ["S -> A", "A -> C | ABA", "B -> +", "C -> a"],
    2: [
        "S -> A",  
        "A -> C | BC",  
        "B -> !",
        "C -> E | CDE",  
        "D -> & | |", 
        "E -> F | BF",
        "F -> H | FGH",  
        "G -> < | > | =",  
        "H -> J | HIJ",  
        "I -> * | /",  
        "J -> L | JKL",  
        "K -> + | -",  
        "L -> b",
    ],
    3: [
        "S -> A",
        "A -> B | C | D",
        "B -> f D t A | f D t A e A",
        "C -> w D d A",
        "D -> F | EF",
        "E -> !",
        "F -> H | FGH",
        "G -> & | |",
        "H -> J | HIJ",
        "I -> < | > | =",
        "J -> L | JKL",
        "K -> * | /",
        "L -> N | LMN",
        "M -> + | -",
        "N -> c",
    ],
}
allowed_characters = {
    1: {"a", "+"},
    2: {"b", "!", "&", "|", "<", ">", "<", ">", "=", "*", "/", "+", "-"},
    3: {
        "c",
        "d",
        "e",
        "f",
        "t",
        "w",
        "!",
        "&",
        "|",
        "<",
        ">",
        "<",
        ">",
        "=",
        "*",
        "/",
        "+",
        "-",
    },
}
correct_responses = {1: ["a+a+a"], 2: ["!b"], 3: ["fctfctcec"]}
lengths_responses = {1: 5, 2: 2, 3: 9}

flag = os.getenv("FLAG", "UlisseCTF{REDACTED}")


def send_introduction():
    print("\n=== Welcome to Ambiguous Collisions ===\n")
    print("I hate it when people don't understand me!")
    print("All problems would be solved if we understood each other more.")
    print("I'm not sure, but I think it's all the language's fault...")


def send_grammar(level):
    print("\nWhy don't we try to use this brand new one:")
    for production in grammars[level]:
        print(production)


def verify(level, attempt):
    attempt = attempt.replace(" ", "")
    if not all(char in allowed_characters[level] for char in attempt):
        print("Mmm... I don't recognize all the symbols you used, you know?")
        return False
    if len(attempt) > lengths_responses[level]:
        print("Mmm... I'm bored.")
        return False
    if attempt not in correct_responses.get(level):
        print("Mmm... I see no ambiguity!")
        return False
    print("Mmm... Well, actually, I'm not sure this time...")
    return True


def handle_level(level):
    send_grammar(level)
    ambiguous = False
    attempts = 0
    while attempts < 10:
        print("Can you express yourself in such a way that I don't understand you?")
        print("(Please write a short phrase so that I don't get bored reading)")
        attempt = input().strip()
        ambiguous = verify(level, attempt)
        if ambiguous:
            return True
        attempts += 1
    print("Looks like there is no way...")
    return False


def send_conclusion():
    print("\nExtending an existing language is hard!")
    print("Any ideas for improvement?")
    print(f"\n{flag}")


def main():
    send_introduction()
    for level in range(1, 4):
        if not handle_level(level):
            return
    send_conclusion()


if __name__ == "__main__":
    main()
