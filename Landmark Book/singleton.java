package com.ferhatiltas.landmarkbook;

import android.graphics.Bitmap;

public class singleton {
     private  Bitmap chosenImage;
     private static singleton singletonn;

    private singleton(){

    }

    public Bitmap getChosenImage() {
        return chosenImage;
    }

    public void setChosenImage(Bitmap chosenImage) {
        this.chosenImage = chosenImage;
    }
    public static singleton getInstance(){
        if (singletonn==null){
            singletonn=new singleton();
        }
         return singletonn;
    }


}



