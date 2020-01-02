from flask import Flask
import datetime
import time
import threading
import os

time.sleep(60)
app = Flask(__name__)

@app.route("/")
def main():
    currentDT = datetime.datetime.now()
    return "Welcome user! current time is " + str(currentDT)

@app.route("/health")
def health():
    return "OK"

def exit_after():
    time.sleep(60)
    os._exit(1)

exit_thread = threading.Thread(target=exit_after)
exit_thread.start()

if __name__ == "__main__":
    app.run(host='0.0.0.0')
