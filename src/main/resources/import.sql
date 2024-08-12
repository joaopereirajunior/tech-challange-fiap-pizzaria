-- Inserindo clientes
INSERT INTO tb_cliente (nome, endereco, telefone) VALUES ('Lucas Oliveira', 'Rua das Flores, 102', '(11) 98765-4321');
INSERT INTO tb_cliente (nome, endereco, telefone) VALUES ('Maria Ferreira', 'Avenida dos Pioneiros, 3', '(11) 99876-5432');
INSERT INTO tb_cliente (nome, endereco, telefone) VALUES ('Pedro Silva', 'Travessa da Amizade, 54', '(11) 91234-5678');
INSERT INTO tb_cliente (nome, endereco, telefone) VALUES ('Ana Costa', 'Alameda dos Pinheiros, 98', '(11) 92345-6789');
INSERT INTO tb_cliente (nome, endereco, telefone) VALUES ('João Santos', 'Rua do Sol, 12', '(11) 93456-7890');
INSERT INTO tb_cliente (nome, endereco, telefone) VALUES ('Beatriz Almeida', 'Avenida Esperança, 1024', '(11) 94567-8901');
INSERT INTO tb_cliente (nome, endereco, telefone) VALUES ('Gabriel Rocha', 'Travessa dos Lírios, 90 ', '(11) 95678-9012');
INSERT INTO tb_cliente (nome, endereco, telefone) VALUES ('Larissa Mendes', 'Alameda das Acácias, 423', '(11) 96789-0123');

-- Inserindo produtos
INSERT INTO tb_produto (nome, tipo, preco) VALUES ('Pizza Margherita', 'Pizza', 25.50);
INSERT INTO tb_produto (nome, tipo, preco) VALUES ('Pizza Calabresa', 'Pizza', 28.00);
INSERT INTO tb_produto (nome, tipo, preco) VALUES ('Pizza Quatro Queijos', 'Pizza', 30.00);
INSERT INTO tb_produto (nome, tipo, preco) VALUES ('Pizza Portuguesa', 'Pizza', 32.00);
INSERT INTO tb_produto (nome, tipo, preco) VALUES ('Pizza Frango com Catupiry', 'Pizza', 29.00);
INSERT INTO tb_produto (nome, tipo, preco) VALUES ('Pizza Vegetariana', 'Pizza', 26.00);
INSERT INTO tb_produto (nome, tipo, preco) VALUES ('Pizza Mussarela', 'Pizza', 28.00);
INSERT INTO tb_produto (nome, tipo, preco) VALUES ('Porção de Batata Frita', 'Acompanhamento', 12.00);
INSERT INTO tb_produto (nome, tipo, preco) VALUES ('Coca-Cola 1,5L', 'Bebida', 7.30);
INSERT INTO tb_produto (nome, tipo, preco) VALUES ('Suco de Laranja 1L', 'Bebida', 8.00);
INSERT INTO tb_produto (nome, tipo, preco) VALUES ('Coca-Cola 2L', 'Bebida', 10.25);
INSERT INTO tb_produto (nome, tipo, preco) VALUES ('Calzone de Presunto e Queijo', 'Pizza', 27.00);
INSERT INTO tb_produto (nome, tipo, preco) VALUES ('Porção de Onion Rings', 'Acompanhamento', 14.00);
INSERT INTO tb_produto (nome, tipo, preco) VALUES ('Água Mineral 500ml', 'Bebida', 5.00);
INSERT INTO tb_produto (nome, tipo, preco) VALUES ('Cerveja Artesanal 600ml', 'Bebida', 15.00);

-- Inserindo pedidos
INSERT INTO tb_pedido (id_cliente, endereco_entrega, status_pedido, data_pedido) VALUES (1, 'Rua das Flores, 102', 'NOVO', '2024-08-02');
INSERT INTO tb_pedido (id_cliente, endereco_entrega, status_pedido, data_pedido) VALUES (2, 'Avenida dos Pioneiros, 3', 'NOVO', '2024-08-03');
INSERT INTO tb_pedido (id_cliente, endereco_entrega, status_pedido, data_pedido) VALUES (3, 'Travessa da Amizade, 54', 'NOVO', '2024-08-03');

-- Inserindo itens de pedidos
INSERT INTO tb_itens_pedido (quantidade, id_pedido, id_produto) VALUES (2, 1, 1);  -- 2 Pizza Margherita para o pedido 1
INSERT INTO tb_itens_pedido (quantidade, id_pedido, id_produto) VALUES (1, 1, 7);  -- 1 Pizza Mussarela para o pedido 1
INSERT INTO tb_itens_pedido (quantidade, id_pedido, id_produto) VALUES (1, 2, 4);  -- 1 Pizza Portuguesa para o pedido 2
INSERT INTO tb_itens_pedido (quantidade, id_pedido, id_produto) VALUES (3, 2, 10); -- 3 Coca-Cola 1,5L para o pedido 2
INSERT INTO tb_itens_pedido (quantidade, id_pedido, id_produto) VALUES (1, 3, 2);  -- 1 Pizza Calabresa para o pedido 3
