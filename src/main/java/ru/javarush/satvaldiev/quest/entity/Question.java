package ru.javarush.satvaldiev.quest.entity;

import static java.util.Objects.isNull;

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
        if (questId <= 0) {
            throw new IllegalArgumentException("Quest ID cannot be 0 or negative.");
        }
        if (questionId <= 0) {
            throw new IllegalArgumentException("Question ID cannot be 0 or negative.");
        }
        if (isNull(startMessage)) {
            throw new IllegalArgumentException("Start message cannot be null.");
        } else if (startMessage.isBlank()) {
            throw new IllegalArgumentException("Start message cannot be blank.");
        }
        if (isNull(firstChoice)) {
            throw new IllegalArgumentException("First choice cannot be null.");
        } else if (firstChoice.isBlank()) {
            throw new IllegalArgumentException("First choice cannot be blank.");
        }
        if (isNull(secondChoice)) {
            throw new IllegalArgumentException("Second choice cannot be null.");
        } else if (secondChoice.isBlank()) {
            throw new IllegalArgumentException("Second choice cannot be blank.");
        }
        if (prevQuestionId < 0) {
            throw new IllegalArgumentException("Previous question ID cannot be negative.");
        }
        if (nextQuestionIdFirst <= 0) {
            throw new IllegalArgumentException("First next question ID cannot be 0 or negative.");
        }
        if (nextQuestionIdSecond < 0) {
            throw new IllegalArgumentException("Second next question ID cannot be negative.");
        }
        if (isNull(winOrLose)) {
            throw new IllegalArgumentException("WinOrLose cannot be null.");
        } else if (winOrLose.isBlank()) {
            throw new IllegalArgumentException("WinOrLose cannot be blank.");
        }
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
