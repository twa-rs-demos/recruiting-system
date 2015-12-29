package com.thoughtworks.twars.bean;

public class QuizItem {
    private int id;
    private String initializedBox;
    private String stepsString;
    private int count;
    private String question;
    private String questionZh;
    private int stepsLength;
    private int maxUpdateTimes;
    private int answer;
    private String description;
    private String descriptionZh;
    private String chartPath;
    private String infoPath;


    public int getId() {
        return id;
    }

    public String getInitializedBox() {
        return initializedBox;
    }

    public String getStepsString() {
        return stepsString;
    }

    public int getCount() {
        return count;
    }

    public String getQuestion() {
        return question;
    }

    public String getQuestionZh() {
        return questionZh;
    }

    public int getStepsLength() {
        return stepsLength;
    }

    public int getMaxUpdateTimes() {
        return maxUpdateTimes;
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionZh() {
        return descriptionZh;
    }

    public String getChartPath() {
        return chartPath;
    }

    public String getInfoPath() {
        return infoPath;
    }

    public void setInitializedBox(String initializedBox) {
        this.initializedBox = initializedBox;
    }

    public void setStepsString(String stepsString) {
        this.stepsString = stepsString;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setQuestionZh(String questionZh) {
        this.questionZh = questionZh;
    }

    public void setStepsLength(int stepsLength) {
        this.stepsLength = stepsLength;
    }

    public void setMaxUpdateTimes(int maxUpdateTimes) {
        this.maxUpdateTimes = maxUpdateTimes;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDescriptionZh(String descriptionZh) {
        this.descriptionZh = descriptionZh;
    }

    public void setChartPath(String chartPath) {
        this.chartPath = chartPath;
    }

    public void setInfoPath(String infoPath) {
        this.infoPath = infoPath;
    }
}
