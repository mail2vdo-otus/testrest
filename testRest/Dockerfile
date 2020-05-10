FROM anapsix/alpine-java

MAINTAINER vorobiev.org
RUN apk --update add curl
COPY ./target/testRest-1.0-SNAPSHOT.jar ./opt/

ENTRYPOINT ["java"]

CMD ["-jar", "./opt/testRest-1.0-SNAPSHOT.jar"]

EXPOSE 8000