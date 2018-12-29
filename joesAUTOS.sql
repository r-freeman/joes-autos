-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 27, 2018 at 09:55 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `joesAUTOS`
--
CREATE DATABASE IF NOT EXISTS `joesAUTOS` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `joesAUTOS`;

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `employee_id` int(6) NOT NULL,
  `role` varchar(20) NOT NULL,
  `commission_rate` double(10,2) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telephone` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `town` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL,
  `post_code` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`employee_id`, `role`, `commission_rate`, `first_name`, `last_name`, `email`, `telephone`, `address`, `town`, `city`, `country`, `post_code`) VALUES
(4, 'Admin', 0.00, 'Kristen', 'Nelson', 'kirstenn@armyspy.com', '07819653827', '59 Scrimshire Lane', 'Aston Ingham', '', 'United Kingdom', 'HR9 6FL'),
(5, 'Admin', 0.00, 'Ryan', 'Freeman', '', '', '', '', '', '', ''),
(6, 'Sales Manager', 0.00, 'Byron', 'Tomlin', 'byront@joesautos.com', '07906120349', '55 Leicester Road', 'Auldhouse', '', 'United Kingdom', 'G75 4QP'),
(7, 'Salesman', 10.00, 'Ryan', '', '', '', '', '', '', '', ''),
(8, 'salesman', 10.00, 'John', 'Smith', '', '', '', '', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `motorbikes`
--

CREATE TABLE `motorbikes` (
  `motorbike_id` int(6) NOT NULL,
  `make` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `engine` varchar(20) NOT NULL,
  `frame` varchar(20) NOT NULL,
  `colour` varchar(20) NOT NULL,
  `year` int(4) NOT NULL,
  `price` double(10,2) NOT NULL DEFAULT '0.00',
  `stock` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `motorbikes`
--

INSERT INTO `motorbikes` (`motorbike_id`, `make`, `model`, `engine`, `frame`, `colour`, `year`, `price`, `stock`) VALUES
(3, 'Ducati', '696', '696cc', 'Sport', 'red', 2013, 5250.00, 1),
(5, 'Harley Davidson', 'FLH', '1690cc', 'Chopper', 'black', 2013, 13250.00, 10),
(6, 'Kawasaki', 'Ninja', '600cc', 'Sport', 'green', 2000, 1650.00, 1),
(7, 'Yamaha', 'YZ', '450cc', 'Motocross', 'blue', 2016, 3500.00, 16),
(9, 'Kawasaki', 'KX', '125cc', 'Motocross', 'green', 2010, 1450.00, 3);

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `sale_id` int(6) NOT NULL,
  `status` varchar(20) NOT NULL,
  `total` double(10,2) NOT NULL,
  `commission` double(10,2) NOT NULL,
  `employee_id` int(6) NOT NULL,
  `motorbike_id` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`sale_id`, `status`, `total`, `commission`, `employee_id`, `motorbike_id`) VALUES
(1, 'complete', 4305.00, 0.00, 7, 7),
(2, 'complete', 2029.50, 0.00, 7, 6),
(3, 'complete', 4305.00, 350.00, 7, 7),
(4, 'complete', 2029.50, 165.00, 7, 6),
(5, 'complete', 4305.00, 350.00, 7, 7),
(6, 'complete', 2029.50, 165.00, 7, 6),
(7, 'complete', 2029.50, 165.00, 7, 6),
(8, 'complete', 1783.50, 145.00, 8, 9),
(9, 'void', 4305.00, 350.00, 7, 7);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`employee_id`);

--
-- Indexes for table `motorbikes`
--
ALTER TABLE `motorbikes`
  ADD PRIMARY KEY (`motorbike_id`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`sale_id`),
  ADD KEY `motorbike_id` (`motorbike_id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `employee_id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `motorbikes`
--
ALTER TABLE `motorbikes`
  MODIFY `motorbike_id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `sale_id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `sales`
--
ALTER TABLE `sales`
  ADD CONSTRAINT `sales_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`),
  ADD CONSTRAINT `sales_ibfk_2` FOREIGN KEY (`motorbike_id`) REFERENCES `motorbikes` (`motorbike_id`),
  ADD CONSTRAINT `sales_ibfk_3` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
