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
    private String winOrLose;



    public Question(){

    }

    public Question(long questId, long questionId, String startMessage, String firstChoice, String secondChoice, long prevQuestionId, long nextQuestionIdFirst, long nextQuestionIdSecond, String winOrLose) {
        this.questId = questId;
        this.questionId = questionId;
        this.startMessage = startMessage;
        this.firstChoice = firstChoice;
        this.secondChoice = secondChoice;
        this.prevQuestionId = prevQuestionId;
        this.nextQuestionIdFirst = nextQuestionIdFirst;
        this.nextQuestionIdSecond = nextQuestionIdSecond;
        this.winOrLose = winOrLose;
    }

    public long getQuestId() {
        return questId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public String getStartMessage() {
        return startMessage;
    }

    public String getFirstChoice() {
        return firstChoice;
    }

    public String getSecondChoice() {
        return secondChoice;
    }

    public long getPrevQuestionId() {
        return prevQuestionId;
    }

    public long getNextQuestionIdFirst() {
        return nextQuestionIdFirst;
    }

    public long getNextQuestionIdSecond() {
        return nextQuestionIdSecond;
    }

    public String getWinOrLose() {
        return winOrLose;
    }

}
