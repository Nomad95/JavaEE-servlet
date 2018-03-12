package pl.politechnika;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author student
 */
public class CalcServlet extends HttpServlet {
    public static final String NUMBER_REGEXP = "[0-9]+";


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession userSession = request.getSession(true);

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalcServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Wynik operacji:</h1>");

            out.println(calculateAndGetString(request, userSession));

            out.println("<a href='/calc/calc.jsp' ><button> powrot: </button></a>");
            out.println("<br /> <form method=\"DELETE\" action=\"/calc\">  <input type=\"submit\" value=\"Wyczysc\">\n <input type=\"hidden\" name=\"_method\" value=\"DELETE\"></form> ");

            out.println("<h1>Historia operacji:</h1>");

            out.println(getCalculationHistory(userSession));

            out.println("</body>");
            out.println("</html>");
        }
    }

    private String calculateAndGetString(HttpServletRequest request, HttpSession session) {
        CalcFormParams calcFormParams = getFormsParams(request);
        CalculationParams calculationParams = getCalculationParams();

        String errorMessage = parseAndValidateParams(calculationParams, calcFormParams);
        if (errorMessage.isEmpty()) {
            String resultString = calculateResult(calculationParams);
            saveCalculationToSession(session, resultString);

            return resultString;
        }

        return errorMessage;
    }

    private void saveCalculationToSession(HttpSession session, String resultString) {
        if(Objects.isNull(session.getAttribute("list"))) {
            ArrayList<String> strings = new ArrayList<>();
            strings.add(resultString);
            session.setAttribute("list", strings);
        } else {
            List<String> list = (List<String>) session.getAttribute("list");
            list.add(resultString);
            session.setAttribute("list", list);
        }
    }

    private CalculationParams getCalculationParams() {
        return new CalculationParams();
    }

    private CalcFormParams getFormsParams(HttpServletRequest request) {
        return new CalcFormParams(
                    request.getParameter("leftParam"),
                    request.getParameter("rightParam"),
                    request.getParameter("operation"));
    }

    private String parseAndValidateParams(CalculationParams calcParams, CalcFormParams formParams) {
        if (Objects.isNull(formParams.getLeftParam()) || Objects.isNull(formParams.getRightParam()) || Objects.isNull(formParams.getMathOperation())) {
            return "Proszę nie wpisywać recznie URLow :P <a href='/calc/calc.jsp' >powrot: </a>";
        }
        if (formParams.getLeftParam().isEmpty() || formParams.getRightParam().isEmpty()) {
            return "Proszę wpisać argumenty";
        }

        try {
            calcParams.setLeftParameter(Double.parseDouble(formParams.getLeftParam()));
        } catch (NullPointerException e) {
            return "Błąd - nie podano parametru";
        } catch (NumberFormatException e) {
            return "Lewy parametr " + formParams.getLeftParam() + " jest niepoprawny";
        }

        try {
            calcParams.setRightParameter(Double.parseDouble(formParams.getRightParam()));
        } catch (NullPointerException e) {
            return "Błąd - nie podano parametru";
        } catch (NumberFormatException e) {
            return "Prawy parametr " + formParams.getRightParam() + " jest niepoprawny";
        }

        if (formParams.getMathOperation().isEmpty()) {
            return "Wejdź najpierw do formularza proszę";
        }

        if (formParams.getMathOperation().isEmpty()) {
            return "Proszę przejść najpierw do formularza <br />  <button href='/calc/calc.jsp' >powrot: </button>";
        }
        if (formParams.getMathOperation().equals("/") && calcParams.getRightParameter() == 0) {
            return "Błąd: dzielenie przez zero";
        }
        calcParams.setMathOperation(formParams.getMathOperation());

        return "";
    }

    private String calculateResult(CalculationParams calcParams) {
        StringBuilder resultStringBuilder = new StringBuilder("<h1>")
                .append(calcParams.getLeftParameter()).append(" ")
                .append(calcParams.getMathOperation()).append(" ")
                .append(calcParams.getRightParameter()).append(" = ");

        switch (calcParams.getMathOperation()) {
            case "+":
                resultStringBuilder.append((calcParams.getLeftParameter()) + calcParams.getRightParameter());
                break;
            case "-":
                resultStringBuilder.append((calcParams.getLeftParameter()) - calcParams.getRightParameter());
                break;
            case "*":
                resultStringBuilder.append((calcParams.getLeftParameter()) * calcParams.getRightParameter());
                break;
            case "/":
                resultStringBuilder.append((calcParams.getLeftParameter()) / calcParams.getRightParameter());
                break;
            default: return "Błąd działania";
        }

        return resultStringBuilder.toString();
    }

    private String getCalculationHistory(HttpSession session) {
        List list = (List) session.getAttribute("list");
        return list.toString();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if("DELETE".equals(request.getParameter("_method"))) {
            processSessionDelete(request, response);
        }
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processSessionDelete(req, resp);
    }

    private void processSessionDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        HttpSession userSession = request.getSession(true);

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CalcServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Wyczyszczono historię</h1>");

            out.println("<a href='/calc/calc.jsp' ><button>powrot:</button> </a>");
            out.println("<br /> <form method=\"DELETE\" action=\"/calc\">  <input type=\"submit\" value=\"Wyczysc\">\n <input type=\"hidden\" name=\"_method\" value=\"DELETE\"></form> ");

            out.println("<h1>Historia operacji:</h1>");

            out.println(getCalculationHistory(userSession));

            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private boolean isValid(String input, String reg) {
        Pattern pattern=Pattern.compile(reg);
        Matcher seq= pattern.matcher(input);
        return seq.find();
    }

}
