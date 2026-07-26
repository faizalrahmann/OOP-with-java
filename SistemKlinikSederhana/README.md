# Sistem Klinik Sederhana

Aplikasi desktop Java Swing untuk pengelolaan klinik sederhana. Aplikasi ini dibuat untuk membantu tugas administrasi klinik, termasuk manajemen data pasien, dokter, dan janji temu.

## Fitur Utama
- Login pengguna dengan akun admin.
- Menu utama sederhana.
- Manajemen data pasien.
- Manajemen data dokter.
- Transaksi janji temu pasien dengan dokter.
- Laporan ringkas jumlah pasien, dokter, dan janji temu.

## Struktur Proyek
- `src/model` - Kelas entitas data seperti `Person`, `User`, `Patient`, `Doctor`, dan `Appointment`.
- `src/ui` - Tampilan antarmuka Java Swing seperti `LoginFrame`, `MainFrame`, `PatientForm`, `DoctorForm`, dan `AppointmentForm`.
- `src/service` - Logika bisnis dan validasi pada `ClinicService`.
- `src/repository` - Akses database di `ClinicRepository`.
- `src/database` - Koneksi database dan inisialisasi pada `DatabaseConnection`.
- `lib` - Folder untuk JDBC driver MySQL (`mysqlconnector.jar`).

## Kebutuhan
- Java JDK 17 atau lebih baru.
- MySQL server (XAMPP atau MySQL biasa).
- JDBC driver MySQL (`mysqlconnector.jar`).

## Persiapan Database
Aplikasi menggunakan database `db_klinik`. Jika database belum ada, jalankan query SQL berikut di phpMyAdmin atau MySQL CLI:

```sql
DROP DATABASE IF EXISTS db_klinik;
CREATE DATABASE db_klinik CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE db_klinik;

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  full_name VARCHAR(100),
  username VARCHAR(50) UNIQUE,
  password VARCHAR(100),
  role VARCHAR(20),
  phone VARCHAR(20),
  address VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE patients (
  id INT AUTO_INCREMENT PRIMARY KEY,
  full_name VARCHAR(100),
  phone VARCHAR(20),
  address VARCHAR(255),
  birth_date VARCHAR(20),
  gender VARCHAR(20),
  medical_history TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE doctors (
  id INT AUTO_INCREMENT PRIMARY KEY,
  full_name VARCHAR(100),
  phone VARCHAR(20),
  address VARCHAR(255),
  specialty VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE appointments (
  id INT AUTO_INCREMENT PRIMARY KEY,
  patient_id INT,
  doctor_id INT,
  appointment_date VARCHAR(20),
  status VARCHAR(30),
  notes TEXT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO users (full_name, username, password, role, phone, address)
VALUES ('Admin Klinik', 'admin', 'admin123', 'admin', '08123456789', 'Bandung');
```

## Cara Menjalankan
1. Pastikan MySQL server XAMPP atau MySQL biasa berjalan.
2. Pastikan file JDBC driver MySQL tersedia di folder `lib`.
3. Buka PowerShell di folder proyek.
4. Jalankan perintah berikut:

```powershell
javac -cp "lib\mysqlconnector.jar" -d out src\*.java src\database\*.java src\model\*.java src\repository\*.java src\service\*.java src\ui\*.java
java -cp "out;lib\mysqlconnector.jar" App
```

Jika Anda menggunakan `run.bat`, cukup jalankan:

```powershell
.\run.bat
```

## Login Default
- Username: `admin`
- Password: `admin123`

## Konsep PBO yang Digunakan
- `Abstract Class` pada `Person`.
- `Inheritance` pada `User`, `Patient`, `Doctor` dari `Person`.
- `Polymorphism` dengan method `displayInfo()`.
- `Encapsulation` melalui atribut private dan getter/setter.
- `Package` untuk memisahkan model, UI, service, repository, dan database.
- `Collection` menggunakan `List` untuk menampung data.
- `Exception Handling` untuk validasi dan error handling.
- `File Handling` di `ClinicService` untuk ekspor laporan.
- `Database` menggunakan MySQL untuk penyimpanan data.

## Struktur Database
Tabel utama:
- `users`
- `patients`
- `doctors`
- `appointments`

## Catatan
README ini sudah disesuaikan untuk proyek dengan XAMPP/MySQL dan JDBC driver. Pastikan `mysqlconnector.jar` berada di folder `lib` sebelum menjalankan aplikasi.