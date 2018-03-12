package pl.politechnika;

public class CalculationParams {
    private double leftParameter;
    private double rightParameter;
    private String mathOperation;

    public CalculationParams(double leftParameter, double rightParameter, String mathOperation) {
        this.leftParameter = leftParameter;
        this.rightParameter = rightParameter;
        this.mathOperation = mathOperation;
    }

    public CalculationParams() {
    }

    public double getLeftParameter() {
        return leftParameter;
    }

    public void setLeftParameter(double leftParameter) {
        this.leftParameter = leftParameter;
    }

    public double getRightParameter() {
        return rightParameter;
    }

    public void setRightParameter(double rightParameter) {
        this.rightParameter = rightParameter;
    }

    public String getMathOperation() {
        return mathOperation;
    }

    public void setMathOperation(String mathOperation) {
        this.mathOperation = mathOperation;
    }
}
