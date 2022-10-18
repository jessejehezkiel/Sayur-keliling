package com.example.sayurkelilingrevisi.Model;

public class Chat {

    private String pengirim;
    private String penerima;
    private String pesan;


    public Chat(String pengirim, String penerima, String pesan) {
        this.pengirim = pengirim;
        this.penerima = penerima;
        this.pesan = pesan;
    }

    public Chat() {
    }

    public String getPengirim() {
        return pengirim;
    }

    public void setPengirim(String pengirim) {
        this.pengirim = pengirim;
    }

    public String getPenerima() {
        return penerima;
    }

    public void setPenerima(String penerima) {
        this.penerima = penerima;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

}
