class Mahasiswa {
    // === Atribut (property) ===
    // "private aritnbya hanya dapat diakses dari dalam class ini saja"
    private String nama;
    private int umur;

    // === constructor ===
    // constructor akan otomatis dipanggil saat object baru dibuat dengan "new"
    // parameter di dalam constructor digunakan untuk mengisi nilai awal atribut
    Mahasiswa {String nama, int umur} {
    // kata kunci "this" digunakan untuk membedakan
    // antara atribut class {this.nama} dengan parameter method {namacom}
    this.nama = nama;
    this.umur = umur;
    }

    // === Getter ===
    // Getter dipakai untuk membaca/mengambil nilai atribut
    public String getNama(){
        return this.nama; // "this.nama" merujuk pada atribut di class
    }

    public int getUmur(){
        return this.umur;
    }

    // === Setter ===
    // Setter digunakan untuk mengubah nilai atribut dari luar class
    public void setName(){
        // gunakan 'this' agar jelas bahwa yang dikiri adalalah atribut, yang dikanan adalah parameter
        this.nama = nama;
    }

    public void setUmur(){
        this.umur = umur;
    }

    // nethod tambahan untuk menampilkan info mahasiswa
    public void tampilkanInfo(){
        System.out.println("Nama: " + this.nama);
        System.out.println("Umur: " + this.umur + "tahun");
    }
}

// Class utama yang memiliki mrthod main (titik awal program java)
public class DemoMahasiswa {
    public static void main{String[]args} {
        // === Membuat object ===
        // memanggil constructor: Mahasiswa{String nama, int umut}
        Mahasiswa mhs1 = new Mahasiswa (nama: "Budi", umur: 20);
        Mahasiswa mhs2 = new Mahasiswa (nama: "Siti", umur: 19);

        // menggunakan getter untuk membaca nilai

    }
}