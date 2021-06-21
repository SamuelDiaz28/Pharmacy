package mx.test.pharmacy.roomData.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import mx.test.pharmacy.roomData.entities.Medicines;

@Dao
public interface MedicinesDao {

    @Insert
    void insert(Medicines userInfo);

    @Insert
    void insert(List<Medicines> medicines);

    @Update
    void update(List<Medicines> medicines);

    @Delete
    void delete(Medicines medicines);

    @Delete
    void delete(List<Medicines> medicines);

    @Query("SELECT * FROM medicines")
    List<Medicines> get();

    @Query("SELECT * FROM medicines")
    List<Medicines> getAll();
}
