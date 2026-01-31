create table public.t_question
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
    title                   varchar(255) not null,
    description             text,
    topic                   varchar(255) not null,
    type                    varchar(255) not null,
    primary key (id)
);

create sequence public.sq_question start with 1 increment by 1;

CREATE UNIQUE INDEX T_QUESTION__HASH_ID_UK ON public.t_question (hash_id);

ALTER TABLE public.t_question ADD CONSTRAINT T_QUESTION__TOPIC_CHECK CHECK (topic IN ('GERAL', 'MOTIVO_VISITA_GERAL', 'MOTIVO_VISITA_ACOMPANHAMENTO', 'MOTIVO_VISITA_CONTROLE_AMBIENTAL_VETORIAL', 'MOTIVO_VISITA_BUSCA_ATIVA', 'ANTROPOMETRIA', 'SINAIS_VITAIS', 'GLICEMIA', 'DESFECHO'));
ALTER TABLE public.t_question ADD CONSTRAINT T_QUESTION__TYPE_CHECK CHECK (type IN ('OPEN_FIELD', 'BOOLEAN_FIELD', 'DATE_FIELD', 'SHIFT_FIELD'));