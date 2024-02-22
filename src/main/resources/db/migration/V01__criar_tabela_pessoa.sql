CREATE SEQUENCE cliente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE cliente (
    id BIGINT NOT NULL DEFAULT nextval('cliente_seq'),
    nome character varying(60) not null
);


ALTER TABLE cliente ADD CONSTRAINT pk_cliente PRIMARY KEY (id);


---Tabela lançamento de pedido
CREATE SEQUENCE pedido_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE pedido (
    id BIGINT NOT NULL DEFAULT nextval('pedido_seq'),
    status char(1),
    valor_total double precision not null,
    data_pedido timestamp without time zone,
    id_cliente BIGINT NOT NULL

);

ALTER TABLE pedido ADD CONSTRAINT pk_pedido PRIMARY KEY (id);
ALTER TABLE ONLY pedido
    ADD CONSTRAINT fk_id_pedido_cliente FOREIGN KEY (id_cliente) REFERENCES public.cliente(id);



CREATE SEQUENCE produto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE produto (
    id BIGINT NOT NULL DEFAULT nextval('produto_seq'),
    nome character varying(60) not null,
    quantidade double precision not null,
    preco_produto double precision not null
   

);
ALTER TABLE produto ADD CONSTRAINT pk_produto PRIMARY KEY (id);



CREATE SEQUENCE item_pedido_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
CREATE TABLE item_pedido (
    id BIGINT NOT NULL DEFAULT nextval('item_pedido_seq'),
    quantidade double precision not null,
    preco_unitario double precision not null,
    preco_total double precision not null,
    id_produto BIGINT NOT NULL,
    id_pedido BIGINT NOT NULL

);

ALTER TABLE item_pedido ADD CONSTRAINT pk_item_pedido PRIMARY KEY (id);
ALTER TABLE ONLY item_pedido
    ADD CONSTRAINT fk_id_item_pedido_produto FOREIGN KEY (id_produto) REFERENCES public.produto(id);
ALTER TABLE ONLY item_pedido
    ADD CONSTRAINT fk_id_item_pedido_pedido FOREIGN KEY (id_pedido) REFERENCES public.pedido(id);