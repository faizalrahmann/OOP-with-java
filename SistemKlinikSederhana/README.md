# Sistem Klinik Sederhana

Aplikasi desktop Java Swing dengan MySQL untuk studi kasus sistem klinik sederhana.

## Fitur
- Login
- Menu utama
- Data pasien
- Data dokter
- Transaksi janji temu
- Laporan sederhana

## Struktur PBO
- Abstract Class: Person
- Inheritance: User, Patient, Doctor extends Person
- Encapsulation: field private di kelas model
- Polymorphism: override displayInfo()
- Package: dipisah ke package model, ui, repository, service, database

## Jalankan
1. Pastikan MySQL berjalan.
2. Buat database `klinik_sederhana` atau biarkan aplikasi membuatnya.
3. Pastikan driver MySQL JDBC tersedia.
4. Jalankan `javac -d out $(find src -name "*.java")` lalu `java -cp out App`
