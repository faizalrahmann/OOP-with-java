# Tasklist dan MVP Scope - WarungQU (Versi Kasir POS)

## Phase 1: Database Setup dan OOP Models [ ]
- [ ] Buat skema database MySQL bernama warungqu_db.
- [ ] Buat struktur tabel database:
  - tabel_produk (id, nama, harga)
  - tabel_transaksi (id, tipe, tanggal, total, bayar, kembalian)
  - tabel_detail_transaksi (id, id_transaksi, id_produk, jumlah, subtotal)
  - tabel_pengeluaran (id, id_transaksi, keterangan)
- [ ] Tulis Java Class Model di package model/: Transaksi.java, Pemasukan.java, Pengeluaran.java, Produk.java, dan DetailTransaksi.java.
- [ ] Implementasikan enkapsulasi (atribut private + getter setter) pada seluruh class model.
- [ ] Buat class Koneksi.java di package config/ menggunakan driver JDBC untuk menghubungkan program ke MySQL.

## Phase 2: Login Screen dan CRUD Produk [ ]
- [ ] Desain interface LoginForm.java dengan field Username dan Password.
- [ ] Implementasikan verifikasi login single-user (statis hardcoded atau menggunakan akun default di database).
- [ ] Desain ProdukPanel.java yang berisi tabel daftar barang serta form input Tambah/Edit/Hapus produk.
- [ ] Buat class ProdukDAO.java untuk menangani aksi query SQL (INSERT, UPDATE, DELETE, SELECT) dari data produk ke database.
- [ ] Hubungkan ProdukPanel dengan ProdukDAO agar data produk sinkron secara real-time.

## Phase 3: Core Engine - Mesin Kasir dan Hitung Pesanan [ ]
- [ ] Desain KasirPanel.java dengan komponen: JComboBox/JTable pilihan produk, Spinner/TextField Jumlah Beli, Tombol Tambah ke Keranjang, JTable Keranjang Belanja, Label Grand Total, TextField Uang Bayar, dan Label Uang Kembalian.
- [ ] Logika Tombol Tambah: Mengambil produk terpilih -> hitung harga dikali jumlah -> masukkan sebagai baris baru di JTable Keranjang -> perbarui angka Label Grand Total.
- [ ] Logika Real-time Kembalian: Pasang DocumentListener pada TextField Uang Bayar untuk menghitung otomatis Uang Bayar dikurangi Grand Total setiap kali user mengetikkan angka.
- [ ] Logika Tombol Selesai Transaksi:
  - Insert data utama ke tabel_transaksi (tipe: Pemasukan).
  - Lakukan perulangan (looping) pada seluruh baris data di JTable Keranjang untuk di-insert satu per satu ke tabel_detail_transaksi.
  - Bersihkan isi keranjang (clear table) setelah transaksi sukses disimpan.

## Phase 4: Pengeluaran dan Dashboard Statistik [ ]
- [ ] Tambahkan komponen input pengeluaran operasional (TextField Nominal dan Keterangan) pada sistem.
- [ ] Logika Simpan Pengeluaran: Insert data ke tabel_transaksi (tipe: Pengeluaran) lalu simpan detailnya ke tabel_pengeluaran.
- [ ] Desain UI DashboardForm.java sebagai wadah utama dan pasang 3 Kartu KPI Ringkasan Hari Ini:
  - Kartu Pemasukan: Query SUM(total) WHERE tipe='Pemasukan' AND DATE(tanggal)=CURDATE().
  - Kartu Pengeluaran: Query SUM(total) WHERE tipe='Pengeluaran' AND DATE(tanggal)=CURDATE().
  - Kartu Saldo Bersih: Hasil pengurangan otomatis secara matematis antara Pemasukan dan Pengeluaran hari itu.
- [ ] Pasang tabel Recent Activity di bagian bawah dashboard untuk menampilkan 5 transaksi terakhir yang dilakukan hari ini.

## Phase 5: Finalization dan Documentation [ ]
- [ ] Tambahkan tab menu Riwayat untuk memuat seluruh log transaksi lama dalam bentuk tabel panjang untuk pelaporan.
- [ ] Lakukan pengujian menyeluruh (End-to-End Test): Coba skenario login -> tambah produk -> simpan transaksi kasir -> input pengeluaran -> cek ketepatan kalkulasi saldo di dashboard.
- [ ] Ekspor database dari phpMyAdmin/MySQL Workbench menjadi berkas fisik warungqu_db.sql dan letakkan di folder database/.
- [ ] Rapikan repositori GitHub dan siapkan penyusunan laporan makalah PDF sesuai instruksi tugas kampus.