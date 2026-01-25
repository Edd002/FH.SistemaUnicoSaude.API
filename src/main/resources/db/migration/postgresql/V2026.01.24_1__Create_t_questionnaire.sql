create table public.t_questionnaire
(
    id                      int8         not null,
    created_in              timestamp(6) not null,
    created_by              varchar(255),
    deleted                 boolean      not null,
    deleted_in              timestamp(6),
    deleted_by              varchar(255),
    hash_id                 varchar(255) not null,
    updated_in              timestamp(6),
    updated_by              varchar(255),
    name                    varchar(255) not null,
    description             varchar(255) not null,
    profissional_cns        varchar(255) not null,
    cbo                     varchar(255) not null,
    cnes                    varchar(255) not null,
    ine                     varchar(255) not null,
    primary key (id)
);

create sequence public.sq_questionnaire start with 1 increment by 1;

CREATE UNIQUE INDEX T_QUESTIONNAIRE__HASH_ID_UK ON public.t_questionnaire (hash_id);
CREATE UNIQUE INDEX T_QUESTIONNAIRE__NAME_UK ON public.t_questionnaire (upper(name), deleted) WHERE deleted IS NULL OR deleted = false;