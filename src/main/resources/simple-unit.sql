DROP TABLE IF EXISTS company;

CREATE TABLE company (
    company_id SERIAL,
    company_name VARCHAR(100),
    PRIMARY KEY (company_id)
);

INSERT INTO company (company_name) VALUES ('First Company');
INSERT INTO company (company_name) VALUES ('Second Company');
