FROM tomee:8-jre-8.0.6-plus

RUN wget https://jdbc.postgresql.org/download/postgresql-42.2.5.jar\
        && mv postgresql-42.2.5.jar /usr/local/tomee/lib

COPY docker/datasource-config.xml /usr/local/tomee/conf/context.xml

COPY target/wslab-server.war /usr/local/tomee/webapps/