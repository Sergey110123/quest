package ru.javarush.satvaldiev.quest.entity;

public class Quest {
    private long questId;
    private String questName;
    private String questDescription;

    public Quest(){

    }

    public Quest(long questId, String questName, String questDescription) {
        this.questId = questId;
        this.questName = questName;
        this.questDescription = questDescription;
    }

    public long getQuestId() {
        return questId;
    }

    public void setQuestId(long questId) {
        this.questId = questId;
    }

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public String getQuestDescription() {
        return questDescription;
    }

    public void setQuestDescription(String questDescription) {
        this.questDescription = questDescription;
    }
}
