create table public.t_answer
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
    answered_alternative    varchar(255) not null,
    fk_question             int8         not null,
    fk_questionnaire_user   int8         not null,
    primary key (id)
);

create sequence public.sq_answer start with 1 increment by 1;

alter table if exists public.t_answer add constraint t_answer__fk_question foreign key (fk_question) references t_question;
alter table if exists public.t_answer add constraint t_answer__fk_questionnaire_user foreign key (fk_questionnaire_user) references t_questionnaire_user;

CREATE UNIQUE INDEX T_ANSWER__HASH_ID_UK ON public.t_answer (hash_id);

ALTER TABLE public.t_answer ADD CONSTRAINT T_ANSWER__ANSWERED_ALTERNATIVE_CHECK CHECK (answered_alternative IN ('A', 'B', 'C', 'D', 'E'));