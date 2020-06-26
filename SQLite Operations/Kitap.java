package com.ferhatiltas.sqlite_ekleguncellesil;

public class Kitap {
    int id;
    String baslik;
    String yazar;

    public Kitap(String baslik, String yazar) {
        this.baslik = baslik;
        this.yazar = yazar;
    }

    public Kitap() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getYazar() {
        return yazar;
    }

    public void setYazar(String yazar) {
        this.yazar = yazar;
    }
}
