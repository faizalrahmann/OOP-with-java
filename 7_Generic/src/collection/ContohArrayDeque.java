package collection;

import java.util.ArrayDeque;

public class ContohArrayDeque {

    public static void main(String[] args) {

        // Membuat ArrayDeque
        ArrayDeque<String> antrian = new ArrayDeque<>();

        // ==============================
        // PENGGUNAAN SEBAGAI QUEUE (FIFO)
        // ==============================
        System.out.println("=== ARRAYDEQUE SEBAGAI QUEUE (FIFO) ===");

        // Menambahkan elemen ke belakang - offer()
        antrian.offer("Mahasiswa A");
        antrian.offer("Mahasiswa B");
        antrian.offer("Mahasiswa C");
        antrian.offer("Mahasiswa D");

        System.out.println("Antrian awal : " + antrian);
        System.out.println("Ukuran       : " + antrian.size());

        // Melihat elemen terdepan tanpa menghapus - peek()
        System.out.println("\nElemen terdepan (peek) : " + antrian.peek());

        // Mengambil dan menghapus elemen terdepan - poll()
        System.out.println("\n=== PROSES ANTRIAN (POLL) ===");
        System.out.println("Dipanggil : " + antrian.poll());
        System.out.println("Dipanggil : " + antrian.poll());
        System.out.println("Sisa antrian : " + antrian);

        // ==============================
        // PENGGUNAAN SEBAGAI STACK (LIFO)
        // ==============================
        System.out.println("\n=== ARRAYDEQUE SEBAGAI STACK (LIFO) ===");

        ArrayDeque<String> tumpukan = new ArrayDeque<>();

        // Menambahkan elemen ke atas tumpukan - push()
        tumpukan.push("Lantai 1");
        tumpukan.push("Lantai 2");
        tumpukan.push("Lantai 3");
        tumpukan.push("Lantai 4");

        System.out.println("Tumpukan awal : " + tumpukan);

        // Melihat elemen teratas tanpa menghapus - peek()
        System.out.println("Elemen teratas (peek) : " + tumpukan.peek());

        // Mengambil dan menghapus elemen teratas - pop()
        System.out.println("\n=== PROSES TUMPUKAN (POP) ===");
        System.out.println("Diambil : " + tumpukan.pop());
        System.out.println("Diambil : " + tumpukan.pop());
        System.out.println("Sisa tumpukan : " + tumpukan);

        // ==============================
        // OPERASI TAMBAHAN
        // ==============================
        System.out.println("\n=== OPERASI TAMBAHAN ===");

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        // Tambah dari depan - addFirst() dan dari belakang - addLast()
        deque.addLast(10);
        deque.addLast(20);
        deque.addLast(30);
        deque.addFirst(5);

        System.out.println("Deque : " + deque);

        // Hapus dari depan - removeFirst() dan dari belakang - removeLast()
        System.out.println("removeFirst() : " + deque.removeFirst());
        System.out.println("removeLast()  : " + deque.removeLast());
        System.out.println("Deque setelah remove : " + deque);
    }
}
