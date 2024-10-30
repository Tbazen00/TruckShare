-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 12, 2024 at 05:18 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `truckshare`
--

-- --------------------------------------------------------

--
-- Table structure for table `conversation`
--

CREATE TABLE `conversation` (
  `conv_id` int(11) NOT NULL,
  `last_message` varbinary(255) DEFAULT NULL,
  `listing_id` int(11) NOT NULL,
  `msg_id` varbinary(255) DEFAULT NULL,
  `source_user_id` int(11) NOT NULL,
  `source_username` varchar(255) DEFAULT NULL,
  `support_needed` bit(1) NOT NULL,
  `target_user_id` int(11) NOT NULL,
  `target_username` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `conversation`
--

INSERT INTO `conversation` (`conv_id`, `last_message`, `listing_id`, `msg_id`, `source_user_id`, `source_username`, `support_needed`, `target_user_id`, `target_username`, `title`) VALUES
(52, NULL, 352, NULL, 52, 'userExample', b'0', 2, 'providerExample', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `conversation_seq`
--

CREATE TABLE `conversation_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `conversation_seq`
--

INSERT INTO `conversation_seq` (`next_val`) VALUES
(151);

-- --------------------------------------------------------

--
-- Table structure for table `listing`
--

CREATE TABLE `listing` (
  `listing_id` int(11) NOT NULL,
  `conv_id` int(11) NOT NULL,
  `details` varchar(255) DEFAULT NULL,
  `image` varbinary(255) DEFAULT NULL,
  `is_new` int(11) NOT NULL,
  `reserve_user_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `target_date` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `listing`
--

INSERT INTO `listing` (`listing_id`, `conv_id`, `details`, `image`, `is_new`, `reserve_user_id`, `status`, `target_date`, `title`, `user_id`, `username`) VALUES
(1, 1, '2018 15,000 GVWR, tows 9k', NULL, 0, 52, 2, NULL, 'Ford F-250', 2, 'asd'),
(2, 2, '2021, Black, Crew Cab, tow hitch provided', NULL, 0, 52, 1, NULL, 'RAM 2500', 2, 'asd'),
(202, 0, '11k lbs Capacity Gooseneck, low load floor, ramp included', NULL, 0, 0, 0, NULL, 'BigTex 14GN Tandem Flatbed', 2, NULL),
(302, 0, 'Silver, Ramp included, Dampened door, 5k capacity', NULL, 0, 0, 0, NULL, 'Carry-On 6\'x12\' Enclosed Box Trailer', 2, 'asd'),
(352, 52, 'Various attachments available, call or message me to inquire', NULL, 0, 0, 0, NULL, '2021 John Deere 1023E Utility Tractor', 2, 'asd');

-- --------------------------------------------------------

--
-- Table structure for table `listing_seq`
--

CREATE TABLE `listing_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `listing_seq`
--

INSERT INTO `listing_seq` (`next_val`) VALUES
(451);

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `msg_id` int(11) NOT NULL,
  `conv_id` int(11) NOT NULL,
  `payload` varchar(255) DEFAULT NULL,
  `source_user_id` int(11) NOT NULL,
  `target_user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`msg_id`, `conv_id`, `payload`, `source_user_id`, `target_user_id`) VALUES
(252, 52, '2024-06-11 23:00:26\nuserExample: Hi what attachments do you have available? ', 52, 0),
(302, 52, '2024-06-11 23:02:35\nproviderExample: Got a front loader, backhoe, seeder, and some more. What are you looking for? ', 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `message_seq`
--

CREATE TABLE `message_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `message_seq`
--

INSERT INTO `message_seq` (`next_val`) VALUES
(401);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `is_banned` bit(1) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_type` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `first_name`, `is_banned`, `last_name`, `password`, `user_type`, `username`) VALUES
(0, '', b'0', '', '', 0, 'Guest'),
(2, 'frst', b'0', 'last', '123', 1, 'providerExample'),
(52, 'fsrt', b'0', 'lst', '123', 1, 'userExample');

-- --------------------------------------------------------

--
-- Table structure for table `user_seq`
--

CREATE TABLE `user_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_seq`
--

INSERT INTO `user_seq` (`next_val`) VALUES
(151);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `conversation`
--
ALTER TABLE `conversation`
  ADD PRIMARY KEY (`conv_id`);

--
-- Indexes for table `listing`
--
ALTER TABLE `listing`
  ADD PRIMARY KEY (`listing_id`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`msg_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
