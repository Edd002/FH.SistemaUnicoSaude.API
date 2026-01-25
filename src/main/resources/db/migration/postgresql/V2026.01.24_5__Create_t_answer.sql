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
CREATE UNIQUE INDEX T_ANSWER__FK_QUESTION_AND_FK_QUESTIONNAIRE_USER_UK ON public.t_answer (fk_question, fk_questionnaire_user, deleted) WHERE deleted IS NULL OR deleted = false;

ALTER TABLE public.t_answer ADD CONSTRAINT T_ANSWER__ANSWERED_ALTERNATIVE_CHECK CHECK (answered_alternative IN ('A_1', 'A_2', 'A_3', 'A_4', 'A_5', 'A_6', 'A_7', 'A_8', 'A_9', 'A_10', 'A_11', 'A_12', 'A_13', 'A_14', 'A_15', 'A_16', 'A_17', 'A_18', 'A_19', 'A_20', 'A_21', 'A_22', 'A_23'));