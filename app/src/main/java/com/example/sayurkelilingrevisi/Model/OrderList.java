package com.example.sayurkelilingrevisi.Model;

public class OrderList {

    private String id;
    private String pemesan;
    private String pedagang;
    private String alamatpemesan;
    private String tgl;
    private String pesanan;
    private String jam;
    private String total;

    public OrderList() {
    }

    public OrderList(String id, String pemesan, String pedagang,
                     String alamatpemesan, String tgl, String pesanan,
                     String jam, String total) {
        this.id = id;
        this.pemesan = pemesan;
        this.pedagang = pedagang;
        this.alamatpemesan = alamatpemesan;
        this.tgl = tgl;
        this.pesanan = pesanan;
        this.jam = jam;
        this.total = total;

    }

    public String getPemesan() {
        return pemesan;
    }

    public void setPemesan(String pemesan) {
        this.pemesan = pemesan;
    }

    public String getAlamatpemesan() {
        return alamatpemesan;
    }

    public void setAlamatpemesan(String alamatpemesan) {
        this.alamatpemesan = alamatpemesan;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getPesanan() {
        return pesanan;
    }

    public void setPesanan(String pesanan) {
        this.pesanan = pesanan;
    }

    public String getPedagang() {
        return pedagang;
    }

    public void setPedagang(String pedagang) {
        this.pedagang = pedagang;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJam() {
        return jam;
    }

    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}
