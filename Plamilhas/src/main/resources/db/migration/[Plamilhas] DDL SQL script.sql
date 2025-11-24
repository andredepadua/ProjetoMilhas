-- ============================================================
--  PROJETO PLAMILHAS - Modelo Relacional (PostgreSQL)
--  Descrição: Estrutura de tabelas, chaves, índices e comentários
--  Observação: Execute este script em um banco de dados vazio
-- ============================================================

-- ============================================================
-- Extensões necessárias
-- ============================================================
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- ============================================================
-- Tipos ENUM (Status e Origens)
-- ============================================================
CREATE TYPE status_pontos AS ENUM ('PENDENTE', 'PARCIALMENTE_CREDITADO', 'CREDITADO', 'FALHOU');
CREATE TYPE origem_pontos AS ENUM ('COMPRA', 'AJUSTE', 'RESGATE', 'MANUAL');

-- ============================================================
-- Usuários e controle de acesso
-- ============================================================
CREATE TABLE usuario (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    nome_completo varchar(200) NOT NULL,
    email varchar(255) NOT NULL UNIQUE,
    senha_hash varchar(255) NOT NULL,
    ativo boolean NOT NULL DEFAULT true,
    criado_em timestamptz NOT NULL DEFAULT now(),
    atualizado_em timestamptz NOT NULL DEFAULT now(),
    ultimo_login timestamptz,
    perfil jsonb
);

COMMENT ON TABLE usuario IS 'Tabela de usuários do sistema Plamilhas';
COMMENT ON COLUMN usuario.email IS 'E-mail único do usuário (usado para login)';
COMMENT ON COLUMN usuario.senha_hash IS 'Senha armazenada com hash seguro (bcrypt/argon2)';

-- ------------------------------------------------------------
CREATE TABLE papel (
    id serial PRIMARY KEY,
    nome varchar(50) NOT NULL UNIQUE,
    descricao text
);

COMMENT ON TABLE papel IS 'Papéis de acesso (ex.: administrador, usuário padrão)';

-- ------------------------------------------------------------
CREATE TABLE usuario_papel (
    usuario_id uuid NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    papel_id int NOT NULL REFERENCES papel(id) ON DELETE CASCADE,
    atribuido_em timestamptz NOT NULL DEFAULT now(),
    PRIMARY KEY (usuario_id, papel_id)
);

COMMENT ON TABLE usuario_papel IS 'Associação N:N entre usuários e papéis de acesso';

-- ============================================================
-- Bandeiras, Programas e Tipos de Cartão
-- ============================================================
CREATE TABLE bandeira (
    id serial PRIMARY KEY,
    nome varchar(100) NOT NULL UNIQUE,
    pais varchar(50),
    criado_em timestamptz NOT NULL DEFAULT now()
);
COMMENT ON TABLE bandeira IS 'Bandeiras de cartão (ex.: Visa, MasterCard, Elo)';

-- ------------------------------------------------------------
CREATE TABLE programa (
    id serial PRIMARY KEY,
    nome varchar(150) NOT NULL,
    operadora varchar(150),
    descricao text,
    categoria_id int,
    criado_em timestamptz NOT NULL DEFAULT now(),
    UNIQUE(nome, operadora)
);

COMMENT ON TABLE programa IS 'Programas de pontos (ex.: Smiles, Latam Pass, TudoAzul)';

-- ------------------------------------------------------------
CREATE TABLE tipo_cartao (
    id serial PRIMARY KEY,
    nome varchar(50) NOT NULL UNIQUE,
    descricao text
);
COMMENT ON TABLE tipo_cartao IS 'Tipos de cartão (crédito, débito, co-branded, etc.)';

-- ============================================================
-- Cartões dos Usuários
-- ============================================================
CREATE TABLE cartao_usuario (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    usuario_id uuid NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    bandeira_id int NOT NULL REFERENCES bandeira(id),
    programa_id int NOT NULL REFERENCES programa(id),
    apelido varchar(150),
    ultimos4 char(4),
    bin_prefixo varchar(8),
    tipo_id int REFERENCES tipo_cartao(id),
    ativo boolean NOT NULL DEFAULT true,
    criado_em timestamptz NOT NULL DEFAULT now(),
    UNIQUE(usuario_id, ultimos4, bin_prefixo)
);
COMMENT ON TABLE cartao_usuario IS 'Cartões cadastrados pelos usuários, associados a programas e bandeiras';

-- ============================================================
-- Saldos de Pontos
-- ============================================================
CREATE TABLE saldo_pontos (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    usuario_id uuid NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    programa_id int NOT NULL REFERENCES programa(id) ON DELETE CASCADE,
    saldo bigint NOT NULL DEFAULT 0,
    saldo_pendente bigint NOT NULL DEFAULT 0,
    atualizado_em timestamptz NOT NULL DEFAULT now(),
    UNIQUE(usuario_id, programa_id)
);
COMMENT ON TABLE saldo_pontos IS 'Saldo atual e pendente de pontos por usuário e programa';

