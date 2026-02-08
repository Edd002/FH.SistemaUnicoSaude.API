create table public.t_form_template_question
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
    fk_form_template   int8         not null,
    fk_question        int8         not null,
    primary key (id)
);

create sequence public.sq_form_template_question start with 1 increment by 1;

alter table if exists public.t_form_template_question add constraint t_form_template_question__fk_form_template foreign key (fk_form_template) references t_form_template;
alter table if exists public.t_form_template_question add constraint t_form_template_question__fk_question foreign key (fk_question) references t_question;

ALTER TABLE public.t_form_template_question ADD FK_FORM_TEMPLATE_UK_FIELD INT8 AS (CASE deleted WHEN TRUE THEN NULL ELSE fk_form_template END);
ALTER TABLE public.t_form_template_question ADD FK_QUESTION_UK_FIELD INT8 AS (CASE deleted WHEN TRUE THEN NULL ELSE fk_question END);

CREATE UNIQUE INDEX T_FORM_TEMPLATE_QUESTION__HASH_ID_UK ON public.t_form_template_question (hash_id);
CREATE UNIQUE INDEX T_FORM_TEMPLATE_QUESTION__FK_FORM_TEMPLATE_AND_FK_QUESTION_UK ON public.t_form_template_question (FK_FORM_TEMPLATE_UK_FIELD, FK_QUESTION_UK_FIELD);