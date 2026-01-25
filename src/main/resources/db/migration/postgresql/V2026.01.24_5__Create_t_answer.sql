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
    visitation_option       varchar(255) not null,
    delivered_answer        varchar(255) not null,
    fk_question             int8         not null,
    fk_questionnaire_user   int8         not null,
    primary key (id)
);

create sequence public.sq_answer start with 1 increment by 1;

alter table if exists public.t_answer add constraint t_answer__fk_question foreign key (fk_question) references t_question;
alter table if exists public.t_answer add constraint t_answer__fk_questionnaire_user foreign key (fk_questionnaire_user) references t_questionnaire_user;

CREATE UNIQUE INDEX T_ANSWER__HASH_ID_UK ON public.t_answer (hash_id);
CREATE UNIQUE INDEX T_ANSWER__FK_QUESTION_AND_FK_QUESTIONNAIRE_USER_UK ON public.t_answer (fk_question, fk_questionnaire_user, deleted) WHERE deleted IS NULL OR deleted = false;

ALTER TABLE public.t_answer ADD CONSTRAINT T_ANSWER__VISITATION_OPTION_CHECK CHECK (visitation_option IN ('V_1', 'V_2', 'V_3', 'V_4', 'V_5', 'V_6', 'V_7', 'V_8', 'V_9', 'V_10', 'V_11', 'V_12', 'V_13', 'V_14', 'V_15', 'V_16', 'V_17', 'V_18', 'V_19', 'V_20', 'V_21', 'V_22', 'V_23'));