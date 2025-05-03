#!/usr/bin/env python3

import os
import requests
from pwn import *
import logging

logging.disable()

# Per le challenge web
URL = os.environ.get("URL", "http://todo.challs.todo.it")
if URL.endswith("/"):
    URL = URL[:-1]

# Se challenge tcp
HOST = os.environ.get("HOST", "todo.challs.todo.it")
PORT = int(os.environ.get("PORT", 34001))


r = remote(HOST, PORT)

for c in [b"a+a+a", b"!b&b", b"fctfctcec"]:
    r.sendline(c)

txt = r.recvall().split(b"\n")[-2].decode()

print(txt)
