package com.github.wellwineo.bmi_calculator.ormliteDB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    public static final String DATABASE_NAME = "results";
    public static final int DATABASE_VERSION = 1;

    private Dao<Result, Long> resultsDBS;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database,
                         ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Result.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database,
                          ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            if(checkTableExists(database)){
                TableUtils.dropTable(connectionSource, Result.class, false);
                onCreate(database, connectionSource);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean checkTableExists(SQLiteDatabase database){
        Cursor cursor = null;

        try {
            cursor = database.query("results", null, null, null,
                    null, null, null);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Dao<Result, Long> getResults() throws SQLException {
        if (resultsDBS == null)
            resultsDBS = getDao(Result.class);
        return resultsDBS;
    }

    @Override
    public void close() {
        resultsDBS = null;
        super.close();
    }

    public void clearTable() throws SQLException {
        TableUtils.clearTable(getConnectionSource(), Result.class);
    }
}
