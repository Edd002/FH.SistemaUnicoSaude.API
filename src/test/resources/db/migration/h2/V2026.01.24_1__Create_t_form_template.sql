create table public.t_form_template
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
    professional_cns        varchar(255) not null,
    cbo                     varchar(255) not null,
    cnes                    varchar(255) not null,
    ine                     varchar(255) not null,
    is_active               boolean      not null,
    primary key (id)
);

create sequence public.sq_form_template start with 1 increment by 1;

ALTER TABLE public.t_form_template ADD NAME_UK_FIELD VARCHAR(255) AS (CASE deleted WHEN TRUE THEN NULL ELSE upper(name) END);

CREATE UNIQUE INDEX T_FORM_TEMPLATE__HASH_ID_UK ON public.t_form_template (hash_id);
CREATE UNIQUE INDEX T_FORM_TEMPLATE__NAME_UK ON public.t_form_template (NAME_UK_FIELD);