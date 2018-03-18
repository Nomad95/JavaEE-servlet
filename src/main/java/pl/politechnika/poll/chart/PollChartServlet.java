package pl.politechnika.poll.chart;

import pl.politechnika.util.TextFileWriter;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

public class PollChartServlet extends HttpServlet {

    public static final String TITLE = "Ankieta";
    public static final String X_AXIS_NAME = "Technologie";
    public static final String Y_AXIS_NAME = "Ilosc glosow";
    public static final String IMAGE_FORMAT = "jpg";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletOutputStream out = response.getOutputStream();
        TextFileWriter textFileWriter = new TextFileWriter();
        Map<String, String> pollValuesMap = textFileWriter.readFromFile();
        BufferedImage chartImage = PollBarChart.getChartImageFromValuesMap(TITLE, X_AXIS_NAME, Y_AXIS_NAME, pollValuesMap);
        ImageIO.write(chartImage, IMAGE_FORMAT, out);
        out.close();
    }

}
