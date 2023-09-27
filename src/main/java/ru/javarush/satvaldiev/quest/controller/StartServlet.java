package ru.javarush.satvaldiev.quest.controller;

import java.io.*;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ru.javarush.satvaldiev.quest.entity.Quest;
import ru.javarush.satvaldiev.quest.entity.Question;
import ru.javarush.satvaldiev.quest.repository.QuestRepository;
import ru.javarush.satvaldiev.quest.repository.QuestionRepository;
import ru.javarush.satvaldiev.quest.service.QuestService;
import ru.javarush.satvaldiev.quest.service.QuestionService;

@WebServlet(name = "startServlet", value = "/start-servlet")
public class StartServlet extends HttpServlet {

    private List<String> questsList;
    private QuestService questService;
    private QuestionService questionService;

    public void init() {
        questService = new QuestService(new QuestRepository());
        questionService = new QuestionService(new QuestionRepository());
        questsList = questService.getAllQuestsNames();
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession currentSession = request.getSession();
        String playerName = request.getParameter("name");
        if (playerName.isBlank()) playerName = "Гость";
        if (currentSession.getAttribute("playerName") == null) {
            currentSession.setAttribute("playerName", playerName);
        }
        else playerName = (String) currentSession.getAttribute("playerName");

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><head>\n" +
                "  <title>Квесты от Сереги</title>\n" +
                "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">\n" +
                "</head><body>");
        out.println("<h1>"+ playerName + ", выберите квест из списка:</h1>");
        out.println("<table>");

        for (String s : questsList) {
           long questId = questService.getQuest(s).orElse(null).getQuestId();
           out.println("<tr><td onclick=\"window.location='/start-servlet?questionId=null&questId=" + questId + "'\">" + s +"</td></tr>");
        }
        out.println("</table>");
        out.println("</body></html>");
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if  (request.getParameter("questionId").equals("null")) {
            Long questId = Long.valueOf(request.getParameter("questId"));
            Quest quest = questService.getQuestById(questId).orElse(null);
            String questDescription = quest.getQuestDescription();
            String[] splitQuestDescription = questDescription.split("\n");
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><head>\n" +
                    "  <title>Квесты от Сереги</title>\n" +
                    "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">\n" +
                    "</head><body>");
            for (int i = 0; i < splitQuestDescription.length; i++) {
                if (splitQuestDescription[i].startsWith("<<<")) {
                    String edited = splitQuestDescription[i].replaceAll("<<<", "");
                    out.println("<h2>"+ edited + "</h2>");
                }
                else out.println("<p>" + splitQuestDescription[i] + "</p>");
            }
            out.println("<table><tr>");
            out.println("<td><button id=\"button1\" onclick=\"window.location='/start-servlet?questionId=1&questId=" + questId + "'\">Начать</td>");
            out.println("</tr></table>");

        }
        else {
            Long questId = Long.valueOf(request.getParameter("questId"));
            Long questionId = Long.valueOf(request.getParameter("questionId"));
            Question question = questionService.getNextQuestion(questId, questionId).orElse(null);
            String startMessage = question.getStartMessage();
            String[] splitStartMessage = startMessage.split("\n");
            response.setContentType("text/html");

            PrintWriter out = response.getWriter();
            out.println("<html><head>\n" +
                    "  <title>Квесты от Сереги</title>\n" +
                    "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">\n" +
                    "</head><body>");
            for (int i = 0; i < splitStartMessage.length; i++) {
                if (splitStartMessage[i].startsWith("<<<")) {
                    String edited = splitStartMessage[i].replaceAll("<<<", "");
                    out.println("<h2>"+ edited + "</h2>");
                }
                else out.println("<p>"+ splitStartMessage[i] + "</p>");
            }
            out.println("<table><tr>");
            out.println("<td><button id=\"button1\" onclick=\"window.location='/start-servlet?questionId=" + question.getNextQuestionIdFirst()+ "&questId=" + questId + "'\">" + question.getFirstChoice() +"</td>");
            if (question.getNextQuestionIdSecond() != 0) {
                out.println("<td><button id=\"button2\" onclick=\"window.location='/start-servlet?questionId=" + question.getNextQuestionIdSecond()+ "&questId=" + questId + "'\">" + question.getSecondChoice() +"</td>");
            }
            else {
                out.println("<td><form action=\"/start-servlet\" method=\"post\"><button name=\"name\">" + question.getSecondChoice() +"</button></form></td>");
            }
            out.println("</tr></table>");
            out.println("</body></html>");
        }
    }
    public void destroy() {
    }
}