-- =============================================================================
-- Dados de demonstracao - so no profile dev
--
-- Nao e migration versionada: e um callback do Flyway (afterMigrate), que roda
-- depois de TODO migrate, inclusive quando nao ha migration nova. Por isso cada
-- INSERT e idempotente (ON CONFLICT DO NOTHING): rodar de novo nao duplica nada.
--
-- Fica fora de db/migration de proposito. Migration versionada e permanente e
-- roda em todo ambiente; dado de teste numa V3 iria parar em producao e nao
-- teria como ser "apagado antes de usar de verdade".
--
-- Ativado por spring.flyway.locations em application-dev.properties.
-- =============================================================================

-- Catalogo. Medidas em metros; cor, material e formato guardam o name() dos
-- enums em caixadaagua/enums.
INSERT INTO caixa_da_agua
    (marca, modelo, capacidade, altura, largura, profundidade,
     cor, material, formato, preco, estoque_atual)
VALUES
    ('Tigre',   'Basic 310',      310, 0.55, 0.90, 0.90, 'AZUL', 'POLIETILENO', 'QUADRADA',  189.90, 12),
    ('Tigre',   'Basic 500',      500, 0.65, 1.05, 1.05, 'AZUL', 'POLIETILENO', 'QUADRADA',  279.90,  8),
    ('Fortlev', 'Standard 1000', 1000, 0.85, 1.30, 1.30, 'AZUL', 'POLIETILENO', 'QUADRADA',  549.90,  5),
    ('Fortlev', 'Standard 2000', 2000, 1.05, 1.60, 1.60, 'AZUL', 'POLIETILENO', 'QUADRADA', 1049.90,  2)
ON CONFLICT (marca, modelo, capacidade) DO NOTHING;

-- Um vendedor e um cliente, so para ter com quem fechar a primeira venda.
INSERT INTO pessoa (nome, cpf_cnpj, telefone) VALUES
    ('Maria Vendedora', '11122233344', '83988880000'),
    ('Joao Cliente',    '55566677788', '83977770000')
ON CONFLICT (cpf_cnpj) DO NOTHING;

INSERT INTO funcionario (pessoa_id, setor, salario, data_admissao)
SELECT id, 'VENDAS', 1800.00, CURRENT_DATE
  FROM pessoa
 WHERE cpf_cnpj = '11122233344'
ON CONFLICT (pessoa_id) DO NOTHING;

INSERT INTO cliente (pessoa_id, limite_credito)
SELECT id, 1000.00
  FROM pessoa
 WHERE cpf_cnpj = '55566677788'
ON CONFLICT (pessoa_id) DO NOTHING;
