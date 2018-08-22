package com.agnt45.revaplacement.Classes;

import java.util.ArrayList;

public class Question{
    String QuestionText;
    String[] OptionList;
    public Question(String QuestionText, String[] OptionList) {
        this.QuestionText = QuestionText;
        this.OptionList = OptionList;
    }

    public String getQuestionText() {
        return this.QuestionText;
    }

    public void setQuestionText(String QuestionText) {
        this.QuestionText = QuestionText;
    }

    public String[] getOptionList() {
        return this.OptionList;
    }

    public void setOptionList(String[] OptionList) {
        this.OptionList = OptionList;
    }


    public Question() {
    }
}
