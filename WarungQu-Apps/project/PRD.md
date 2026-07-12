# Product Requirement Document (PRD) - WarungQU v1.0

## 1. Product Goal
Mendigitalisasi pencatatan arus kas manual pada warung keluarga menjadi sistem Point of Sales (POS) desktop berbasis Java. Aplikasi ini memfokuskan fiturnya pada perhitungan pesanan belanjaan pelanggan (keranjang belanja), kalkulator kembalian otomatis, serta pencatatan pengeluaran operasional secara akurat demi mencegah salah hitung keuangan harian.

## 2. Target User dan Access Control
- Pengguna Tunggal (Single User): Pemilik warung bertindak langsung sebagai kasir yang memegang kendali penuh atas manajemen produk, transaksi kasir, input pengeluaran, serta pemantauan total saldo harian dalam satu akun terpusat.

## 3. Core Features (Fitur Utama)
- Authentication: Halaman login tunggal untuk mengamankan akses data keuangan aplikasi.
- Manajemen Produk (CRUD): Menu untuk menambah, melihat, mengubah, dan menghapus daftar barang/produk yang dijual di warung beserta harganya.
- Modul Kasir (Pemasukan Berbasis Pesanan):
  - Memilih produk dari daftar dan menentukan jumlah beli.
  - Tabel Keranjang Belanja Sementara yang menghitung otomatis Grand Total.
  - Kalkulator Kembalian (Input uang bayar -> hitung kembalian secara real-time).
- Modul Pengeluaran: Form input langsung (nominal dan keterangan) untuk biaya operasional seperti kulakan stok, bayar listrik warung, atau keperluan mendadak.
- Ringkasan Harian (Dashboard): Tampilan kartu statistik real-time yang menjumlahkan Total Pemasukan, Total Pengeluaran, dan Saldo Bersih (Laba) pada hari berjalan.
- Riwayat Transaksi: Halaman khusus untuk melihat seluruh rekap log aktivitas transaksi yang tersimpan di database.

## 4. OOP Architecture Blueprint (Syarat Akademik)
Struktur kode Java wajib mengimplementasikan pilar Object-Oriented Programming secara eksplisit:
- Parent Class: Transaksi (Atribut: id_transaksi, tanggal, total).
- Child Class (Inheritance): 
  - Class Pemasukan (Meng-extends Transaksi, ditambah atribut uang_bayar dan uang_kembalian).
  - Class Pengeluaran (Meng-extends Transaksi, ditambah atribut keterangan_operasional).
- Encapsulation: Seluruh atribut/properti di dalam class model bertipe private dan hanya bisa diakses atau diubah menggunakan metode getter dan setter.
- Class Entitas dan Asosiasi:
  - Class Produk (Atribut: id_produk, nama_produk, harga).
  - Class DetailTransaksi (Atribut: id_detail, id_produk, jumlah, subtotal) sebagai representasi item di dalam keranjang belanja yang berasosiasi dengan transaksi induk.
- Class Helper: Koneksi menggunakan Pola Singleton untuk memastikan hanya ada satu koneksi aktif ke database MySQL selama aplikasi berjalan.