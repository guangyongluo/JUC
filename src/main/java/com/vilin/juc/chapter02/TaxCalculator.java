package com.vilin.juc.chapter02;

public class TaxCalculator {

    private final double salary;

    private final double bonus;

    private CalculatorStrategy calculatorStrategy;

    public TaxCalculator(double salary, double bonus){
        this.salary = salary;
        this.bonus = bonus;
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    public CalculatorStrategy getCalculatorStrategy() {
        return calculatorStrategy;
    }

    public void setCalculatorStrategy(CalculatorStrategy calculatorStrategy) {
        this.calculatorStrategy = calculatorStrategy;
    }

    protected double calTax(){
        return calculatorStrategy.calculate(salary, bonus);
    }

    public double calculate(){
        return this.calTax();
    }
}
