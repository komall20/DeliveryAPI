-- Table: public.order_delivery

-- DROP TABLE IF EXISTS public.order_delivery;

CREATE TABLE IF NOT EXISTS public.order_delivery
(
    id integer NOT NULL DEFAULT nextval('order_delivery_id_seq'::regclass),
    customer_id integer NOT NULL,
    store_id integer NOT NULL,
    order_id integer NOT NULL,
    image_id integer NOT NULL,
    image_code bytea NOT NULL,
    payment_status character varying(25) COLLATE pg_catalog."default" NOT NULL,
    delivery_status character varying(25) COLLATE pg_catalog."default" NOT NULL,
    pickup_date timestamp with time zone,
    CONSTRAINT order_delivery_pkey PRIMARY KEY (id),
    CONSTRAINT customer_order_store_unique UNIQUE (customer_id, order_id, store_id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.order_delivery
    OWNER to postgres;