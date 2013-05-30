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

### Runtime

- mysql:mysql-connection-java:5.1.25 @ [Central Repository](http://search.maven.org/#artifactdetails%7Cmysql%7Cmysql-connector-java%7C5.1.25%7Cjar)

### Compile

- javax.servlet:jstl:1.2 @ [Central Repository](http://search.maven.org/#artifactdetails%7Cjavax.servlet%7Cjstl%7C1.2%7Cjar)
- org.slf4j:slf4j-api:1.7.5 @ [Central Repository](http://search.maven.org/#artifactdetails%7Corg.slf4j%7Cslf4j-api%7C1.7.5%7Cjar) (Not sure)
- ch.qos.logback:logback-classic:1.0.13 @ [Central Repository](http://search.maven.org/#artifactdetails%7Cch.qos.logback%7Clogback-classic%7C1.0.13%7Cjar) (Not sure)

### Provided

- JBoss Application Server 7 or Apache Tomcat 7 librairies
