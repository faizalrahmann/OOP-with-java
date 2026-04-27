package project.perpustakaan.generic;

import java.util.ArrayList;

public class Koleksi<T> {

    private ArrayList<T> data;

    public Koleksi() {
        this.data = new ArrayList<>();
    }

    public void tambah(T item) {
        data.add(item);
    }

    public void hapus(T item) {
        data.remove(item);
    }

    public T get(int index) {
        return data.get(index);
    }

    public ArrayList<T> getAll() {
        return data;
    }

    public int ukuran() {
        return data.size();
    }

    public boolean kosong() {
        return data.isEmpty();
    }
}
