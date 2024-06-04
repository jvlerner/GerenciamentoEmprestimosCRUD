
-- Criando a estrutura para banco de dados `a3db`
CREATE DATABASE IF NOT EXISTS `a3db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `a3db`;

-- Criando a estrutura para tabela `emprestimo`
DROP TABLE IF EXISTS `emprestimo`; -- Dropa e recria caso já exista
CREATE TABLE IF NOT EXISTS `emprestimo` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `pessoaId` int(255) NOT NULL,
  `ferramentaId` int(255) NOT NULL,
  `dataOut` date NOT NULL,
  `dataIn` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Criando a estrutura para tabela `ferramenta`
DROP TABLE IF EXISTS `ferramenta`; -- Dropa e recria caso já exista
CREATE TABLE IF NOT EXISTS `ferramenta` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `ferramenta` varchar(255) NOT NULL,
  `marca` varchar(255) NOT NULL,
  `preco` varchar(255) NOT NULL,
  `emprestado` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Inserindo dados para a tabela `ferramenta`
INSERT INTO `ferramenta` (`id`, `ferramenta`, `marca`, `preco`, `emprestado`) VALUES
(1, 'Martelo', 'Vonder', '149.0', 0),
(2, 'Serra', 'Bosch', '99.0', 0),
(3, 'Chave de fenda', 'Vonder', '19.90', 0),
(4, 'Furadeira', 'Makita', '1949.9', 0),
(5, 'Chave de Boca 18', 'Vonder', '25.9', 0),
(6, 'Chave de Boca 20', 'Vonder', '27.9', 0),
(7, 'Chave de Boca 22', 'Vonder', '29.9', 0),
(8, 'Chave de Boca 24', 'Vonder', '31.9', 0),
(9, 'Chave de Boca 8', 'Vonder', '12.9', 0),
(10, 'Chave de Boca 12', 'Vonder', '16.9', 0);


-- Criando a estrutura para tabela `pessoa`
DROP TABLE IF EXISTS `pessoa`; -- Dropa e recria caso já exista
CREATE TABLE IF NOT EXISTS `pessoa` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `telefone` (`telefone`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Inserindo dados para a tabela `pessoa`
INSERT INTO `pessoa` (`id`, `nome`, `telefone`, `email`) VALUES
(1, 'João', '48991695854', 'joao@gmail.com'),
(2, 'Osmar', '54992345687', 'osmar@gmail.com'),
(3, 'Allan', '22994323232', 'allan@gmail.com'),
(4, 'Julia', '91991830235', 'julia@gmail.com'),
(5, 'Ana', '11345678901', 'ana@gmail.com'),
(6, 'Maria', '11567890123', 'maria@gmail.com'),
(7, 'Pedro', '11789012345', 'pedro@gmail.com'),
(8, 'Fernanda', '11234567890', 'fernanda@gmail.com'),
(9, 'Carlos', '11987654321', 'carlos@gmail.com'),
(10, 'Rodrigo', '11890123456', 'rodrigo@gmail.com'),
(11, 'Juliana', '11678901234', 'juliana@gmail.com'),
(12, 'Lucas', '11901234567', 'lucas@gmail.com'),
(13, 'Camila', '11456789012', 'camila@gmail.com'),
(14, 'Mariana', '11234567891', 'mariana@gmail.com');
