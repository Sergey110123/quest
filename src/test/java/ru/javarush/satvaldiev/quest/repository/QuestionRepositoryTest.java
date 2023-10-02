package ru.javarush.satvaldiev.quest.repository;

import org.junit.jupiter.api.Test;
import ru.javarush.satvaldiev.quest.entity.Question;
import ru.javarush.satvaldiev.quest.service.QuestionService;
import static org.junit.jupiter.api.Assertions.*;

class QuestionRepositoryTest {

    @Test
    void QuestIdIsZeroInGetNextQuestionFirstParameter() {
        QuestionService questionService = new QuestionService(new QuestionRepository());
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> questionService.getNextQuestion(0, 1));
        assertEquals("Quest ID cannot be 0 or negative.", exception.getMessage());
    }
    @Test
    void QuestIdIsNegativeInGetNextQuestionFirstParameter() {
        QuestionService questionService = new QuestionService(new QuestionRepository());
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> questionService.getNextQuestion(-1, 1));
        assertEquals("Quest ID cannot be 0 or negative.", exception.getMessage());
    }
    @Test
    void QuestionIdIsZeroInGetNextQuestionSecondParameter() {
        QuestionService questionService = new QuestionService(new QuestionRepository());
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> questionService.getNextQuestion(1, 0));
        assertEquals("Question ID cannot be 0 or negative.", exception.getMessage());
    }
    @Test
    void QuestionIdIsNegativeInGetNextQuestionSecondParameter() {
        QuestionService questionService = new QuestionService(new QuestionRepository());
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> questionService.getNextQuestion(1, -1));
        assertEquals("Question ID cannot be 0 or negative.", exception.getMessage());
    }

    @Test
    void getNextQuestionFromQuestionRepository() {
        QuestionService questionService = new QuestionService(new QuestionRepository());
        Question question = questionService.getNextQuestion(1, 3).orElse(null);
        assert question != null;
        assertEquals(3, question.getQuestionId());
    }
}