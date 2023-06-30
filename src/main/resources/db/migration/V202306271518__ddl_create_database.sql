CREATE TABLE person
(
    idt_person BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT "[PK] Primary Key",
    des_name VARCHAR(500) NOT NULL COMMENT "[PII] Name of person",
    des_email VARCHAR(500) NOT NULL COMMENT "[PII] Email of person",
    des_extra_information TEXT COMMENT "[PII] Extra data abaout person",
    dat_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT "[NOT_SENSITIVE_DATA] Date when this person was inserted",
    dat_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT "[NOT_SENSITIVE_DATA] Date when this person was updated",
    PRIMARY KEY (idt_person)
);

CREATE INDEX person_idx01 ON person (des_name);
CREATE INDEX person_idx02 ON person (des_email);

CREATE TABLE usersys
(
    idt_user BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT "[PK] Primary Key",
    des_name VARCHAR(500) NOT NULL COMMENT "[PII] Name of user",
    des_username VARCHAR(500) NOT NULL COMMENT "[PII] Username of person",
    des_password TEXT COMMENT "[PII] User password",
    des_profile VARCHAR(50) COMMENT "[NOT_SENSITIVE_DATA] User profile",
    dat_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT "[NOT_SENSITIVE_DATA] When this data was inserted",
    dat_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT "[NOT_SENSITIVE_DATA] When this data was updated",
    PRIMARY KEY (idt_user)
);

CREATE INDEX usersys_idx01 ON usersys (des_name);

CREATE TABLE book
(
    idt_book BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT "[PK] Primary Key",
    des_name VARCHAR(500) NOT NULL COMMENT "[NOT_SENSITIVE_DATA] Name of book",
    des_code VARCHAR(500) NOT NULL COMMENT "[NOT_SENSITIVE_DATA] Code of book",
    num_quantity INT UNSIGNED DEFAULT 1 COMMENT "[NOT_SENSITIVE_DATA] Quantity of this book",
    num_volume INT UNSIGNED DEFAULT 1 COMMENT "[NOT_SENSITIVE_DATA] Book volume",
    dat_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT "[NOT_SENSITIVE_DATA] When this data was inserted",
    dat_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT "[NOT_SENSITIVE_DATA] When this data was updated",
    PRIMARY KEY (idt_book)
);

CREATE INDEX book_idx01 ON book (des_name);
CREATE INDEX book_idx02 ON book (des_code);
CREATE INDEX book_idx03 ON book (num_quantity);

CREATE TABLE loan
(
    idt_loan BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT "[PK] Primary Key",
    idt_user BIGINT UNSIGNED NOT NULL COMMENT "[FK] User - manager",
    idt_person BIGINT UNSIGNED NOT NULL COMMENT "[FK] Person - requester",
    dat_return_at DATE COMMENT "[NOT_SENSITIVE_DATA] Moment to book return",
    dat_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT "[NOT_SENSITIVE_DATA] When this data was inserted",
    dat_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT "[NOT_SENSITIVE_DATA] When this data was updated",
    PRIMARY KEY (idt_loan)
);

CREATE INDEX loan_idx01 ON loan (idt_person);
CREATE INDEX loan_idx02 ON loan (dat_return_at);

ALTER TABLE loan ADD CONSTRAINT fk_loan_user FOREIGN KEY (idt_user) REFERENCES usersys(idt_user);
ALTER TABLE loan ADD CONSTRAINT fk_loan_person FOREIGN KEY (idt_person) REFERENCES person(idt_person);

CREATE TABLE log
(
    idt_log BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT "[PK] Primary Key",
    idt_user BIGINT UNSIGNED NOT NULL COMMENT "[FK] User - manager",
    des_action VARCHAR(255) NOT NULL COMMENT "[NOT_SENSITIVE_DATA] Action",
    json_old_entity JSON NOT NULL COMMENT "[NOT_SENSITIVE_DATA] Old entity",
    json_new_entity JSON NOT NULL COMMENT "[NOT_SENSITIVE_DATA] New entity",
    dat_created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT "[NOT_SENSITIVE_DATA] When this data was inserted",
    dat_updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT "[NOT_SENSITIVE_DATA] When this data was updated",
    PRIMARY KEY (idt_log)
);

CREATE INDEX log_idx00 ON log (dat_created_at);
CREATE INDEX log_idx01 ON log (des_action, dat_created_at);
CREATE INDEX log_idx02 ON log (idt_user);

ALTER TABLE log ADD CONSTRAINT fk_log_user FOREIGN KEY (idt_user) REFERENCES usersys(idt_user);