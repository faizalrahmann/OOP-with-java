CREATE DATABASE IF NOT EXISTS warungqu_db;
USE warungqu_db;

CREATE TABLE IF NOT EXISTS tabel_produk (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    harga DECIMAL(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS tabel_transaksi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipe ENUM('Pemasukan', 'Pengeluaran') NOT NULL,
    tanggal DATETIME DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(12,2) NOT NULL,
    bayar DECIMAL(12,2) DEFAULT 0.00,
    kembalian DECIMAL(12,2) DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS tabel_detail_transaksi (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_transaksi INT NOT NULL,
    id_produk INT NOT NULL,
    jumlah INT NOT NULL,
    subtotal DECIMAL(12,2) NOT NULL,
    CONSTRAINT fk_detail_transaksi_transaksi
        FOREIGN KEY (id_transaksi) REFERENCES tabel_transaksi(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_detail_transaksi_produk
        FOREIGN KEY (id_produk) REFERENCES tabel_produk(id)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS tabel_pengeluaran (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_transaksi INT NOT NULL,
    keterangan TEXT NOT NULL,
    CONSTRAINT fk_pengeluaran_transaksi
        FOREIGN KEY (id_transaksi) REFERENCES tabel_transaksi(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
