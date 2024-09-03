CREATE SCHEMA IF NOT EXISTS solva;

CREATE TABLE IF NOT EXISTS solva.limit (
                                           id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                                           limit_sum DOUBLE PRECISION,
                                           created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS solva.account (
                                             id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                                             account_number BIGINT NOT NULL UNIQUE,
                                             limit_id BIGINT,
                                             CONSTRAINT fk_limit FOREIGN KEY (limit_id) REFERENCES solva.limit(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS solva.transaction (
                                                 id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                                                 account_from BIGINT,
                                                 account_to BIGINT,
                                                 sum DOUBLE PRECISION,
                                                 currency_short_name VARCHAR NOT NULL,
                                                 expense_category VARCHAR NOT NULL,
                                                 limit_exceeded BOOLEAN,
                                                 created_at TIMESTAMP,
                                                 CONSTRAINT fk_account_from FOREIGN KEY (account_from) REFERENCES solva.account(account_number),
                                                 CONSTRAINT fk_account_to FOREIGN KEY (account_to) REFERENCES solva.account(account_number)
);
