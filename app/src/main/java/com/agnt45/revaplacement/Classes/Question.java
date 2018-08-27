package com.agnt45.revaplacement.Classes;

import java.util.ArrayList;

public class Question{
    public Question(String questionText, ArrayList<String> optionArray) {
        this.questionText = questionText;
        this.optionArray = optionArray;
    }

    public Question() {
        //Empty Constructor
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public ArrayList<String> getOptionArray() {
        return optionArray;
    }

    public void setOptionArray(ArrayList<String> optionArray) {
        this.optionArray = optionArray;
    }

    String questionText;
    ArrayList<String> optionArray;



}
