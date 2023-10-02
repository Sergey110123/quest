package ru.javarush.satvaldiev.quest.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Test
    void questIdIsZeroInQuestionConstructor(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Question(0, 1,
                "Test", "Test", "Test", 0, 2, 3, "Test"));
        assertEquals("Quest ID cannot be 0 or negative.", exception.getMessage());
    }
    @Test
    void questIdIsNegativeInQuestionConstructor(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Question(-1, 1,
                "Test", "Test", "Test", 0, 2, 3, "Test"));
        assertEquals("Quest ID cannot be 0 or negative.", exception.getMessage());
    }
    @Test
    void questionIdIsZeroInQuestionConstructor(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Question(1, 0,
                "Test", "Test", "Test", 0, 2, 3, "Test"));
        assertEquals("Question ID cannot be 0 or negative.", exception.getMessage());
    }
    @Test
    void questionIdIsNegativeInQuestionConstructor(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Question(1, -1,
                "Test", "Test", "Test", 0, 2, 3, "Test"));
        assertEquals("Question ID cannot be 0 or negative.", exception.getMessage());
    }
    @Test
    void startMessageIsNullInQuestConstructor(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Question(1, 1,
                null, "Test", "Test", 0, 2, 3, "Test"));
        assertEquals("Start message cannot be null.", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", "\t", "\r", "\n", "\f", "\u000B"})
    void startMessageIsBlankInQuestConstructor(String strings){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Question(1, 1,
                strings, "Test", "Test", 0, 2, 3, "Test"));
        assertEquals("Start message cannot be blank.", exception.getMessage());
    }
    @Test
    void firstChoiceIsNullInQuestConstructor(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Question(1, 1,
                "Test", null, "Test", 0, 2, 3, "Test"));
        assertEquals("First choice cannot be null.", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", "\t", "\r", "\n", "\f", "\u000B"})
    void firstChoiceIsBlankInQuestConstructor(String strings){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Question(1, 1,
                "Test", strings, "Test", 0, 2, 3, "Test"));
        assertEquals("First choice cannot be blank.", exception.getMessage());
    }
    @Test
    void secondChoiceIsNullInQuestConstructor(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Question(1, 1,
                "Test", "Test", null, 0, 2, 3, "Test"));
        assertEquals("Second choice cannot be null.", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", "\t", "\r", "\n", "\f", "\u000B"})
    void secondChoiceIsBlankInQuestConstructor(String strings){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Question(1, 1,
                "Test", "Test", strings, 0, 2, 3, "Test"));
        assertEquals("Second choice cannot be blank.", exception.getMessage());
    }
    @Test
    void prevQuestionIdIsNegativeInQuestionConstructor(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Question(1, 1,
                "Test", "Test", "Test", -1, 2, 3, "Test"));
        assertEquals("Previous question ID cannot be negative.", exception.getMessage());
    }
    @Test
    void nextQuestionIdFirstIsZeroInQuestionConstructor(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Question(1, 1,
                "Test", "Test", "Test", 0, 0, 3, "Test"));
        assertEquals("First next question ID cannot be 0 or negative.", exception.getMessage());
    }
    @Test
    void nextQuestionIdFirstIsNegativeInQuestionConstructor(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Question(1, 1,
                "Test", "Test", "Test", 0, -1, 3, "Test"));
        assertEquals("First next question ID cannot be 0 or negative.", exception.getMessage());
    }
    @Test
    void nextQuestionIdSecondIsNegativeInQuestionConstructor(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Question(1, 1,
                "Test", "Test", "Test", 0, 1, -1, "Test"));
        assertEquals("Second next question ID cannot be negative.", exception.getMessage());
    }
    @Test
    void winOrLoseIsNullInQuestConstructor(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Question(1, 1,
                "Test", "Test", "Test", 0, 2, 3, null));
        assertEquals("WinOrLose cannot be null.", exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"", "\t", "\r", "\n", "\f", "\u000B"})
    void winOrLoseIsBlankInQuestConstructor(String strings){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Question(1, 1,
                "Test", "Test", "Test", 0, 2, 3, strings));
        assertEquals("WinOrLose cannot be blank.", exception.getMessage());
    }

    @Test
    void getQuestId() {
        Question question = new Question(1, 1,
                "Test", "Test", "Test", 0, 2, 3, "Test");
        assertEquals(1, question.getQuestId());
    }

    @Test
    void getQuestionId() {
        Question question = new Question(1, 2,
                "Test", "Test", "Test", 0, 2, 3, "Test");
        assertEquals(2, question.getQuestionId());
    }

    @Test
    void getStartMessage() {
        Question question = new Question(1, 2,
                "Hello", "Test", "Test", 0, 2, 3, "Test");
        assertEquals("Hello", question.getStartMessage());
    }

    @Test
    void getFirstChoice() {
        Question question = new Question(1, 2,
                "Test", "First", "Test", 0, 2, 3, "Test");
        assertEquals("First", question.getFirstChoice());
    }

    @Test
    void getSecondChoice() {
        Question question = new Question(1, 2,
                "Test", "Test", "Second", 0, 2, 3, "Test");
        assertEquals("Second", question.getSecondChoice());
    }

    @Test
    void getPrevQuestionId() {
        Question question = new Question(1, 2,
                "Test", "Test", "Test", 0, 2, 3, "Test");
        assertEquals(0, question.getPrevQuestionId());
    }

    @Test
    void getNextQuestionIdFirst() {
        Question question = new Question(1, 2,
                "Test", "Test", "Test", 0, 2, 3, "Test");
        assertEquals(2, question.getNextQuestionIdFirst());
    }

    @Test
    void getNextQuestionIdSecond() {
        Question question = new Question(1, 2,
                "Test", "Test", "Test", 0, 2, 3, "Test");
        assertEquals(3, question.getNextQuestionIdSecond());
    }

    @Test
    void getWinOrLose() {
        Question question = new Question(1, 2,
                "Test", "Test", "Second", 0, 2, 3, "Win");
        assertEquals("Win", question.getWinOrLose());
    }
}