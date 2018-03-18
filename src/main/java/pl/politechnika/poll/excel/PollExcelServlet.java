package pl.politechnika.poll.excel;

import pl.politechnika.util.TextFileWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class PollExcelServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/vnd.ms-excel");
        TextFileWriter textFileWriter = new TextFileWriter();
        Map<String, String> pollResultsMap = textFileWriter.readFromFile();
        PrintWriter out = response.getWriter();
        out.println("Technologa\tGlosy");
        pollResultsMap.forEach((technology, count) -> out.println(technology + "\t" + count));
        int sumOfVotes = pollResultsMap.values().stream().mapToInt(Integer::parseInt).sum();
        out.println("Razem\t" + sumOfVotes);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
