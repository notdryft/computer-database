# Computer-database, jdk only version

Formation project based on the play sample application

## Preparing the database and user

```SQL
CREATE DATABASE computerdb_development CHARACTER SET utf8;

CREATE USER computerdb@localhost identified by 'computerdb';

GRANT ALL ON computerdb_development.* TO computerdb@localhost;

FLUSH PRIVILEGES;
```

Then inject the scripts.

## Dependencies

- javax.servlet:jstl:1.2 @ http://mvnrepository.com/artifact/javax.servlet/jstl/1.2
- mysql:mysql-connection-java:5.1.25 @ http://mvnrepository.com/artifact/mysql/mysql-connector-java/5.1.25