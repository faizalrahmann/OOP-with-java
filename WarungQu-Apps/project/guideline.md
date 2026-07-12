# Guideline dan Tech Stack System - WarungQU

## 1. Tech Stack Selection
- Language: Java (JDK 8 atau versi di atasnya)
- GUI Framework: Java Swing (JFrame, JPanel, JTable, JButton, JTextField)
- Database: MySQL / MariaDB (Driver: MySQL Connector/J / JDBC)
- Database Output: File warungqu_db.sql untuk dilampirkan ke dalam repositori GitHub.

## 2. Project Structure Directory
Susunan package dan folder Java untuk project WarungQU wajib mengikuti arsitektur modular berikut agar rapi dan terstruktur:

WarungQU/
│
├── src/
│   └── com/
│       └── warungqu/
│           ├── main/
│           │   └── Main.java               # Entry point untuk menjalankan aplikasi pertama kali
│           │
│           ├── config/
│           │   └── Koneksi.java            # Class Helper koneksi ke MySQL (Singleton Pattern)
│           │
│           ├── model/
│           │   ├── Transaksi.java          # Parent Class utama untuk struktur transaksi
│           │   ├── Pemasukan.java          # Child Class (Inheritance dari Transaksi)
│           │   ├── Pengeluaran.java        # Child Class (Inheritance dari Transaksi)
│           │   ├── Produk.java             # Class model entitas data barang
│           │   └── DetailTransaksi.java    # Class model untuk item di keranjang belanja
│           │
│           ├── dao/
│           │   ├── TransaksiDAO.java       # Menangani Query SQL transaksi (Insert, Read, SUM)
│           │   └── ProdukDAO.java          # Menangani Query SQL produk (CRUD)
│           │
│           └── view/
│               ├── LoginForm.java          # Tampilan GUI Halaman Login (JFrame)
│               ├── DashboardForm.java      # Tampilan GUI Utama/Frame Induk Navigasi
│               ├── KasirPanel.java         # UI Panel untuk mesin kasir dan hitung pesanan (JPanel)
│               └── ProdukPanel.java        # UI Panel untuk kelola data stok/barang (JPanel)
│
├── database/
│   └── warungqu_db.sql                     # File backup skema database untuk disubmit
│
└── lib/
    └── mysql-connector-j-x.x.x.jar        # Driver library JDBC MySQL

## 3. Design Personality
- Sederhana dan Fungsional: Mengutamakan kecepatan input data dan kejelasan visual saat warung sedang ramai pembeli.
- Presisi Keuangan: Menggunakan tampilan font yang tegas, tebal, dan kontras tinggi untuk urusan nominal mata uang (Rupiah).

## 4. Color Palette (UI Kasir POS)
- Primary Green (Pemasukan): Emerald Green (#2e7d32) -> Penanda elemen uang masuk, total omzet, dan tombol sukses.
- Danger Red (Pengeluaran): Crimson Red (#c62828) -> Penanda elemen uang keluar, biaya operasional, dan tombol hapus/batal.
- Accent Color: Royal Blue (#1565c0) -> Untuk elemen tombol navigasi utama (Login, Simpan, Cetak).
- Neutral Dark: Charcoal Grey (#212121) -> Warna teks utama agar nyaman dibaca.
- Neutral Light: Light Grey (#f5f5f5) -> Warna dasar background aplikasi.

## 5. Component dan Validation Rules
- Format Keuangan: Penulisan nominal uang pada UI wajib diformat menggunakan pemisah ribuan Rupiah (Contoh: Rp 150.000).
- Validasi Form: Setiap input angka (harga, jumlah beli, nominal pengeluaran) wajib divalidasi oleh sistem:
  - Tidak boleh kosong.
  - Harus berupa angka (tidak boleh mengandung huruf/simbol).
  - Nilai tidak boleh minus (< 0).
- Kalkulator Otomatis: Input pada kolom Uang Bayar di mesin kasir harus memicu kalkulasi pengurangan secara real-time untuk menampilkan nominal Uang Kembalian.