-- ============================================================
-- Compras / Transações
-- ============================================================
CREATE TABLE compra (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    usuario_id uuid NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    cartao_usuario_id uuid REFERENCES cartao_usuario(id) ON DELETE SET NULL,
    programa_id int NOT NULL REFERENCES programa(id),
    valor numeric(12,2) NOT NULL,
    moeda char(3) DEFAULT 'BRL',
    data_compra date NOT NULL,
    registrada_em timestamptz NOT NULL DEFAULT now(),
    pontos_esperados bigint,
    pontos_calculados bigint,
    status_pontos status_pontos NOT NULL DEFAULT 'PENDENTE',
    data_prevista_credito date,
    data_creditado timestamptz,
    observacoes text
);
COMMENT ON TABLE compra IS 'Registros de compras realizadas pelos usuários (geram pontos)';

-- ============================================================
-- Comprovantes / Anexos
-- ============================================================
CREATE TABLE anexo (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    compra_id uuid REFERENCES compra(id) ON DELETE CASCADE,
    usuario_id uuid NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    caminho_arquivo text NOT NULL,
    nome_arquivo varchar(255),
    tipo_arquivo varchar(50),
    tamanho bigint,
    enviado_em timestamptz NOT NULL DEFAULT now()
);
COMMENT ON TABLE anexo IS 'Arquivos anexados às compras (comprovantes, notas fiscais, etc.)';

-- ============================================================
-- Histórico de Pontos (Auditoria)
-- ============================================================
CREATE TABLE historico_pontos (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    usuario_id uuid NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    programa_id int NOT NULL REFERENCES programa(id),
    variacao integer NOT NULL,
    saldo_apos bigint,
    origem origem_pontos NOT NULL,
    origem_id uuid,
    criado_em timestamptz NOT NULL DEFAULT now(),
    observacoes text
);
COMMENT ON TABLE historico_pontos IS 'Histórico detalhado de alterações de pontos (ganhos e resgates)';

CREATE INDEX idx_historico_pontos_usuario ON historico_pontos(usuario_id, criado_em);

-- ============================================================
-- Resgates
-- ============================================================
CREATE TABLE resgate (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    usuario_id uuid NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    programa_id int NOT NULL REFERENCES programa(id),
    pontos_usados bigint NOT NULL,
    data_resgate timestamptz NOT NULL DEFAULT now(),
    descricao text,
    status varchar(30) DEFAULT 'CONCLUIDO'
);
COMMENT ON TABLE resgate IS 'Tabela de resgates de pontos pelos usuários';

-- ============================================================
-- Notificações e Tokens Push
-- ============================================================
CREATE TABLE token_push (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    usuario_id uuid NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    token text NOT NULL UNIQUE,
    informacoes_dispositivo text,
    criado_em timestamptz NOT NULL DEFAULT now(),
    ultimo_acesso timestamptz
);
COMMENT ON TABLE token_push IS 'Tokens FCM utilizados para notificações push nos dispositivos do usuário';

-- ------------------------------------------------------------
CREATE TABLE notificacao (
    id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
    usuario_id uuid NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    tipo varchar(80),
    conteudo jsonb,
    agendada_em timestamptz,
    enviada_em timestamptz,
    status varchar(30) DEFAULT 'PENDENTE'
);
COMMENT ON TABLE notificacao IS 'Notificações enviadas ou agendadas para o usuário';

-- ============================================================
-- Conquistas / Gamificação
-- ============================================================
CREATE TABLE conquista (
    id serial PRIMARY KEY,
    codigo varchar(80) UNIQUE NOT NULL,
    titulo varchar(200) NOT NULL,
    descricao text,
    criterio jsonb,
    recompensa_pontos int DEFAULT 0
);
COMMENT ON TABLE conquista IS 'Conquistas e metas de gamificação (ex.: acumulou 10.000 pontos)';

-- ------------------------------------------------------------
CREATE TABLE usuario_conquista (
    usuario_id uuid NOT NULL REFERENCES usuario(id) ON DELETE CASCADE,
    conquista_id int NOT NULL REFERENCES conquista(id) ON DELETE CASCADE,
    conquistada_em timestamptz NOT NULL DEFAULT now(),
    meta jsonb,
    PRIMARY KEY(usuario_id, conquista_id)
);
COMMENT ON TABLE usuario_conquista IS 'Associação N:N entre usuários e conquistas desbloqueadas';

-- ============================================================
-- Auditoria de Alterações
-- ============================================================
CREATE TABLE log_auditoria (
    id bigserial PRIMARY KEY,
    entidade varchar(100),
    entidade_id text,
    operacao varchar(20),
    usuario_id uuid,
    dados jsonb,
    criado_em timestamptz NOT NULL DEFAULT now()
);
COMMENT ON TABLE log_auditoria IS 'Registra alterações administrativas e ações relevantes no sistema';

-- ============================================================
-- Índices adicionais
-- ============================================================
CREATE INDEX idx_usuario_email ON usuario(email);
CREATE INDEX idx_compra_usuario ON compra(usuario_id, data_compra);
CREATE INDEX idx_saldo_pontos_usuario ON saldo_pontos(usuario_id);
CREATE INDEX idx_notificacao_usuario ON notificacao(usuario_id);
CREATE INDEX idx_token_push_usuario ON token_push(usuario_id);

-- ============================================================
-- FIM DO SCRIPT
-- ============================================================
