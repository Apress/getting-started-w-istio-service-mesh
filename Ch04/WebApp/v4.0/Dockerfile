FROM python:3.7-alpine
COPY ./requirement.txt /app/requirement.txt
WORKDIR /app
RUN pip install -r requirement.txt
COPY . /app
ENTRYPOINT [ "python" ]
ARG ver=NA
ENV VERSION=$ver
CMD [ "app.py" ]

