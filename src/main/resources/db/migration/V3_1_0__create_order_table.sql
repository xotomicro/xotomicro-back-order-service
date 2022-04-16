CREATE SEQUENCE order_id_seq;
CREATE TABLE "order"
(
    "id"                  BIGINT PRIMARY KEY DEFAULT nextval('order_id_seq'),
    "order_type"          VARCHAR(20) NOT NULL,
    "product_name"        VARCHAR(20) NOT NULL,
    "product_description" VARCHAR(20) NOT NULL
);