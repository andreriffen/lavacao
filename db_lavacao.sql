-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 07/08/2024 às 04:41
-- Versão do servidor: 8.3.0
-- Versão do PHP: 8.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `db_lavacao`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cpf` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `telefone` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `endereco` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `data_nascimento` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Despejando dados para a tabela `cliente`
--

INSERT INTO `cliente` (`id`, `nome`, `cpf`, `telefone`, `email`, `endereco`, `data_nascimento`) VALUES
(4, 'Alessandra Silva', '111.111.111-11', '(11) 91111-1111', 'alessandra.silva@gmail.com', 'Rua das Palmeiras, São Paulo, SP', '1985-01-15'),
(5, 'Bruno Oliveira', '222.222.222-22', '(21) 92222-2222', 'bruno.oliveira@hotmail.com', 'Avenida Brasil, Rio de Janeiro, RJ', '1979-05-30'),
(6, 'Carlos Pereira', '333.333.333-33', '(31) 93333-3333', 'carlos.pereira@yahoo.com', 'Rua dos Bandeirantes, Belo Horizonte, MG', '1990-08-22'),
(7, 'Daniela Rocha', '444.444.444-44', '(41) 94444-4444', 'daniela.rocha@gmail.com', 'Rua XV de Novembro, Curitiba, PR', '1982-03-10'),
(8, 'Eduardo Lima', '555.555.555-55', '(51) 95555-5555', 'eduardo.lima@hotmail.com', 'Avenida Ipiranga, Porto Alegre, RS', '1995-06-12'),
(9, 'Fernanda Costa', '666.666.666-66', '(61) 96666-6666', 'fernanda.costa@yahoo.com', 'Setor Comercial Sul, Brasília, DF', '1988-11-09'),
(10, 'Gustavo Martins', '777.777.777-77', '(71) 97777-7777', 'gustavo.martins@gmail.com', 'Rua da Paciência, Salvador, BA', '1992-07-25'),
(11, 'Helena Ferreira', '888.888.888-88', '(81) 98888-8888', 'helena.ferreira@hotmail.com', 'Avenida Boa Viagem, Recife, PE', '1981-12-05'),
(12, 'Igor Almeida', '999.999.999-99', '(91) 99999-9999', 'igor.almeida@yahoo.com', 'Avenida Nazaré, Belém, PA', '1997-04-03'),
(13, 'Joana Mendes', '111.111.111-12', '(51) 91111-1122', 'joana.mendes@gmail.com', 'Rua da Praia, Porto Alegre, RS', '1989-09-17'),
(14, 'Lucas Santos', '222.222.222-23', '(71) 92222-2233', 'lucas.santos@hotmail.com', 'Avenida Sete de Setembro, Salvador, BA', '1993-02-28'),
(15, 'Mariana Souza', '333.333.333-34', '(81) 93333-3344', 'mariana.souza@yahoo.com', 'Rua da Aurora, Recife, PE', '1991-01-10'),
(16, 'Nicolas Ribeiro', '444.444.444-45', '(21) 94444-4455', 'nicolas.ribeiro@gmail.com', 'Rua da Alfândega, Rio de Janeiro, RJ', '1987-04-27'),
(17, 'Olivia Carvalho', '555.555.555-56', '(31) 95555-5566', 'olivia.carvalho@hotmail.com', 'Avenida Afonso Pena, Belo Horizonte, MG', '1984-08-19'),
(18, 'Pedro Batista', '666.666.666-67', '(41) 96666-6677', 'pedro.batista@yahoo.com', 'Rua Marechal Deodoro, Curitiba, PR', '1996-06-04'),
(19, 'Quintino Reis', '777.777.777-78', '(61) 97777-7788', 'quintino.reis@gmail.com', 'Setor Bancário Sul, Brasília, DF', '1983-12-21'),
(20, 'Renata Moraes', '888.888.888-89', '(91) 98888-8899', 'renata.moraes@hotmail.com', 'Rua dos Mundurucus, Belém, PA', '1998-03-18'),
(21, 'Sofia Nunes', '999.999.999-90', '(81) 99999-9000', 'sofia.nunes@yahoo.com', 'Avenida Conde da Boa Vista, Recife, PE', '1985-05-11'),
(22, 'Thiago Barbosa', '111.111.111-13', '(21) 91111-1133', 'thiago.barbosa@gmail.com', 'Rua do Passeio, Rio de Janeiro, RJ', '1992-10-29'),
(23, 'Ursula Duarte', '222.222.222-24', '(51) 92222-2244', 'ursula.duarte@hotmail.com', 'Avenida Borges de Medeiros, Porto Alegre, RS', '1990-07-15'),
(24, 'Vitor Gonçalves', '333.333.333-35', '(71) 93333-3355', 'vitor.goncalves@yahoo.com', 'Rua Chile, Salvador, BA', '1994-11-20'),
(25, 'Wesley Araujo', '444.444.444-46', '(81) 94444-4466', 'wesley.araujo@gmail.com', 'Rua do Imperador, Recife, PE', '1986-09-08'),
(26, 'Ximena Farias', '555.555.555-57', '(91) 95555-5577', 'ximena.farias@hotmail.com', 'Rua 28 de Setembro, Belém, PA', '1997-01-03'),
(27, 'Yasmin Campos', '666.666.666-68', '(41) 96666-6688', 'yasmin.campos@yahoo.com', 'Avenida Cândido de Abreu, Curitiba, PR', '1988-03-25');

-- --------------------------------------------------------

--
-- Estrutura para tabela `cor`
--

DROP TABLE IF EXISTS `cor`;
CREATE TABLE IF NOT EXISTS `cor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Despejando dados para a tabela `cor`
--

