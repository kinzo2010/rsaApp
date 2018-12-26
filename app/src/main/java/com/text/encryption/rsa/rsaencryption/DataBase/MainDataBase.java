package com.text.encryption.rsa.rsaencryption.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {MainDataObject.class}, version = 1)
public abstract class MainDataBase extends RoomDatabase{


    private static final String MAIN_DATABASE_NAME = "MainDataBase.db";

    private static MainDataBase mainDataBase;
    public static MainDataBase getInstance(Context context) {

        if(mainDataBase == null) {

            mainDataBase = Room.databaseBuilder(context, MainDataBase.class, MAIN_DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();

        }

        return mainDataBase;

    }

    public abstract MainDataBaseDAO getMainDataBaseDAO();

}
