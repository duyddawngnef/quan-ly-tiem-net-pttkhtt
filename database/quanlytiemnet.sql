-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               9.5.0 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.14.0.7165
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for quanlytiemnet_simple
CREATE DATABASE IF NOT EXISTS `quanlytiemnet_simple` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `quanlytiemnet_simple`;

-- Dumping structure for table quanlytiemnet_simple.chitiethoadon
CREATE TABLE IF NOT EXISTS `chitiethoadon` (
  `MaCTHD` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaHD` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `LoaiChiTiet` enum('GIOCHOI','DICHVU') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MoTa` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SoLuong` decimal(10,2) NOT NULL DEFAULT '1.00',
  `DonGia` decimal(10,2) NOT NULL,
  `ThanhTien` decimal(12,2) NOT NULL,
  PRIMARY KEY (`MaCTHD`),
  KEY `idx_mahd_ct` (`MaHD`),
  KEY `idx_loaict` (`LoaiChiTiet`),
  CONSTRAINT `fk_cthd_hoadon` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`),
  CONSTRAINT `chk_dongia_ct` CHECK ((`DonGia` >= 0)),
  CONSTRAINT `chk_soluong_ct` CHECK ((`SoLuong` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table quanlytiemnet_simple.chitiethoadon: ~109 rows (approximately)
INSERT INTO `chitiethoadon` (`MaCTHD`, `MaHD`, `LoaiChiTiet`, `MoTa`, `SoLuong`, `DonGia`, `ThanhTien`) VALUES
	('CTHD001', 'HD001', 'DICHVU', 'Sting Dâu', 1.00, 12000.00, 12000.00),
	('CTHD002', 'HD002', 'GIOCHOI', 'Tiền giờ chơi', 3.00, 5000.00, 15000.00),
	('CTHD003', 'HD002', 'DICHVU', 'Mì Tôm Trứng', 1.00, 25000.00, 25000.00),
	('CTHD004', 'HD002', 'DICHVU', 'Sting Dâu', 1.00, 12000.00, 12000.00),
	('CTHD005', 'HD003', 'GIOCHOI', 'Tiền giờ chơi', 2.00, 12000.00, 24000.00),
	('CTHD006', 'HD003', 'DICHVU', 'Cafe Sữa Đá', 2.00, 15000.00, 30000.00),
	('CTHD007', 'HD004', 'GIOCHOI', 'Trừ giờ combo/gói', 2.00, 0.00, 0.00),
	('CTHD008', 'HD005', 'GIOCHOI', 'Tiền giờ chơi', 4.00, 8000.00, 32000.00),
	('CTHD009', 'HD005', 'DICHVU', 'Cơm Chiên Dương Châu', 1.00, 35000.00, 35000.00),
	('CTHD010', 'HD005', 'DICHVU', 'Coca Cola', 1.00, 12000.00, 12000.00),
	('CTHD011', 'HD006', 'GIOCHOI', 'Tiền giờ chơi', 1.50, 6000.00, 9000.00),
	('CTHD012', 'HD007', 'GIOCHOI', 'Trừ giờ combo/gói', 2.00, 0.00, 0.00),
	('CTHD013', 'HD008', 'GIOCHOI', 'Tiền giờ chơi', 2.50, 5000.00, 12500.00),
	('CTHD014', 'HD008', 'DICHVU', 'Nước Suối', 2.00, 8000.00, 16000.00),
	('CTHD015', 'HD009', 'GIOCHOI', 'Trừ giờ combo/gói', 3.50, 0.00, 0.00),
	('CTHD016', 'HD010', 'GIOCHOI', 'Tiền giờ chơi', 2.00, 8000.00, 16000.00),
	('CTHD017', 'HD010', 'DICHVU', 'Bò Húc', 1.00, 15000.00, 15000.00),
	('CTHD018', 'HD011', 'GIOCHOI', 'Tiền giờ chơi', 4.00, 5000.00, 20000.00),
	('CTHD019', 'HD011', 'DICHVU', 'Sting Dâu', 1.00, 12000.00, 12000.00),
	('CTHD020', 'HD011', 'DICHVU', 'Nước Suối', 1.00, 8000.00, 8000.00),
	('CTHD021', 'HD012', 'GIOCHOI', 'Tiền giờ chơi', 5.00, 8000.00, 40000.00),
	('CTHD022', 'HD012', 'DICHVU', 'Khoai Tây Chiên', 1.00, 20000.00, 20000.00),
	('CTHD023', 'HD012', 'DICHVU', 'Coca Cola', 2.00, 12000.00, 24000.00),
	('CTHD024', 'HD013', 'GIOCHOI', 'Tiền giờ chơi', 3.00, 6000.00, 18000.00),
	('CTHD025', 'HD013', 'DICHVU', 'Sting Dâu', 1.00, 12000.00, 12000.00),
	('CTHD026', 'HD014', 'GIOCHOI', 'Tiền giờ chơi', 3.00, 10000.00, 30000.00),
	('CTHD027', 'HD014', 'DICHVU', 'Thẻ Game Garena 20k', 1.00, 20000.00, 20000.00),
	('CTHD028', 'HD015', 'GIOCHOI', 'Tiền giờ chơi', 2.00, 5000.00, 10000.00),
	('CTHD029', 'HD016', 'GIOCHOI', 'Tiền giờ chơi', 4.00, 8000.00, 32000.00),
	('CTHD030', 'HD016', 'DICHVU', 'Bò Húc', 1.00, 15000.00, 15000.00),
	('CTHD031', 'HD016', 'DICHVU', 'Mì Tôm Trứng', 1.00, 25000.00, 25000.00),
	('CTHD032', 'HD017', 'GIOCHOI', 'Tiền giờ chơi', 2.00, 6000.00, 12000.00),
	('CTHD033', 'HD018', 'GIOCHOI', 'Trừ giờ combo/gói', 3.00, 0.00, 0.00),
	('CTHD034', 'HD019', 'GIOCHOI', 'Tiền giờ chơi', 2.00, 8000.00, 16000.00),
	('CTHD035', 'HD020', 'GIOCHOI', 'Trừ giờ combo/gói', 2.50, 0.00, 0.00),
	('CTHD036', 'HD021', 'GIOCHOI', 'Trừ giờ combo/gói', 1.50, 0.00, 0.00),
	('CTHD037', 'HD022', 'GIOCHOI', 'Trừ giờ combo/gói', 3.00, 0.00, 0.00),
	('CTHD038', 'HD023', 'GIOCHOI', 'Tiền giờ chơi', 0.50, 8000.00, 4000.00),
	('CTHD039', 'HD024', 'GIOCHOI', 'Trừ giờ combo/gói', 4.00, 0.00, 0.00),
	('CTHD040', 'HD025', 'GIOCHOI', 'Tiền giờ chơi', 4.00, 5000.00, 20000.00),
	('CTHD041', 'HD025', 'DICHVU', 'Cafe Sữa Đá', 2.00, 15000.00, 30000.00),
	('CTHD042', 'HD026', 'GIOCHOI', 'Tiền giờ chơi', 3.00, 8000.00, 24000.00),
	('CTHD043', 'HD026', 'DICHVU', 'Sting Dâu', 1.00, 12000.00, 12000.00),
	('CTHD044', 'HD027', 'GIOCHOI', 'Tiền giờ chơi', 3.50, 6000.00, 21000.00),
	('CTHD045', 'HD027', 'DICHVU', 'Mì Tôm Trứng', 1.00, 25000.00, 25000.00),
	('CTHD046', 'HD028', 'GIOCHOI', 'Tiền giờ chơi', 2.50, 5000.00, 12500.00),
	('CTHD047', 'HD028', 'DICHVU', 'Nước Suối', 2.00, 8000.00, 16000.00),
	('CTHD048', 'HD029', 'GIOCHOI', 'Tiền giờ chơi', 3.00, 12000.00, 36000.00),
	('CTHD049', 'HD029', 'DICHVU', 'Bánh Mì Ốp La', 2.00, 20000.00, 40000.00),
	('CTHD050', 'HD030', 'GIOCHOI', 'Trừ giờ combo/gói', 2.00, 0.00, 0.00),
	('CTHD051', 'HD031', 'GIOCHOI', 'Tiền giờ chơi', 3.00, 8000.00, 24000.00),
	('CTHD052', 'HD031', 'DICHVU', 'Coca Cola', 2.00, 12000.00, 24000.00),
	('CTHD053', 'HD032', 'GIOCHOI', 'Trừ giờ combo/gói', 3.00, 0.00, 0.00),
	('CTHD054', 'HD033', 'GIOCHOI', 'Tiền giờ chơi', 2.00, 5000.00, 10000.00),
	('CTHD055', 'HD033', 'DICHVU', 'Sting Dâu', 1.00, 12000.00, 12000.00),
	('CTHD056', 'HD034', 'GIOCHOI', 'Tiền giờ chơi', 2.50, 6000.00, 15000.00),
	('CTHD057', 'HD034', 'DICHVU', 'Cafe Sữa Đá', 1.00, 15000.00, 15000.00),
	('CTHD058', 'HD035', 'GIOCHOI', 'Trừ giờ combo/gói', 4.00, 0.00, 0.00),
	('CTHD059', 'HD036', 'GIOCHOI', 'Tiền giờ chơi', 4.00, 8000.00, 32000.00),
	('CTHD060', 'HD036', 'DICHVU', 'Bò Húc', 1.00, 15000.00, 15000.00),
	('CTHD061', 'HD036', 'DICHVU', 'Cơm Chiên Dương Châu', 1.00, 35000.00, 35000.00),
	('CTHD062', 'HD037', 'GIOCHOI', 'Trừ giờ combo/gói', 5.00, 0.00, 0.00),
	('CTHD063', 'HD038', 'GIOCHOI', 'Tiền giờ chơi', 3.00, 6000.00, 18000.00),
	('CTHD064', 'HD038', 'DICHVU', 'Coca Cola', 2.00, 12000.00, 24000.00),
	('CTHD065', 'HD039', 'GIOCHOI', 'Tiền giờ chơi', 4.00, 5000.00, 20000.00),
	('CTHD066', 'HD039', 'DICHVU', 'Khoai Tây Chiên', 1.00, 20000.00, 20000.00),
	('CTHD067', 'HD040', 'GIOCHOI', 'Tiền giờ chơi', 3.50, 6000.00, 21000.00),
	('CTHD068', 'HD041', 'GIOCHOI', 'Trừ giờ combo/gói', 4.00, 0.00, 0.00),
	('CTHD069', 'HD042', 'GIOCHOI', 'Trừ giờ combo/gói', 3.00, 0.00, 0.00),
	('CTHD070', 'HD043', 'GIOCHOI', 'Tiền giờ chơi', 4.00, 8000.00, 32000.00),
	('CTHD071', 'HD043', 'DICHVU', 'Mì Tôm Trứng', 1.00, 25000.00, 25000.00),
	('CTHD072', 'HD043', 'DICHVU', 'Coca Cola', 1.00, 12000.00, 12000.00),
	('CTHD073', 'HD044', 'GIOCHOI', 'Tiền giờ chơi', 4.00, 8000.00, 32000.00),
	('CTHD074', 'HD044', 'DICHVU', 'Bò Húc', 1.00, 15000.00, 15000.00),
	('CTHD075', 'HD045', 'GIOCHOI', 'Trừ giờ combo/gói', 5.00, 0.00, 0.00),
	('CTHD076', 'HD046', 'GIOCHOI', 'Trừ giờ combo/gói', 3.00, 0.00, 0.00),
	('CTHD077', 'HD047', 'GIOCHOI', 'Tiền giờ chơi', 4.00, 5000.00, 20000.00),
	('CTHD078', 'HD047', 'DICHVU', 'Sting Dâu', 1.00, 12000.00, 12000.00),
	('CTHD079', 'HD047', 'DICHVU', 'Nước Suối', 1.00, 8000.00, 8000.00),
	('CTHD080', 'HD048', 'GIOCHOI', 'Tiền giờ chơi', 4.00, 10000.00, 40000.00),
	('CTHD081', 'HD048', 'DICHVU', 'Thẻ Game Garena 20k', 1.00, 20000.00, 20000.00),
	('CTHD082', 'HD048', 'DICHVU', 'Cafe Sữa Đá', 2.00, 15000.00, 30000.00),
	('CTHD083', 'HD049', 'GIOCHOI', 'Trừ giờ combo/gói', 4.00, 0.00, 0.00),
	('CTHD084', 'HD050', 'GIOCHOI', 'Tiền giờ chơi', 4.00, 8000.00, 32000.00),
	('CTHD085', 'HD050', 'DICHVU', 'Cơm Chiên Dương Châu', 1.00, 35000.00, 35000.00),
	('CTHD086', 'HD050', 'DICHVU', 'Coca Cola', 1.00, 12000.00, 12000.00),
	('CTHD087', 'HD051', 'GIOCHOI', 'Tiền giờ chơi', 2.00, 6000.00, 12000.00),
	('CTHD088', 'HD052', 'GIOCHOI', 'Tiền giờ chơi', 3.00, 5000.00, 15000.00),
	('CTHD089', 'HD052', 'DICHVU', 'Sting Dâu', 1.00, 12000.00, 12000.00),
	('CTHD090', 'HD053', 'GIOCHOI', 'Tiền giờ chơi', 2.00, 6000.00, 12000.00),
	('CTHD091', 'HD053', 'DICHVU', 'Nước Suối', 1.00, 8000.00, 8000.00),
	('CTHD092', 'HD059', 'GIOCHOI', 'Giờ chơi máy MAY018', 2.07, 6000.00, 12400.00),
	('CTHD093', 'HD059', 'DICHVU', 'Dịch vụ: DV004', 1.00, 35000.00, 35000.00),
	('CTHD094', 'HD059', 'DICHVU', 'Dịch vụ: DV002', 1.00, 12000.00, 12000.00),
	('CTHD095', 'HD060', 'GIOCHOI', 'Giờ chơi máy MAY018', 2.03, 6000.00, 12200.00),
	('CTHD096', 'HD060', 'DICHVU', 'Dịch vụ: DV004', 2.00, 35000.00, 70000.00),
	('CTHD097', 'HD061', 'GIOCHOI', 'Giờ chơi máy MAY018', 2.03, 6000.00, 12200.00),
	('CTHD098', 'HD061', 'DICHVU', 'Dịch vụ: DV003', 3.00, 25000.00, 75000.00),
	('CTHD099', 'HD062', 'GIOCHOI', 'Giờ chơi máy MAY017', 0.18, 6000.00, 1100.00),
	('CTHD100', 'HD062', 'DICHVU', 'Dịch vụ: DV004', 3.00, 35000.00, 105000.00),
	('CTHD101', 'HD062', 'DICHVU', 'Dịch vụ: DV004', 4.00, 35000.00, 140000.00),
	('CTHD102', 'HD063', 'DICHVU', 'Dịch vụ: DV002', 3.00, 12000.00, 36000.00),
	('CTHD103', 'HD064', 'GIOCHOI', 'Giờ chơi máy MAY018', 4.00, 6000.00, 24000.00),
	('CTHD104', 'HD065', 'GIOCHOI', 'Giờ chơi máy MAY012', 363.07, 10000.00, 3630666.67),
	('CTHD105', 'HD065', 'DICHVU', 'Dịch vụ: DV001', 1.00, 12000.00, 12000.00),
	('CTHD106', 'HD065', 'DICHVU', 'Dịch vụ: DV003', 1.00, 25000.00, 25000.00),
	('CTHD107', 'HD066', 'GIOCHOI', 'Giờ chơi máy MAY017', 0.03, 6000.00, 200.00),
	('CTHD108', 'HD066', 'DICHVU', 'Dịch vụ: DV002', 1.00, 12000.00, 12000.00),
	('CTHD109', 'HD066', 'DICHVU', 'Dịch vụ: DV001', 1.00, 12000.00, 12000.00);

-- Dumping structure for table quanlytiemnet_simple.chitietphieunhap
CREATE TABLE IF NOT EXISTS `chitietphieunhap` (
  `MaCTPN` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaPhieuNhap` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaDV` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SoLuong` int NOT NULL,
  `GiaNhap` decimal(10,2) NOT NULL COMMENT 'Giá nhập từ NCC',
  `ThanhTien` decimal(12,2) NOT NULL COMMENT 'SoLuong * GiaNhap',
  PRIMARY KEY (`MaCTPN`),
  KEY `idx_maphieunhap_ct` (`MaPhieuNhap`),
  KEY `idx_madv_ct` (`MaDV`),
  CONSTRAINT `fk_ctpn_dichvu` FOREIGN KEY (`MaDV`) REFERENCES `dichvu` (`MaDV`),
  CONSTRAINT `fk_ctpn_phieunhap` FOREIGN KEY (`MaPhieuNhap`) REFERENCES `phieunhaphang` (`MaPhieuNhap`) ON DELETE CASCADE,
  CONSTRAINT `chk_gianhap` CHECK ((`GiaNhap` >= 0)),
  CONSTRAINT `chk_soluong_pn` CHECK ((`SoLuong` > 0)),
  CONSTRAINT `chk_thanhtien_pn` CHECK ((`ThanhTien` = (`SoLuong` * `GiaNhap`)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table quanlytiemnet_simple.chitietphieunhap: ~34 rows (approximately)
INSERT INTO `chitietphieunhap` (`MaCTPN`, `MaPhieuNhap`, `MaDV`, `SoLuong`, `GiaNhap`, `ThanhTien`) VALUES
	('CTPN001', 'PN001', 'DV001', 5, 8000.00, 40000.00),
	('CTPN002', 'PN001', 'DV002', 5, 8000.00, 40000.00),
	('CTPN003', 'PN002', 'DV003', 2, 25000.00, 50000.00),
	('CTPN004', 'PN002', 'DV005', 2, 5000.00, 10000.00),
	('CTPN005', 'PN003', 'DV002', 4, 8000.00, 32000.00),
	('CTPN006', 'PN003', 'DV006', 1, 15000.00, 15000.00),
	('CTPN007', 'PN003', 'DV005', 1, 5000.00, 5000.00),
	('CTPN008', 'PN004', 'DV010', 2, 18000.00, 36000.00),
	('CTPN009', 'PN004', 'DV007', 1, 14000.00, 14000.00),
	('CTPN010', 'PN005', 'DV001', 3, 8000.00, 24000.00),
	('CTPN011', 'PN005', 'DV005', 3, 5000.00, 15000.00),
	('CTPN012', 'PN005', 'DV002', 1, 8000.00, 8000.00),
	('CTPN013', 'PN006', 'DV003', 2, 25000.00, 50000.00),
	('CTPN014', 'PN006', 'DV005', 4, 5000.00, 20000.00),
	('CTPN015', 'PN007', 'DV009', 3, 18000.00, 54000.00),
	('CTPN016', 'PN007', 'DV008', 1, 11000.00, 11000.00),
	('CTPN017', 'PN008', 'DV001', 4, 8000.00, 32000.00),
	('CTPN018', 'PN008', 'DV006', 1, 15000.00, 15000.00),
	('CTPN019', 'PN008', 'DV002', 1, 8000.00, 8000.00),
	('CTPN020', 'PN009', 'DV010', 2, 18000.00, 36000.00),
	('CTPN021', 'PN009', 'DV007', 1, 14000.00, 14000.00),
	('CTPN022', 'PN010', 'DV001', 4, 8000.00, 32000.00),
	('CTPN023', 'PN010', 'DV002', 3, 8000.00, 24000.00),
	('CTPN024', 'PN011', 'DV003', 2, 25000.00, 50000.00),
	('CTPN025', 'PN011', 'DV005', 1, 5000.00, 5000.00),
	('CTPN026', 'PN012', 'DV008', 2, 11000.00, 22000.00),
	('CTPN027', 'PN012', 'DV006', 1, 15000.00, 15000.00),
	('CTPN028', 'PN012', 'DV005', 2, 5000.00, 10000.00),
	('CTPN029', 'PN013', 'DV001', 3, 8000.00, 24000.00),
	('CTPN030', 'PN013', 'DV002', 2, 8000.00, 16000.00),
	('CTPN031', 'PN014', 'DV010', 2, 18000.00, 36000.00),
	('CTPN032', 'PN015', 'DV005', 4, 5000.00, 20000.00),
	('CTPN033', 'PN015', 'DV001', 2, 8000.00, 16000.00),
	('CTPN034', 'PN016', 'DV004', 18, 36000.00, 648000.00);

-- Dumping structure for table quanlytiemnet_simple.chuongtrinhkhuyenmai
CREATE TABLE IF NOT EXISTS `chuongtrinhkhuyenmai` (
  `MaCTKM` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `TenCT` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `LoaiKM` enum('PHANTRAM','SOTIEN','TANGGIO') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'PHANTRAM',
  `GiaTriKM` decimal(10,2) NOT NULL COMMENT '% hoặc số tiền hoặc số giờ',
  `DieuKienToiThieu` decimal(12,2) DEFAULT '0.00' COMMENT 'Số tiền nạp tối thiểu',
  `NgayBatDau` datetime NOT NULL,
  `NgayKetThuc` datetime NOT NULL,
  `TrangThai` enum('HOATDONG','NGUNG','HETHAN') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'HOATDONG',
  PRIMARY KEY (`MaCTKM`),
  KEY `idx_trangthai_km` (`TrangThai`),
  KEY `idx_ngay_km` (`NgayBatDau`,`NgayKetThuc`),
  CONSTRAINT `chk_dieukien` CHECK ((`DieuKienToiThieu` >= 0)),
  CONSTRAINT `chk_giatrikm` CHECK ((`GiaTriKM` > 0)),
  CONSTRAINT `chk_ngaykhuyenmai` CHECK ((`NgayKetThuc` > `NgayBatDau`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table quanlytiemnet_simple.chuongtrinhkhuyenmai: ~6 rows (approximately)
INSERT INTO `chuongtrinhkhuyenmai` (`MaCTKM`, `TenCT`, `LoaiKM`, `GiaTriKM`, `DieuKienToiThieu`, `NgayBatDau`, `NgayKetThuc`, `TrangThai`) VALUES
	('KM001', 'Khai Trương', 'PHANTRAM', 20.00, 50000.00, '2023-01-01 00:00:00', '2023-01-31 23:59:59', 'HETHAN'),
	('KM002', 'Chào Hè', 'TANGGIO', 2.00, 100000.00, '2023-06-01 00:00:00', '2023-08-31 23:59:59', 'HETHAN'),
	('KM003', 'Nạp Lần Đầu', 'PHANTRAM', 50.00, 20000.00, '2024-01-01 00:00:00', '2026-12-31 23:59:59', 'HOATDONG'),
	('KM004', 'Tặng Tiền', 'SOTIEN', 10000.00, 100000.00, '2024-01-01 00:00:00', '2026-12-31 23:59:59', 'HOATDONG'),
	('KM005', 'Flash Sale T3', 'PHANTRAM', 30.00, 150000.00, '2026-03-01 00:00:00', '2026-03-31 23:59:59', 'HOATDONG'),
	('KM006', 'Sinh Nhật Tiệm', 'SOTIEN', 20000.00, 50000.00, '2026-01-01 00:00:00', '2026-01-31 23:59:59', 'HETHAN');

-- Dumping structure for table quanlytiemnet_simple.dichvu
CREATE TABLE IF NOT EXISTS `dichvu` (
  `MaDV` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `TenDV` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `LoaiDV` enum('DOUONG','THUCPHAM','KHAC') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'DOUONG',
  `DonGia` decimal(10,2) NOT NULL,
  `DonViTinh` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'Cái',
  `SoLuongTon` int DEFAULT '0',
  `TrangThai` enum('CONHANG','HETHANG','NGUNGBAN') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'CONHANG',
  PRIMARY KEY (`MaDV`),
  KEY `idx_loaidv` (`LoaiDV`),
  KEY `idx_trangthai_dv` (`TrangThai`),
  CONSTRAINT `chk_dongia` CHECK ((`DonGia` >= 0)),
  CONSTRAINT `chk_soluongton` CHECK ((`SoLuongTon` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table quanlytiemnet_simple.dichvu: ~10 rows (approximately)
INSERT INTO `dichvu` (`MaDV`, `TenDV`, `LoaiDV`, `DonGia`, `DonViTinh`, `SoLuongTon`, `TrangThai`) VALUES
	('DV001', 'Sting Dâu', 'DOUONG', 12000.00, 'Chai', 119, 'CONHANG'),
	('DV002', 'Coca Cola', 'DOUONG', 12000.00, 'Lon', 96, 'CONHANG'),
	('DV003', 'Mì Tôm Trứng', 'THUCPHAM', 25000.00, 'Tô', 90, 'CONHANG'),
	('DV004', 'Cơm Chiên Dương Châu', 'THUCPHAM', 35000.00, 'Dĩa', 36, 'CONHANG'),
	('DV005', 'Nước Suối', 'DOUONG', 8000.00, 'Chai', 148, 'CONHANG'),
	('DV006', 'Bò Húc', 'DOUONG', 15000.00, 'Lon', 75, 'CONHANG'),
	('DV007', 'Khoai Tây Chiên', 'THUCPHAM', 20000.00, 'Dĩa', 38, 'CONHANG'),
	('DV008', 'Cafe Sữa Đá', 'DOUONG', 15000.00, 'Ly', 195, 'CONHANG'),
	('DV009', 'Thẻ Game Garena 20k', 'KHAC', 20000.00, 'Thẻ', 48, 'CONHANG'),
	('DV010', 'Bánh Mì Ốp La', 'THUCPHAM', 20000.00, 'Cái', 18, 'CONHANG');

-- Dumping structure for table quanlytiemnet_simple.goidichvu
CREATE TABLE IF NOT EXISTS `goidichvu` (
  `MaGoi` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `TenGoi` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `LoaiGoi` enum('THEOGIO','THEONGAY','THEOTUAN','THEOTHANG') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'THEOGIO',
  `SoGio` decimal(10,2) NOT NULL COMMENT 'Tổng số giờ trong gói',
  `SoNgayHieuLuc` int DEFAULT '30' COMMENT 'Số ngày có hiệu lực',
  `GiaGoc` decimal(12,2) NOT NULL COMMENT 'Giá nếu mua lẻ',
  `GiaGoi` decimal(12,2) NOT NULL COMMENT 'Giá bán gói',
  `ApDungChoKhu` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Tên khu áp dụng',
  `TrangThai` enum('HOATDONG','NGUNG') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'HOATDONG',
  PRIMARY KEY (`MaGoi`),
  KEY `idx_trangthai_goi` (`TrangThai`),
  KEY `idx_loaigoi` (`LoaiGoi`),
  CONSTRAINT `chk_giagoihople` CHECK (((`GiaGoi` > 0) and (`GiaGoi` <= `GiaGoc`))),
  CONSTRAINT `chk_sogio` CHECK ((`SoGio` > 0)),
  CONSTRAINT `chk_songayhieuluc` CHECK ((`SoNgayHieuLuc` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table quanlytiemnet_simple.goidichvu: ~5 rows (approximately)
INSERT INTO `goidichvu` (`MaGoi`, `TenGoi`, `LoaiGoi`, `SoGio`, `SoNgayHieuLuc`, `GiaGoc`, `GiaGoi`, `ApDungChoKhu`, `TrangThai`) VALUES
	('GOI001', 'Combo Sáng (3h)', 'THEOGIO', 3.00, 30, 15000.00, 12000.00, NULL, 'HOATDONG'),
	('GOI002', 'Combo Đêm (7h)', 'THEOGIO', 7.00, 30, 35000.00, 25000.00, NULL, 'HOATDONG'),
	('GOI003', 'Gói Ngày (10h)', 'THEONGAY', 10.00, 30, 50000.00, 40000.00, NULL, 'HOATDONG'),
	('GOI004', 'Gói Tuần (50h)', 'THEOTUAN', 50.00, 30, 250000.00, 200000.00, NULL, 'HOATDONG'),
	('GOI005', 'Gói VIP Tháng', 'THEOTHANG', 80.00, 30, 640000.00, 500000.00, 'Khu VIP (B)', 'HOATDONG');

-- Dumping structure for table quanlytiemnet_simple.goidichvu_khachhang
CREATE TABLE IF NOT EXISTS `goidichvu_khachhang` (
  `MaGoiKH` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaKH` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaGoi` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaNV` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SoGioBanDau` decimal(10,2) NOT NULL,
  `SoGioConLai` decimal(10,2) NOT NULL,
  `NgayMua` datetime DEFAULT CURRENT_TIMESTAMP,
  `NgayHetHan` datetime NOT NULL,
  `GiaMua` decimal(12,2) NOT NULL,
  `TrangThai` enum('CONHAN','HETHAN','DAHETGIO') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'CONHAN',
  PRIMARY KEY (`MaGoiKH`),
  KEY `idx_makh_goi` (`MaKH`),
  KEY `idx_makh_trangthai_goi` (`MaKH`,`TrangThai`),
  KEY `idx_trangthai_goikh` (`TrangThai`),
  KEY `idx_ngayhethan` (`NgayHetHan`),
  KEY `fk_goikh_goidichvu` (`MaGoi`),
  KEY `fk_goikh_nhanvien` (`MaNV`),
  CONSTRAINT `fk_goikh_goidichvu` FOREIGN KEY (`MaGoi`) REFERENCES `goidichvu` (`MaGoi`),
  CONSTRAINT `fk_goikh_khachhang` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`),
  CONSTRAINT `fk_goikh_nhanvien` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`),
  CONSTRAINT `chk_giamua` CHECK ((`GiaMua` > 0)),
  CONSTRAINT `chk_ngayhethan` CHECK ((`NgayHetHan` > `NgayMua`)),
  CONSTRAINT `chk_sogioconlai` CHECK ((`SoGioConLai` >= 0)),
  CONSTRAINT `chk_sogioconlai_max` CHECK ((`SoGioConLai` <= `SoGioBanDau`))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table quanlytiemnet_simple.goidichvu_khachhang: ~11 rows (approximately)
INSERT INTO `goidichvu_khachhang` (`MaGoiKH`, `MaKH`, `MaGoi`, `MaNV`, `SoGioBanDau`, `SoGioConLai`, `NgayMua`, `NgayHetHan`, `GiaMua`, `TrangThai`) VALUES
	('GOIKH001', 'KH001', 'GOI001', 'NV002', 3.00, 0.00, '2026-01-05 09:00:00', '2026-02-04 23:59:59', 12000.00, 'DAHETGIO'),
	('GOIKH002', 'KH002', 'GOI002', 'NV002', 7.00, 4.00, '2026-01-08 10:00:00', '2026-02-07 23:59:59', 25000.00, 'CONHAN'),
	('GOIKH003', 'KH004', 'GOI004', 'NV002', 50.00, 44.00, '2026-01-10 11:00:00', '2026-02-09 23:59:59', 200000.00, 'CONHAN'),
	('GOIKH004', 'KH007', 'GOI003', 'NV004', 10.00, 8.00, '2026-01-12 08:00:00', '2026-02-11 23:59:59', 40000.00, 'CONHAN'),
	('GOIKH005', 'KH009', 'GOI002', 'NV002', 7.00, 5.50, '2026-01-20 14:00:00', '2026-02-19 23:59:59', 25000.00, 'CONHAN'),
	('GOIKH006', 'KH012', 'GOI004', 'NV004', 50.00, 42.00, '2026-02-01 09:00:00', '2026-03-03 23:59:59', 200000.00, 'CONHAN'),
	('GOIKH007', 'KH003', 'GOI001', 'NV002', 3.00, 1.50, '2026-02-10 10:00:00', '2026-03-12 23:59:59', 12000.00, 'CONHAN'),
	('GOIKH008', 'KH006', 'GOI003', 'NV004', 10.00, 7.00, '2026-02-15 15:00:00', '2026-03-17 23:59:59', 40000.00, 'CONHAN'),
	('GOIKH009', 'KH011', 'GOI005', 'NV002', 80.00, 65.00, '2026-03-01 09:00:00', '2026-03-31 23:59:59', 500000.00, 'CONHAN'),
	('GOIKH010', 'KH014', 'GOI002', 'NV004', 7.00, 3.00, '2026-03-05 11:00:00', '2026-04-04 23:59:59', 25000.00, 'CONHAN'),
	('GOIKH011', 'KH002', 'GOI001', 'NV001', 3.00, 3.00, '2026-03-18 10:34:49', '2026-04-17 10:34:49', 12000.00, 'CONHAN');

-- Dumping structure for table quanlytiemnet_simple.hoadon
CREATE TABLE IF NOT EXISTS `hoadon` (
  `MaHD` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaPhien` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaKH` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaNV` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `NgayLap` datetime DEFAULT CURRENT_TIMESTAMP,
  `TienGioChoi` decimal(12,2) DEFAULT '0.00',
  `TienDichVu` decimal(12,2) DEFAULT '0.00',
  `TongTien` decimal(12,2) NOT NULL COMMENT 'TienGioChoi + TienDichVu',
  `GiamGia` decimal(12,2) DEFAULT '0.00',
  `ThanhToan` decimal(12,2) NOT NULL COMMENT 'TongTien - GiamGia',
  `PhuongThucTT` enum('TIENMAT','CHUYENKHOAN','MOMO','VNPAY','TAIKHOAN') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'TAIKHOAN',
  `TrangThai` enum('CHUATHANHTOAN','DATHANHTOAN') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'CHUATHANHTOAN',
  PRIMARY KEY (`MaHD`),
  UNIQUE KEY `uk_maphien` (`MaPhien`),
  KEY `idx_makh_hd` (`MaKH`),
  KEY `idx_ngaylap` (`NgayLap`),
  KEY `idx_trangthai_hd` (`TrangThai`),
  KEY `fk_hoadon_nhanvien` (`MaNV`),
  CONSTRAINT `fk_hoadon_khachhang` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`),
  CONSTRAINT `fk_hoadon_nhanvien` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`),
  CONSTRAINT `fk_hoadon_phien` FOREIGN KEY (`MaPhien`) REFERENCES `phiensudung` (`MaPhien`),
  CONSTRAINT `chk_giamgia` CHECK ((`GiamGia` >= 0)),
  CONSTRAINT `chk_giamgia_max` CHECK ((`GiamGia` <= `TongTien`)),
  CONSTRAINT `chk_tongtien_hd` CHECK ((`TongTien` = (`TienGioChoi` + `TienDichVu`)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table quanlytiemnet_simple.hoadon: ~66 rows (approximately)
INSERT INTO `hoadon` (`MaHD`, `MaPhien`, `MaKH`, `MaNV`, `NgayLap`, `TienGioChoi`, `TienDichVu`, `TongTien`, `GiamGia`, `ThanhToan`, `PhuongThucTT`, `TrangThai`) VALUES
	('HD001', 'PS001', 'KH001', 'NV002', '2026-01-05 12:10:00', 0.00, 12000.00, 12000.00, 0.00, 12000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD002', 'PS002', 'KH002', 'NV002', '2026-01-06 11:00:00', 15000.00, 37000.00, 52000.00, 0.00, 52000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD003', 'PS003', 'KH003', 'NV004', '2026-01-07 16:00:00', 24000.00, 30000.00, 54000.00, 0.00, 54000.00, 'TIENMAT', 'DATHANHTOAN'),
	('HD004', 'PS004', 'KH004', 'NV002', '2026-01-10 12:00:00', 0.00, 0.00, 0.00, 0.00, 0.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD005', 'PS005', 'KH005', 'NV002', '2026-01-11 22:00:00', 32000.00, 47000.00, 79000.00, 0.00, 79000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD006', 'PS006', 'KH006', 'NV004', '2026-01-12 10:30:00', 9000.00, 0.00, 9000.00, 0.00, 9000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD007', 'PS007', 'KH007', 'NV002', '2026-01-13 10:00:00', 0.00, 0.00, 0.00, 0.00, 0.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD008', 'PS008', 'KH008', 'NV002', '2026-01-14 17:30:00', 12500.00, 16000.00, 28500.00, 0.00, 28500.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD009', 'PS009', 'KH009', 'NV004', '2026-01-15 22:30:00', 0.00, 0.00, 0.00, 0.00, 0.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD010', 'PS010', 'KH010', 'NV002', '2026-01-16 12:00:00', 16000.00, 15000.00, 31000.00, 0.00, 31000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD011', 'PS011', 'KH011', 'NV004', '2026-01-17 12:00:00', 20000.00, 20000.00, 40000.00, 0.00, 40000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD012', 'PS012', 'KH012', 'NV002', '2026-01-18 19:00:00', 40000.00, 44000.00, 84000.00, 0.00, 84000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD013', 'PS013', 'KH013', 'NV004', '2026-01-19 23:00:00', 18000.00, 12000.00, 30000.00, 0.00, 30000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD014', 'PS014', 'KH014', 'NV002', '2026-01-20 12:00:00', 30000.00, 20000.00, 50000.00, 0.00, 50000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD015', 'PS015', 'KH001', 'NV002', '2026-01-21 17:00:00', 10000.00, 0.00, 10000.00, 0.00, 10000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD016', 'PS016', 'KH015', 'NV004', '2026-01-22 22:00:00', 32000.00, 40000.00, 72000.00, 0.00, 72000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD017', 'PS017', 'KH016', 'NV002', '2026-01-23 12:00:00', 12000.00, 0.00, 12000.00, 0.00, 12000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD018', 'PS018', 'KH002', 'NV004', '2026-01-25 12:00:00', 0.00, 0.00, 0.00, 0.00, 0.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD019', 'PS019', 'KH005', 'NV002', '2026-01-27 21:00:00', 16000.00, 0.00, 16000.00, 0.00, 16000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD020', 'PS020', 'KH004', 'NV004', '2026-01-29 12:30:00', 0.00, 0.00, 0.00, 0.00, 0.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD021', 'PS021', 'KH003', 'NV002', '2026-02-01 09:30:00', 0.00, 0.00, 0.00, 0.00, 0.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD022', 'PS022', 'KH006', 'NV004', '2026-02-02 17:00:00', 0.00, 0.00, 0.00, 0.00, 0.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD023', 'PS023', 'KH009', 'NV002', '2026-02-03 12:00:00', 4000.00, 0.00, 4000.00, 0.00, 4000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD024', 'PS024', 'KH012', 'NV004', '2026-02-04 13:00:00', 0.00, 0.00, 0.00, 0.00, 0.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD025', 'PS025', 'KH011', 'NV002', '2026-02-05 22:00:00', 20000.00, 30000.00, 50000.00, 0.00, 50000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD026', 'PS026', 'KH010', 'NV004', '2026-02-06 11:00:00', 24000.00, 12000.00, 36000.00, 0.00, 36000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD027', 'PS027', 'KH013', 'NV002', '2026-02-07 23:30:00', 21000.00, 25000.00, 46000.00, 0.00, 46000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD028', 'PS028', 'KH001', 'NV004', '2026-02-08 11:30:00', 12500.00, 16000.00, 28500.00, 0.00, 28500.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD029', 'PS029', 'KH014', 'NV002', '2026-02-10 17:00:00', 36000.00, 40000.00, 76000.00, 0.00, 76000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD030', 'PS030', 'KH007', 'NV004', '2026-02-11 10:00:00', 0.00, 0.00, 0.00, 0.00, 0.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD031', 'PS031', 'KH008', 'NV002', '2026-02-12 18:00:00', 24000.00, 24000.00, 48000.00, 0.00, 48000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD032', 'PS032', 'KH002', 'NV004', '2026-02-14 22:00:00', 0.00, 0.00, 0.00, 0.00, 0.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD033', 'PS033', 'KH015', 'NV002', '2026-02-15 12:00:00', 10000.00, 12000.00, 22000.00, 0.00, 22000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD034', 'PS034', 'KH016', 'NV004', '2026-02-17 22:30:00', 15000.00, 15000.00, 30000.00, 0.00, 30000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD035', 'PS035', 'KH004', 'NV002', '2026-02-18 12:00:00', 0.00, 0.00, 0.00, 0.00, 0.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD036', 'PS036', 'KH005', 'NV004', '2026-02-19 22:00:00', 32000.00, 50000.00, 82000.00, 0.00, 82000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD037', 'PS037', 'KH012', 'NV002', '2026-02-21 14:00:00', 0.00, 0.00, 0.00, 0.00, 0.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD038', 'PS038', 'KH009', 'NV004', '2026-02-23 23:00:00', 18000.00, 24000.00, 42000.00, 0.00, 42000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD039', 'PS039', 'KH011', 'NV002', '2026-02-25 12:00:00', 20000.00, 20000.00, 40000.00, 0.00, 40000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD040', 'PS040', 'KH013', 'NV004', '2026-02-27 17:30:00', 21000.00, 0.00, 21000.00, 0.00, 21000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD041', 'PS041', 'KH011', 'NV002', '2026-03-01 13:00:00', 0.00, 0.00, 0.00, 0.00, 0.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD042', 'PS042', 'KH006', 'NV004', '2026-03-02 17:00:00', 0.00, 0.00, 0.00, 0.00, 0.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD043', 'PS043', 'KH002', 'NV002', '2026-03-03 14:00:00', 32000.00, 37000.00, 69000.00, 0.00, 69000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD044', 'PS044', 'KH009', 'NV004', '2026-03-04 22:00:00', 32000.00, 15000.00, 47000.00, 0.00, 47000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD045', 'PS045', 'KH012', 'NV002', '2026-03-05 13:00:00', 0.00, 0.00, 0.00, 0.00, 0.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD046', 'PS046', 'KH014', 'NV004', '2026-03-06 17:00:00', 0.00, 0.00, 0.00, 0.00, 0.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD047', 'PS047', 'KH005', 'NV002', '2026-03-08 13:00:00', 20000.00, 20000.00, 40000.00, 0.00, 40000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD048', 'PS048', 'KH001', 'NV004', '2026-03-10 12:00:00', 40000.00, 50000.00, 90000.00, 9000.00, 81000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD049', 'PS049', 'KH007', 'NV002', '2026-03-11 18:00:00', 0.00, 0.00, 0.00, 0.00, 0.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD050', 'PS050', 'KH010', 'NV004', '2026-03-12 23:00:00', 32000.00, 47000.00, 79000.00, 0.00, 79000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD051', 'PS051', 'KH013', 'NV002', '2026-03-13 12:00:00', 12000.00, 0.00, 12000.00, 0.00, 12000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD052', 'PS052', 'KH015', 'NV004', '2026-03-14 23:00:00', 15000.00, 12000.00, 27000.00, 0.00, 27000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD053', 'PS053', 'KH016', 'NV002', '2026-03-15 11:00:00', 12000.00, 8000.00, 20000.00, 0.00, 20000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD054', 'PS056', 'KH008', 'NV002', '2026-03-17 22:03:34', 57750.00, 0.00, 57750.00, 0.00, 57750.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD055', 'PS054', 'KH003', 'NV002', '2026-03-18 10:29:11', 132416.67, 0.00, 132416.67, 0.00, 95000.00, 'TAIKHOAN', 'CHUATHANHTOAN'),
	('HD056', 'PS055', 'KH011', 'NV004', '2026-03-18 10:33:29', 255500.00, 0.00, 255500.00, 0.00, 110000.00, 'TIENMAT', 'DATHANHTOAN'),
	('HD057', 'PS058', 'KH014', 'NV001', '2026-03-18 10:55:43', 77100.00, 16000.00, 93100.00, 0.00, 93100.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD058', 'PS059', 'KH012', 'NV001', '2026-03-18 11:01:09', 6100.00, 25000.00, 31100.00, 0.00, 31100.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD059', 'PS060', 'KH012', 'NV001', '2026-03-18 11:10:06', 12400.00, 47000.00, 59400.00, 0.00, 59400.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD060', 'PS061', 'KH014', 'NV001', '2026-03-18 11:20:38', 12200.00, 35000.00, 47200.00, 0.00, 47200.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD061', 'PS062', 'KH013', 'NV001', '2026-03-18 11:23:59', 12200.00, 25000.00, 37200.00, 0.00, 37200.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD062', 'PS063', 'KH013', 'NV001', '2026-03-18 11:46:12', 1100.00, 70000.00, 71100.00, 0.00, 22800.00, 'TAIKHOAN', 'CHUATHANHTOAN'),
	('HD063', 'PS064', 'KH016', 'NV001', '2026-03-18 11:46:49', 0.00, 12000.00, 12000.00, 0.00, 12000.00, 'TAIKHOAN', 'DATHANHTOAN'),
	('HD064', 'PS065', 'KH014', 'NV001', '2026-03-18 12:48:32', 24000.00, 0.00, 24000.00, 0.00, 4700.00, 'TAIKHOAN', 'CHUATHANHTOAN'),
	('HD065', 'PS069', 'KH018', 'NV001', '2026-03-18 13:47:47', 3630666.67, 37000.00, 3667666.67, 0.00, 75000.00, 'TAIKHOAN', 'CHUATHANHTOAN'),
	('HD066', 'PS071', 'KH001', 'NV006', '2026-03-19 12:48:03', 200.00, 24000.00, 24200.00, 0.00, 24200.00, 'TAIKHOAN', 'DATHANHTOAN');

-- Dumping structure for table quanlytiemnet_simple.khachhang
CREATE TABLE IF NOT EXISTS `khachhang` (
  `MaKH` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Ho` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Ten` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SoDienThoai` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `TenDangNhap` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MatKhau` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SoDu` decimal(12,2) DEFAULT '0.00',
  `TrangThai` enum('HOATDONG','NGUNG') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'HOATDONG',
  PRIMARY KEY (`MaKH`),
  UNIQUE KEY `uk_tendangnhap_kh` (`TenDangNhap`),
  KEY `idx_sodienthoai` (`SoDienThoai`),
  KEY `idx_trangthai_kh` (`TrangThai`),
  CONSTRAINT `chk_sodu` CHECK ((`SoDu` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table quanlytiemnet_simple.khachhang: ~18 rows (approximately)
INSERT INTO `khachhang` (`MaKH`, `Ho`, `Ten`, `SoDienThoai`, `TenDangNhap`, `MatKhau`, `SoDu`, `TrangThai`) VALUES
	('KH001', 'Nguyễn', 'Hoàng Nam', '0901234561', 'hoangnam', '123456', 83800.00, 'HOATDONG'),
	('KH002', 'Lê', 'Thị Hồng', '0901234562', 'hongle', '123456', 168000.00, 'HOATDONG'),
	('KH003', 'Trần', 'Minh Tuấn', '0901234563', 'tuantran', '123456', 0.00, 'HOATDONG'),
	('KH004', 'Phạm', 'Quốc Anh', '0901234564', 'anhpham', '123456', 210000.00, 'HOATDONG'),
	('KH005', 'Hoàng', 'Thị Mai', '0901234565', 'maihoang', '123456', 50000.00, 'HOATDONG'),
	('KH006', 'Phan', 'Tuấn Kiệt', '0901234566', 'tuankiet', '123456', 130000.00, 'HOATDONG'),
	('KH007', 'Vũ', 'Minh Hiếu', '0901234567', 'hieugaming', '123456', 630000.00, 'HOATDONG'),
	('KH008', 'Đặng', 'Thị Mai', '0901234568', 'mai_cherry', '123456', 27250.00, 'HOATDONG'),
	('KH009', 'Ngô', 'Bảo Long', '0901234569', 'long_dragon', '123456', 160000.00, 'HOATDONG'),
	('KH010', 'Bùi', 'Phương Thảo', '0901234570', 'thaocute', '123456', 75000.00, 'HOATDONG'),
	('KH011', 'Đỗ', 'Hùng Dũng', '0901234571', 'dung_lol', '123456', 0.00, 'HOATDONG'),
	('KH012', 'Lý', 'Quang Hải', '0901234572', 'hai_fifa', '123456', 209500.00, 'HOATDONG'),
	('KH013', 'Trương', 'Mỹ Lan', '0901234573', 'lan_pubg', '123456', 0.00, 'HOATDONG'),
	('KH014', 'Hồ', 'Tấn Tài', '0901234574', 'tai_valorant', '123456', 0.00, 'HOATDONG'),
	('KH015', 'Dương', 'Văn Lâm', '0901234575', 'lam_csgo', '123456', 0.00, 'HOATDONG'),
	('KH016', 'Cao', 'Thị Ngọc', '0901234576', 'ngoc_aov', '123456', 8000.00, 'HOATDONG'),
	('KH017', 'Mai', 'Văn Phúc', '0901234577', 'phuc_ml', '123456', 5000.00, 'NGUNG'),
	('KH018', 'Pham', 'Dang', '0123456789', '0123456789', 'jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=', 0.00, 'HOATDONG');

-- Dumping structure for table quanlytiemnet_simple.khumay
CREATE TABLE IF NOT EXISTS `khumay` (
  `MaKhu` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `TenKhu` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `GiaCoSo` decimal(10,2) NOT NULL COMMENT 'Giá cơ sở mỗi giờ',
  `SoMayToiDa` int DEFAULT '0',
  `TrangThai` enum('HOATDONG','NGUNG') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'HOATDONG',
  PRIMARY KEY (`MaKhu`),
  UNIQUE KEY `uk_tenkhu` (`TenKhu`),
  KEY `idx_trangthai_khu` (`TrangThai`),
  CONSTRAINT `chk_giacoso` CHECK ((`GiaCoSo` > 0)),
  CONSTRAINT `chk_somaytoida` CHECK ((`SoMayToiDa` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table quanlytiemnet_simple.khumay: ~5 rows (approximately)
INSERT INTO `khumay` (`MaKhu`, `TenKhu`, `GiaCoSo`, `SoMayToiDa`, `TrangThai`) VALUES
	('KHU001', 'Khu Thường (A)', 5000.00, 20, 'HOATDONG'),
	('KHU002', 'Khu VIP (B)', 8000.00, 15, 'HOATDONG'),
	('KHU003', 'Khu Thi Đấu (S)', 10000.00, 10, 'HOATDONG'),
	('KHU004', 'Khu Couple (C)', 12000.00, 5, 'HOATDONG'),
	('KHU005', 'Khu Hút Thuốc (D)', 6000.00, 15, 'HOATDONG');

-- Dumping structure for table quanlytiemnet_simple.lichsunaptien
CREATE TABLE IF NOT EXISTS `lichsunaptien` (
  `MaNap` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaKH` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaNV` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaCTKM` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `SoTienNap` decimal(12,2) NOT NULL,
  `KhuyenMai` decimal(12,2) DEFAULT '0.00',
  `TongTienCong` decimal(12,2) NOT NULL COMMENT 'SoTienNap + KhuyenMai',
  `SoDuTruoc` decimal(12,2) NOT NULL,
  `SoDuSau` decimal(12,2) NOT NULL,
  `PhuongThuc` enum('TIENMAT','CHUYENKHOAN','MOMO','VNPAY','ZALOPAY','THE') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'TIENMAT',
  `MaGiaoDich` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `NgayNap` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`MaNap`),
  KEY `idx_makh_nap` (`MaKH`),
  KEY `idx_ngaynap` (`NgayNap`),
  KEY `idx_phuongthuc` (`PhuongThuc`),
  KEY `idx_mactkm` (`MaCTKM`),
  KEY `fk_naptien_nhanvien` (`MaNV`),
  CONSTRAINT `fk_naptien_khachhang` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`),
  CONSTRAINT `fk_naptien_khuyenmai` FOREIGN KEY (`MaCTKM`) REFERENCES `chuongtrinhkhuyenmai` (`MaCTKM`) ON DELETE SET NULL,
  CONSTRAINT `fk_naptien_nhanvien` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`),
  CONSTRAINT `chk_khuyenmai` CHECK ((`KhuyenMai` >= 0)),
  CONSTRAINT `chk_sodusau_nap` CHECK ((`SoDuSau` = (`SoDuTruoc` + `TongTienCong`))),
  CONSTRAINT `chk_sotiennap` CHECK ((`SoTienNap` > 0)),
  CONSTRAINT `chk_tongtien` CHECK ((`TongTienCong` = (`SoTienNap` + `KhuyenMai`)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table quanlytiemnet_simple.lichsunaptien: ~21 rows (approximately)
INSERT INTO `lichsunaptien` (`MaNap`, `MaKH`, `MaNV`, `MaCTKM`, `SoTienNap`, `KhuyenMai`, `TongTienCong`, `SoDuTruoc`, `SoDuSau`, `PhuongThuc`, `MaGiaoDich`, `NgayNap`) VALUES
	('NAP001', 'KH001', 'NV002', 'KM003', 50000.00, 25000.00, 75000.00, 0.00, 75000.00, 'TIENMAT', NULL, '2026-01-05 08:30:00'),
	('NAP002', 'KH002', 'NV002', NULL, 100000.00, 0.00, 100000.00, 50000.00, 150000.00, 'CHUYENKHOAN', NULL, '2026-01-06 09:00:00'),
	('NAP003', 'KH004', 'NV002', 'KM004', 200000.00, 10000.00, 210000.00, 10000.00, 220000.00, 'MOMO', NULL, '2026-01-08 10:30:00'),
	('NAP004', 'KH005', 'NV004', NULL, 50000.00, 0.00, 50000.00, 0.00, 50000.00, 'TIENMAT', NULL, '2026-01-10 11:00:00'),
	('NAP005', 'KH007', 'NV004', 'KM003', 500000.00, 250000.00, 750000.00, 0.00, 750000.00, 'VNPAY', 'VNPAY26010501', '2026-01-12 08:10:00'),
	('NAP006', 'KH001', 'NV002', NULL, 50000.00, 0.00, 50000.00, 75000.00, 125000.00, 'TIENMAT', NULL, '2026-01-15 14:00:00'),
	('NAP007', 'KH009', 'NV002', NULL, 100000.00, 0.00, 100000.00, 0.00, 100000.00, 'ZALOPAY', 'ZLP26012001', '2026-01-20 16:00:00'),
	('NAP008', 'KH006', 'NV004', 'KM006', 50000.00, 20000.00, 70000.00, 0.00, 70000.00, 'TIENMAT', NULL, '2026-01-22 09:00:00'),
	('NAP009', 'KH010', 'NV002', NULL, 100000.00, 0.00, 100000.00, 0.00, 100000.00, 'MOMO', 'MOMO26012501', '2026-01-25 10:00:00'),
	('NAP010', 'KH008', 'NV004', NULL, 100000.00, 0.00, 100000.00, 0.00, 100000.00, 'TIENMAT', NULL, '2026-01-28 15:00:00'),
	('NAP011', 'KH011', 'NV002', 'KM004', 100000.00, 10000.00, 110000.00, 0.00, 110000.00, 'CHUYENKHOAN', NULL, '2026-02-03 09:00:00'),
	('NAP012', 'KH012', 'NV002', NULL, 300000.00, 0.00, 300000.00, 0.00, 300000.00, 'VNPAY', 'VNPAY26020301', '2026-02-05 10:00:00'),
	('NAP013', 'KH003', 'NV004', NULL, 50000.00, 0.00, 50000.00, 0.00, 50000.00, 'TIENMAT', NULL, '2026-02-08 11:00:00'),
	('NAP014', 'KH013', 'NV002', 'KM003', 50000.00, 25000.00, 75000.00, 0.00, 75000.00, 'TIENMAT', NULL, '2026-02-12 14:00:00'),
	('NAP015', 'KH014', 'NV004', NULL, 100000.00, 0.00, 100000.00, 0.00, 100000.00, 'MOMO', 'MOMO26021501', '2026-02-15 09:30:00'),
	('NAP016', 'KH016', 'NV002', NULL, 50000.00, 0.00, 50000.00, 0.00, 50000.00, 'TIENMAT', NULL, '2026-02-20 16:00:00'),
	('NAP017', 'KH011', 'NV002', 'KM005', 200000.00, 60000.00, 260000.00, 110000.00, 370000.00, 'CHUYENKHOAN', NULL, '2026-03-02 09:00:00'),
	('NAP018', 'KH009', 'NV004', NULL, 100000.00, 0.00, 100000.00, 100000.00, 200000.00, 'ZALOPAY', 'ZLP26030501', '2026-03-05 10:00:00'),
	('NAP019', 'KH014', 'NV002', 'KM005', 150000.00, 45000.00, 195000.00, 100000.00, 295000.00, 'VNPAY', 'VNPAY26031001', '2026-03-10 11:00:00'),
	('NAP020', 'KH015', 'NV004', NULL, 50000.00, 0.00, 50000.00, 0.00, 50000.00, 'TIENMAT', NULL, '2026-03-12 14:00:00'),
	('NAP021', 'KH018', 'NV001', 'KM003', 50000.00, 25000.00, 75000.00, 0.00, 75000.00, 'TIENMAT', NULL, '2026-03-18 13:42:26');

-- Dumping structure for table quanlytiemnet_simple.maytinh
CREATE TABLE IF NOT EXISTS `maytinh` (
  `MaMay` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `TenMay` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaKhu` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CauHinh` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  `GiaMoiGio` decimal(10,2) NOT NULL,
  `TrangThai` enum('TRONG','DANGDUNG','BAOTRI','NGUNG') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'TRONG',
  PRIMARY KEY (`MaMay`),
  UNIQUE KEY `uk_tenmay` (`TenMay`),
  KEY `idx_trangthai_may` (`TrangThai`),
  KEY `idx_makhu` (`MaKhu`),
  CONSTRAINT `fk_maytinh_khumay` FOREIGN KEY (`MaKhu`) REFERENCES `khumay` (`MaKhu`) ON DELETE SET NULL,
  CONSTRAINT `chk_giamoigio` CHECK ((`GiaMoiGio` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table quanlytiemnet_simple.maytinh: ~18 rows (approximately)
INSERT INTO `maytinh` (`MaMay`, `TenMay`, `MaKhu`, `CauHinh`, `GiaMoiGio`, `TrangThai`) VALUES
	('MAY001', 'MAY-A01', 'KHU001', 'i3 10100F, GTX 1050Ti, 8GB RAM', 5000.00, 'TRONG'),
	('MAY002', 'MAY-A02', 'KHU001', 'i3 10100F, GTX 1050Ti, 8GB RAM', 5000.00, 'TRONG'),
	('MAY003', 'MAY-A03', 'KHU001', 'i3 10100F, GTX 1050Ti, 8GB RAM', 5000.00, 'TRONG'),
	('MAY004', 'MAY-A04', 'KHU001', 'i3 10100F, GTX 1050Ti, 8GB RAM', 5000.00, 'BAOTRI'),
	('MAY005', 'MAY-A05', 'KHU001', 'i3 10100F, GTX 1050Ti, 8GB RAM', 5000.00, 'TRONG'),
	('MAY006', 'MAY-A06', 'KHU001', 'i3 10100F, GTX 1050Ti, 8GB RAM', 5000.00, 'TRONG'),
	('MAY007', 'MAY-B01', 'KHU002', 'i5 12400F, RTX 3060, 16GB RAM', 8000.00, 'TRONG'),
	('MAY008', 'MAY-B02', 'KHU002', 'i5 12400F, RTX 3060, 16GB RAM', 8000.00, 'TRONG'),
	('MAY009', 'MAY-B03', 'KHU002', 'i5 12400F, RTX 3060, 16GB RAM', 8000.00, 'TRONG'),
	('MAY010', 'MAY-B04', 'KHU002', 'i5 12400F, RTX 3060, 16GB RAM', 8000.00, 'DANGDUNG'),
	('MAY011', 'MAY-S01', 'KHU003', 'i7 13700K, RTX 4070, 32GB RAM', 10000.00, 'TRONG'),
	('MAY012', 'MAY-S02', 'KHU003', 'i7 13700K, RTX 4070, 32GB RAM', 10000.00, 'TRONG'),
	('MAY013', 'MAY-S03', 'KHU003', 'i7 13700K, RTX 4070, 32GB RAM', 10000.00, 'TRONG'),
	('MAY014', 'MAY-C01', 'KHU004', 'i5 12400F, GTX 1660S, 16GB RAM', 12000.00, 'TRONG'),
	('MAY015', 'MAY-C02', 'KHU004', 'i5 12400F, GTX 1660S, 16GB RAM', 12000.00, 'TRONG'),
	('MAY016', 'MAY-D01', 'KHU005', 'i3 12100F, GTX 1650, 8GB RAM', 6000.00, 'DANGDUNG'),
	('MAY017', 'MAY-D02', 'KHU005', 'i3 12100F, GTX 1650, 8GB RAM', 6000.00, 'DANGDUNG'),
	('MAY018', 'MAY-D03', 'KHU005', 'i3 12100F, GTX 1650, 8GB RAM', 6000.00, 'DANGDUNG');

-- Dumping structure for table quanlytiemnet_simple.nhacungcap
CREATE TABLE IF NOT EXISTS `nhacungcap` (
  `MaNCC` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `TenNCC` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SoDienThoai` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `DiaChi` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `NguoiLienHe` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT 'Tên người đại diện/liên hệ',
  `TrangThai` enum('HOATDONG','NGUNG') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'HOATDONG',
  PRIMARY KEY (`MaNCC`),
  KEY `idx_trangthai_ncc` (`TrangThai`),
  KEY `idx_tenncc` (`TenNCC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table quanlytiemnet_simple.nhacungcap: ~5 rows (approximately)
INSERT INTO `nhacungcap` (`MaNCC`, `TenNCC`, `SoDienThoai`, `Email`, `DiaChi`, `NguoiLienHe`, `TrangThai`) VALUES
	('NCC001', 'Công ty Pepsico', '02839123456', 'contact@pepsi.vn', 'Q1, TP.HCM', 'Anh Nam', 'HOATDONG'),
	('NCC002', 'Đại lý Mì Hảo Hảo', '02839123457', 'sales@acecook.vn', 'Tân Bình, TP.HCM', 'Chị Lan', 'HOATDONG'),
	('NCC003', 'Vi tính Lê Phụng', '02839123458', 'support@lephung.vn', 'Q3, TP.HCM', 'Anh Minh', 'HOATDONG'),
	('NCC004', 'Coca Cola VN', '02839123459', 'sales@coca.vn', 'Thủ Đức, TP.HCM', 'Anh Tú', 'HOATDONG'),
	('NCC005', 'Bánh Mì Staff', '02839123460', 'order@banhmi.vn', 'Q10, TP.HCM', 'Cô Ba', 'HOATDONG');

-- Dumping structure for table quanlytiemnet_simple.nhanvien
CREATE TABLE IF NOT EXISTS `nhanvien` (
  `MaNV` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Ho` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `Ten` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ChucVu` enum('QUANLY','NHANVIEN','THUNGAN') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'NHANVIEN',
  `TenDangNhap` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MatKhau` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `TrangThai` enum('DANGLAMVIEC','NGHIVIEC') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'DANGLAMVIEC',
  PRIMARY KEY (`MaNV`),
  UNIQUE KEY `uk_tendangnhap_nv` (`TenDangNhap`),
  KEY `idx_trangthai_nv` (`TrangThai`),
  KEY `idx_chucvu` (`ChucVu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table quanlytiemnet_simple.nhanvien: ~6 rows (approximately)
INSERT INTO `nhanvien` (`MaNV`, `Ho`, `Ten`, `ChucVu`, `TenDangNhap`, `MatKhau`, `TrangThai`) VALUES
	('NV001', 'Nguyễn', 'Văn A', 'QUANLY', 'admin', '123456', 'DANGLAMVIEC'),
	('NV002', 'Trần', 'Thị B', 'THUNGAN', 'thungan01', '123456', 'DANGLAMVIEC'),
	('NV003', 'Lê', 'Văn C', 'NHANVIEN', 'kythuat01', '123456', 'DANGLAMVIEC'),
	('NV004', 'Phạm', 'Thị D', 'THUNGAN', 'thungan02', '123456', 'DANGLAMVIEC'),
	('NV005', 'Hoàng', 'Văn E', 'NHANVIEN', 'phucvu01', '123456', 'NGHIVIEC'),
	('NV006', 'Đặng', 'Thị F', 'NHANVIEN', 'phucvu02', '123456', 'DANGLAMVIEC');

-- Dumping structure for table quanlytiemnet_simple.phiensudung
CREATE TABLE IF NOT EXISTS `phiensudung` (
  `MaPhien` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaKH` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaMay` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaNV` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `MaGoiKH` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `GioBatDau` datetime NOT NULL,
  `GioKetThuc` datetime DEFAULT NULL,
  `TongGio` decimal(10,2) DEFAULT '0.00' COMMENT 'Tổng giờ chơi',
  `GioSuDungTuGoi` decimal(10,2) DEFAULT '0.00' COMMENT 'Giờ dùng từ gói',
  `GioSuDungTuTaiKhoan` decimal(10,2) DEFAULT '0.00' COMMENT 'Giờ dùng từ tài khoản',
  `GiaMoiGio` decimal(10,2) NOT NULL COMMENT 'Giá tại thời điểm sử dụng',
  `TienGioChoi` decimal(12,2) DEFAULT '0.00' COMMENT 'Tiền giờ chơi',
  `LoaiThanhToan` enum('TAIKHOAN','GOI','KETHOP') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'TAIKHOAN',
  `TrangThai` enum('DANGCHOI','DAKETTHUC') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'DANGCHOI',
  PRIMARY KEY (`MaPhien`),
  KEY `idx_makh_phien` (`MaKH`),
  KEY `idx_mamay_phien` (`MaMay`),
  KEY `idx_trangthai_phien` (`TrangThai`),
  KEY `idx_giobatdau` (`GioBatDau`),
  KEY `fk_phien_nhanvien` (`MaNV`),
  KEY `fk_phien_goikh` (`MaGoiKH`),
  CONSTRAINT `fk_phien_goikh` FOREIGN KEY (`MaGoiKH`) REFERENCES `goidichvu_khachhang` (`MaGoiKH`) ON DELETE SET NULL,
  CONSTRAINT `fk_phien_khachhang` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`),
  CONSTRAINT `fk_phien_maytinh` FOREIGN KEY (`MaMay`) REFERENCES `maytinh` (`MaMay`),
  CONSTRAINT `fk_phien_nhanvien` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`) ON DELETE SET NULL,
  CONSTRAINT `chk_giosdgoi` CHECK ((`GioSuDungTuGoi` >= 0)),
  CONSTRAINT `chk_giosdtk` CHECK ((`GioSuDungTuTaiKhoan` >= 0)),
  CONSTRAINT `chk_tiengiochoi` CHECK ((`TienGioChoi` >= 0)),
  CONSTRAINT `chk_tonggio` CHECK ((`TongGio` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table quanlytiemnet_simple.phiensudung: ~72 rows (approximately)
INSERT INTO `phiensudung` (`MaPhien`, `MaKH`, `MaMay`, `MaNV`, `MaGoiKH`, `GioBatDau`, `GioKetThuc`, `TongGio`, `GioSuDungTuGoi`, `GioSuDungTuTaiKhoan`, `GiaMoiGio`, `TienGioChoi`, `LoaiThanhToan`, `TrangThai`) VALUES
	('PS001', 'KH001', 'MAY001', 'NV002', 'GOIKH001', '2026-01-05 09:10:00', '2026-01-05 12:10:00', 3.00, 3.00, 0.00, 5000.00, 0.00, 'GOI', 'DAKETTHUC'),
	('PS002', 'KH002', 'MAY002', 'NV002', NULL, '2026-01-06 08:00:00', '2026-01-06 11:00:00', 3.00, 0.00, 3.00, 5000.00, 15000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS003', 'KH003', 'MAY014', 'NV004', NULL, '2026-01-07 14:00:00', '2026-01-07 16:00:00', 2.00, 0.00, 2.00, 12000.00, 24000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS004', 'KH004', 'MAY011', 'NV002', 'GOIKH003', '2026-01-10 10:00:00', '2026-01-10 12:00:00', 2.00, 2.00, 0.00, 10000.00, 0.00, 'GOI', 'DAKETTHUC'),
	('PS005', 'KH005', 'MAY007', 'NV002', NULL, '2026-01-11 18:00:00', '2026-01-11 22:00:00', 4.00, 0.00, 4.00, 8000.00, 32000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS006', 'KH006', 'MAY016', 'NV004', NULL, '2026-01-12 09:00:00', '2026-01-12 10:30:00', 1.50, 0.00, 1.50, 6000.00, 9000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS007', 'KH007', 'MAY012', 'NV002', 'GOIKH004', '2026-01-13 08:00:00', '2026-01-13 10:00:00', 2.00, 2.00, 0.00, 10000.00, 0.00, 'GOI', 'DAKETTHUC'),
	('PS008', 'KH008', 'MAY003', 'NV002', NULL, '2026-01-14 15:00:00', '2026-01-14 17:30:00', 2.50, 0.00, 2.50, 5000.00, 12500.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS009', 'KH009', 'MAY015', 'NV004', 'GOIKH005', '2026-01-15 19:00:00', '2026-01-15 22:30:00', 3.50, 3.50, 0.00, 6000.00, 0.00, 'GOI', 'DAKETTHUC'),
	('PS010', 'KH010', 'MAY009', 'NV002', NULL, '2026-01-16 10:00:00', '2026-01-16 12:00:00', 2.00, 0.00, 2.00, 8000.00, 16000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS011', 'KH011', 'MAY001', 'NV004', NULL, '2026-01-17 08:00:00', '2026-01-17 12:00:00', 4.00, 0.00, 4.00, 5000.00, 20000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS012', 'KH012', 'MAY008', 'NV002', NULL, '2026-01-18 14:00:00', '2026-01-18 19:00:00', 5.00, 0.00, 5.00, 8000.00, 40000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS013', 'KH013', 'MAY016', 'NV004', NULL, '2026-01-19 20:00:00', '2026-01-19 23:00:00', 3.00, 0.00, 3.00, 6000.00, 18000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS014', 'KH014', 'MAY013', 'NV002', NULL, '2026-01-20 09:00:00', '2026-01-20 12:00:00', 3.00, 0.00, 3.00, 10000.00, 30000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS015', 'KH001', 'MAY002', 'NV002', NULL, '2026-01-21 15:00:00', '2026-01-21 17:00:00', 2.00, 0.00, 2.00, 5000.00, 10000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS016', 'KH015', 'MAY006', 'NV004', NULL, '2026-01-22 18:00:00', '2026-01-22 22:00:00', 4.00, 0.00, 4.00, 8000.00, 32000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS017', 'KH016', 'MAY017', 'NV002', NULL, '2026-01-23 10:00:00', '2026-01-23 12:00:00', 2.00, 0.00, 2.00, 6000.00, 12000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS018', 'KH002', 'MAY010', 'NV004', 'GOIKH002', '2026-01-25 09:00:00', '2026-01-25 12:00:00', 3.00, 3.00, 0.00, 8000.00, 0.00, 'GOI', 'DAKETTHUC'),
	('PS019', 'KH005', 'MAY007', 'NV002', NULL, '2026-01-27 19:00:00', '2026-01-27 21:00:00', 2.00, 0.00, 2.00, 8000.00, 16000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS020', 'KH004', 'MAY011', 'NV004', 'GOIKH003', '2026-01-29 10:00:00', '2026-01-29 12:30:00', 2.50, 2.50, 0.00, 10000.00, 0.00, 'GOI', 'DAKETTHUC'),
	('PS021', 'KH003', 'MAY001', 'NV002', 'GOIKH007', '2026-02-01 08:00:00', '2026-02-01 09:30:00', 1.50, 1.50, 0.00, 5000.00, 0.00, 'GOI', 'DAKETTHUC'),
	('PS022', 'KH006', 'MAY015', 'NV004', 'GOIKH008', '2026-02-02 14:00:00', '2026-02-02 17:00:00', 3.00, 3.00, 0.00, 6000.00, 0.00, 'GOI', 'DAKETTHUC'),
	('PS023', 'KH009', 'MAY009', 'NV002', 'GOIKH005', '2026-02-03 10:00:00', '2026-02-03 12:00:00', 2.00, 1.50, 0.50, 8000.00, 4000.00, 'KETHOP', 'DAKETTHUC'),
	('PS024', 'KH012', 'MAY012', 'NV004', 'GOIKH006', '2026-02-04 09:00:00', '2026-02-04 13:00:00', 4.00, 4.00, 0.00, 10000.00, 0.00, 'GOI', 'DAKETTHUC'),
	('PS025', 'KH011', 'MAY002', 'NV002', NULL, '2026-02-05 18:00:00', '2026-02-05 22:00:00', 4.00, 0.00, 4.00, 5000.00, 20000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS026', 'KH010', 'MAY008', 'NV004', NULL, '2026-02-06 08:00:00', '2026-02-06 11:00:00', 3.00, 0.00, 3.00, 8000.00, 24000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS027', 'KH013', 'MAY018', 'NV002', NULL, '2026-02-07 20:00:00', '2026-02-07 23:30:00', 3.50, 0.00, 3.50, 6000.00, 21000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS028', 'KH001', 'MAY005', 'NV004', NULL, '2026-02-08 09:00:00', '2026-02-08 11:30:00', 2.50, 0.00, 2.50, 5000.00, 12500.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS029', 'KH014', 'MAY014', 'NV002', NULL, '2026-02-10 14:00:00', '2026-02-10 17:00:00', 3.00, 0.00, 3.00, 12000.00, 36000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS030', 'KH007', 'MAY013', 'NV004', 'GOIKH004', '2026-02-11 08:00:00', '2026-02-11 10:00:00', 2.00, 2.00, 0.00, 10000.00, 0.00, 'GOI', 'DAKETTHUC'),
	('PS031', 'KH008', 'MAY006', 'NV002', NULL, '2026-02-12 15:00:00', '2026-02-12 18:00:00', 3.00, 0.00, 3.00, 8000.00, 24000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS032', 'KH002', 'MAY009', 'NV004', 'GOIKH002', '2026-02-14 19:00:00', '2026-02-14 22:00:00', 3.00, 3.00, 0.00, 8000.00, 0.00, 'GOI', 'DAKETTHUC'),
	('PS033', 'KH015', 'MAY001', 'NV002', NULL, '2026-02-15 10:00:00', '2026-02-15 12:00:00', 2.00, 0.00, 2.00, 5000.00, 10000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS034', 'KH016', 'MAY017', 'NV004', NULL, '2026-02-17 20:00:00', '2026-02-17 22:30:00', 2.50, 0.00, 2.50, 6000.00, 15000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS035', 'KH004', 'MAY011', 'NV002', 'GOIKH003', '2026-02-18 08:00:00', '2026-02-18 12:00:00', 4.00, 4.00, 0.00, 10000.00, 0.00, 'GOI', 'DAKETTHUC'),
	('PS036', 'KH005', 'MAY007', 'NV004', NULL, '2026-02-19 18:00:00', '2026-02-19 22:00:00', 4.00, 0.00, 4.00, 8000.00, 32000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS037', 'KH012', 'MAY008', 'NV002', 'GOIKH006', '2026-02-21 09:00:00', '2026-02-21 14:00:00', 5.00, 5.00, 0.00, 8000.00, 0.00, 'GOI', 'DAKETTHUC'),
	('PS038', 'KH009', 'MAY016', 'NV004', NULL, '2026-02-23 20:00:00', '2026-02-23 23:00:00', 3.00, 0.00, 3.00, 6000.00, 18000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS039', 'KH011', 'MAY002', 'NV002', NULL, '2026-02-25 08:00:00', '2026-02-25 12:00:00', 4.00, 0.00, 4.00, 5000.00, 20000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS040', 'KH013', 'MAY015', 'NV004', NULL, '2026-02-27 14:00:00', '2026-02-27 17:30:00', 3.50, 0.00, 3.50, 6000.00, 21000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS041', 'KH011', 'MAY001', 'NV002', 'GOIKH009', '2026-03-01 09:00:00', '2026-03-01 13:00:00', 4.00, 4.00, 0.00, 5000.00, 0.00, 'GOI', 'DAKETTHUC'),
	('PS042', 'KH006', 'MAY016', 'NV004', 'GOIKH008', '2026-03-02 14:00:00', '2026-03-02 17:00:00', 3.00, 3.00, 0.00, 6000.00, 0.00, 'GOI', 'DAKETTHUC'),
	('PS043', 'KH002', 'MAY007', 'NV002', NULL, '2026-03-03 10:00:00', '2026-03-03 14:00:00', 4.00, 0.00, 4.00, 8000.00, 32000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS044', 'KH009', 'MAY009', 'NV004', NULL, '2026-03-04 18:00:00', '2026-03-04 22:00:00', 4.00, 0.00, 4.00, 8000.00, 32000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS045', 'KH012', 'MAY012', 'NV002', 'GOIKH006', '2026-03-05 08:00:00', '2026-03-05 13:00:00', 5.00, 5.00, 0.00, 10000.00, 0.00, 'GOI', 'DAKETTHUC'),
	('PS046', 'KH014', 'MAY014', 'NV004', 'GOIKH010', '2026-03-06 14:00:00', '2026-03-06 17:00:00', 3.00, 3.00, 0.00, 12000.00, 0.00, 'GOI', 'DAKETTHUC'),
	('PS047', 'KH005', 'MAY002', 'NV002', NULL, '2026-03-08 09:00:00', '2026-03-08 13:00:00', 4.00, 0.00, 4.00, 5000.00, 20000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS048', 'KH001', 'MAY010', 'NV004', NULL, '2026-03-10 08:00:00', '2026-03-10 12:00:00', 4.00, 0.00, 4.00, 10000.00, 40000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS049', 'KH007', 'MAY013', 'NV002', 'GOIKH004', '2026-03-11 14:00:00', '2026-03-11 18:00:00', 4.00, 4.00, 0.00, 10000.00, 0.00, 'GOI', 'DAKETTHUC'),
	('PS050', 'KH010', 'MAY008', 'NV004', NULL, '2026-03-12 19:00:00', '2026-03-12 23:00:00', 4.00, 0.00, 4.00, 8000.00, 32000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS051', 'KH013', 'MAY018', 'NV002', NULL, '2026-03-13 10:00:00', '2026-03-13 12:00:00', 2.00, 0.00, 2.00, 6000.00, 12000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS052', 'KH015', 'MAY005', 'NV004', NULL, '2026-03-14 20:00:00', '2026-03-14 23:00:00', 3.00, 0.00, 3.00, 5000.00, 15000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS053', 'KH016', 'MAY016', 'NV002', NULL, '2026-03-15 09:00:00', '2026-03-15 11:00:00', 2.00, 0.00, 2.00, 6000.00, 12000.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS054', 'KH003', 'MAY003', 'NV002', 'GOIKH007', '2026-03-17 08:00:00', '2026-03-18 10:29:11', 26.48, 0.00, 26.48, 5000.00, 132416.67, 'GOI', 'DAKETTHUC'),
	('PS055', 'KH011', 'MAY012', 'NV004', 'GOIKH009', '2026-03-17 09:00:00', '2026-03-18 10:33:29', 25.55, 0.00, 25.55, 10000.00, 255500.00, 'GOI', 'DAKETTHUC'),
	('PS056', 'KH008', 'MAY006', 'NV002', NULL, '2026-03-17 10:30:00', '2026-03-17 22:03:34', 11.55, 0.00, 11.55, 8000.00, 57750.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS057', 'KH015', 'MAY017', 'NV004', NULL, '2026-03-17 11:00:00', '2026-03-17 22:03:34', 11.05, 0.00, 11.05, 6000.00, 66300.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS058', 'KH014', 'MAY017', 'NV001', 'GOIKH010', '2026-03-17 22:03:51', '2026-03-18 10:55:43', 12.85, 0.00, 12.85, 6000.00, 77100.00, 'GOI', 'DAKETTHUC'),
	('PS059', 'KH012', 'MAY017', 'NV001', 'GOIKH006', '2026-03-18 09:59:42', '2026-03-18 11:01:09', 1.02, 0.00, 1.02, 6000.00, 6100.00, 'GOI', 'DAKETTHUC'),
	('PS060', 'KH012', 'MAY018', 'NV001', 'GOIKH006', '2026-03-18 09:06:01', '2026-03-18 11:10:06', 2.07, 0.00, 2.07, 6000.00, 12400.00, 'GOI', 'DAKETTHUC'),
	('PS061', 'KH014', 'MAY018', 'NV001', 'GOIKH010', '2026-03-18 09:17:42', '2026-03-18 11:20:38', 2.03, 0.00, 2.03, 6000.00, 12200.00, 'GOI', 'DAKETTHUC'),
	('PS062', 'KH013', 'MAY018', 'NV001', NULL, '2026-03-18 09:21:36', '2026-03-18 11:23:59', 2.03, 0.00, 2.03, 6000.00, 12200.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS063', 'KH013', 'MAY017', 'NV001', NULL, '2026-03-18 11:34:46', '2026-03-18 11:46:12', 0.18, 0.00, 0.18, 6000.00, 1100.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS064', 'KH016', 'MAY015', 'NV001', NULL, '2026-03-18 11:46:25', '2026-03-18 11:46:49', 0.00, 0.00, 0.00, 12000.00, 0.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS065', 'KH014', 'MAY018', 'NV001', 'GOIKH010', '2026-03-18 08:47:42', '2026-03-18 12:48:32', 4.00, 0.00, 4.00, 6000.00, 24000.00, 'GOI', 'DAKETTHUC'),
	('PS066', 'KH012', 'MAY016', 'NV001', 'GOIKH006', '2026-03-18 07:49:07', NULL, 0.00, 0.00, 0.00, 6000.00, 0.00, 'GOI', 'DANGCHOI'),
	('PS067', 'KH007', 'MAY010', 'NV001', 'GOIKH004', '2026-03-17 12:49:45', NULL, 0.00, 0.00, 0.00, 8000.00, 0.00, 'GOI', 'DANGCHOI'),
	('PS068', 'KH002', 'MAY018', 'NV001', 'GOIKH002', '2026-03-18 06:50:34', NULL, 0.00, 0.00, 0.00, 6000.00, 0.00, 'GOI', 'DANGCHOI'),
	('PS069', 'KH018', 'MAY012', 'NV001', NULL, '2026-03-03 10:43:16', '2026-03-18 13:47:47', 363.07, 0.00, 363.07, 10000.00, 3630666.67, 'TAIKHOAN', 'DAKETTHUC'),
	('PS070', 'KH001', 'MAY017', NULL, NULL, '2026-03-19 12:33:04', '2026-03-19 12:33:38', 0.00, 0.00, 0.00, 6000.00, 0.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS071', 'KH001', 'MAY017', 'NV006', NULL, '2026-03-19 12:45:14', '2026-03-19 12:48:03', 0.03, 0.00, 0.03, 6000.00, 200.00, 'TAIKHOAN', 'DAKETTHUC'),
	('PS072', 'KH001', 'MAY017', 'NV006', NULL, '2026-03-19 12:49:18', NULL, 0.00, 0.00, 0.00, 6000.00, 0.00, 'TAIKHOAN', 'DANGCHOI');

-- Dumping structure for table quanlytiemnet_simple.phieunhaphang
CREATE TABLE IF NOT EXISTS `phieunhaphang` (
  `MaPhieuNhap` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaNCC` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaNV` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `NgayNhap` datetime DEFAULT CURRENT_TIMESTAMP,
  `TongTien` decimal(15,2) NOT NULL DEFAULT '0.00',
  `TrangThai` enum('CHODUYET','DANHAP','DAHUY') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'CHODUYET',
  PRIMARY KEY (`MaPhieuNhap`),
  KEY `idx_mancc_pn` (`MaNCC`),
  KEY `idx_manv_pn` (`MaNV`),
  KEY `idx_ngaynhap` (`NgayNhap`),
  KEY `idx_trangthai_pn` (`TrangThai`),
  CONSTRAINT `fk_phieunhap_nhacungcap` FOREIGN KEY (`MaNCC`) REFERENCES `nhacungcap` (`MaNCC`),
  CONSTRAINT `fk_phieunhap_nhanvien` FOREIGN KEY (`MaNV`) REFERENCES `nhanvien` (`MaNV`),
  CONSTRAINT `chk_tongtien_pn` CHECK ((`TongTien` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table quanlytiemnet_simple.phieunhaphang: ~16 rows (approximately)
INSERT INTO `phieunhaphang` (`MaPhieuNhap`, `MaNCC`, `MaNV`, `NgayNhap`, `TongTien`, `TrangThai`) VALUES
	('PN001', 'NCC001', 'NV002', '2026-01-03 08:00:00', 80000.00, 'DANHAP'),
	('PN002', 'NCC002', 'NV002', '2026-01-07 09:00:00', 60000.00, 'DANHAP'),
	('PN003', 'NCC004', 'NV004', '2026-01-14 10:00:00', 55000.00, 'DANHAP'),
	('PN004', 'NCC005', 'NV002', '2026-01-20 08:30:00', 50000.00, 'DANHAP'),
	('PN005', 'NCC001', 'NV004', '2026-01-27 09:00:00', 45000.00, 'DANHAP'),
	('PN006', 'NCC002', 'NV002', '2026-02-03 08:00:00', 70000.00, 'DANHAP'),
	('PN007', 'NCC003', 'NV004', '2026-02-10 09:00:00', 65000.00, 'DANHAP'),
	('PN008', 'NCC001', 'NV002', '2026-02-18 08:30:00', 55000.00, 'DANHAP'),
	('PN009', 'NCC005', 'NV004', '2026-02-25 10:00:00', 50000.00, 'DANHAP'),
	('PN010', 'NCC001', 'NV002', '2026-03-01 08:00:00', 60000.00, 'DANHAP'),
	('PN011', 'NCC002', 'NV004', '2026-03-04 09:00:00', 55000.00, 'DANHAP'),
	('PN012', 'NCC004', 'NV002', '2026-03-07 08:30:00', 50000.00, 'DANHAP'),
	('PN013', 'NCC001', 'NV004', '2026-03-10 09:00:00', 45000.00, 'DANHAP'),
	('PN014', 'NCC005', 'NV002', '2026-03-13 08:30:00', 40000.00, 'DANHAP'),
	('PN015', 'NCC002', 'NV004', '2026-03-16 09:00:00', 35000.00, 'DANHAP'),
	('PN016', 'NCC002', 'NV001', '2026-03-18 12:46:35', 648000.00, 'DANHAP');

-- Dumping structure for table quanlytiemnet_simple.sudungdichvu
CREATE TABLE IF NOT EXISTS `sudungdichvu` (
  `MaSD` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaPhien` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `MaDV` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `SoLuong` int NOT NULL DEFAULT '1',
  `DonGia` decimal(10,2) NOT NULL COMMENT 'Giá tại thời điểm mua',
  `ThanhTien` decimal(12,2) NOT NULL COMMENT 'SoLuong * DonGia',
  `ThoiGian` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`MaSD`),
  KEY `idx_maphien_sd` (`MaPhien`),
  KEY `idx_madv_sd` (`MaDV`),
  CONSTRAINT `fk_sudung_dichvu` FOREIGN KEY (`MaDV`) REFERENCES `dichvu` (`MaDV`),
  CONSTRAINT `fk_sudung_phien` FOREIGN KEY (`MaPhien`) REFERENCES `phiensudung` (`MaPhien`),
  CONSTRAINT `chk_dongia_sd` CHECK ((`DonGia` >= 0)),
  CONSTRAINT `chk_soluong` CHECK ((`SoLuong` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table quanlytiemnet_simple.sudungdichvu: ~55 rows (approximately)
INSERT INTO `sudungdichvu` (`MaSD`, `MaPhien`, `MaDV`, `SoLuong`, `DonGia`, `ThanhTien`, `ThoiGian`) VALUES
	('SD001', 'PS001', 'DV001', 1, 12000.00, 12000.00, '2026-01-05 09:30:00'),
	('SD002', 'PS002', 'DV003', 1, 25000.00, 25000.00, '2026-01-06 08:30:00'),
	('SD003', 'PS002', 'DV001', 1, 12000.00, 12000.00, '2026-01-06 09:00:00'),
	('SD004', 'PS003', 'DV008', 2, 15000.00, 30000.00, '2026-01-07 14:30:00'),
	('SD005', 'PS005', 'DV004', 1, 35000.00, 35000.00, '2026-01-11 18:30:00'),
	('SD006', 'PS005', 'DV002', 1, 12000.00, 12000.00, '2026-01-11 19:00:00'),
	('SD007', 'PS008', 'DV005', 2, 8000.00, 16000.00, '2026-01-14 15:30:00'),
	('SD008', 'PS010', 'DV006', 1, 15000.00, 15000.00, '2026-01-16 10:30:00'),
	('SD009', 'PS011', 'DV001', 1, 12000.00, 12000.00, '2026-01-17 08:30:00'),
	('SD010', 'PS011', 'DV005', 1, 8000.00, 8000.00, '2026-01-17 09:00:00'),
	('SD011', 'PS012', 'DV007', 1, 20000.00, 20000.00, '2026-01-18 14:30:00'),
	('SD012', 'PS012', 'DV002', 2, 12000.00, 24000.00, '2026-01-18 15:00:00'),
	('SD013', 'PS013', 'DV001', 1, 12000.00, 12000.00, '2026-01-19 20:30:00'),
	('SD014', 'PS014', 'DV009', 1, 20000.00, 20000.00, '2026-01-20 09:30:00'),
	('SD015', 'PS016', 'DV006', 1, 15000.00, 15000.00, '2026-01-22 18:30:00'),
	('SD016', 'PS016', 'DV003', 1, 25000.00, 25000.00, '2026-01-22 19:00:00'),
	('SD017', 'PS025', 'DV008', 2, 15000.00, 30000.00, '2026-02-05 18:30:00'),
	('SD018', 'PS026', 'DV001', 1, 12000.00, 12000.00, '2026-02-06 08:30:00'),
	('SD019', 'PS027', 'DV003', 1, 25000.00, 25000.00, '2026-02-07 20:30:00'),
	('SD020', 'PS028', 'DV005', 2, 8000.00, 16000.00, '2026-02-08 09:30:00'),
	('SD021', 'PS029', 'DV010', 2, 20000.00, 40000.00, '2026-02-10 14:30:00'),
	('SD022', 'PS031', 'DV002', 2, 12000.00, 24000.00, '2026-02-12 15:30:00'),
	('SD023', 'PS033', 'DV001', 1, 12000.00, 12000.00, '2026-02-15 10:30:00'),
	('SD024', 'PS034', 'DV008', 1, 15000.00, 15000.00, '2026-02-17 20:30:00'),
	('SD025', 'PS036', 'DV006', 1, 15000.00, 15000.00, '2026-02-19 18:30:00'),
	('SD026', 'PS036', 'DV004', 1, 35000.00, 35000.00, '2026-02-19 19:00:00'),
	('SD027', 'PS038', 'DV001', 2, 12000.00, 24000.00, '2026-02-23 20:30:00'),
	('SD028', 'PS039', 'DV007', 1, 20000.00, 20000.00, '2026-02-25 08:30:00'),
	('SD029', 'PS043', 'DV003', 1, 25000.00, 25000.00, '2026-03-03 10:30:00'),
	('SD030', 'PS043', 'DV002', 1, 12000.00, 12000.00, '2026-03-03 11:00:00'),
	('SD031', 'PS044', 'DV006', 1, 15000.00, 15000.00, '2026-03-04 18:30:00'),
	('SD032', 'PS047', 'DV001', 1, 12000.00, 12000.00, '2026-03-08 09:30:00'),
	('SD033', 'PS047', 'DV005', 1, 8000.00, 8000.00, '2026-03-08 10:00:00'),
	('SD034', 'PS048', 'DV009', 1, 20000.00, 20000.00, '2026-03-10 08:30:00'),
	('SD035', 'PS048', 'DV008', 2, 15000.00, 30000.00, '2026-03-10 09:00:00'),
	('SD036', 'PS050', 'DV004', 1, 35000.00, 35000.00, '2026-03-12 19:30:00'),
	('SD037', 'PS050', 'DV002', 1, 12000.00, 12000.00, '2026-03-12 20:00:00'),
	('SD038', 'PS052', 'DV001', 1, 12000.00, 12000.00, '2026-03-14 20:30:00'),
	('SD039', 'PS053', 'DV005', 1, 8000.00, 8000.00, '2026-03-15 09:30:00'),
	('SD040', 'PS058', 'DV005', 1, 8000.00, 8000.00, '2026-03-18 10:30:30'),
	('SD041', 'PS058', 'DV005', 1, 8000.00, 8000.00, '2026-03-18 10:55:29'),
	('SD042', 'PS059', 'DV003', 1, 25000.00, 25000.00, '2026-03-18 11:01:05'),
	('SD043', 'PS060', 'DV004', 1, 35000.00, 35000.00, '2026-03-18 11:09:55'),
	('SD044', 'PS060', 'DV002', 1, 12000.00, 12000.00, '2026-03-18 11:09:55'),
	('SD045', 'PS061', 'DV004', 2, 35000.00, 70000.00, '2026-03-18 11:19:16'),
	('SD046', 'PS062', 'DV003', 3, 25000.00, 75000.00, '2026-03-18 11:22:09'),
	('SD047', 'PS063', 'DV004', 3, 35000.00, 105000.00, '2026-03-18 11:34:55'),
	('SD048', 'PS063', 'DV004', 4, 35000.00, 140000.00, '2026-03-18 11:46:02'),
	('SD049', 'PS064', 'DV002', 3, 12000.00, 36000.00, '2026-03-18 11:46:33'),
	('SD050', 'PS069', 'DV001', 1, 12000.00, 12000.00, '2026-03-18 13:43:42'),
	('SD051', 'PS069', 'DV003', 1, 25000.00, 25000.00, '2026-03-18 13:43:42'),
	('SD052', 'PS070', 'DV002', 1, 12000.00, 12000.00, '2026-03-19 12:33:27'),
	('SD053', 'PS071', 'DV002', 1, 12000.00, 12000.00, '2026-03-19 12:45:32'),
	('SD054', 'PS071', 'DV001', 1, 12000.00, 12000.00, '2026-03-19 12:47:24'),
	('SD055', 'PS072', 'DV001', 1, 12000.00, 12000.00, '2026-03-19 12:57:00');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
