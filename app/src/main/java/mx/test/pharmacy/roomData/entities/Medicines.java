package mx.test.pharmacy.roomData.entities;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "medicines")
public class Medicines implements Serializable {

    @PrimaryKey
    private int id;
    private String name;
    private String grammage;
    private String imgMedicine;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrammage() {
        return grammage;
    }

    public void setGrammage(String grammage) {
        this.grammage = grammage;
    }

    public String getImgMedicine() {
        return imgMedicine;
    }

    public void setImgMedicine(String imgMedicine) {
        this.imgMedicine = imgMedicine;
    }
}
