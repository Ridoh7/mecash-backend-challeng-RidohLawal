CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE accounts (
    id BIGSERIAL PRIMARY KEY,
    account_number VARCHAR(20) UNIQUE NOT NULL,
    currency VARCHAR(1) NOT NULL,
    balance NUMERIC(19,2) NOT NULL DEFAULT 0,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_account_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
);

CREATE TABLE transactions (
    id BIGSERIAL PRIMARY KEY,
    sender_account_id BIGINT NOT NULL,
    receiver_account_id BIGINT NOT NULL,
    amount_sent NUMERIC(19,2) NOT NULL,
    amount_received NUMERIC(19,2) NOT NULL,
    exchange_rate NUMERIC(19,6) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_sender
        FOREIGN KEY (sender_account_id)
        REFERENCES accounts(id),

    CONSTRAINT fk_receiver
        FOREIGN KEY (receiver_account_id)
        REFERENCES accounts(id)
);