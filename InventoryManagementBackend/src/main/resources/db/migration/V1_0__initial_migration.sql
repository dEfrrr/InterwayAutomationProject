CREATE TABLE product (
                         id BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT,
                         price DOUBLE PRECISION NOT NULL,
                         quantity_in_stock INTEGER NOT NULL,
                         category VARCHAR(255),
                         CONSTRAINT ch_price_non_negative CHECK ( price >= 0 ),
                         CONSTRAINT ch_quantity_non_negative CHECK ( quantity_in_stock >= 0 )
);
