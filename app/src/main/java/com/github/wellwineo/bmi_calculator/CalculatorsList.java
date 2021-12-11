package com.github.wellwineo.bmi_calculator;

public class CalculatorsList {
    private static int idx = 0;

    // wrapper class for button
    public static class customButton {
        private int fragmentId;
        private String text;

        public customButton(String text, int id){
            this.text = text;
            this.fragmentId = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getFragmentId() {
            return fragmentId;
        }

        public void setFragmentId(int fragmentId) {
            this.fragmentId = fragmentId;
        }
    }

    public static customButton[] buttons = new customButton[]{
            new customButton("Индекс массы тела", R.id.calculateFragment),
            new customButton("Индекс массы тела", R.id.calculateFragment),
            new customButton("Индекс массы тела", R.id.calculateFragment),
            new customButton("Индекс массы тела", R.id.calculateFragment),
            new customButton("Индекс массы тела", R.id.calculateFragment),
            new customButton("Индекс массы тела", R.id.calculateFragment),
            new customButton("Индекс массы тела", R.id.calculateFragment),
            new customButton("Индекс массы тела", R.id.calculateFragment),
            new customButton("Индекс массы тела", R.id.calculateFragment),
            new customButton("Индекс массы тела", R.id.calculateFragment),
            new customButton("Индекс массы тела", R.id.calculateFragment),
            new customButton("Индекс массы тела", R.id.calculateFragment),
            new customButton("Индекс массы тела", R.id.calculateFragment)
    };

}
