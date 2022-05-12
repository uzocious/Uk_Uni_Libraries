-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 12, 2022 at 04:30 PM
-- Server version: 5.7.26
-- PHP Version: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `uk_uni_libraries`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
CREATE TABLE IF NOT EXISTS `books` (
  `Book_ID` int(10) NOT NULL,
  `Title` varchar(250) NOT NULL,
  `Author` varchar(250) NOT NULL,
  `Year` varchar(50) NOT NULL,
  `Edition` varchar(250) DEFAULT NULL,
  `Publisher` varchar(250) NOT NULL,
  `Language` varchar(100) NOT NULL,
  `Pages` varchar(50) NOT NULL,
  PRIMARY KEY (`Book_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`Book_ID`, `Title`, `Author`, `Year`, `Edition`, `Publisher`, `Language`, `Pages`) VALUES
(159280336, 'Stock Market Wizards', 'Jack D. Schwager', '2008', NULL, 'Marketplace Books', 'English', '177'),
(602045456, 'Jack Ma and Alibaba', 'Yan Qicheng', '2018', NULL, 'Elex Media Komputindo', 'indonesian\r\n', '184'),
(978239536, 'Operations and Supply Chain Management for MBAs', 'Jack R. Meredith, Scott M. Shafer', '2008', '6th', 'Wiley', 'English', '374'),
(978019996, 'Great Songwriting Techniques', 'Jack Perricone', '2018', NULL, 'Oxford University Press', 'English', '398'),
(978031627, 'Stalking Jack the Ripper', 'Kerri Maniscalco', '2016', NULL, 'Jimmy Patterson', 'English', '336'),
(111827304, 'Hedge Fund Market Wizards', 'Jack D. Schwager, Ed Seykota', '2012', NULL, 'Wiley', 'English', '526'),
(617295981, 'Classic Computer Science Problems in Python', 'David Kopec', '2019', NULL, 'Manning', 'English', '206'),
(111884155, 'Data Science For Dummies', 'Lillian Pierson', '2015', '1st', 'John Wiley & Sons\r\n', 'English', '483'),
(978178588, 'Principles of Data Science', 'Sinan Ozdemir', '2012', NULL, 'Odin', 'English', '389'),
(978147375, 'Foundations of Computer Science', 'Behrouz Forouzan', '2018', '4th', 'Cengage', 'English', '706'),
(978149208, 'Software Engineering at Google', 'Hyrum Wright, Tom Manshreck, Titus Winters', '2020', NULL, 'O\'Reilly Media', 'English', '421'),
(978113858, 'Security for Software Engineers', 'James Helfrich', '2019', NULL, 'CRC Press\r\n', 'English', '350'),
(148420290, 'MATLAB Control Systems Engineering', 'Cesar Perez Lopez', '2014', NULL, 'Apress', 'English', '170'),
(973373970, 'Java Programming', 'Joyce Farrell', '2019', '9th', 'Seji', 'English', '898'),
(149194600, 'Fluent Python', 'Luciano Ramalho', '2015', '1st', 'O\'Reilly Media', 'English', '768');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
CREATE TABLE IF NOT EXISTS `students` (
  `Student_ID` int(10) NOT NULL,
  `Firstname` varchar(250) NOT NULL,
  `Lastname` varchar(250) NOT NULL,
  `Pass_Word` varchar(250) NOT NULL,
  PRIMARY KEY (`Student_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`Student_ID`, `Firstname`, `Lastname`, `Pass_Word`) VALUES
(1, 'Uzocious', 'U', 'ÿ³tñe7');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
