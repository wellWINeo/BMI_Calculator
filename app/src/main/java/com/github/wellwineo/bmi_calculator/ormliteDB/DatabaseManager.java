package com.github.wellwineo.bmi_calculator.ormliteDB;

import android.content.Context;
import android.provider.ContactsContract;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.nio.file.OpenOption;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager {
    private final String TAG = DatabaseManager.this.getClass().getSimpleName();
    private final Context context;
    private static DatabaseManager INSTANCE;
    private DatabaseHelper databaseHelper;

    private Dao<Result, Long> resultDao;
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String RESULT = "result";
    private static final String VALUES = "values";

    public DatabaseManager(Context context){
        this.context = context;
        databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);

        try {
            resultDao = databaseHelper.getResults();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseManager getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = new DatabaseManager(context);
        return INSTANCE;
    }

    public void releaseDB(){
        if (databaseHelper != null){
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
            INSTANCE = null;
        }
    }

    public int clearAllData(){
        try {
            if (databaseHelper == null)
                return -1;
            databaseHelper.clearTable();
            return 0;
        } catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    public int addResult(Result result) {
        int count = 0;
        try {
            UpdateBuilder updateBuilder = resultDao.updateBuilder();
            String title = result.getTitle() != null ? result.getTitle() : "";
            String _result = result.getResult() != null ? result.getResult() : "";
            HashMap<String, String> values = result.getValues() != null ?
                    result.getValues() :
                    new HashMap<>();

            if (resultDao == null)
                return -1;

            resultDao.create(result);

            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int deleteResult(int id){
        try {
            if (resultDao == null)
                return -1;

            DeleteBuilder deleteBuilder = resultDao.deleteBuilder();
            deleteBuilder.where().eq(ID, id);
            deleteBuilder.delete();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<Result> getAllResults(){
        try {
            if (resultDao == null)
                return null;

            return resultDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
