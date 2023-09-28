package ru.javarush.satvaldiev.quest.controller;

import java.io.*;
import java.util.List;
import java.util.Objects;

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

        if (currentSession.getAttribute("numberOfLoses") == null) {
            Integer loses = 0;
            currentSession.setAttribute("numberOfLoses", loses);
        }
        if (currentSession.getAttribute("numberOfWins") == null) {
            Integer wins = 0;
            currentSession.setAttribute("numberOfWins", wins);
        }
        int totalLoses = (int) currentSession.getAttribute("numberOfLoses");
        int totalWins = (int) currentSession.getAttribute("numberOfWins");
        int totalGames = totalLoses + totalWins;
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html lang=\"en\"><head>\n<meta charset=\"utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">" +
                "  <title>Квесты от Сереги</title>\n" +
                "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">\n" +
                "</head><body>");
        out.println("<div class=\"d-flex justify-content-center\"><h2>"+ playerName + ", выберите квест из списка:</h2></div>");
        out.println("<table>");

        for (String s : questsList) {
           long questId = Objects.requireNonNull(questService.getQuest(s).orElse(null)).getQuestId();

           out.println("<tr><td onclick=\"window.location='/start-servlet?questionId=null&questId=" + questId + "'\"><p class=\"fs-4\">" + questId + ". " + s +"</p></td></tr>");

        }
        out.println("</table>");
        displayStatistics(currentSession, totalLoses, totalWins, totalGames, out);
        out.println("</body></html>");
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession currentSession = request.getSession();
        PrintWriter out = response.getWriter();
        if (currentSession.getAttribute("numberOfLoses") == null) {
            Integer loses = 0;
            currentSession.setAttribute("numberOfLoses", loses);
        }
        if (currentSession.getAttribute("numberOfWins") == null) {
            Integer wins = 0;
            currentSession.setAttribute("numberOfWins", wins);
        }
        if  (request.getParameter("questionId").equals("null")) {
            long questId = Long.parseLong(request.getParameter("questId"));
            Quest quest = questService.getQuestById(questId).orElse(null);
            assert quest != null;
            String questDescription = quest.getQuestDescription();
            String[] splitQuestDescription = questDescription.split("\n");
            response.setContentType("text/html");
            out.println("<html lang=\"en\"><head>\n<meta charset=\"utf-8\">\n" +
                    "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">" +
                    "  <title>Квесты от Сереги</title>\n" +
                    "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">\n" +
                    "</head><body>");
            for (String s : splitQuestDescription) {
                if (s.startsWith("<<<")) {
                    String edited = s.replaceAll("<<<", "");
                    out.println("<div class=\"d-flex justify-content-center\"><h2>" + edited + "</h2></div>");
                } else out.println("<p class=\"fs-4\">" + s + "</p>");
            }
            out.println("<br><div class=\"d-flex justify-content-center\">");
            out.println("<button type=\"button\" class=\"btn btn-primary\" id=\"button1\" onclick=\"window.location='/start-servlet?questionId=1&questId=" + questId + "'\">Начать</button>");
            out.println("</div>");
        }
        else {
            long questId = Long.parseLong(request.getParameter("questId"));
            long questionId = Long.parseLong(request.getParameter("questionId"));
            Question question = questionService.getNextQuestion(questId, questionId).orElse(null);
            assert question != null;
            if (question.getWinOrLose().equals("lose")) {
                Integer numberOfLoses = (Integer) currentSession.getAttribute("numberOfLoses");
                numberOfLoses += 1;
                System.out.println(numberOfLoses);
                currentSession.setAttribute("numberOfLoses", numberOfLoses);
            }
            if (question.getWinOrLose().equals("win")) {
                Integer numberOfWins = (Integer) currentSession.getAttribute("numberOfWins");
                numberOfWins += 1;
                currentSession.setAttribute("numberOfWins", numberOfWins);
            }
            String startMessage = question.getStartMessage();
            String[] splitStartMessage = startMessage.split("\n");
            response.setContentType("text/html");
            out.println("<html lang=\"en\"><head>\n" +
                    "  <title>Квесты от Сереги</title>\n" +
                    "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN\" crossorigin=\"anonymous\">\n" +
                    "</head><body>");
            for (String s : splitStartMessage) {
                if (s.startsWith("<<<")) {
                    String edited = s.replaceAll("<<<", "");
                    out.println("<div class=\"d-flex justify-content-center\"><h2>" + edited + "</h2></div>");
                } else out.println("<p class=\"fs-4\">" + s + "</p>");
            }
            out.println("<table><tr>");
            out.println("<td><form><button type=\"button\" class=\"btn btn-primary\" id=\"button1\" onclick=\"window.location='/start-servlet?questionId=" + question.getNextQuestionIdFirst()+ "&questId=" + questId + "'\">" + question.getFirstChoice() +"</button></form></td>");
            if (question.getNextQuestionIdSecond() != 0) {
                out.println("<td><form><button type=\"button\" class=\"btn btn-secondary\" id=\"button2\" onclick=\"window.location='/start-servlet?questionId=" + question.getNextQuestionIdSecond()+ "&questId=" + questId + "'\">" + question.getSecondChoice() +"</button></form></td>");
            }
            else {
                out.println("<td><form action=\"/start-servlet\" method=\"post\"><button class=\"btn btn-secondary\" name=\"name\">" + question.getSecondChoice() +"</button></form></td>");
            }
            out.println("</tr></table>");
        }
        int totalLoses = (int) currentSession.getAttribute("numberOfLoses");
        int totalWins = (int) currentSession.getAttribute("numberOfWins");
        int totalGames = totalLoses + totalWins;
        displayStatistics(currentSession, totalLoses, totalWins, totalGames, out);
        out.println("</body></html>");
    }

    private void displayStatistics(HttpSession currentSession, int totalLoses, int totalWins, int totalGames, PrintWriter out) {
        out.println("<br>");
        out.println("<table class=\"table\"><tr class=\"table-primary\"><td>Статистика:</td><td></td></tr>");
        out.println("<tr class=\"table-light\"><td>Имя игрока:</td><td>" + currentSession.getAttribute("playerName") + "</td></tr>");
        out.println("<tr class=\"table-light\"><td>Всего сыграно:</td><td>" + totalGames + "</td></tr>");
        out.println("<tr class=\"table-success\"><td>Всего побед:</td><td>" + totalWins + "</td></tr>");
        out.println("<tr class=\"table-danger\"><td>Всего поражений:</td><td>" + totalLoses + "</td></tr></table>");

    }

    public void destroy() {
    }
}