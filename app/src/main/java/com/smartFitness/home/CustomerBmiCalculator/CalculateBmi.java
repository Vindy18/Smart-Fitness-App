package com.smartFitness.home.CustomerBmiCalculator;


public class CalculateBmi {

    protected Double bmiValue(Double height, Double weight) {
        Double result =((weight / (height * height)) * 10000);
        return result;
    }
}
