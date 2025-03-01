BEGIN;

CREATE TYPE movement as ENUM('IN','OUT');

COMMIT;

CREATE TABLE IF NOT EXISTS move(
    id_move BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_ingredient int REFERENCES ingredient(id_ingredient),
    move_type movement NOT NULL,
    quantity DECIMAL NOT NULL,
    unit unit NOT NULL,
    update_move DATE NOT NULL
);

INSERT INTO move (id_ingredient,move_type,quantity,unit,update_move)
VALUES
(4,'IN',50,'U','2025-02-01 08:00:00'),
(3,'IN',100,'U','2025-02-01 08:00:00'),
(2,'IN',20,'L','2025-02-01 08:00:00'),
(1,'IN',10000,'G','2025-02-01 08:00:00');
