-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 19 jan. 2024 à 00:35
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
  `date` date NOT NULL,
  `address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id`, `montant`, `customer_id`, `date`, `address`) VALUES
(4, 0, 8, '2023-12-13', ''),
(5, 2097, 9, '2023-12-14', ''),
(6, 1398, 8, '2023-12-15', ''),
(7, 1398, 8, '2023-12-16', ''),
(8, 1398, 8, '2023-12-16', ''),
(9, 1398, 8, '2023-12-16', ''),
(10, 1398, 8, '2023-12-17', ''),
(11, 1398, 8, '2023-12-19', ''),
(12, 1398, 8, '2023-12-19', ''),
(13, 1398, 8, '2023-12-20', ''),
(14, 699, 8, '2023-12-21', ''),
(15, 4893, 10, '2023-12-22', ''),
(16, 4893, 9, '2023-12-23', ''),
(17, 7689, 9, '2023-12-25', ''),
(18, 2796, 9, '2023-12-26', ''),
(19, 4893, 9, '2023-12-28', ''),
(20, 11883, 9, '2023-12-28', ''),
(21, 5592, 9, '2023-12-28', ''),
(22, 4194, 9, '2023-12-28', ''),
(23, 7689, 9, '2023-12-29', ''),
(24, 11883, 9, '2023-12-29', ''),
(25, 2137, 9, '2023-12-29', '');

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
  `phonenumber` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `fournisseur`
--

INSERT INTO `fournisseur` (`id`, `nom`, `prenom`, `email`, `adresse`, `phonenumber`) VALUES
(35, 'Faouzi', 'Chargui', 'Faouzi@gmail.com', 'Lagoulette', 97708777),
(36, 'Chokri', 'Jbeli', 'chokrijbeli@gmail.com', 'Khaireddine', 98653214),
(37, 'Aicha', 'Dhaoui', 'Aicha@gmail.com', 'SidiBouSaid', 56064618),
(39, 'wassef', 'chargui', 'fghjkl@gmail.com', 'fghjkl', 95885333),
(40, 'Aicha', 'Dhaoui', 'Aicha@gmail.com', 'SidiBouSaid', 56064618);

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
(31, 4, 23, 5),
(32, 3, 24, 5),
(33, 4, 24, 5),
(34, 5, 24, 7),
(35, 4, 25, 4),
(36, 3, 25, 3);

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
(3, 'Tarte', 699, 10, 'produit2.png', 'Available', 26, 6),
(4, 'Milk', 10, 14, 'produit1.png', 'Available', 40, 10),
(5, 'Huile', 699, 14, 'produit3.png', 'Available', 99, 8),
(6, 'Jus', 15, 14, 'produit6.png', 'Available', 61, 9),
(9, 'Par Defaut', 10000, 16, 'produit6.png', 'Unavailable', 12, 11),
(11, 'ghjkl', 564, 0, 'produit1.png', 'Unavailable', 0, 7);

-- --------------------------------------------------------

--
-- Structure de la table `stock`
--

CREATE TABLE `stock` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `marque` varchar(255) DEFAULT NULL,
  `categorie` enum('Complement_alimentaire','Cosmetique_Bio') DEFAULT NULL,
  `prix` decimal(10,2) DEFAULT NULL,
  `produit_id` int(11) DEFAULT NULL,
  `quantite` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `stock`
--

INSERT INTO `stock` (`id`, `nom`, `marque`, `categorie`, `prix`, `produit_id`, `quantite`) VALUES
(28, '3sal', 'Creme', 'Complement_alimentaire', 699.00, 6, 2234),
(30, 'Milk', 'nourriture', 'Complement_alimentaire', 10.00, 4, 120);

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
-- Index pour la table `stock`
--
ALTER TABLE `stock`
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT pour la table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `fournisseur`
--
ALTER TABLE `fournisseur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=46;

--
-- AUTO_INCREMENT pour la table `marque`
--
ALTER TABLE `marque`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `panier_id_produit_id`
--
ALTER TABLE `panier_id_produit_id`
  MODIFY `id` int(250) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT pour la table `produit`
--
ALTER TABLE `produit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `stock`
--
ALTER TABLE `stock`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
