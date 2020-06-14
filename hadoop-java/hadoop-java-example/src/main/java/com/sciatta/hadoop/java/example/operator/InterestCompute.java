package com.sciatta.hadoop.java.example.operator;

import java.math.BigDecimal;

/**
 * Created by yangxiaoyu on 2019-03-30<br>
 * All Rights Reserved(C) 2017 - 2019 SCIATTA<br><p/>
 * InterestCompute
 */
public class InterestCompute {
    private static final int YEAR_DAY = 365;
    private static final int SEVEN_DAY = 7;

    public static void main(String[] args) {
        double credit = 10000.00;

        double year = 10.81;
        double sevenDay = 2.06;

        System.out.format("fixed 10.81 is : %f%n", fixed(credit, year));
        System.out.format("fixed 2.06 is : %f%n", fixed(credit, sevenDay));
        System.out.format("flexible is : %f%n", flexible(credit, sevenDay));
    }

    private static double flexible(double credit, double income) {
        return compute(credit, income, SEVEN_DAY);
    }

    private static double fixed(double credit, double income) {
        return compute(credit, income, YEAR_DAY);
    }

    private static double compute(double credit, double income, int period) {
        int remainder = YEAR_DAY;
        double sum = 0.0;
        while ((remainder = (remainder - period)) >= 0) {
            sum += oneDayInterest(credit + sum, income) * period;
        }
        return sum;
    }

    private static double oneDayInterest(double credit, double income) {
        return (income / 100) * credit / YEAR_DAY;
    }
}
