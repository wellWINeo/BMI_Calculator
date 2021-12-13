package com.github.wellwineo.bmi_calculator.ormliteDB;

import java.io.Serializable;
import java.util.HashMap;

public class ValuesOrmLiteAdapter implements Serializable {
    public HashMap<String, String> values = new HashMap<>();

    public ValuesOrmLiteAdapter() { }
}
