CREATE TABLE if not exists clientes
(
    id     serial primary key not null,
    nome   varchar(100)       not null,
    limite integer            not null,
    saldo  integer            not null
);

create table if not exists transacoes
(
    id           serial primary key not null,
    tipo         char(1)            not null,
    descricao    varchar(10)        not null,
    valor        integer            not null,
    cliente_id   integer            not null,
    realizada_em timestamp          not null default now()
);

create index if not exists idx_cliente_id on transacoes (cliente_id);

INSERT INTO clientes (id, nome, limite, saldo)
VALUES (1, 'o barato sai caro', 1000 * 100, 0),
       (2, 'zan corp ltda', 800 * 100, 0),
       (3, 'les cruders', 10000 * 100, 0),
       (4, 'padaria joia de cocaia', 100000 * 100, 0),
       (5, 'kid mais', 5000 * 100, 0);

create or replace function atualizar_saldo()
returns trigger as $$

DECLARE
    v_saldo INTEGER;
    v_limite INTEGER;
BEGIN
    SELECT saldo, limite INTO v_saldo, v_limite
    FROM clientes WHERE id = NEW.cliente_id
        FOR UPDATE;

    IF NEW.tipo = 'd' AND (v_saldo - NEW.valor) < -v_limite THEN
        RAISE EXCEPTION 'Débito excede o limite do cliente';
    END IF;

    IF NEW.tipo = 'd' THEN
        UPDATE clientes SET saldo = saldo - NEW.valor WHERE id = NEW.cliente_id;
    ELSE
        UPDATE clientes SET saldo = saldo + NEW.valor WHERE id = NEW.cliente_id;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER atualizar_saldo_trigger
    AFTER INSERT ON transacoes
    FOR EACH ROW
EXECUTE FUNCTION atualizar_saldo();


select *
from clientes;

select *
from transacoes;