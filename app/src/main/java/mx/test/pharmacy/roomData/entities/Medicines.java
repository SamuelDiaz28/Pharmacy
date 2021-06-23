package mx.test.pharmacy.roomData.entities;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "medicines")
public class Medicines implements Serializable {

    @PrimaryKey(autoGenerate = true) private int id;

    private int idMedicament;
    private String name;
    private String grammage;
    private String cost;
    private String category;
    private int idPharmacy;
    private String activeIngredient;
    private String imgMedicine;
    private String presentation;
    private String pharmaceuticalForm;



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

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public int getIdMedicament() {
        return idMedicament;
    }

    public void setIdMedicament(int idMedicament) {
        this.idMedicament = idMedicament;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIdPharmacy() {
        return idPharmacy;
    }

    public void setIdPharmacy(int idPharmacy) {
        this.idPharmacy = idPharmacy;
    }

    public String getActiveIngredient() {
        return activeIngredient;
    }

    public void setActiveIngredient(String activeIngredient) {
        this.activeIngredient = activeIngredient;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getPharmaceuticalForm() {
        return pharmaceuticalForm;
    }

    public void setPharmaceuticalForm(String pharmaceuticalForm) {
        this.pharmaceuticalForm = pharmaceuticalForm;
    }
}
