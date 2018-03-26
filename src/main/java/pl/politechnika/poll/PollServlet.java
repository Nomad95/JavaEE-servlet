package pl.politechnika.poll;

import pl.politechnika.util.TextFileWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class PollServlet extends HttpServlet {

    public static final String HAS_VOTED_IN_POLL_COOKIE_NAME = "hasVotedInPoll";

    static {
        TextFileWriter.initFile();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Map<String, String[]> parameterMap = request.getParameterMap();
        TextFileWriter textFileWriter = new TextFileWriter();

        try (PrintWriter out = response.getWriter()) {
            Optional<Cookie> maybeCookie = getPollCookie(request);
            if(maybeCookie.isPresent()) {
                out.println("Glosowales juz w tej ankiecie <br />");
            } else {
                createPollCookie(response);
                saveResultsToFile(parameterMap, textFileWriter);
                printChosenTechnologies(parameterMap, out);
            }
            printPollResults(out, textFileWriter);
            printReturnButton(out);
            printAdditionalLinks(out);
            printRedirectButton(out);
        }
    }

    private Optional<Cookie> getPollCookie(HttpServletRequest request) {
        List<Cookie> cookies = request.getCookies() != null ? Arrays.asList(request.getCookies()) : new ArrayList<Cookie>();
        return cookies.stream().filter(cookie -> HAS_VOTED_IN_POLL_COOKIE_NAME.equals(cookie.getName())).findFirst();
    }

    private void createPollCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(HAS_VOTED_IN_POLL_COOKIE_NAME, "true");
        cookie.setMaxAge(60*60*2);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    private void printAdditionalLinks(PrintWriter out) {
        out.println("<br /> <a href='/poll/excel' > Pokaz wyniki w arkuszu </a>");
        out.println("<br /> <a href='/poll/chart' > Pokaz wyniki na wykresie </a>");
    }

    private void printRedirectButton(PrintWriter out) {
        out.println("<a href='/redirect' ><button>powrot do strony startowej:</button> </a>");
    }

    private void printChosenTechnologies(Map<String, String[]> parameterMap, PrintWriter out) {
        out.print("Wbrales nastepujace technologie: <br />");
        parameterMap.forEach((key, value) -> out.print("• " + value[0] + "<br />"));
    }

    private void printReturnButton(PrintWriter out) {
        out.println("<a href='/poll/poll.jsp' ><button>powrot do sondy:</button> </a>");
    }

    private void saveResultsToFile(Map<String, String[]> parameterMap, TextFileWriter textFileWriter) {
        textFileWriter.saveToFile(parameterMap);
    }

    private void printPollResults(PrintWriter out, TextFileWriter textFileWriter) {
        out.print("<br> Zobacz wyniki ankiety: <br />");
        Map<String, String> resultsMap = textFileWriter.readFromFile();
        resultsMap.forEach((technology, count) -> out.print("• " + technology + " : " + count + "<br />"));
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }


}
