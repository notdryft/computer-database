CREATE TABLE company (
  id   BIGINT       NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  CONSTRAINT pk_company PRIMARY KEY (id))
;

CREATE TABLE computer (
  id           BIGINT       NOT NULL AUTO_INCREMENT,
  name         VARCHAR(255) NOT NULL,
  introduced   DATE,
  discontinued DATE,
  company_id   BIGINT,
  CONSTRAINT pk_computer PRIMARY KEY (id))
;

ALTER TABLE computer ADD CONSTRAINT fk_computer_company_1 FOREIGN KEY (company_id) REFERENCES company (id)
  ON DELETE RESTRICT
  ON UPDATE RESTRICT;

CREATE INDEX ix_computer_company_1 ON computer (company_id);
