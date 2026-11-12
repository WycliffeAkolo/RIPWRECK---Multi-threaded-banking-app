#!/usr/bin/env python3
import subprocess

for _ in range(1000):
    result = subprocess.run(
        ['java', 'Main'],
        stdout=subprocess.PIPE,
        stderr=subprocess.STDOUT,
    )
    data = result.stdout
    if b'-' in data:
        print(data.decode('ascii'))
        break