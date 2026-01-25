create table public.t_alternative
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
    option                  varchar(255) not null,
    body                    text         not null,
    fk_question             int8         not null,
    primary key (id)
);

create sequence public.sq_alternative start with 1 increment by 1;

CREATE UNIQUE INDEX T_ALTERNATIVE__HASH_ID_UK ON public.t_alternative (hash_id);

ALTER TABLE public.t_alternative ADD CONSTRAINT T_ALTERNATIVE__OPTION_CHECK CHECK (option IN ('A', 'B', 'C', 'D', 'E'));