package com.example.nks.discgolfscorecard;

public class Hole {
    private int par;
    private int hole_number;
    private int result;

    public Hole(int par_p, int hole_number_p) {
        par = par_p;
        hole_number = hole_number_p;
        result = 0;
    }

    public void increaseResult() {
        result++;
    }

    public void setResult(int score) {
        result = score;
    }

    public int getPar() {
        return par;
    }

    public int getHoleNumber() {
        return hole_number;
    }
}
