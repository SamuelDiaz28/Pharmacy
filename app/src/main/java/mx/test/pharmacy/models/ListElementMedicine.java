package mx.test.pharmacy.models;

import android.graphics.Bitmap;

public class ListElementMedicine {

    public String name;
    public String cost;
    //public Bitmap resourceImg;
    public String resourceImg;
    public String grammage;

    public ListElementMedicine(String name, String cost, String grammage, String resourceImg){
        this.name = name;
        this.cost = cost;
        this.grammage = grammage;
        this.resourceImg = resourceImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getResourceImg() {
        return resourceImg;
    }

    public void setResourceImg(String resourceImg) {
        this.resourceImg = resourceImg;
    }

    public String getGrammage() {
        return grammage;
    }

    public void setGrammage(String grammage) {
        this.grammage = grammage;
    }
}
