package com.nagel.loanapp;

public class Loan {

    static Loan instance = null;
    double principal = 0;     // the amount borrowed inc. costs
    double interestRate = 0;  // interest rate as a number between 0 and 1 that indicates the rate for 1 period
    int periods = 0;          // the number of periods and thus number of payments

    public Loan() {
    }

    public static Loan getInstance() {
        if(instance == null)
        {
            synchronized (Loan.class)
            {
                if(instance == null) instance = new Loan();
            }
        }
        return instance;
    }

    public double payment()
    {
        return principal * interestRate / (1 - Math.pow(1 + interestRate, -periods));
    }

    public double outstanding(int n)
    {
        return principal * Math.pow(1 + interestRate, n) - payment() * (Math.pow(1 + interestRate, n) - 1) / interestRate;
    }

    public double interest(int n)
    {
        return outstanding(n - 1) * interestRate;
    }

    public double repayment(int n)
    {
        return payment() - interest(n);
    }


    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public int getPeriods() {
        return periods;
    }
}
