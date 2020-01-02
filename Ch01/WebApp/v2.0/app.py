from flask import Flask
import datetime

app = Flask(__name__)

@app.route("/")
def main():
    currentDT = datetime.datetime.now()
    return "Welcome user! current time is " + str(currentDT)

if __name__ == "__main__":
    app.run(host='0.0.0.0')
