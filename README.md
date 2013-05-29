Computer-database formation project
-----------------------------------

Preparing the database and user:

```SQL
CREATE DATABASE computerdb_development CHARACTER SET utf8;

CREATE USER computerdb@localhost identified by 'computerdb';

GRANT ALL ON computerdb_development.* TO computerdb@localhost;

FLUSH PRIVILEGES;
```

Then inject the scripts.