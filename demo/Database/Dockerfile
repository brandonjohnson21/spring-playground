FROM mysql:8
ENV MYSQL_ROOT_PASSWORD password
ENV MYSQL_DATABASE mydb
# File from the previous step
COPY setup.sql /docker-entrypoint-initdb.d
EXPOSE 3306
