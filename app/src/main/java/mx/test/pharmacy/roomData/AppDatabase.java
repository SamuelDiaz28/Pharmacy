package mx.test.pharmacy.roomData;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import mx.test.pharmacy.roomData.converter.Converter;
import mx.test.pharmacy.roomData.dao.MedicinesDao;
import mx.test.pharmacy.roomData.entities.Medicines;

@TypeConverters(Converter.class)
@Database(version = 1, exportSchema = false, entities = {Medicines.class})
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "PHARMACY_DB";

    private static AppDatabase INSTANCE = null;

    abstract public MedicinesDao medicinesDao();

    public static AppDatabase getInstance(Context context) {
        if(INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, AppDatabase.DB_NAME).fallbackToDestructiveMigration().build();
        return INSTANCE;
    }

}