INSERT INTO `cor` (`id`, `nome`) VALUES
(4, 'Branco'),
(5, 'Preto'),
(6, 'Cinza'),
(7, 'Prata'),
(8, 'Vermelho'),
(9, 'Azul'),
(10, 'Verde'),
(11, 'Amarelo'),
(12, 'Laranja'),
(13, 'Marrom'),
(14, 'Roxo'),
(15, 'Rosa'),
(16, 'Dourado'),
(17, 'Bronze'),
(18, 'Branco Pérola'),
(19, 'Preto Fosco'),
(20, 'Cinza Grafite'),
(21, 'Prata Metálico'),
(22, 'Vermelho Escuro'),
(23, 'Azul Marinho'),
(24, 'Verde Musgo'),
(25, 'Amarelo Ouro'),
(26, 'Laranja Tangerina'),
(27, 'Marrom Chocolate'),
(28, 'Roxo Violeta'),
(29, 'Rosa Choque'),
(30, 'Dourado Metalizado'),
(31, 'Bronze Envelhecido'),
(32, 'Azul Celeste'),
(33, 'Verde Esmeralda'),
(34, 'Vermelho Rubi'),
(35, 'Amarelo Canário'),
(36, 'Azul Turquesa'),
(37, 'Cinza Chumbo'),
(38, 'Prata Fosco'),
(39, 'Verde Oliva'),
(40, 'Laranja Cobre'),
(41, 'Vermelho Ferrari'),
(42, 'Azul Royal'),
(43, 'Verde Limão'),
(44, 'Branco Gelo'),
(45, 'Preto Ônix'),
(46, 'Cinza Titânio'),
(47, 'Prata Champagne'),
(48, 'Vermelho Carmim'),
(49, 'Azul Safira'),
(50, 'Verde Floresta'),
(51, 'Amarelo Neon'),
(52, 'Laranja Lava'),
(53, 'Marrom Mogno'),
(54, 'Roxo Berinjela'),
(55, 'Rosa Pastel'),
(56, 'Dourado Solar'),
(57, 'Bronze Fosco');

-- --------------------------------------------------------

--
-- Estrutura para tabela `marca`
--

DROP TABLE IF EXISTS `marca`;
CREATE TABLE IF NOT EXISTS `marca` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Despejando dados para a tabela `marca`
--

