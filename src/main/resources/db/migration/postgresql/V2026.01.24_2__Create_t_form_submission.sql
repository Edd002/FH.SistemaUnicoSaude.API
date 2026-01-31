create table public.t_form_submission
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
    is_answered             boolean      not null,
    collected_at            timestamp(6) not null,
    synced_at               timestamp(6) not null,
    general_observation     text,
    fk_form_template        int8         not null,
    fk_health_professional  int8         not null,
    primary key (id)
);

create sequence public.sq_form_submission start with 1 increment by 1;

alter table if exists public.t_form_submission add constraint t_form_submission__fk_form_template foreign key (fk_form_template) references t_form_template;
alter table if exists public.t_form_submission add constraint t_form_submission__fk_health_professional foreign key (fk_health_professional) references t_user;

CREATE UNIQUE INDEX T_FORM_SUBMISSION__HASH_ID_UK ON public.t_form_submission (hash_id);