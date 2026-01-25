alter table if exists public.t_alternative add constraint t_question__fk_question foreign key (fk_question) references t_question;

CREATE UNIQUE INDEX T_ALTERNATIVE__FK_QUESTION_AND_OPTION_UK ON public.t_alternative (fk_question, option, deleted) WHERE deleted IS NULL OR deleted = false;