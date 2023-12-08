-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2023. Dec 08. 01:43
-- Kiszolgáló verziója: 10.4.24-MariaDB
-- PHP verzió: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `wumpus`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `rank_tb`
--

CREATE TABLE `rank_tb` (
  `id` int(11) NOT NULL,
  `playerName` varchar(45) NOT NULL,
  `points` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `status`
--

CREATE TABLE `status` (
  `id` int(11) NOT NULL,
  `heroX` int(11) NOT NULL,
  `heroY` int(11) NOT NULL,
  `steps` int(11) NOT NULL,
  `size` int(11) NOT NULL,
  `xGold` int(11) NOT NULL,
  `yGold` int(11) NOT NULL,
  `xStone1` int(11) NOT NULL,
  `yStone1` int(11) NOT NULL,
  `xU` int(11) NOT NULL,
  `yU` int(11) NOT NULL,
  `xStone2` int(11) NOT NULL,
  `yStone2` int(11) NOT NULL,
  `xStone3` int(11) NOT NULL,
  `yStone3` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `direction` char(1) NOT NULL,
  `initialX` int(11) NOT NULL,
  `initialY` int(11) NOT NULL,
  `arrows` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- A tábla adatainak kiíratása `status`
--

INSERT INTO `status` (`id`, `heroX`, `heroY`, `steps`, `size`, `xGold`, `yGold`, `xStone1`, `yStone1`, `xU`, `yU`, `xStone2`, `yStone2`, `xStone3`, `yStone3`, `name`, `direction`, `initialX`, `initialY`, `arrows`) VALUES
(35, 1, 2, 0, 7, 0, 2, 1, 4, 2, 6, 3, 6, 6, 2, 'null', 'W', 1, 2, 0),
(36, 10, 3, 4, 11, 9, 8, 8, 6, 4, 0, 10, 3, 6, 3, 'null', 'E', 10, 3, 2);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `rank_tb`
--
ALTER TABLE `rank_tb`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`);

--
-- A tábla indexei `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_UNIQUE` (`id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `rank_tb`
--
ALTER TABLE `rank_tb`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT a táblához `status`
--
ALTER TABLE `status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
