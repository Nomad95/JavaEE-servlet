package pl.politechnika.calc;

public class CalcFormParams {
    private String leftParam;
    private String rightParam;
    private String mathOperation;

    public CalcFormParams(String leftParam, String rightParam, String mathOperation) {
        this.leftParam = leftParam;
        this.rightParam = rightParam;
        this.mathOperation = mathOperation;
    }

    public CalcFormParams() {
    }

    public String getLeftParam() {
        return leftParam;
    }

    public void setLeftParam(String leftParam) {
        this.leftParam = leftParam;
    }

    public String getRightParam() {
        return rightParam;
    }

    public void setRightParam(String rightParam) {
        this.rightParam = rightParam;
    }

    public String getMathOperation() {
        return mathOperation;
    }

    public void setMathOperation(String mathOperation) {
        this.mathOperation = mathOperation;
    }
}
