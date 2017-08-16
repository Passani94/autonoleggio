-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Ago 17, 2017 alle 00:38
-- Versione del server: 10.1.25-MariaDB
-- Versione PHP: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `autonoleggio`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `breve_termine`
--

CREATE TABLE `breve_termine` (
  `Cod_BT` varchar(25) NOT NULL,
  `Km_al_Giorno` decimal(3,0) DEFAULT NULL,
  `1_Giorno` decimal(7,0) DEFAULT NULL,
  `2_Giorni` decimal(7,0) DEFAULT NULL,
  `3_4_Giorni` decimal(7,0) DEFAULT NULL,
  `5_Giorni` decimal(7,0) DEFAULT NULL,
  `6_7_Giorni` decimal(10,0) DEFAULT NULL,
  `Giorno_Extra` decimal(7,0) DEFAULT NULL,
  `Km_Extra` decimal(4,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `breve_termine`
--

INSERT INTO `breve_termine` (`Cod_BT`, `Km_al_Giorno`, `1_Giorno`, `2_Giorni`, `3_4_Giorni`, `5_Giorni`, `6_7_Giorni`, `Giorno_Extra`, `Km_Extra`) VALUES
('Autobus_12_Posti', '250', '180', '178', '175', '174', '1100', '170', '0.50'),
('Autobus_16_Posti', '250', '220', '218', '215', '210', '1350', '210', '0.60'),
('Autocaravan_4_Posti', '200', '90', '88', '85', '83', '500', '85', '0.25'),
('Autocaravan_6_Posti', '200', '110', '105', '100', '98', '630', '100', '0.35'),
('Autocarro_Cabinato', '200', '219', '199', '177', '175', '1046', '210', '0.42'),
('Autocarro_Furgonato', '200', '240', '235', '230', '228', '1000', '230', '0.39'),
('Automobile_Berlina', '150', '210', '192', '170', '169', '1005', '200', '0.40'),
('Automobile_Cabriolet', '150', '185', '180', '178', '175', '870', '175', '0.35'),
('Automobile_Coupè', '150', '180', '175', '160', '160', '850', '170', '0.36'),
('Automobile_Fuoristrada', '150', '180', '170', '155', '155', '860', '170', '0.45'),
('Automobile_Limousine', '150', '400', '385', '375', '375', '1400', '380', '0.80'),
('Automobile_Multispazio', '150', '170', '155', '135', '135', '800', '160', '0.30'),
('Automobile_SUV', '150', '175', '160', '142', '140', '837', '170', '0.34'),
('Automobile_Utilitaria', '150', '86', '78', '69', '68', '410', '80', '0.17'),
('Imbarcazione_Barca_Motore', NULL, '5000', '4900', '4850', '4700', '32400', '4500', NULL),
('Imbarcazione_Catamarano', NULL, '4600', '4500', '4400', '4380', '30460', '4300', NULL),
('Motociclo_Motocicletta', '150', '80', '78', '76', '73', '390', '70', '0.25'),
('Motociclo_Scooter', '100', '40', '35', '32', '30', '190', '30', '0.15'),
('Natante_Gommone', NULL, '150', '140', '138', '135', '930', '140', NULL),
('Quadriciclo_Quad_Bike', '150', '60', '58', '54', '52', '240', '50', '0.35');

-- --------------------------------------------------------

--
-- Struttura della tabella `cliente`
--

CREATE TABLE `cliente` (
  `CF_PIVA` varchar(16) NOT NULL,
  `Tipologia` varchar(15) NOT NULL,
  `Ragione_Sociale` varchar(40) NOT NULL,
  `CAP` decimal(5,0) DEFAULT NULL,
  `Citta` varchar(25) DEFAULT NULL,
  `Via` varchar(20) DEFAULT NULL,
  `Numero` decimal(3,0) DEFAULT NULL,
  `Telefono` decimal(10,0) DEFAULT NULL,
  `Email` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `cliente`
--

INSERT INTO `cliente` (`CF_PIVA`, `Tipologia`, `Ragione_Sociale`, `CAP`, `Citta`, `Via`, `Numero`, `Telefono`, `Email`) VALUES
('00061290672', 'Azienda', 'MIVV s.r.l.', '64027', 'Sant\'Omero', 'Zona Industriale', '2', '8612120237', 'mivv@email.it'),
('00179300447', 'Azienda', 'Silvio Meletti s.r.l.', '63100', 'Ascoli Piceno', 'Zona Industriale', '10', '3789654123', 'silviomeletti@email.it'),
('01634280679', 'Associazione', 'ASD Alba Adriatica Calcio', '64011', 'Alba Adriatica', 'dello Sport', '1', '3458007891', 'albaadriaticacalcio@email.it'),
('01846840674', 'Azienda', 'Fratelli Branciaroli Arrosticini s.r.l.', '64013', 'Corropoli', 'dell\'Industria', '20', '3383738109', 'fratellibranciaroli@email.it'),
('01863160675', 'Azienda', 'Lucci Arrosticini s.r.l.', '64035', 'Castilenti', 'Plavignano', '35', '861999331', 'lucciarrosticini@email.it'),
('01898110679', 'Associazione', 'Sunrise Sport', '64018', 'Tortoreto', 'Ignazio Silone', '44', '861782161', 'sunrisesport@email.it'),
('05935650969', 'Azienda', 'Giochi Preziosi S.p.A.', '20815', 'Cogliate', 'delle Primule', '5', '296461170', 'giochipreziosi@email.it'),
('06672120158', 'Azienda', 'Davide Campari S.p.A.', '20099', 'Sesto San Giovanni', 'Franco Sacchetti', '20', '3785129654', 'davidecampari@email.it'),
('06996881006', 'Azienda', 'Birra Peroni s.r.l.', '155', 'Roma', 'Renato Birolli', '8', '3393678542', 'birraperoni@email.it'),
('91033760447', 'Associazione', 'Coccinella \"Dina Sergiacomi\"', '63074', 'San Benedetto del Tronto', 'Puglia', '84', '337314077', 'coccinella@email.it'),
('BBDLNZ94E29H769Q', 'Privato', 'Abbadini Lorenzo', '64018', 'Tortoreto', 'Gandhi', '2', '3482968869', 'lorenzo.abbadini@email.it'),
('CLMCTN74P26E098H', 'Privato', 'Catone Colombo', '34070', 'Gorizia', 'Medina', '133', '3593214567', 'colombocatone@email.it'),
('CMPPLA95S18I348F', 'Privato', 'Compagnoni Paolo', '64013', 'Corropoli', 'Piane', '33', '3397619766', 'paolo.compagnoni@email.it'),
('FLLVIA96R61I754S', 'Privato', 'Folliero Iva', '96010', 'Siracusa', 'Santa Teresa', '139', '3238043345', 'follieroiva@email.it'),
('GDTGLL76B59G478N', 'Privato', 'Gallo Giuditta', '61010', 'Montemaggio', 'Stadera', '78', '3453765789', 'gallogiuditta@email.it'),
('GRCSTN83H52H703G', 'Privato', 'Greco Santina', '84020', 'Salerno', 'Valpantena', '16', '3214560987', 'grecosantina@email.it'),
('LGGPLT55B13A271X', 'Privato', 'Loggia Ippolito', '60121', 'Ancona', 'Giuseppe Garibaldi', '63', '3974563218', 'ippolitologgia@email.it'),
('MNLMRS71R64A662K', 'Privato', 'Monaldo Marisa', '70053', 'Canosa di Puglia', 'Campi Flegrei', '80', '3353674591', 'monaldomarisa@email.it'),
('MRCCLL90E50G224L', 'Privato', 'Marchesi Camilla', '27023', 'Cassolnovo', 'Alessandro Manzoni', '94', '378959636', 'marchesicamilla@email.it'),
('MRCNRO73S55E202J', 'Privato', 'Marchesi Nora', '58020', 'Grosseto', 'Vittorio Emanuele', '13', '3367697854', 'marchesinora@email.it'),
('MRCNTL83M44A952G', 'Privato', 'Marcelo Natalia', '39050', 'Auna di Sopra', 'Alessandro Farnese', '61', '3204597861', 'marcelonatalia@email.it'),
('MRTSRN73H13H501X', 'Privato', 'Moretti Severino', '120', 'Roma', 'Belviglieri', '138', '3283563789', 'morettiseverino@email.it'),
('MZZDNT86D10H199X', 'Privato', 'Mazzi Durante', '48018', 'Case Mezzano', 'Mercato', '34', '3451267569', 'mazzidurante@email.it'),
('SCSVLR94T06C134G', 'Privato', 'Scisci Valerio', '65015', 'Montesilvano', 'dalla Chiesa', '5', '3296473842', 'valerio.scisci@email.it'),
('SLAFNZ83M04E506Z', 'Privato', 'Sal Fiorenzo', '73047', 'Monteroni di Lecce', 'Antonio Beccadelli', '76', '3503674897', 'salfiorenzo@email.it');

-- --------------------------------------------------------

--
-- Struttura della tabella `lungo_termine`
--

CREATE TABLE `lungo_termine` (
  `Cod_LT` varchar(25) NOT NULL,
  `1_Anno` decimal(5,0) DEFAULT NULL,
  `2_Anni` decimal(5,0) DEFAULT NULL,
  `3_Anni` decimal(5,0) DEFAULT NULL,
  `Anticipo` decimal(6,0) DEFAULT NULL,
  `Km_Anno` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `lungo_termine`
--

INSERT INTO `lungo_termine` (`Cod_LT`, `1_Anno`, `2_Anni`, `3_Anni`, `Anticipo`, `Km_Anno`) VALUES
('Automobile_Berlina', '470', '402', '350', '3500', 15000),
('Automobile_Cabriolet', '430', '368', '320', '3500', 15000),
('Automobile_Coupè', '420', '356', '310', '3500', 15000),
('Automobile_Fuoristrada', '410', '345', '300', '3000', 15000),
('Automobile_Multispazio', '390', '333', '290', '2500', 10000),
('Automobile_SUV', '370', '322', '280', '3500', 10000),
('Automobile_Utilitaria', '280', '230', '200', '1500', 10000);

-- --------------------------------------------------------

--
-- Struttura della tabella `noleggio`
--

CREATE TABLE `noleggio` (
  `Cod_Noleggio` mediumint(9) NOT NULL,
  `Tipologia` varchar(5) DEFAULT NULL,
  `Veicolo` char(7) DEFAULT NULL,
  `Cliente` varchar(16) DEFAULT NULL,
  `Data_Inizio` date DEFAULT NULL,
  `Data_Fine` date DEFAULT NULL,
  `Costo_Totale` decimal(10,2) DEFAULT NULL,
  `Acconto` decimal(10,2) DEFAULT NULL,
  `Cognome` varchar(15) DEFAULT NULL,
  `Nome` varchar(15) DEFAULT NULL,
  `Num_Patente` char(10) DEFAULT NULL,
  `Valida_Fino_a` date DEFAULT NULL,
  `Rilasciata_Da` varchar(20) DEFAULT NULL,
  `Rilasciata_Il` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `noleggio`
--

INSERT INTO `noleggio` (`Cod_Noleggio`, `Tipologia`, `Veicolo`, `Cliente`, `Data_Inizio`, `Data_Fine`, `Costo_Totale`, `Acconto`, `Cognome`, `Nome`, `Num_Patente`, `Valida_Fino_a`, `Rilasciata_Da`, `Rilasciata_Il`) VALUES
(1, 'Lungo', 'BA456ER', 'MRTSRN73H13H501X', '2016-01-10', '2017-01-10', '4440.00', '1500.00', 'Severino', 'Moretti', 'RM1234567M', '2022-10-08', 'MC-RM', '2012-10-08'),
(2, 'Breve', 'DH179PK', 'MRCNRO73S55E202J', '2016-02-05', '2016-02-15', '1440.00', NULL, 'Marchesi', 'Nora', 'GR2341567P', '2021-11-09', 'MC-GR', '2011-11-09'),
(3, 'Breve', 'EB000LA', 'LGGPLT55B13A271X', '2016-02-16', '2016-02-18', '207.00', NULL, 'Loggia', 'Ippolito', 'AN1205671G', '2020-08-08', 'MC-AN', '2010-08-08'),
(4, 'Breve', 'EJ789VK', 'FLLVIA96R61I754S', '2016-03-01', '2016-03-07', '1005.00', NULL, 'Folliero', 'Iva', 'SR5734310Z', '2017-12-12', 'MC-SR', '2007-12-12'),
(5, 'Lungo', 'DJ123RD', 'CLMCTN74P26E098H', '2016-03-05', '2017-03-05', '4680.00', '2500.00', 'Catone', 'Colombo', 'GO5790015A', '2018-01-01', 'MC-GO', '2008-01-01'),
(6, 'Lungo', 'ER954AD', '06672120158', '2016-04-02', '2016-04-02', '86.00', NULL, 'Dellucci', 'Martino', 'MI5078901B', '2019-07-07', 'MC-MI', '2009-07-07'),
(7, 'Breve', 'X269DL', 'GDTGLL76B59G478N', '2016-04-10', '2016-04-14', '150.00', NULL, 'Gallo', 'Giuditta', 'SI7335049Y', '2019-09-09', 'MC-SI', '2010-09-09'),
(8, 'Breve', 'BC567DE', '01863160675', '2016-05-29', '2016-05-29', '400.00', NULL, 'Sabbatini', 'Nadia', 'TE2634647B', '2021-08-08', 'MC-TE', '2011-08-01'),
(9, 'Breve', 'AA12345', 'SLAFNZ83M04E506Z', '2016-05-14', '2016-05-17', '304.00', NULL, 'Sal', 'Fiorenzo', 'LE2344413C', '2022-05-05', 'MC-LE', '2012-05-05'),
(10, 'Breve', 'ZA709TZ', 'MRCCLL90E50G224L', '2016-06-16', '2016-06-22', '860.00', NULL, 'Marchesi ', 'Camilla', 'PV7030201F', '2018-01-01', 'MC-PV', '2008-01-01'),
(11, 'Breve', 'ZC829PA', 'MZZDNT86D10H199X', '2016-06-25', '2016-06-28', '620.00', NULL, 'Mazzi', 'Durante', 'TN8099017T', '2023-06-06', 'MC-TN', '2013-06-06'),
(12, 'Lungo', 'DH179PK', 'MRCNTL83M44A952G', '2016-07-04', '2017-07-04', '4680.00', '2500.00', 'Marcelo', 'Natalia', 'BZ9288114C', '2023-07-07', 'MC-BZ', '2013-07-07'),
(13, 'Breve', 'EB000LA', 'GRCSTN83H52H703G', '2016-07-15', '2016-07-18', '276.00', NULL, 'Greco', 'Santina', 'SA7123553F', '2017-11-11', 'MC-SA', '2007-11-11'),
(14, 'Breve', 'FI777GA', '91033760447', '2016-08-04', '2016-08-07', '688.00', NULL, 'Mancini', 'Siro', 'AP9150290R', '2019-12-12', 'MC-AP', '2009-12-12'),
(15, 'Breve', '4PC123', '05935650969', '2016-08-17', '2016-08-22', '25920.00', NULL, 'Diamante', 'Marino', 'BO1021050A', '2025-07-06', 'MC-BO', '2015-07-06'),
(16, 'Breve', 'DT765PC', '00061290672', '2016-09-02', '2016-09-07', '870.00', NULL, 'Bianchi', 'Luisa', 'TE8221421B', '2019-01-10', 'MC-TE', '2009-01-10'),
(17, 'Breve', 'X269DL', 'MNLMRS71R64A662K', '2016-09-14', '2016-09-17', '128.00', NULL, 'Monaldo', 'Marisa', 'BT1000567D', '2019-09-09', 'MC-BT', '2009-09-09'),
(18, 'Breve', 'BA567TA', 'SCSVLR94T06C134G', '2016-10-11', '2016-10-18', '730.00', NULL, 'Scisci', 'Valerio', 'PE2052051F', '2022-05-05', 'MC-PE', '2012-05-05'),
(19, 'Lungo', 'DC123AB', 'MZZDNT86D10H199X', '2016-10-21', '2017-10-21', '5640.00', '3500.00', 'Mazzi', 'Durante', 'TN8099017T', '2023-06-06', 'MC-TN', '2013-06-06'),
(20, 'Breve', 'FI777GA', '91033760447', '2016-11-12', '2016-11-17', '1080.00', NULL, 'Mancini', 'Siro', 'AP9150290R', '2019-12-12', 'MC-AP', '2009-12-12'),
(21, 'Breve', 'BC567DE', 'CMPPLA95S18I348F', '2016-11-18', '2016-11-18', '400.00', NULL, 'Compagnoni', 'Paolo', 'TE1457896A', '2023-12-12', 'MC-TE', '2013-12-12'),
(22, 'Breve', 'FI777GA', '01634280679', '2017-01-01', '2017-01-07', '1080.00', NULL, 'Zettici', 'Clemente', 'TE5612983B', '2019-10-10', 'MC-TE', '2009-10-10'),
(23, 'Breve', 'DR675CD', 'MRTSRN73H13H501X', '2017-01-15', '2017-01-22', '420.00', NULL, 'Moretti', 'Severino', 'RM1234567M', '2022-10-08', 'MC-RM', '2012-10-08'),
(24, 'Breve', 'DE789PE', 'MRCNRO73S55E202J', '2017-02-01', '2017-01-09', '1190.00', NULL, 'Marchesi', 'Nora', 'GR2341567P', '2021-11-09', 'MC-GR', '2011-11-09'),
(25, 'Breve', 'DR657CD', 'SLAFNZ83M04E506Z', '2017-02-02', '2017-02-07', '410.00', NULL, 'Sal', 'Fiorenzo', 'LE2344413C', '2022-05-05', 'MC-LE', '2012-05-05'),
(26, 'Lungo', 'BA456ER', '00061290672', '2017-03-01', '2018-03-01', '3996.00', '3500.00', 'Bianchi', 'Luisa', 'TE8221421B', '2019-01-10', 'MC-TE', '2009-01-10'),
(27, 'Breve', 'FI777GA', '01898110679', '2017-03-03', '2017-03-07', '840.00', NULL, 'Bellucci', 'Smeralda', 'TE4376841G', '2017-12-15', 'MC-TE', '2007-12-15'),
(28, 'Breve', 'ZC829PA', 'LGGPLT55B13A271X', '2017-04-02', '2017-04-07', '860.00', NULL, 'Loggia', 'Ippolito', 'AN1205671G', '2020-08-08', 'MC-AN', '2010-08-08'),
(29, 'Breve', '4PC123', '00179300447', '2017-04-04', '2017-04-04', '5000.00', NULL, 'Costa', 'Marcella', 'AP1296854A', '2017-10-10', 'MC-AP', '2007-10-10'),
(30, 'Lungo', 'DJ123RD', '01863160675', '2017-05-14', '2018-05-14', '4212.00', '2500.00', 'Boni', 'Eleonora', 'TE5671234X', '2018-09-09', 'MC-TE', '2008-09-09'),
(31, 'Breve', 'BC567DE', 'BBDLNZ94E29H769Q', '2017-05-29', '2017-05-30', '770.00', NULL, 'Abbadini', 'Lorenzo', 'TE1234567A', '2022-09-05', 'MC-TE', '2012-09-05'),
(32, 'Breve', '8PC789', '06996881006', '2017-06-10', '2017-06-10', '150.00', NULL, 'Fiorentini', 'Sandro', 'RM5643789S', '2021-12-12', 'MC-RM', '2011-12-12'),
(33, 'Breve', 'AA12345', 'CLMCTN74P26E098H', '2017-06-18', '2017-06-19', '156.00', NULL, 'Catone', 'Colombo', 'GO5790015A', '2018-01-01', 'MC-GO', '2008-01-01'),
(34, 'Lungo', 'DV145FD', '00179300447', '2017-07-09', '2018-07-09', '3996.00', '3500.00', 'Schiavone', 'Azzurra', 'AP5469812A', '2019-06-06', 'MC-AP', '2009-06-06'),
(35, 'Lungo', 'BB105FD', '01846840674', '2017-07-11', '2018-07-11', '4644.00', '3500.00', 'Padovano', 'Savino', 'TE456123A', '2025-05-05', 'MC-TE', '2015-05-05'),
(36, 'Breve', 'EB000LA', 'BBDLNZ94E29H769Q', '2017-08-27', '2017-09-05', '650.00', NULL, 'Abbadini', 'Lorenzo', 'TE1234567A', '2022-09-05', 'MC-TE', '2012-09-05'),
(37, 'Breve', 'ER954AD', 'CMPPLA95S18I348F', '2017-08-28', '2017-09-05', '570.00', NULL, 'Compagnoni', 'Paolo', 'TE1457896A', '2023-12-12', 'MC-TE', '2013-12-12'),
(38, 'Breve', 'DR657CD', 'SCSVLR94T06C134G', '2017-08-29', '2017-09-05', '490.00', NULL, 'Scisci', 'Valerio', 'PE2052051F', '2022-05-05', 'MC-PE', '2012-05-05');

-- --------------------------------------------------------

--
-- Struttura della tabella `operatore`
--

CREATE TABLE `operatore` (
  `ID_Operatore` varchar(25) NOT NULL,
  `Password` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `operatore`
--

INSERT INTO `operatore` (`ID_Operatore`, `Password`) VALUES
('admin', 'admin'),
('user', 'user');

-- --------------------------------------------------------

--
-- Struttura della tabella `veicolo`
--

CREATE TABLE `veicolo` (
  `Targa` char(7) NOT NULL,
  `Tipologia` varchar(25) DEFAULT NULL,
  `Marca` varchar(15) DEFAULT NULL,
  `Nome` varchar(20) DEFAULT NULL,
  `Disponibilita` char(2) DEFAULT NULL,
  `Alimentazione` varchar(15) DEFAULT NULL,
  `Km_Effettuati` bigint(20) DEFAULT NULL,
  `Dimensioni` varchar(20) DEFAULT NULL,
  `Data_Immatricolazione` date DEFAULT NULL,
  `Data_Scadenza_Bollo` date DEFAULT NULL,
  `Data_Scadenza_Tagliando` date DEFAULT NULL,
  `Data_Scadenza_Assicurazione` date DEFAULT NULL,
  `Data_Scadenza_Ormeggio` date DEFAULT NULL,
  `Data_Scadenza_Costo_Alaggio` date DEFAULT NULL,
  `Costobt` varchar(25) DEFAULT NULL,
  `Costolt` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `veicolo`
--

INSERT INTO `veicolo` (`Targa`, `Tipologia`, `Marca`, `Nome`, `Disponibilita`, `Alimentazione`, `Km_Effettuati`, `Dimensioni`, `Data_Immatricolazione`, `Data_Scadenza_Bollo`, `Data_Scadenza_Tagliando`, `Data_Scadenza_Assicurazione`, `Data_Scadenza_Ormeggio`, `Data_Scadenza_Costo_Alaggio`, `Costobt`, `Costolt`) VALUES
('4PC123', 'Barca_Motore', 'Bayliner', 'Bowrider', 'SI', 'Benzina', 4000, NULL, '2015-06-15', '2017-09-30', '2017-09-30', '2017-09-30', '2017-09-30', '2017-09-30', 'Imbarcazione_Barca_Motore', NULL),
('8PC567', 'Catamarano', 'Rodman', 'Runabout', 'NO', 'Benzina', 12000, NULL, '2015-02-12', '2017-12-12', '2017-11-12', '2017-12-25', '2017-10-23', '2017-10-25', 'Imbarcazione_Catamarano', NULL),
('8PC789', 'Gommone', 'Searay', 'Easyway', 'SI', 'Benzina', 2500, NULL, '2016-11-14', '2017-10-10', '2017-10-10', '2017-10-10', '2017-09-15', '2017-09-15', 'Natante_Gommone', NULL),
('AA12345', 'Motocicletta', 'Yamaha', 'Tracer 700', 'SI', 'Benzina', 15000, '213/80/127', '2016-03-11', '2017-10-11', '2017-12-16', '2017-10-15', NULL, NULL, 'Motociclo_Motocicletta', NULL),
('AM79810', 'Quad_Bike', 'Yamaha', 'Grizzly 700', 'NO', 'Benzina', 7000, '207/123/125', '2016-12-12', '2017-11-28', '2017-10-19', '2017-11-23', NULL, NULL, 'Quadriciclo_Quad_Bike', NULL),
('BA456ER', 'SUV', 'Ford', 'Kuga', 'SI', 'Diesel', 28350, '452/183/168', '2015-12-15', '2017-12-12', '2017-10-15', '2017-12-16', NULL, NULL, 'Automobile_SUV', 'Automobile_SUV'),
('BA567TA', 'Autocaravan_6_Posti', 'CI', 'Elliot 98', 'SI', 'Diesel', 48000, '720/230/320', '2014-12-16', '2017-09-19', '2017-09-19', '2017-09-19', NULL, NULL, 'Autocaravan_6_Posti', NULL),
('BB105FD', 'Cabriolet', 'Audi', 'A3 Cabriolet', 'SI', 'Diesel', 50000, '442/179/141', '2014-06-10', '2017-09-06', '2017-09-06', '2017-09-06', NULL, NULL, 'Automobile_Cabriolet', 'Cabriolet'),
('BC567DE', 'Limousine', 'Cadillac', 'DTS Stretch', 'SI', 'Benzina', 14750, NULL, '2014-12-15', '2017-10-10', '2018-01-02', '2017-12-15', NULL, NULL, 'Automobile_Limousine', 'Automobile_Limousine'),
('BE345NE', 'Autocarro_Cabinato', 'Fiat', 'Ducato', 'SI', 'Diesel', 10000, '496/179/225', '2016-10-15', '2017-12-11', '2017-10-11', '2017-12-15', NULL, NULL, 'Autocarro_Cabinato', NULL),
('BF123AD', 'Coupè', 'BMW', 'Serie 2 Coupè', 'SI', 'Diesel', 45000, '443/177/142', '2013-05-10', '2018-02-01', '2018-02-01', '2018-02-01', NULL, NULL, 'Automobile_Coupè', 'Coupè'),
('BU777BU', 'Autocarro_Furgonato', 'Fiat', 'Ducato 33', 'SI', 'Diesel', 15500, '600/179/252', '2016-09-23', '2017-10-15', '2017-10-15', '2017-10-15', NULL, NULL, 'Autocarro_Furgonato', NULL),
('CA128TD', 'Autobus_12_Posti', 'Ford', 'Transit L2-H2', 'NO', 'Diesel', 62000, '757/205/278', '2014-06-27', '2018-01-14', '2018-07-13', '2018-06-05', NULL, NULL, 'Autobus_12_Posti', NULL),
('DC123AB', 'Berlina', 'BMW', 'Serie 3', 'SI', 'Benzina', 23456, '462/181/142', '2015-12-25', '2017-10-11', '2017-10-11', '2017-10-11', NULL, NULL, 'Automobile_Berlina', 'Automobile_Berlina'),
('DE456TI', 'Autocaravan_4_Posti', 'CI', 'Magis 95XT', 'NO', 'Diesel', 35000, '299/231/320', '2015-12-15', '2017-10-15', '2017-11-13', '2017-12-12', NULL, NULL, 'Autocaravan_4_Posti', NULL),
('DE789PE', 'Coupè', 'BMW', 'Z4M', 'SI', 'Benzina', 18000, '411/190/126', '2016-03-12', '2017-09-11', '2017-11-12', '2017-12-14', NULL, NULL, 'Automobile_Coupè', 'Automobile_Coupè'),
('DH179PK', 'Multispazio', 'Volkswagen', 'Touran', 'SI', 'Diesel', 25000, '453/183/163', '2015-03-11', '2017-09-15', '2017-09-15', '2017-09-15', NULL, NULL, 'Automobile_Multispazio', 'Multispazio'),
('DJ123RD', 'Multispazio', 'Fiat', 'Doblò', 'SI', 'Benzina', 35000, '425/172/183', '2015-10-11', '2017-08-25', '2017-09-15', '2017-10-11', NULL, NULL, 'Automobile_Multispazio', 'Automobile_Multispazio'),
('DR657CD', 'Utilitaria', 'Opel', 'Adam', 'SI', 'Benzina', 42000, '370/172/148', '2014-06-20', '2017-12-22', '2017-12-22', '2017-12-22', NULL, NULL, 'Automobile_Utilitaria', 'Utilitaria'),
('DT765PC', 'Cabriolet', 'Fiat', '124 Spider', 'SI', 'Benzina', 14769, '405/174/123', '2016-12-12', '2017-11-12', '2017-12-25', '2017-12-31', NULL, NULL, 'Automobile_Cabriolet', 'Automobile_Cabriolet'),
('DV145FD', 'SUV', 'Opel', 'Mokka X', 'SI', 'Benzina', 15000, '428/178/166', '2016-06-15', '2017-10-18', '2017-10-18', '2017-10-18', NULL, NULL, 'Automobile_SUV', 'SUV'),
('EB000LA', 'Utilitaria', 'Fiat', 'Panda', 'SI', 'Metano', 15000, '365/164/155', '2016-07-20', '2018-04-05', '2017-08-20', '2018-04-05', NULL, NULL, 'Automobile_Utilitaria', 'Automobile_Utilitaria'),
('EJ789VK', 'Berlina', 'Alfa Romeo', 'Giulia', 'SI', 'Benzina', 20000, '464/186/144', '2016-01-01', '2018-01-01', '2018-01-01', '2018-08-01', NULL, NULL, 'Automobile_Berlina', 'Berlina'),
('ER954AD', 'Utilitaria', 'Volkswagen', 'Up!', 'SI', 'Benzina', 15500, '360/164/150', '2016-10-25', '2017-10-28', '2017-10-28', '2017-12-28', NULL, NULL, 'Automobile_Utilitaria', 'Utilitaria'),
('FI777GA', 'Autobus_16_Posti', 'Mercedes', 'Transfer 34', 'SI', 'Diesel', 59000, '696/199/262', '2015-03-04', '2018-12-11', '2018-09-15', '2018-10-13', NULL, NULL, 'Autobus_16_Posti', NULL),
('X269DL', 'Scooter', 'Gilera', 'Runner 50', 'SI', 'Benzina', 3400, '184/75/100', '2016-12-12', '2017-10-12', '2017-10-23', '2017-10-21', NULL, NULL, 'Motociclo_Scooter', NULL),
('ZA709TZ', 'Fuoristrada', 'Land Rover', 'Defender', 'SI', 'Benzina', 16458, '404/179/200', '2016-12-12', '2018-02-02', '2018-03-10', '2018-02-12', NULL, NULL, 'Automobile_Fuoristrada', 'Automobile_Fuoristrada'),
('ZC829PA', 'Fuoristrada', 'Mitsubishi', 'Pajero', 'SI', 'Diesel', 60000, '439/188/186', '2010-05-12', '2017-11-10', '2017-11-10', '2017-11-10', NULL, NULL, 'Automobile_Fuoristrada', 'Fuoristrada');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `breve_termine`
--
ALTER TABLE `breve_termine`
  ADD PRIMARY KEY (`Cod_BT`);

--
-- Indici per le tabelle `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`CF_PIVA`);

--
-- Indici per le tabelle `lungo_termine`
--
ALTER TABLE `lungo_termine`
  ADD PRIMARY KEY (`Cod_LT`);

--
-- Indici per le tabelle `noleggio`
--
ALTER TABLE `noleggio`
  ADD PRIMARY KEY (`Cod_Noleggio`);

--
-- Indici per le tabelle `operatore`
--
ALTER TABLE `operatore`
  ADD PRIMARY KEY (`ID_Operatore`);

--
-- Indici per le tabelle `veicolo`
--
ALTER TABLE `veicolo`
  ADD PRIMARY KEY (`Targa`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `noleggio`
--
ALTER TABLE `noleggio`
  MODIFY `Cod_Noleggio` mediumint(9) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
