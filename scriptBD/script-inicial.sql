-- criar banco de dados -------------------------------------------------------
create database if not exists bd_sistema_api;


-- criar tabelas --------------------------------------------------------------
create table if not exists t_cliente (
	id_cliente int not null auto_increment,
	codigo int not null, 
	nome varchar(200) not null,
	primary key(id_cliente)
);


create table if not exists t_pedido (
	id_pedido int not null auto_increment, 
	numero_controle int not null, 
	data_cadastro date,
	nome varchar(200) not null, 
	valor double not null,
	quantidade int, 
	valor_total double not null,
	id_cliente int,
	primary key(id_pedido),
	foreign key (id_cliente) references t_cliente(id_cliente)
);


-- carga inicial de clientes --------------------------------------------------
insert into t_cliente (codigo, nome)
select 1, 'Cliente 1' from dual where not exists (select codigo from t_cliente where codigo = 1);

insert into t_cliente (codigo, nome)
select 2, 'Cliente 2' from dual where not exists (select codigo from t_cliente where codigo = 2);

insert into t_cliente (codigo, nome)
select 3, 'Cliente 3' from dual where not exists (select codigo from t_cliente where codigo = 3);

insert into t_cliente (codigo, nome)
select 4, 'Cliente 4' from dual where not exists (select codigo from t_cliente where codigo = 4);

insert into t_cliente (codigo, nome)
select 5, 'Cliente 5' from dual where not exists (select codigo from t_cliente where codigo = 5);

insert into t_cliente (codigo, nome)
select 6, 'Cliente 6' from dual where not exists (select codigo from t_cliente where codigo = 6);

insert into t_cliente (codigo, nome)
select 7, 'Cliente 7' from dual where not exists (select codigo from t_cliente where codigo = 7);

insert into t_cliente (codigo, nome)
select 8, 'Cliente 8' from dual where not exists (select codigo from t_cliente where codigo = 8);

insert into t_cliente (codigo, nome)
select 9, 'Cliente 9' from dual where not exists (select codigo from t_cliente where codigo = 9);

insert into t_cliente (codigo, nome)
select 10, 'Cliente 10' from dual where not exists (select codigo from t_cliente where codigo = 10);
