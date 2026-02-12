-- DROP SCHEMA public;

CREATE SCHEMA public AUTHORIZATION pg_database_owner;

COMMENT ON SCHEMA public IS 'standard public schema';

-- DROP SEQUENCE category_dict_category_id_seq;

CREATE SEQUENCE category_dict_category_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE category_dict_category_id_seq OWNER TO testuser;
GRANT ALL ON SEQUENCE category_dict_category_id_seq TO testuser;

-- DROP SEQUENCE category_type_s_type_seq;

CREATE SEQUENCE category_type_s_type_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE category_type_s_type_seq OWNER TO testuser;
GRANT ALL ON SEQUENCE category_type_s_type_seq TO testuser;

-- DROP SEQUENCE check_acc_dict_account_type_id_seq;

CREATE SEQUENCE check_acc_dict_account_type_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE check_acc_dict_account_type_id_seq OWNER TO testuser;
GRANT ALL ON SEQUENCE check_acc_dict_account_type_id_seq TO testuser;

-- DROP SEQUENCE check_account_cheack_account_id_seq;

CREATE SEQUENCE check_account_cheack_account_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE check_account_cheack_account_id_seq OWNER TO testuser;
GRANT ALL ON SEQUENCE check_account_cheack_account_id_seq TO testuser;

-- DROP SEQUENCE check_account_check_account_id_seq;

CREATE SEQUENCE check_account_check_account_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE check_account_check_account_id_seq OWNER TO testuser;
GRANT ALL ON SEQUENCE check_account_check_account_id_seq TO testuser;

-- DROP SEQUENCE periodicity_type_periodicity_id_seq;

CREATE SEQUENCE periodicity_type_periodicity_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE periodicity_type_periodicity_id_seq OWNER TO testuser;
GRANT ALL ON SEQUENCE periodicity_type_periodicity_id_seq TO testuser;

-- DROP SEQUENCE s_category_s_type_seq;

CREATE SEQUENCE s_category_s_type_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE s_category_s_type_seq OWNER TO testuser;
GRANT ALL ON SEQUENCE s_category_s_type_seq TO testuser;

-- DROP SEQUENCE subj_s_id_seq;

CREATE SEQUENCE subj_s_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE subj_s_id_seq OWNER TO testuser;
GRANT ALL ON SEQUENCE subj_s_id_seq TO testuser;

-- DROP SEQUENCE subj_s_id_seq1;

CREATE SEQUENCE subj_s_id_seq1
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE subj_s_id_seq1 OWNER TO testuser;
GRANT ALL ON SEQUENCE subj_s_id_seq1 TO testuser;

-- DROP SEQUENCE subj_s_type_seq;

CREATE SEQUENCE subj_s_type_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START 1
	CACHE 1
	NO CYCLE;

-- Permissions

ALTER SEQUENCE subj_s_type_seq OWNER TO testuser;
GRANT ALL ON SEQUENCE subj_s_type_seq TO testuser;
-- public.category_dict definition

-- Drop table

-- DROP TABLE category_dict;

CREATE TABLE category_dict (
	category_id bigserial NOT NULL, -- Уникальный ID. Внешняя ссылка на справочник категорий
	category_type varchar(32) COLLATE "ru-RU-x-icu" NOT NULL, -- Название типа категории
	category_type_desc text NULL, -- Подробное описание категории
	CONSTRAINT category_dict_pk PRIMARY KEY (category_id)
);
COMMENT ON TABLE public.category_dict IS 'Справочник категорий операций';

-- Column comments

COMMENT ON COLUMN public.category_dict.category_type IS 'Название типа категории';
COMMENT ON COLUMN public.category_dict.category_type_desc IS 'Подробное описание категории';
COMMENT ON COLUMN public.category_dict.category_id IS 'Уникальный ID. Внешняя ссылка на справочник категорий';

-- Permissions

ALTER TABLE category_dict OWNER TO testuser;
GRANT ALL ON TABLE category_dict TO testuser;


-- public.check_acc_dict definition

-- Drop table

-- DROP TABLE check_acc_dict;

CREATE TABLE check_acc_dict (
	account_type_id bigserial NOT NULL, -- Уникальный ID счета. Ссылка на справочник счетов
	account_type_name varchar(64) NOT NULL, -- Название счета для операций бюджета
	account_type_desc text COLLATE "ru-RU-x-icu" NULL, -- Подробное описание аккаунта
	CONSTRAINT acc_type_pk PRIMARY KEY (account_type_id)
);
COMMENT ON TABLE public.check_acc_dict IS 'Справочник счетов для операций';

-- Column comments

COMMENT ON COLUMN public.check_acc_dict.account_type_name IS 'Название счета для операций бюджета';
COMMENT ON COLUMN public.check_acc_dict.account_type_desc IS 'Подробное описание аккаунта';
COMMENT ON COLUMN public.check_acc_dict.account_type_id IS 'Уникальный ID счета. Ссылка на справочник счетов';

