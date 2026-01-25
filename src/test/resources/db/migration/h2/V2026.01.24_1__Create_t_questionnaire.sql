create table public.t_questionnaire
(
    id           int8         not null,
    created_in   timestamp(6) not null,
    created_by   varchar(255),
    deleted      boolean      not null,
    deleted_in   timestamp(6),
    deleted_by   varchar(255),
    hash_id      varchar(255) not null,
    updated_in   timestamp(6),
    updated_by   varchar(255),
    name         varchar(255) not null,
    description  varchar(255) not null,
    primary key (id)
);

create sequence public.sq_questionnaire start with 1 increment by 1;

ALTER TABLE public.t_questionnaire ADD NAME_UK_FIELD VARCHAR(255) AS (CASE deleted WHEN TRUE THEN NULL ELSE upper(name) END);

CREATE UNIQUE INDEX T_QUESTIONNAIRE__HASH_ID_UK ON public.t_questionnaire (hash_id);
CREATE UNIQUE INDEX T_QUESTIONNAIRE__NAME_UK ON public.t_questionnaire (NAME_UK_FIELD);