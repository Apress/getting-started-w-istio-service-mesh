#!/bin/sh
nohup python app.py &
tail -f /dev/stdout
