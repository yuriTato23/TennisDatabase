-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Apr 27, 2024 at 10:44 PM
-- Server version: 5.7.39
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tennis23`
--

-- --------------------------------------------------------

--
-- Table structure for table `committee`
--

CREATE TABLE `committee` (
  `player_no` int(11) NOT NULL,
  `begin_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `position` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `committee`
--

INSERT INTO `committee` (`player_no`, `begin_date`, `end_date`, `position`) VALUES
(8, '1991-01-01', '1991-12-31', 'Secretary'),
(8, '1993-01-01', '1993-12-31', 'Member'),
(8, '1994-01-01', '2023-11-17', 'Member'),
(27, '1990-01-01', '1990-12-31', 'Member'),
(27, '1991-01-01', '1991-12-31', 'Treasurer'),
(27, '1993-01-01', '1993-12-31', 'Treasurer'),
(57, '0002-11-30', '0002-11-30', ''),
(57, '1992-01-01', '1992-12-31', 'Secretary'),
(57, '1992-02-01', '1992-03-01', 'Treasurer'),
(57, '2002-11-21', '0002-11-30', '');

-- --------------------------------------------------------

--
-- Table structure for table `matches`
--

CREATE TABLE `matches` (
  `match_no` int(11) NOT NULL,
  `team_no` int(11) NOT NULL,
  `player_no` int(11) NOT NULL,
  `won` smallint(6) NOT NULL,
  `lost` smallint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `matches`
--

INSERT INTO `matches` (`match_no`, `team_no`, `player_no`, `won`, `lost`) VALUES
(4, 1, 44, 3, 2),
(5, 1, 83, 0, 3),
(6, 1, 2, 1, 3),
(7, 1, 57, 3, 0),
(8, 1, 8, 0, 3),
(9, 2, 27, 3, 2),
(10, 2, 104, 3, 2);

-- --------------------------------------------------------

--
-- Table structure for table `penalties`
--

CREATE TABLE `penalties` (
  `payment_no` int(11) NOT NULL,
  `player_no` int(11) NOT NULL,
  `payment_date` date NOT NULL,
  `amount` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `penalties`
--

INSERT INTO `penalties` (`payment_no`, `player_no`, `payment_date`, `amount`) VALUES
(2, 44, '1981-05-05', '75'),
(4, 104, '1984-12-08', '50'),
(5, 44, '1980-12-08', '25'),
(6, 8, '1980-12-08', '25'),
(7, 27, '0002-11-30', '0'),
(8, 27, '1984-11-12', '75');

-- --------------------------------------------------------

--
-- Table structure for table `players`
--

CREATE TABLE `players` (
  `player_no` int(3) NOT NULL,
  `name` varchar(20) NOT NULL,
  `initials` varchar(60) NOT NULL,
  `birth_date` date DEFAULT NULL,
  `sex` char(1) NOT NULL,
  `joined` year(4) NOT NULL,
  `street` varchar(40) NOT NULL,
  `house_no` char(4) DEFAULT NULL,
  `postcode` char(6) DEFAULT NULL,
  `town` varchar(30) NOT NULL,
  `phone_no` char(13) DEFAULT NULL,
  `league_no` char(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `players`
--

INSERT INTO `players` (`player_no`, `name`, `initials`, `birth_date`, `sex`, `joined`, `street`, `house_no`, `postcode`, `town`, `phone_no`, `league_no`) VALUES
(2, 'Everett', 'R', '1948-09-01', 'M', 1975, 'Stoney Road', '43', '3575NH', 'Stratford', '70237893', '2411'),
(7, 'Wise', 'GWC', '1963-05-11', 'M', 1981, 'Edgecomb Way', '39', '9758VB', 'Stratford', '70347689', ''),
(8, 'Newcastle', 'B', '1962-07-08', 'F', 1980, 'Station Road', '4', '6584RO', 'Inglewood', '707458458', '2983'),
(27, 'Collins', 'DD', '1964-12-28', 'F', 1983, 'Long Drive', '804', '8457DK', 'Eltham', '79234857', '2513'),
(28, 'Collins', 'C', '1963-06-22', 'F', 1983, 'Old Main Road', '10', '1294QK', 'Midhurst', '71659599', NULL),
(39, 'Bishops', 'D', '1959-10-29', 'M', 1980, 'Eaton Square', '78', '9629CD', 'Stratford', '70393435', NULL),
(44, 'Baker', 'E', '1963-01-09', 'M', 1980, 'Lewis Street', '23', '4444LJ', 'Inglewood', '70368753', '1124'),
(57, 'Brown', 'M', '1971-08-17', 'M', 1985, 'Edgecombe Way', '16', '4377CB', 'Stratford', '70473458', '6409'),
(83, 'Hope ', 'PK', '1956-11-11', 'M', 1982, 'Magdalene Road', '16A', '1812UP', 'Stratford', '70353548', '1608'),
(95, 'Miller', 'P', '1963-05-14', 'M', 1972, 'High Street', '33A', '57460P', 'Douglas', '70867564', NULL),
(100, 'Parmenter', 'P', '1963-02-28', 'M', 1979, 'Haseltine Lane', '80', '1234KK', 'Stratford', '70494593', '6524'),
(104, 'Moorman', 'D', '1970-05-10', 'F', 1984, 'Stout Street', '65', '9437AO', 'Eltham', '79987571', '7060');

-- --------------------------------------------------------

--
-- Table structure for table `teams`
--

CREATE TABLE `teams` (
  `team_no` int(11) NOT NULL,
  `player_no` int(11) NOT NULL,
  `division` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teams`
--

INSERT INTO `teams` (`team_no`, `player_no`, `division`) VALUES
(1, 2, ''),
(2, 27, 'second'),
(3, 2, '');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `committee`
--
ALTER TABLE `committee`
  ADD PRIMARY KEY (`player_no`,`begin_date`);

--
-- Indexes for table `matches`
--
ALTER TABLE `matches`
  ADD PRIMARY KEY (`match_no`),
  ADD KEY `FOREIGN` (`player_no`,`team_no`) USING BTREE,
  ADD KEY `team_no` (`team_no`);

--
-- Indexes for table `penalties`
--
ALTER TABLE `penalties`
  ADD PRIMARY KEY (`payment_no`),
  ADD KEY `FOREIGN` (`player_no`) USING BTREE;

--
-- Indexes for table `players`
--
ALTER TABLE `players`
  ADD PRIMARY KEY (`player_no`);

--
-- Indexes for table `teams`
--
ALTER TABLE `teams`
  ADD PRIMARY KEY (`team_no`),
  ADD KEY `FOREIGN` (`player_no`) USING BTREE;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `committee`
--
ALTER TABLE `committee`
  ADD CONSTRAINT `committee_ibfk_1` FOREIGN KEY (`player_no`) REFERENCES `players` (`player_no`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `matches`
--
ALTER TABLE `matches`
  ADD CONSTRAINT `matches_ibfk_1` FOREIGN KEY (`player_no`) REFERENCES `players` (`player_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `matches_ibfk_2` FOREIGN KEY (`team_no`) REFERENCES `teams` (`team_no`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `penalties`
--
ALTER TABLE `penalties`
  ADD CONSTRAINT `penalties_ibfk_1` FOREIGN KEY (`player_no`) REFERENCES `players` (`player_no`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `teams`
--
ALTER TABLE `teams`
  ADD CONSTRAINT `teams_ibfk_1` FOREIGN KEY (`player_no`) REFERENCES `players` (`player_no`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
