FROM python:3.7-alpine
COPY ./requirement.txt /app/requirement.txt
RUN wget -O /usr/local/bin/dumb-init https://github.com/Yelp/dumb-init/releases/download/v1.2.2/dumb-init_1.2.2_amd64
RUN chmod +x /usr/local/bin/dumb-init
WORKDIR /app
RUN pip install -r requirement.txt
COPY . /app
ARG ver=NA
ENV VERSION=$ver
#ENTRYPOINT ["python"]
#CMD [ "app.py" ]

ENTRYPOINT ["/bin/sh", "/app/start.sh"]