INSERT INTO `marca` (`id`, `nome`) VALUES
(1, 'Chevrolet'),
(2, 'Fiat'),
(3, 'Ford'),
(4, 'Volkswagen'),
(5, 'Honda'),
(6, 'Toyota'),
(7, 'Hyundai'),
(8, 'Renault'),
(9, 'Nissan'),
(10, 'Jeep'),
(11, 'Peugeot'),
(12, 'Citroën'),
(13, 'Mitsubishi'),
(14, 'Kia'),
(15, 'Suzuki'),
(16, 'Chery'),
(17, 'BMW'),
(18, 'Mercedes-Benz'),
(19, 'Audi'),
(20, 'Jaguar'),
(21, 'Land Rover'),
(22, 'Volvo'),
(23, 'Mini'),
(24, 'Porsche'),
(25, 'Ferrari'),
(26, 'Lamborghini'),
(27, 'Maserati'),
(28, 'Aston Martin'),
(29, 'Bentley'),
(30, 'Rolls-Royce'),
(31, 'Bugatti'),
(32, 'Tesla'),
(33, 'Alfa Romeo'),
(34, 'Lifan'),
(35, 'JAC'),
(36, 'Troller'),
(37, 'Hummer'),
(38, 'Dodge'),
(39, 'RAM'),
(40, 'Subaru'),
(41, 'Lexus'),
(42, 'Infiniti'),
(43, 'Acura'),
(44, 'Genesis'),
(45, 'SsangYong'),
(46, 'Mahindra'),
(47, 'Foton'),
(48, 'BYD'),
(49, 'Great Wall'),
(50, 'Geely'),
(51, 'MG'),
(52, 'Isuzu'),
(53, 'Daihatsu'),
(54, 'Pontiac'),
(55, 'Buick'),
(56, 'Cadillac'),
(57, 'GMC'),
(58, 'Lincoln'),
(59, 'Chrysler'),
(60, 'Plymouth'),
(61, 'Oldsmobile'),
(62, 'Mercury'),
(63, 'Saab'),
(64, 'Rover'),
(65, 'Harley-Davidson'),
(66, 'Yamaha'),
(67, 'Kawasaki'),
(68, 'Ducati'),
(69, 'Triumph'),
(70, 'Royal Enfield'),
(71, 'BMW Motorrad'),
(72, 'Honda Motos'),
(73, 'Suzuki Motos'),
(74, 'KTM'),
(75, 'Aprilia'),
(76, 'MV Agusta'),
(77, 'Husqvarna'),
(78, 'Bajaj'),
(79, 'Hero MotoCorp'),
(80, 'Benelli'),
(81, 'Norton'),
(82, 'Indian Motorcycle'),
(83, 'Scania'),
(84, 'Iveco'),
(85, 'MAN'),
(86, 'International'),
(87, 'Volvo Caminhões'),
(88, 'Mercedes-Benz Caminhões'),
(89, 'DAF'),
(90, 'Peterbilt'),
(91, 'Kenworth'),
(92, 'Mack Trucks'),
(93, 'Freightliner'),
(94, 'Western Star'),
(95, 'Ford Caminhões'),
(96, 'Hyundai Caminhões'),
(97, 'Tatra'),
(98, 'Hino Motors'),
(99, 'Isuzu Caminhões'),
(100, 'Sinotruk'),
(101, 'JAC Caminhões');

-- --------------------------------------------------------

--
-- Estrutura para tabela `modelo`
--

DROP TABLE IF EXISTS `modelo`;
CREATE TABLE IF NOT EXISTS `modelo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `servico`
--

DROP TABLE IF EXISTS `servico`;
CREATE TABLE IF NOT EXISTS `servico` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `valor` decimal(10,2) NOT NULL,
  `pontos` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Despejando dados para a tabela `servico`
--

INSERT INTO `servico` (`id`, `descricao`, `valor`, `pontos`) VALUES
(1, 'Lavação Completa', 70.00, 10),
(2, 'Polimento', 50.00, 10),
(3, 'Lavação Motor', 100.00, 10);

-- --------------------------------------------------------

--
-- Estrutura para tabela `veiculo`
--

DROP TABLE IF EXISTS `veiculo`;
CREATE TABLE IF NOT EXISTS `veiculo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `placa` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `observacoes` text COLLATE utf8mb4_unicode_ci,
  `modelo_id` int DEFAULT NULL,
  `cor_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `modelo_id` (`modelo_id`),
  KEY `cor_id` (`cor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `veiculo`
--
ALTER TABLE `veiculo`
  ADD CONSTRAINT `veiculo_ibfk_1` FOREIGN KEY (`modelo_id`) REFERENCES `modelo` (`id`),
  ADD CONSTRAINT `veiculo_ibfk_2` FOREIGN KEY (`cor_id`) REFERENCES `cor` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