-- Permissions

ALTER TABLE check_acc_dict OWNER TO testuser;
GRANT ALL ON TABLE check_acc_dict TO testuser;


-- public.periodicity_type definition

-- Drop table

-- DROP TABLE periodicity_type;

CREATE TABLE periodicity_type (
	periodicity_id bigserial NOT NULL, -- Уникальный ID. Внешняя ссылка на типы счетов
	period_name varchar(32) COLLATE "ru-RU-x-icu" NULL, -- Название периода - "Ежемесячно"
	period_code varchar(16) NOT NULL, -- Системное название - monthly
	sort_order int4 NOT NULL, -- Порядок в выпадающем списке
	CONSTRAINT periodicity_type_pk PRIMARY KEY (periodicity_id)
);
COMMENT ON TABLE public.periodicity_type IS 'Типы переодичности операций';

-- Column comments

COMMENT ON COLUMN public.periodicity_type.period_name IS 'Название периода - "Ежемесячно"';
COMMENT ON COLUMN public.periodicity_type.period_code IS 'Системное название - monthly';
COMMENT ON COLUMN public.periodicity_type.sort_order IS 'Порядок в выпадающем списке';
COMMENT ON COLUMN public.periodicity_type.periodicity_id IS 'Уникальный ID. Внешняя ссылка на типы счетов';

-- Permissions

ALTER TABLE periodicity_type OWNER TO testuser;
GRANT ALL ON TABLE periodicity_type TO testuser;


-- public.category_type definition

-- Drop table

-- DROP TABLE category_type;

CREATE TABLE category_type (
	s_type bigserial NOT NULL, -- Уникальный ID. Внешняя ссылка на типы категорий
	category_name varchar(32) COLLATE "ru-RU-x-icu" NOT NULL, -- Название категории
	category_code varchar(32) COLLATE "en-US-x-icu" NOT NULL, -- Системный код категории
	budget_limit float8 NULL, -- Лимит по категории (например, на месяц)
	is_active bool NOT NULL, -- Активна ли категория
	category_desc text COLLATE "ru-RU-x-icu" NULL, -- Пояснение к категории
	category_id int8 NOT NULL, -- Ссылка на справочник категорий операций
	CONSTRAINT s_type_pk PRIMARY KEY (s_type),
	CONSTRAINT fk_cat_categid FOREIGN KEY (category_id) REFERENCES category_dict(category_id) ON DELETE RESTRICT
);
COMMENT ON TABLE public.category_type IS 'Категории операций';

-- Column comments

COMMENT ON COLUMN public.category_type.category_name IS 'Название категории';
COMMENT ON COLUMN public.category_type.category_code IS 'Системный код категории';
COMMENT ON COLUMN public.category_type.budget_limit IS 'Лимит по категории (например, на месяц)';
COMMENT ON COLUMN public.category_type.is_active IS 'Активна ли категория';
COMMENT ON COLUMN public.category_type.category_desc IS 'Пояснение к категории';
COMMENT ON COLUMN public.category_type.category_id IS 'Ссылка на справочник категорий операций';
COMMENT ON COLUMN public.category_type.s_type IS 'Уникальный ID. Внешняя ссылка на типы категорий';

-- Permissions

ALTER TABLE category_type OWNER TO testuser;
GRANT ALL ON TABLE category_type TO testuser;


-- public.check_account definition

-- Drop table

-- DROP TABLE check_account;

CREATE TABLE check_account (
	check_account_id bigserial NOT NULL, -- Уникальный ID. Внешняя ссылка на типы счетов
	account_name varchar(32) COLLATE "ru-RU-x-icu" NOT NULL, -- Название счета для пользователя
	account_type_id int8 NOT NULL, -- Внешняя ссылка на справочник счетов
	acc_currency varchar(3) NOT NULL, -- Валюта счета
	acc_balance float8 NOT NULL, -- Текущий баланс счета
	acc_initial_balance float8 NOT NULL, -- Начальный баланс счета
	acc_owner_name varchar(64) COLLATE "ru-RU-x-icu" NOT NULL, -- Имя владельца счета
	acc_owner_id varchar(64) NULL, -- ID владельца счета
	bank_name varchar(64) COLLATE "ru-RU-x-icu" NULL, -- Название банка
	acc_is_active bool NOT NULL, -- Активен ли счет
	acc_is_default bool NOT NULL, -- Счет по умолчанию для операций
	credit_limit float8 NULL, -- Кредитный лимит для кредитных карт
	CONSTRAINT check_account_pk PRIMARY KEY (check_account_id),
	CONSTRAINT fk_check_acctypeid FOREIGN KEY (account_type_id) REFERENCES check_acc_dict(account_type_id) ON DELETE RESTRICT
);
COMMENT ON TABLE public.check_account IS 'Типы счетов';

