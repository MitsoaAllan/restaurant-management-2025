BEGIN;

CREATE TYPE movement as ENUM('IN','OUT');

COMMIT;

CREATE TABLE IF NOT EXISTS move(
    id_move BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    move_type movement NOT NULL,
    quantity DECIMAL NOT NULL,
    unit unit NOT NULL,
    update_move DATE NOT NULL
);