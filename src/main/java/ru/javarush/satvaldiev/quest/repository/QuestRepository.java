package ru.javarush.satvaldiev.quest.repository;

import ru.javarush.satvaldiev.quest.entity.Quest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.util.Objects.isNull;

public class QuestRepository {
    private static final List<Quest> questStorage = new CopyOnWriteArrayList<>() {{
        add(new Quest(1L, "На страже галактики", "<<<Пролог\nТы стоишь в космическом порту и готов подняться на борт своего корабля. " +
                "Разве ты не об этом мечтал? Стать капитаном галактического судна с экипажем, который будет совершать подвиги под твоим командованием. Так что вперед!\n" +
                "<<<Знакомство с экипажем\n Когда ты поднялся на борт корабля, тебя поприветствовала девушка с черной палкой в руках:\n" +
                "- Здравствуйте, командир! Я Зинаида - ваша помощница. Видите? Там в углу пьет кофе наш штурман - сержант Перегарный Шлейф, под штурвалом сидит наш бортпроводник - " +
                "Черный Богдан, а фотографирует его Сергей Стальная Пятка - наш медик. Экипаж готов к выполнению ваших указаний!"));
    }};
    public List<String> getAllQuestsNames() {
        List<String> namesList= new ArrayList<>();
        for (Quest s: questStorage) {
            namesList.add(s.getQuestName());
        }
        if (namesList.isEmpty())
            throw new IllegalArgumentException("Quest storage cannot be empty.");
        return namesList;
    }

    public Optional<Quest> getQuest(String questName) {
        if (isNull(questName)) {
            throw new IllegalArgumentException("Quest name cannot be null.");
        } else if (questName.isBlank()) {
            throw new IllegalArgumentException("Quest name cannot be blank.");
        }
        return questStorage.stream().filter(Quest -> questName.equals(Quest.getQuestName())).findFirst();
    }
    public Optional<Quest> getQuestById(long questId) {
        if (questId <= 0) {
            throw new IllegalArgumentException("Quest ID cannot be 0 or negative.");
        }
        return questStorage.stream().filter(Quest -> questId == Quest.getQuestId()).findFirst();
    }
}
