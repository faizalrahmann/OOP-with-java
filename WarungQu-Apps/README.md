# WarungQU - Kasir POS Java Swing

WarungQU adalah aplikasi kasir point-of-sale sederhana berbasis Java Swing dan MySQL yang mendukung manajemen produk, transaksi penjualan, pengeluaran operasional, serta laporan riwayat transaksi.

## Fitur Utama

- Login form dengan verifikasi user statis.
- CRUD produk: tambah, edit, hapus produk.
- Mesin kasir: pilih produk, jumlah, tambah ke keranjang, hitung subtotal dan grand total.
- Pembayaran kasir: simpan transaksi pemasukan dengan detail item.
- Input pengeluaran operasional dan simpan ke database.
- Dashboard statistik hari ini:
  - Pemasukan hari ini
  - Pengeluaran hari ini
  - Saldo bersih hari ini
  - Recent activity transaksi terakhir.
- Riwayat / laporan transaksi lengkap:
  - filter transaksi by tipe (Semua, Pemasukan, Pengeluaran)
  - ringkasan harian realtime
  - detail transaksi item untuk pemasukan
  - keterangan untuk pengeluaran
- Format Rupiah konsisten di seluruh tampilan.

## Struktur Project

- `src/` - kode sumber Java
  - `com.warungqu.config.Koneksi` - koneksi JDBC ke MySQL
  - `com.warungqu.dao.ProdukDAO` - operasi CRUD produk
  - `com.warungqu.dao.TransaksiDAO` - simpan transaksi, laporan, dan detail transaksi
  - `com.warungqu.model` - model domain: `Produk`, `Transaksi`, `Pemasukan`, `Pengeluaran`, `DetailTransaksi`, `DetailTransaksiReport`
  - `com.warungqu.util.FormatUtil` - format rupiah
  - `com.warungqu.view` - UI Swing: `LoginForm`, `DashboardForm`, `ProdukPanel`, `KasirPanel`, `PengeluaranPanel`, `RiwayatPanel`
- `database/warungqu_db.sql` - schema SQL dan dump database (jika sudah dibuat)
- `project/task.md` - daftar task dan scope MVP

## Database

Database yang digunakan: `warungqu_db`

Tabel utama:
- `tabel_produk`
- `tabel_transaksi`
- `tabel_detail_transaksi`
- `tabel_pengeluaran`

Pastikan MySQL berjalan di `localhost:3306` dengan database `warungqu_db` dan user yang sesuai.

## Konfigurasi dan Jalankan

1. Pastikan Java JDK sudah terpasang.
2. Pastikan MySQL berjalan.
3. Buat database dan import `database/warungqu_db.sql` jika belum ada.
4. Buka project di VS Code.
5. Jalankan `LoginForm` sebagai main class.

> Contoh package main: `com.warungqu.view.LoginForm`

## Kredensial Login

Aplikasi menggunakan kredensial hardcoded berikut:

- Username: `admin`
- Password: `admin123`

Bagian login saat ini masih memakai pemeriksaan statis di `LoginForm`.

## Catatan Penting

- Semua angka uang ditampilkan dengan format Rupiah `Rp`.
- Dashboard dan laporan sudah diperkuat agar data harian ter-update setiap kali tab Riwayat dipilih atau tombol Refresh diklik.
- Riwayat menampilkan detail item untuk transaksi pemasukan dan keterangan untuk pengeluaran.

## Pengembangan Selanjutnya

Jika ingin dikembangkan lagi, bisa ditambahkan:
- Filter periode tanggal pada laporan.
- Ekspor laporan ke CSV/PDF.
- Autentikasi user dari database.
- Laporan grafis / chart.
