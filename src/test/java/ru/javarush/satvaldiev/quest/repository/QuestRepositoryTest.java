package ru.javarush.satvaldiev.quest.repository;

import org.junit.jupiter.api.Test;
import ru.javarush.satvaldiev.quest.entity.Quest;
import ru.javarush.satvaldiev.quest.entity.Question;
import ru.javarush.satvaldiev.quest.service.QuestService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class QuestRepositoryTest {
    @Test
    void getAllQuestsNamesFromQuestRepository() {
        QuestService questService = new QuestService(new QuestRepository());
        List<String> list = questService.getAllQuestsNames();
        assertEquals("На страже галактики", list.get(0));
    }

    @Test
    void getQuestByQuestNameIncomingParameterIsNull() {
        QuestRepository questRepository = new QuestRepository();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> questRepository.getQuest(null));
        assertEquals("Quest name cannot be null.", exception.getMessage());
    }
    @Test
    void getQuestByQuestNameIncomingParameterIsBlank() {
        QuestRepository questRepository = new QuestRepository();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> questRepository.getQuest(""));
        assertEquals("Quest name cannot be blank.", exception.getMessage());
    }
    @Test
    void getQuestByQuestName() {
        QuestService questService = new QuestService(new QuestRepository());
        Quest quest = questService.getQuest("На страже галактики").orElse(null);
        assert quest != null;
        assertEquals("На страже галактики", quest.getQuestName());
    }

    @Test
    void getQuestByIdIncomingIdIsZero() {
        QuestRepository questRepository = new QuestRepository();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> questRepository.getQuestById(0));
        assertEquals("Quest ID cannot be 0 or negative.", exception.getMessage());
    }
    @Test
    void getQuestByIdIncomingIdIsNegative() {
        QuestRepository questRepository = new QuestRepository();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> questRepository.getQuestById(-1));
        assertEquals("Quest ID cannot be 0 or negative.", exception.getMessage());
    }
    @Test
    void getQuestById() {
        QuestService questService = new QuestService(new QuestRepository());
        Quest quest = questService.getQuestById(1).orElse(null);
        assert quest != null;
        assertEquals(1, quest.getQuestId());
    }
}