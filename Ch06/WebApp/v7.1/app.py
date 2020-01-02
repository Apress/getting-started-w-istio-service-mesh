from flask import Flask
import datetime
import time
import os
import random

app = Flask(__name__)

global status
status = 200

@app.route("/")
def main():
    currentDT = datetime.datetime.now()
    if (status == 200):
        time.sleep(0.5)
    return "[{}]Welcome user! current time is {} ".format(os.environ['VERSION'],str(currentDT)), status

@app.route("/health")
def health():
    return "OK"

@app.route("/addfault")
def addfault():
    global status
    if (status == 200):
        status = 503
    else:
        status = 200
    return "OK"


if __name__ == "__main__":
    app.run(host='0.0.0.0')
