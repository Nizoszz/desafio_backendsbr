CREATE TABLE IF NOT EXISTS "operations" (
    id SERIAL PRIMARY KEY,
    user_document TEXT NOT NULL,
    credit_card_token TEXT NOT NULL,
    value INTEGER NOT NULL
);