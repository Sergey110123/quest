package ru.javarush.satvaldiev.quest.entity;

import static java.util.Objects.isNull;

public class Quest {
    private long questId;
    private String questName;
    private String questDescription;

    public Quest(){

    }

    public Quest(long questId, String questName, String questDescription) {
        if (questId <= 0) {
            throw new IllegalArgumentException("Quest ID cannot be 0 or negative.");
        }
        if (isNull(questName)) {
            throw new IllegalArgumentException("Quest name cannot be null.");
        } else if (questName.isBlank()) {
            throw new IllegalArgumentException("Quest name cannot be blank.");
        }
        if (isNull(questDescription)) {
            throw new IllegalArgumentException("Quest description cannot be null.");
        } else if (questDescription.isBlank()) {
            throw new IllegalArgumentException("Quest description cannot be blank.");
        }
        this.questId = questId;
        this.questName = questName;
        this.questDescription = questDescription;
    }

    public long getQuestId() {
        return questId;
    }

    public String getQuestName() {
        return questName;
    }

    public String getQuestDescription() {
        return questDescription;
    }


}
