package ru.javarush.satvaldiev.quest.entity;

public class Question {

    private long questId;
    private long questionId;
    private String startMessage;
    private String firstChoice;
    private String secondChoice;
    private long prevQuestionId;
    private long nextQuestionIdFirst;
    private long nextQuestionIdSecond;



    public Question(){

    }

    public Question(long questId, long questionId, String startMessage, String firstChoice, String secondChoice, long prevQuestionId, long nextQuestionIdFirst, long nextQuestionIdSecond) {
        this.questId = questId;
        this.questionId = questionId;
        this.startMessage = startMessage;
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;
        this.prevQuestionId = prevQuestionId;
        this.nextQuestionIdFirst = nextQuestionIdFirst;
        this.nextQuestionIdSecond = nextQuestionIdSecond;
    }

    public long getQuestId() {
        return questId;
    }

    public void setQuestId(long questId) {
        this.questId = questId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getStartMessage() {
        return startMessage;
    }

    public void setStartMessage(String startMessage) {
        this.startMessage = startMessage;
    }

    public String getFirstChoice() {
        return firstChoice;
    }

    public void setFirstChoice(String firstChoice) {
        this.firstChoice = firstChoice;
    }

    public String getSecondChoice() {
        return secondChoice;
    }

    public void setSecondChoice(String secondChoice) {
        this.secondChoice = secondChoice;
    }

    public long getPrevQuestionId() {
        return prevQuestionId;
    }

    public void setPrevQuestionId(long prevQuestionId) {
        this.prevQuestionId = prevQuestionId;
    }

    public long getNextQuestionIdFirst() {
        return nextQuestionIdFirst;
    }

    public void setNextQuestionIdFirst(long nextQuestionIdFirst) {
        this.nextQuestionIdFirst = nextQuestionIdFirst;
    }
    public long getNextQuestionIdSecond() {
        return nextQuestionIdSecond;
    }

    public void setNextQuestionIdSecond(long nextQuestionIdSecond) {
        this.nextQuestionIdSecond = nextQuestionIdSecond;
    }
}