-- Column comments

COMMENT ON COLUMN public.check_account.account_name IS 'Название счета для пользователя';
COMMENT ON COLUMN public.check_account.account_type_id IS 'Внешняя ссылка на справочник счетов';
COMMENT ON COLUMN public.check_account.acc_currency IS 'Валюта счета';
COMMENT ON COLUMN public.check_account.acc_balance IS 'Текущий баланс счета';
COMMENT ON COLUMN public.check_account.acc_initial_balance IS 'Начальный баланс счета';
COMMENT ON COLUMN public.check_account.acc_owner_name IS 'Имя владельца счета';
COMMENT ON COLUMN public.check_account.acc_owner_id IS 'ID владельца счета';
COMMENT ON COLUMN public.check_account.bank_name IS 'Название банка';
COMMENT ON COLUMN public.check_account.acc_is_active IS 'Активен ли счет';
COMMENT ON COLUMN public.check_account.acc_is_default IS 'Счет по умолчанию для операций';
COMMENT ON COLUMN public.check_account.credit_limit IS 'Кредитный лимит для кредитных карт';
COMMENT ON COLUMN public.check_account.check_account_id IS 'Уникальный ID. Внешняя ссылка на типы счетов';

-- Permissions

ALTER TABLE check_account OWNER TO testuser;
GRANT ALL ON TABLE check_account TO testuser;


-- public.subj definition

-- Drop table

-- DROP TABLE subj;

CREATE TABLE subj (
	s_id bigserial NOT NULL, -- Уникальный идентификатор статьи бюджета.
	s_name varchar(32) COLLATE "ru-RU-x-icu" NOT NULL, -- Наименование статьи бюджета.
	amount float8 NOT NULL, -- Сумма
	currency varchar(3) NOT NULL, -- Валюта
	operation_date date NOT NULL, -- Дата проведения финансовой операции
	description varchar(32) COLLATE "ru-RU-x-icu" NULL, -- Подробное описание операции
	tags text COLLATE "ru-RU-x-icu" NULL, -- Массив тегов для категоризации
	created_at timestamp NOT NULL, -- Дата и время создания записи
	created_by varchar(64) NOT NULL, -- Идентификатор пользователя, который создал запись
	updated_at timestamp NULL, -- Дата и время последнего обновления записи
	updated_by varchar(64) NULL, -- Идентификатор пользователя, обновившего запись
	check_account_id int8 NOT NULL, -- Ссылка на типы счетов
	periodicity_id int8 NULL, -- Ссылка на типы периодичности
	s_type int8 NOT NULL, -- Ссылка на типы категорий
	CONSTRAINT subj_id_pk PRIMARY KEY (s_id),
	CONSTRAINT subj_unique UNIQUE (s_type),
	CONSTRAINT fk_subj_chckaccid FOREIGN KEY (check_account_id) REFERENCES check_account(check_account_id) ON DELETE RESTRICT,
	CONSTRAINT fk_subj_period_id FOREIGN KEY (periodicity_id) REFERENCES periodicity_type(periodicity_id) ON DELETE RESTRICT,
	CONSTRAINT fk_subj_stype FOREIGN KEY (s_type) REFERENCES category_type(s_type) ON DELETE RESTRICT
);
COMMENT ON TABLE public.subj IS 'Статья бюджета';

-- Column comments

COMMENT ON COLUMN public.subj.s_id IS 'Уникальный идентификатор статьи бюджета.';
COMMENT ON COLUMN public.subj.s_name IS 'Наименование статьи бюджета.';
COMMENT ON COLUMN public.subj.amount IS 'Сумма';
COMMENT ON COLUMN public.subj.currency IS 'Валюта';
COMMENT ON COLUMN public.subj.operation_date IS 'Дата проведения финансовой операции';
COMMENT ON COLUMN public.subj.description IS 'Подробное описание операции';
COMMENT ON COLUMN public.subj.tags IS 'Массив тегов для категоризации';
COMMENT ON COLUMN public.subj.created_at IS 'Дата и время создания записи';
COMMENT ON COLUMN public.subj.created_by IS 'Идентификатор пользователя, который создал запись';
COMMENT ON COLUMN public.subj.updated_at IS 'Дата и время последнего обновления записи';
COMMENT ON COLUMN public.subj.updated_by IS 'Идентификатор пользователя, обновившего запись';
COMMENT ON COLUMN public.subj.check_account_id IS 'Ссылка на типы счетов';
COMMENT ON COLUMN public.subj.periodicity_id IS 'Ссылка на типы периодичности';
COMMENT ON COLUMN public.subj.s_type IS 'Ссылка на типы категорий';

-- Permissions

ALTER TABLE subj OWNER TO testuser;
GRANT ALL ON TABLE subj TO testuser;




-- Permissions

GRANT ALL ON SCHEMA public TO pg_database_owner;
