create table public.t_questionnaire_user
(
    id                  int8         not null,
    created_in          timestamp(6) not null,
    created_by          varchar(255),
    deleted             boolean      not null,
    deleted_in          timestamp(6),
    deleted_by          varchar(255),
    hash_id             varchar(255) not null,
    updated_in          timestamp(6),
    updated_by          varchar(255),
    answered            boolean      not null,
    fk_questionnaire    int8         not null,
    fk_user             int8         not null,
    primary key (id)
);

create sequence public.sq_questionnaire_user start with 1 increment by 1;

alter table if exists public.t_questionnaire_user add constraint t_questionnaire_user__fk_questionnaire foreign key (fk_questionnaire) references t_questionnaire;
alter table if exists public.t_questionnaire_user add constraint t_questionnaire_user__fk_user foreign key (fk_user) references t_user;

CREATE UNIQUE INDEX T_QUESTIONNAIRE_USER__HASH_ID_UK ON public.t_questionnaire_user (hash_id);
CREATE UNIQUE INDEX T_QUESTIONNAIRE_USER__FK_QUESTIONNAIRE_AND_FK_USER_UK ON public.t_questionnaire_user (fk_questionnaire, fk_user, deleted) WHERE deleted IS NULL OR deleted = false;