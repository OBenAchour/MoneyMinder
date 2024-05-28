-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 27, 2024 at 11:48 PM
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
-- Database: `moneyminderdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `action`
--

CREATE TABLE `action` (
  `id_action` int(11) NOT NULL,
  `prix_achat` float DEFAULT NULL,
  `prix_vente` float DEFAULT NULL,
  `quantite` int(11) DEFAULT NULL,
  `id_portefeuille` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `assets`
--

CREATE TABLE `assets` (
  `id_asset` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `prix` float DEFAULT NULL,
  `id_categ` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `attachment`
--

CREATE TABLE `attachment` (
  `attachmentID` int(11) NOT NULL,
  `ReportID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `categorieassets`
--

CREATE TABLE `categorieassets` (
  `id_categ` int(11) NOT NULL,
  `categ` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `categoriedep`
--

CREATE TABLE `categoriedep` (
  `idCatDep` int(11) NOT NULL,
  `catDep` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `categorieobj`
--

CREATE TABLE `categorieobj` (
  `idObj` int(11) NOT NULL,
  `catObj` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `categorierev`
--

CREATE TABLE `categorierev` (
  `idCatRev` int(11) NOT NULL,
  `catRev` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `commentaire`
--

CREATE TABLE `commentaire` (
  `id_cmt` int(11) NOT NULL,
  `cmt` varchar(255) DEFAULT NULL,
  `id_sujet` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `depense`
--

CREATE TABLE `depense` (
  `id` int(11) NOT NULL,
  `idCatDep` int(11) DEFAULT NULL,
  `id_trans` int(11) DEFAULT NULL,
  `id_quote` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `errorcategory`
--

CREATE TABLE `errorcategory` (
  `ErrorID` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `report_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `frequence`
--

CREATE TABLE `frequence` (
  `id_freq` int(11) NOT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `objectif`
--

CREATE TABLE `objectif` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `montant_globale` float DEFAULT NULL,
  `echeance` float DEFAULT NULL,
  `mois` int(11) DEFAULT NULL,
  `annee` int(11) DEFAULT NULL,
  `commentaire` varchar(255) DEFAULT NULL,
  `id_cat_obj` int(11) DEFAULT NULL,
  `id_trans` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `portefeuille_actions`
--

CREATE TABLE `portefeuille_actions` (
  `id_portefeuille` int(11) NOT NULL,
  `date_creation` date DEFAULT NULL,
  `solde_investissement` float DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `quote_dep`
--

CREATE TABLE `quote_dep` (
  `id_quote_dep` int(11) NOT NULL,
  `quote` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `quote_rev`
--

CREATE TABLE `quote_rev` (
  `id_quote_rev` int(11) NOT NULL,
  `quote` varchar(500) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reporterror`
--

CREATE TABLE `reporterror` (
  `id` int(11) NOT NULL,
  `errorCode` int(11) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `response`
--

CREATE TABLE `response` (
  `id` int(11) NOT NULL,
  `responseText` varchar(255) DEFAULT NULL,
  `ReportID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `revenu`
--

CREATE TABLE `revenu` (
  `id` int(11) NOT NULL,
  `idCatRev` int(11) DEFAULT NULL,
  `id_trans` int(11) DEFAULT NULL,
  `id_quote` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE `status` (
  `statusID` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `report_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sujet`
--

CREATE TABLE `sujet` (
  `id_sujet` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `sujet` varchar(255) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `id_trans` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `montant` float DEFAULT NULL,
  `mois` int(11) DEFAULT NULL,
  `annee` int(11) DEFAULT NULL,
  `commentaire` varchar(255) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_freq` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `Date_naissance` date DEFAULT NULL,
  `tel` int(11) DEFAULT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `Mot_de_passe` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `wallet`
--

CREATE TABLE `wallet` (
  `id_wallet` int(11) NOT NULL,
  `Montant` float DEFAULT NULL,
  `id_trans` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `action`
--
ALTER TABLE `action`
  ADD PRIMARY KEY (`id_action`),
  ADD KEY `id_portefeuille` (`id_portefeuille`);

--
-- Indexes for table `assets`
--
ALTER TABLE `assets`
  ADD PRIMARY KEY (`id_asset`),
  ADD KEY `id_categ` (`id_categ`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `attachment`
--
ALTER TABLE `attachment`
  ADD PRIMARY KEY (`attachmentID`),
  ADD KEY `ReportID` (`ReportID`);

--
-- Indexes for table `categorieassets`
--
ALTER TABLE `categorieassets`
  ADD PRIMARY KEY (`id_categ`);

--
-- Indexes for table `categoriedep`
--
ALTER TABLE `categoriedep`
  ADD PRIMARY KEY (`idCatDep`);

--
-- Indexes for table `categorieobj`
--
ALTER TABLE `categorieobj`
  ADD PRIMARY KEY (`idObj`);

--
-- Indexes for table `categorierev`
--
ALTER TABLE `categorierev`
  ADD PRIMARY KEY (`idCatRev`);

--
-- Indexes for table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`id_cmt`),
  ADD KEY `id_sujet` (`id_sujet`);

--
-- Indexes for table `depense`
--
ALTER TABLE `depense`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idCatDep` (`idCatDep`),
  ADD KEY `fk_id_trans` (`id_trans`),
  ADD KEY `id_quote` (`id_quote`);

--
-- Indexes for table `errorcategory`
--
ALTER TABLE `errorcategory`
  ADD PRIMARY KEY (`ErrorID`),
  ADD KEY `report_id` (`report_id`);

--
-- Indexes for table `frequence`
--
ALTER TABLE `frequence`
  ADD PRIMARY KEY (`id_freq`);

--
-- Indexes for table `objectif`
--
ALTER TABLE `objectif`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idObj` (`id_cat_obj`),
  ADD KEY `id_trans` (`id_trans`);

--
-- Indexes for table `portefeuille_actions`
--
ALTER TABLE `portefeuille_actions`
  ADD PRIMARY KEY (`id_portefeuille`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `quote_dep`
--
ALTER TABLE `quote_dep`
  ADD PRIMARY KEY (`id_quote_dep`);

--
-- Indexes for table `quote_rev`
--
ALTER TABLE `quote_rev`
  ADD PRIMARY KEY (`id_quote_rev`);

--
-- Indexes for table `reporterror`
--
ALTER TABLE `reporterror`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `response`
--
ALTER TABLE `response`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ReportID` (`ReportID`);

--
-- Indexes for table `revenu`
--
ALTER TABLE `revenu`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idCatRev` (`idCatRev`),
  ADD KEY `id_trans` (`id_trans`),
  ADD KEY `id_quote` (`id_quote`);

--
-- Indexes for table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`statusID`),
  ADD KEY `report_id` (`report_id`);

--
-- Indexes for table `sujet`
--
ALTER TABLE `sujet`
  ADD PRIMARY KEY (`id_sujet`),
  ADD KEY `id_user` (`id_user`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id_trans`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_freq` (`id_freq`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `mail` (`mail`);

--
-- Indexes for table `wallet`
--
ALTER TABLE `wallet`
  ADD PRIMARY KEY (`id_wallet`),
  ADD KEY `wallet_ibfk_1` (`id_trans`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `action`
--
ALTER TABLE `action`
  MODIFY `id_action` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `assets`
--
ALTER TABLE `assets`
  MODIFY `id_asset` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `attachment`
--
ALTER TABLE `attachment`
  MODIFY `attachmentID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `categorieassets`
--
ALTER TABLE `categorieassets`
  MODIFY `id_categ` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `categoriedep`
--
ALTER TABLE `categoriedep`
  MODIFY `idCatDep` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `categorieobj`
--
ALTER TABLE `categorieobj`
  MODIFY `idObj` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `categorierev`
--
ALTER TABLE `categorierev`
  MODIFY `idCatRev` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `id_cmt` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `depense`
--
ALTER TABLE `depense`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `errorcategory`
--
ALTER TABLE `errorcategory`
  MODIFY `ErrorID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `frequence`
--
ALTER TABLE `frequence`
  MODIFY `id_freq` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `objectif`
--
ALTER TABLE `objectif`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `portefeuille_actions`
--
ALTER TABLE `portefeuille_actions`
  MODIFY `id_portefeuille` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `quote_dep`
--
ALTER TABLE `quote_dep`
  MODIFY `id_quote_dep` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `quote_rev`
--
ALTER TABLE `quote_rev`
  MODIFY `id_quote_rev` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reporterror`
--
ALTER TABLE `reporterror`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `response`
--
ALTER TABLE `response`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `revenu`
--
ALTER TABLE `revenu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
  MODIFY `statusID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sujet`
--
ALTER TABLE `sujet`
  MODIFY `id_sujet` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id_trans` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `wallet`
--
ALTER TABLE `wallet`
  MODIFY `id_wallet` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `action`
--
ALTER TABLE `action`
  ADD CONSTRAINT `action_ibfk_1` FOREIGN KEY (`id_portefeuille`) REFERENCES `portefeuille_actions` (`id_portefeuille`);

--
-- Constraints for table `assets`
--
ALTER TABLE `assets`
  ADD CONSTRAINT `assets_ibfk_1` FOREIGN KEY (`id_categ`) REFERENCES `categorieassets` (`id_categ`),
  ADD CONSTRAINT `assets_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Constraints for table `attachment`
--
ALTER TABLE `attachment`
  ADD CONSTRAINT `attachment_ibfk_1` FOREIGN KEY (`ReportID`) REFERENCES `reporterror` (`id`);

--
-- Constraints for table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `commentaire_ibfk_1` FOREIGN KEY (`id_sujet`) REFERENCES `sujet` (`id_sujet`);

--
-- Constraints for table `depense`
--
ALTER TABLE `depense`
  ADD CONSTRAINT `depense_ibfk_1` FOREIGN KEY (`idCatDep`) REFERENCES `categoriedep` (`idCatDep`),
  ADD CONSTRAINT `depense_ibfk_2` FOREIGN KEY (`id_quote`) REFERENCES `quote_dep` (`id_quote_dep`),
  ADD CONSTRAINT `fk_id_trans` FOREIGN KEY (`id_trans`) REFERENCES `transactions` (`id_trans`);

--
-- Constraints for table `errorcategory`
--
ALTER TABLE `errorcategory`
  ADD CONSTRAINT `errorcategory_ibfk_1` FOREIGN KEY (`report_id`) REFERENCES `reporterror` (`id`);

--
-- Constraints for table `objectif`
--
ALTER TABLE `objectif`
  ADD CONSTRAINT `objectif_ibfk_1` FOREIGN KEY (`id_cat_obj`) REFERENCES `categorieobj` (`idObj`),
  ADD CONSTRAINT `objectif_ibfk_2` FOREIGN KEY (`id_trans`) REFERENCES `transactions` (`id_trans`);

--
-- Constraints for table `portefeuille_actions`
--
ALTER TABLE `portefeuille_actions`
  ADD CONSTRAINT `portefeuille_actions_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Constraints for table `reporterror`
--
ALTER TABLE `reporterror`
  ADD CONSTRAINT `reporterror_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Constraints for table `response`
--
ALTER TABLE `response`
  ADD CONSTRAINT `response_ibfk_1` FOREIGN KEY (`ReportID`) REFERENCES `reporterror` (`id`);

--
-- Constraints for table `revenu`
--
ALTER TABLE `revenu`
  ADD CONSTRAINT `revenu_ibfk_1` FOREIGN KEY (`idCatRev`) REFERENCES `categorierev` (`idCatRev`),
  ADD CONSTRAINT `revenu_ibfk_2` FOREIGN KEY (`id_trans`) REFERENCES `transactions` (`id_trans`),
  ADD CONSTRAINT `revenu_ibfk_3` FOREIGN KEY (`id_quote`) REFERENCES `quote_rev` (`id_quote_rev`);

--
-- Constraints for table `status`
--
ALTER TABLE `status`
  ADD CONSTRAINT `status_ibfk_1` FOREIGN KEY (`report_id`) REFERENCES `reporterror` (`id`);

--
-- Constraints for table `sujet`
--
ALTER TABLE `sujet`
  ADD CONSTRAINT `sujet_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`);

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`id_freq`) REFERENCES `frequence` (`id_freq`);

--
-- Constraints for table `wallet`
--
ALTER TABLE `wallet`
  ADD CONSTRAINT `wallet_ibfk_1` FOREIGN KEY (`id_trans`) REFERENCES `transactions` (`id_trans`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
