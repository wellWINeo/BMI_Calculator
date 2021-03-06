package com.github.wellwineo.bmi_calculator.ormliteDB;


import com.j256.ormlite.field.DataType;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;

import java.util.HashMap;

@DatabaseTable(tableName = "results")
public class Result {
    @DatabaseField(generatedId = true)
    private
    int id;

    @DatabaseField
    private
    String title;

    @DatabaseField
    private
    String result;

    @DatabaseField
    private
    boolean isOk;

//    @DatabaseField
//    private
//    HashMap<String, String> values;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private
    HashMap<String, String> values;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public HashMap<String, String> getValues() {
        return values;
    }

    public void setValues(HashMap<String, String> values) {
        this.values = values;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public Result(){}

    public Result(int id, String title, String result, boolean isOk,
                  HashMap<String, String> values){
        this.id = id;
        this.title = title;
        this.result = result;
        this.isOk = isOk;
        this.values = values;
    }

    public Result(String title, String result, boolean isOk,
                  HashMap<String, String> values){
        this.title = title;
        this.result = result;
        this.isOk = isOk;
        this.values = values;
    }
}
