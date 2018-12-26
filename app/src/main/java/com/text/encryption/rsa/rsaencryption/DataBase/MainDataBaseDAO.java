package com.text.encryption.rsa.rsaencryption.DataBase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MainDataBaseDAO {

    @Insert
    public void addData(MainDataObject mainDataObject);

    @Query("SELECT * FROM MainDataTable")
    public List<MainDataObject> getAllDataObject();

    @Query("SELECT * FROM MainDataTable WHERE id=:id")
    public MainDataObject getDataById(int id);

    @Query("DELETE FROM MainDataTable WHERE id=:id")
    public void deleteObject(int id);

}
