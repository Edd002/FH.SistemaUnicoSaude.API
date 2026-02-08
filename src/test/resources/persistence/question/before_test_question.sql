INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:05.923', NULL, false, NULL, NULL, '3aecb2404f444b15a498cccaf2b2c820', '2026-02-01 15:29:05.923', NULL, 'TURNO', NULL, 'GERAL', 'SHIFT_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:05.950', NULL, false, NULL, NULL, 'cd0d7c4002bb4180a776c7651cf2da61', '2026-02-01 15:29:05.950', NULL, 'MICROÁREA', NULL, 'GERAL', 'NUMERIC_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:05.968', NULL, false, NULL, NULL, 'e70a5c81e58346829cb0dd67b08255e6', '2026-02-01 15:29:05.968', NULL, 'TIPO DE IMÓVEL', NULL, 'GERAL', 'NUMERIC_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:05.987', NULL, false, NULL, NULL, 'c0c381977ad34ebd944af02b0df75fb7', '2026-02-01 15:29:05.987', NULL, 'Nº PRONTUÁRIO', NULL, 'GERAL', 'OPEN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.005', NULL, false, NULL, NULL, '92c240d8dc5748efa5d55be4c0362135', '2026-02-01 15:29:06.005', NULL, 'CNS ou CPF do Cidadão', '(para visita periódica ou visita domiciliar para controle vetorial, usar o CNS do responsável familiar)', 'GERAL', 'NUMERIC_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.025', NULL, false, NULL, NULL, 'c7f37f40b8534870b413684c32488849', '2026-02-01 15:29:06.025', NULL, 'Data de nascimento', NULL, 'GERAL', 'DATE_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.043', NULL, false, NULL, NULL, 'cb15fc222d4248829222a125e28d4a73', '2026-02-01 15:29:06.043', NULL, 'Sexo', '(F) Feminino (M) Masculino (I) Indeterminado', 'GERAL', 'GENDER_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.062', NULL, false, NULL, NULL, '7e7e5ba29a784edfbba41a7739ceb1fb', '2026-02-01 15:29:06.062', NULL, 'Visita compartilhada com outro profissional', NULL, 'GERAL', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.080', NULL, false, NULL, NULL, '1904d299608b4e419b1c759d1fa5b6d1', '2026-02-01 15:29:06.080', NULL, 'Cadastramento/Atualização', NULL, 'MOTIVO_VISITA_GERAL', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.097', NULL, false, NULL, NULL, '8482bb76f9044a8b8a3fea8942f5e50f', '2026-02-01 15:29:06.097', NULL, 'Visita periódica', NULL, 'MOTIVO_VISITA_GERAL', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.117', NULL, false, NULL, NULL, 'd3e2aaa4081b4e1e8789826e751445fd', '2026-02-01 15:29:06.117', NULL, 'Consulta', NULL, 'MOTIVO_VISITA_BUSCA_ATIVA', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.137', NULL, false, NULL, NULL, '7830481099ca498aa4169cbcf0094429', '2026-02-01 15:29:06.137', NULL, 'Exame', NULL, 'MOTIVO_VISITA_BUSCA_ATIVA', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.156', NULL, false, NULL, NULL, '6085d4e6eae84ac4ac04d9b05ddd2182', '2026-02-01 15:29:06.156', NULL, 'Vacina', NULL, 'MOTIVO_VISITA_BUSCA_ATIVA', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.176', NULL, false, NULL, NULL, '400fa555e27240fca5bc111cb1111054', '2026-02-01 15:29:06.176', NULL, 'Condicionalidades do Bolsa Família', NULL, 'MOTIVO_VISITA_BUSCA_ATIVA', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.194', NULL, false, NULL, NULL, 'd06e2caa6011480c841823a0df58d378', '2026-02-01 15:29:06.194', NULL, 'Gestante', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.213', NULL, false, NULL, NULL, '4a7fc72012934870a3426847b2505dfa', '2026-02-01 15:29:06.213', NULL, 'Puérpera', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.232', NULL, false, NULL, NULL, '7c6dfe71c1214f0e91bdd16cb31fdb46', '2026-02-01 15:29:06.232', NULL, 'Recém-nascido', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.250', NULL, false, NULL, NULL, 'cbe51645926c4e5a89abeb7993390c69', '2026-02-01 15:29:06.250', NULL, 'Criança', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.269', NULL, false, NULL, NULL, 'db09ef53b45d41e094087e663b3af4a2', '2026-02-01 15:29:06.269', NULL, 'Pessoa com desnutrição', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.291', NULL, false, NULL, NULL, 'fae288679f2742359062c0f5ab17691f', '2026-02-01 15:29:06.291', NULL, 'Pessoa em reabilitação ou com deficiência', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.310', NULL, false, NULL, NULL, 'd20f95736959426384ab8dad0e569e39', '2026-02-01 15:29:06.310', NULL, 'Pessoa com hipertensão', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.329', NULL, false, NULL, NULL, '1d1dfd60fe9f4686b5f87c79abd84b32', '2026-02-01 15:29:06.329', NULL, 'Pessoa idosa', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.348', NULL, false, NULL, NULL, 'a276c994325946a899f634f2f96d9492', '2026-02-01 15:29:06.348', NULL, 'Pessoa com diabetes', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.367', NULL, false, NULL, NULL, 'a5254e39a3c74c33aaf12a40a6ccb385', '2026-02-01 15:29:06.367', NULL, 'Pessoa com asma', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.386', NULL, false, NULL, NULL, 'fc0db68cbd194026b014602c4547a2dc', '2026-02-01 15:29:06.386', NULL, 'Pessoa com DPOC/enfisema', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.403', NULL, false, NULL, NULL, 'c9cb70d346b0492bbfc08b2f6a326646', '2026-02-01 15:29:06.403', NULL, 'Pessoa com câncer', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.423', NULL, false, NULL, NULL, 'b9ab2cc26cca4153baa8c0f062425b10', '2026-02-01 15:29:06.423', NULL, 'Pessoa com outras doenças crônicas', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.445', NULL, false, NULL, NULL, '7112b3ec9b5b4d26aa3972bb44e3fa3f', '2026-02-01 15:29:06.445', NULL, 'Pessoa com hanseníase', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.463', NULL, false, NULL, NULL, '52e5104ce3494f6681fb318e4fedcd60', '2026-02-01 15:29:06.463', NULL, 'Pessoa com tuberculose', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.482', NULL, false, NULL, NULL, '1a9582630ac44c9396fb669bb47d858f', '2026-02-01 15:29:06.482', NULL, 'Sintomáticos respiratórios', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.501', NULL, false, NULL, NULL, 'c4a19893ba394c52a447b6026b90a4c4', '2026-02-01 15:29:06.501', NULL, 'Tabagista', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.520', NULL, false, NULL, NULL, 'd9ef4fd016f54f15bfc2b8560b5c6293', '2026-02-01 15:29:06.520', NULL, 'Domiciliados/Acamados', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.539', NULL, false, NULL, NULL, '0c45440f31344419843f379dc1075b3f', '2026-02-01 15:29:06.539', NULL, 'Condições de vulnerabilidade social', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.558', NULL, false, NULL, NULL, '74d6d429538e40649e7c7f5f42ea396c', '2026-02-01 15:29:06.558', NULL, 'Condicionalidades de Bolsa Família', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.575', NULL, false, NULL, NULL, '229a8febb1574812aa4902b3ed3caa3c', '2026-02-01 15:29:06.575', NULL, 'Saúde mental', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.592', NULL, false, NULL, NULL, '34e638392c5c453aba84fabc8e26bcb6', '2026-02-01 15:29:06.592', NULL, 'Usuário de álcool', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.609', NULL, false, NULL, NULL, '1911a9732b7c4837935961ae1ddaaef5', '2026-02-01 15:29:06.609', NULL, 'Usuário de outras drogas', NULL, 'MOTIVO_VISITA_ACOMPANHAMENTO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.626', NULL, false, NULL, NULL, '12fdb515b58f4e7abe2af922c1edaaef', '2026-02-01 15:29:06.626', NULL, 'Ação educativa', NULL, 'MOTIVO_VISITA_CONTROLE_AMBIENTAL_VETORIAL', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.641', NULL, false, NULL, NULL, 'b8a62d857fdc49269b51609e7e674866', '2026-02-01 15:29:06.641', NULL, 'Imóvel com foco', NULL, 'MOTIVO_VISITA_CONTROLE_AMBIENTAL_VETORIAL', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.658', NULL, false, NULL, NULL, 'eac57fb053b54db789c72786b0bbff4b', '2026-02-01 15:29:06.658', NULL, 'Ação mecânica', NULL, 'MOTIVO_VISITA_CONTROLE_AMBIENTAL_VETORIAL', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.674', NULL, false, NULL, NULL, '9d6a91fba6b74882a2285f5f818a1e6c', '2026-02-01 15:29:06.674', NULL, 'Trantamento focal', NULL, 'MOTIVO_VISITA_CONTROLE_AMBIENTAL_VETORIAL', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.692', NULL, false, NULL, NULL, '356d75446be5428380c8cc08ecfdd56c', '2026-02-01 15:29:06.692', NULL, 'Egresso da Internação', NULL, 'MOTIVO_VISITA_GERAL', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.711', NULL, false, NULL, NULL, '912e1f9695b846fcb3ed180c93b350f4', '2026-02-01 15:29:06.711', NULL, 'Convite atividades coletivas/campanha de saúde', NULL, 'MOTIVO_VISITA_GERAL', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.730', NULL, false, NULL, NULL, 'fa286b7a8fcc462b95a5060aad86ef01', '2026-02-01 15:29:06.730', NULL, 'Orientação/prevenção', NULL, 'MOTIVO_VISITA_GERAL', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.749', NULL, false, NULL, NULL, 'cfaf7c1b8c1e4cee8d89a10b438c3b70', '2026-02-01 15:29:06.749', NULL, 'Outros', NULL, 'MOTIVO_VISITA_GERAL', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.767', NULL, false, NULL, NULL, 'e488df114dae494aafbd2e5a55c5376b', '2026-02-01 15:29:06.767', NULL, 'Peso (kg)', NULL, 'ANTROPOMETRIA', 'OPEN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.786', NULL, false, NULL, NULL, 'cd2b6f9acf844d85ba10eec628d0eb30', '2026-02-01 15:29:06.786', NULL, 'Altura (cm)', NULL, 'ANTROPOMETRIA', 'OPEN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.806', NULL, false, NULL, NULL, '0e1d2c85f8fc4e3b94ac22c77a54e8a4', '2026-02-01 15:29:06.806', NULL, 'Temperatura (ºC)', NULL, 'SINAIS_VITAIS', 'OPEN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.825', NULL, false, NULL, NULL, '854bb84b0a5340cdac3216dad56aaf74', '2026-02-01 15:29:06.825', NULL, 'Pressão arterial (mmHg)', NULL, 'SINAIS_VITAIS', 'OPEN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.845', NULL, false, NULL, NULL, '2862a26094cd43058e3a4475362f4e47', '2026-02-01 15:29:06.845', NULL, 'Glicemia capilar (mg/dL)', NULL, 'GLICEMIA', 'OPEN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.867', NULL, false, NULL, NULL, '244b4ce092c2437281ae75a329b74d47', '2026-02-01 15:29:06.867', NULL, 'Momento da coleta', NULL, 'GLICEMIA', 'OPEN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.885', NULL, false, NULL, NULL, '7644bf42618a42738eca3ede0d573d70', '2026-02-01 15:29:06.885', NULL, 'Visita realizada', NULL, 'DESFECHO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.904', NULL, false, NULL, NULL, 'c24c5f35380c4539a8dbcbfba3aafcdc', '2026-02-01 15:29:06.904', NULL, 'Visita recusada', NULL, 'DESFECHO', 'BOOLEAN_FIELD');
INSERT INTO public.t_question
(id, created_in, created_by, deleted, deleted_in, deleted_by, hash_id, updated_in, updated_by, title, description, topic, type)
VALUES(nextval('SQ_QUESTION'), '2026-02-01 15:29:06.923', NULL, false, NULL, NULL, '7c747ea801a44d2dad67b31aad114637', '2026-02-01 15:29:06.923', NULL, 'Ausente', NULL, 'DESFECHO', 'BOOLEAN_FIELD');