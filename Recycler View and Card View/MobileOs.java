package com.ferhatiltas.recyclerview_cardview;

public class MobileOs {
    String aciklama,cikisTarihi;
    int imgSrc;

    public MobileOs(String aciklama, String cikisTarihi, int imgSrc) {
        this.aciklama = aciklama;
        this.cikisTarihi = cikisTarihi;
        this.imgSrc = imgSrc;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getCikisTarihi() {
        return cikisTarihi;
    }

    public void setCikisTarihi(String cikisTarihi) {
        this.cikisTarihi = cikisTarihi;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }
}
