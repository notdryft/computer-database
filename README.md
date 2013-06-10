# Computer-database: Spring Version

Formation project based on the play sample application

## Preparing the database and user

```SQL
CREATE DATABASE computerdb_development CHARACTER SET utf8;

CREATE USER computerdb@localhost identified by 'computerdb';

GRANT ALL ON computerdb_development.* TO computerdb@localhost;

FLUSH PRIVILEGES;
```

Then inject the scripts.

## Deployment

You need to do these two things:

1. Set a server inside Maven's `settings.xml` with the name `Tomcat7Server`
2. The user specified need to have the role `manager-script` in Tomcat7's `tomcat-users.xml`

Then you can either use `mvn install tomcat7:deploy` or `mvn install tomcat7:redeploy`.
