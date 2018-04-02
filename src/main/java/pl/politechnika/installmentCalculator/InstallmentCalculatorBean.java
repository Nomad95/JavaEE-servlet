package pl.politechnika.installmentCalculator;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class InstallmentCalculatorBean implements Serializable {
    static final long serialVersionUID = 1L;

    private Double loanAmount = 0.0;

    private Double interest = 0.0;

    private Integer numberOfInstallments = 0;

    public Double calculateInstallment() {
        System.out.println(loanAmount + " + " + interest + " + " +  numberOfInstallments);
        if (loanAmount > 0 && interest > 0 && Objects.nonNull(numberOfInstallments)) {
            Double monthlyInterest = interest/1200.0;
            return ((loanAmount * monthlyInterest)/(1.0 - ((1.0)/(Math.pow(1.0 + monthlyInterest, numberOfInstallments)))));
        }

        return 0.0;
    }
}
