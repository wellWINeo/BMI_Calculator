package com.github.wellwineo.bmi_calculator.ormliteDB;


import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.field.DatabaseField;

import java.util.HashMap;
import java.util.Map;

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

    public Result(){}
}
