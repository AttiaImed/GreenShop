-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 29 déc. 2023 à 00:10
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `esprit`
--

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id` int(11) NOT NULL,
  `montant` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id`, `montant`, `customer_id`, `date`) VALUES
(4, 0, 8, '2023-12-13'),
(5, 2097, 9, '2023-12-14'),
(6, 1398, 8, '2023-12-15'),
(7, 1398, 8, '2023-12-16'),
(8, 1398, 8, '2023-12-16'),
(9, 1398, 8, '2023-12-16'),
(10, 1398, 8, '2023-12-17'),
(11, 1398, 8, '2023-12-19'),
(12, 1398, 8, '2023-12-19'),
(13, 1398, 8, '2023-12-20'),
(14, 699, 8, '2023-12-21'),
(15, 4893, 10, '2023-12-22'),
(16, 4893, 9, '2023-12-23'),
(17, 7689, 9, '2023-12-25'),
(18, 2796, 9, '2023-12-26'),
(19, 4893, 9, '2023-12-28'),
(20, 11883, 9, '2023-12-28'),
(21, 5592, 9, '2023-12-28'),
(22, 4194, 9, '2023-12-28'),
(23, 7689, 9, '2023-12-29');

-- --------------------------------------------------------

--
-- Structure de la table `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `username` varchar(250) NOT NULL,
  `path` varchar(250) NOT NULL,
  `date` varchar(250) NOT NULL,
  `cID` int(250) NOT NULL,
  `password` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `employee`
--

INSERT INTO `employee` (`id`, `username`, `path`, `date`, `cID`, `password`) VALUES
(1, 'imed', 'imed', '10/12/1995', 12345678, 'imed');

-- --------------------------------------------------------

--
-- Structure de la table `fournisseur`
--

CREATE TABLE `fournisseur` (
  `id` int(11) NOT NULL,
  `nom` varchar(25) NOT NULL,
  `prenom` varchar(25) NOT NULL,
  `email` varchar(30) NOT NULL,
  `adresse` varchar(30) NOT NULL,
  `phonenumber` int(11) NOT NULL,
  `idProduit` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `fournisseur`
--

INSERT INTO `fournisseur` (`id`, `nom`, `prenom`, `email`, `adresse`, `phonenumber`, `idProduit`) VALUES
(17, 'Faouzi', 'Chargui', 'Faouzi@gmail.com', 'Lagoulette', 97708777, 0),
(19, 'Aicha', 'Dhaoui', 'Aicha@gmail.com', 'SidiBouSaid', 56064618, 0),
(20, 'Faouzi', 'Chargui', 'Faouzi@gmail.com', 'Lagoulette', 97708777, 0),
(22, 'Aicha', 'Dhaoui', 'Aicha@gmail.com', 'SidiBouSaid', 56064618, 0),
(23, 'Faouzi', 'Chargui', 'Faouzi@gmail.com', 'Lagoulette', 97708777, 0),
(25, 'Aicha', 'Dhaoui', 'Aicha@gmail.com', 'SidiBouSaid', 56064618, 0),
(28, 'Aicha', 'Dhaoui', 'Aicha@gmail.com', 'SidiBouSaid', 56064618, 0),
(29, 'Attia', 'Imed', 'Attia.imed@gmail.com', 'HayelKadhra', 90605020, 0),
(30, 'Krid', 'Walid', 'walid.krid@gmail.com', 'la goulette casino', 65983265, 0),
(31, 'Belghali', 'Marwen', 'marwenbelghali@gmail.com', 'Haynoubou', 29880632, 0),
(32, 'Faouzi', 'Chargui', 'Faouzi@gmail.com', 'Lagoulette', 97708777, 0),
(34, 'Aicha', 'Dhaoui', 'Aicha@gmail.com', 'SidiBouSaid', 56064618, 0),
(35, 'Faouzi', 'Chargui', 'Faouzi@gmail.com', 'Lagoulette', 97708777, 0),
(36, 'Chokri', 'Jbeli', 'chokrijbeli@gmail.com', 'Khaireddine', 98653214, 0),
(37, 'Aicha', 'Dhaoui', 'Aicha@gmail.com', 'SidiBouSaid', 56064618, 0),
(39, 'Chokri', 'Jbeli', 'chokrijbeli@gmail.com', 'Khaireddine', 98653214, 0),
(40, 'Aicha', 'Dhaoui', 'Aicha@gmail.com', 'SidiBouSaid', 56064618, 0),
(41, 'aichoucha', 'Dhaoui', 'sidibou@gmail.com', 'sidibousaid', 65984756, 0),
(43, 'Chargui', 'wess', 'wess777@gmail.com', 'casino city', 90026584, 0);

-- --------------------------------------------------------

--
-- Structure de la table `marque`
--

CREATE TABLE `marque` (
  `id` int(11) NOT NULL,
  `nom` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `marque`
--

INSERT INTO `marque` (`id`, `nom`) VALUES
(6, 'Sony '),
(7, 'BioTeck'),
(8, 'Bio Produit'),
(9, 'Creme'),
(10, 'nourriture'),
(11, 'Service'),
(12, 'Sony Updated');

-- --------------------------------------------------------

--
-- Structure de la table `panier_id_produit_id`
--

CREATE TABLE `panier_id_produit_id` (
  `id` int(250) NOT NULL,
  `produit_id` int(250) NOT NULL,
  `panier_id` int(250) NOT NULL,
  `quantity` int(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `panier_id_produit_id`
--

INSERT INTO `panier_id_produit_id` (`id`, `produit_id`, `panier_id`, `quantity`) VALUES
(1, 3, 2, 1),
(2, 4, 2, 1),
(3, 3, 3, 1),
(4, 3, 4, 2),
(5, 6, 4, 1),
(6, 3, 5, 2),
(7, 4, 5, 1),
(8, 3, 6, 2),
(9, 3, 7, 2),
(10, 3, 8, 2),
(11, 3, 9, 2),
(12, 3, 10, 2),
(13, 3, 11, 2),
(14, 3, 12, 2),
(15, 3, 13, 2),
(16, 3, 14, 1),
(17, 3, 15, 3),
(18, 4, 15, 4),
(19, 3, 16, 3),
(20, 4, 16, 4),
(21, 3, 17, 7),
(22, 4, 17, 4),
(23, 6, 18, 4),
(24, 4, 19, 7),
(25, 4, 20, 7),
(26, 6, 20, 10),
(27, 6, 21, 8),
(28, 6, 22, 6),
(29, 3, 23, 3),
(30, 5, 23, 3),
(31, 4, 23, 5);

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE `produit` (
  `id` int(11) NOT NULL,
  `nom` varchar(250) NOT NULL,
  `prix` double NOT NULL,
  `stock` int(250) NOT NULL,
  `image` varchar(250) NOT NULL,
  `status` varchar(250) NOT NULL,
  `quantity` int(250) NOT NULL,
  `marque` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `nom`, `prix`, `stock`, `image`, `status`, `quantity`, `marque`) VALUES
(3, 'fghjklmù', 699, 10, 'produit2.png', 'Available', 23, 6),
(4, 'lol44', 699, 14, 'produit1.png', 'Available', 36, 11),
(5, 'Updated TV', 699, 14, 'produit3.png', 'Available', 92, 11),
(6, '3sal', 699, 14, 'produit6.png', 'Available', 61, 9),
(9, 'Orange', 699, 16, 'produit6.png', 'Available', 12, 6);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `nom` varchar(10) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `Type` varchar(250) NOT NULL,
  `Date_inscription` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `email`, `password`, `Type`, `Date_inscription`) VALUES
(2, 'test1', 'imed', 'imed', 'CUSTOMER', '2023-12-19'),
(5, 'test1', 'test@gmail.com', '123', 'CUSTOMER', '2023-12-27'),
(9, 'bika', 'bika', 'imed123', 'CUSTOMER', '2023-12-27');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `marque`
--
ALTER TABLE `marque`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `panier_id_produit_id`
--
ALTER TABLE `panier_id_produit_id`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `produit`
--
ALTER TABLE `produit`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT pour la table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT pour la table `marque`
--
ALTER TABLE `marque`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `panier_id_produit_id`
--
ALTER TABLE `panier_id_produit_id`
  MODIFY `id` int(250) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
