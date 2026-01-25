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

ALTER TABLE public.t_alternative ADD CONSTRAINT T_ALTERNATIVE__OPTION_CHECK CHECK (option IN ('A_1', 'A_2', 'A_3', 'A_4', 'A_5', 'A_6', 'A_7', 'A_8', 'A_9', 'A_10', 'A_11', 'A_12', 'A_13', 'A_14', 'A_15', 'A_16', 'A_17', 'A_18', 'A_19', 'A_20', 'A_21', 'A_22', 'A_23'));