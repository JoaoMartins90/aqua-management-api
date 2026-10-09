-- =============================================================================
-- V2 - a empresa e a conta dela
--
-- Dado que o sistema PRECISA para funcionar, por isso e migration versionada e
-- roda em todo ambiente. A empresa e uma pessoa juridica como qualquer outra; a
-- conta e a conta dessa pessoa. E o que permite pagar salario pelo mesmo
-- caminho por onde se recebe de um cliente.
--
-- Dados de demonstracao (catalogo, cliente e vendedor de teste) NAO entram
-- aqui: ficam em db/dev, que so roda no profile dev.
-- =============================================================================

INSERT INTO pessoa (nome, cpf_cnpj, telefone) VALUES
    ('Gerenciador Caixas Ltda', '11222333000181', '8330000000');

INSERT INTO conta (pessoa_id, descricao, saldo)
SELECT id, 'Caixa da loja', 0
  FROM pessoa
 WHERE cpf_cnpj = '11222333000181';
