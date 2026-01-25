create table public.t_questionnaire_question
(
    id                 int8         not null,
    created_in         timestamp(6) not null,
    created_by         varchar(255),
    deleted            boolean      not null,
    deleted_in         timestamp(6),
    deleted_by         varchar(255),
    hash_id            varchar(255) not null,
    updated_in         timestamp(6),
    updated_by         varchar(255),
    fk_questionnaire   int8         not null,
    fk_question        int8         not null,
    primary key (id)
);

create sequence public.sq_questionnaire_question start with 1 increment by 1;

alter table if exists public.t_questionnaire_question add constraint t_questionnaire_question__fk_questionnaire foreign key (fk_questionnaire) references t_questionnaire_question;
alter table if exists public.t_questionnaire_question add constraint t_questionnaire_question__fk_question foreign key (fk_question) references t_questionnaire_question;

CREATE UNIQUE INDEX T_QUESTIONNAIRE_QUESTION__HASH_ID_UK ON public.t_questionnaire_question (hash_id);
CREATE UNIQUE INDEX T_QUESTIONNAIRE_QUESTION__FK_QUESTIONNAIRE_AND_FK_QUESTION_UK ON public.t_questionnaire_question (fk_questionnaire, fk_question, deleted) WHERE deleted IS NULL OR deleted = false;
