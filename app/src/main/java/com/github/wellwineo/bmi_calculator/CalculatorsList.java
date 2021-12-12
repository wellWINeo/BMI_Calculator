package com.github.wellwineo.bmi_calculator;

public class CalculatorsList {
    private static int idx = 0;

    // wrapper class for button
    public static class customButton {
        private String text;

        public customButton(String text){
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static customButton[] buttons = new customButton[]{
            new customButton("Индекс массы тела"),
            new customButton("Индекс массы тела"),
            new customButton("Индекс массы тела"),
            new customButton("Индекс массы тела"),
            new customButton("Индекс массы тела"),
            new customButton("Индекс массы тела"),
            new customButton("Индекс массы тела"),
            new customButton("Индекс массы тела"),
            new customButton("Индекс массы тела"),
            new customButton("Индекс массы тела"),
            new customButton("Индекс массы тела"),
            new customButton("Индекс массы тела"),
            new customButton("Индекс массы тела")
    };

}
