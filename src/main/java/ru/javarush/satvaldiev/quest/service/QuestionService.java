package ru.javarush.satvaldiev.quest.service;

import ru.javarush.satvaldiev.quest.entity.Question;
import ru.javarush.satvaldiev.quest.repository.QuestionRepository;

import java.util.Optional;

public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Optional<Question> getNextQuestion(long questId, long questionId){
        return questionRepository.getNextQuestion(questId, questionId);
    }
}
