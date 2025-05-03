#!/usr/bin/env python3

import os
import requests
import random
import string

import logging

logging.disable()

# Per le challenge web
URL = os.environ.get("URL", "http://todo.challs.todo.it")
if URL.endswith("/"):
    URL = URL[:-1]


def random_string(length):
    return "".join(
        random.choice(string.ascii_letters + string.digits) for _ in range(length)
    )


s = requests.Session()


def create_language(data):
    s.post(URL + "/grammar_generator.php", data=data)

print("=== NOT flag\n"+ s.get(URL + "/download.php?id=3").text)


create_language({"R": "on", "languageName": random_string(10)})
print("=== Hint 1\n" + s.get(URL + "/download.php?id=1").text)

create_language(
    {
        "languageName": random_string(10),
        "A": "on",
        "H": "on",
        "L": "on",
        "S": "on",
        "W": "on",
    }
)
print("=== Hint 2\n" + s.get(URL + "/download.php?id=1").text)

create_language(
    {
        "languageName": random_string(10),
        "B": "on",
        "G": "on",
        "H": "on",
        "K": "on",
        "W": "on",
    }
)

print("=== Hint 3\n" + s.get(URL + "/download.php?id=1").text)
print("=== Log \n" + s.get(URL + "/download.php?id=2").text)

create_language(
    {
        "languageName": random_string(10),
        "O": "on",
        "G": "on",
        "I": "on",
        "M": "on",
        "Q": "on",
        "S": "on",
    }
)

print("=== Log 2\n" + s.get(URL + "/download.php?id=2").text)


create_language(
    {
        "languageName": random_string(10),
        "N": "on",
        "G": "on",
        "O": "on",
        "T": "on",
        "U": "on",
        "V": "on",
    }
)

create_language(
    {
        "languageName": random_string(10),
        "O": "on",
        "G": "on",
        "I": "on",
        "M": "on",
        "Q": "on",
        "S": "on",
    }
)


print("=== Log 3\n" + s.get(URL + "/download.php?id=2").text)


create_language(
    {
        "languageName": random_string(10),
        "Y": "on",
        "D": "on",
        "G": "on",
        "P": "on",
        "Q": "on",
        "A": "on",
    }
)

print("=== Flag \n" + s.get(URL + "/download.php?id=3").text)
