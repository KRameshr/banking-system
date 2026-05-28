package com.banking.dto;

import java.math.BigDecimal;

public class BudgetRequest {

    private BigDecimal dailyLimit;
    private BigDecimal weeklyLimit;
    private BigDecimal monthlyLimit;

    // Getters
    public BigDecimal getDailyLimit() { return dailyLimit; }
    public BigDecimal getWeeklyLimit() { return weeklyLimit; }
    public BigDecimal getMonthlyLimit() { return monthlyLimit; }

    // Setters
    public void setDailyLimit(BigDecimal d) { this.dailyLimit = d; }
    public void setWeeklyLimit(BigDecimal w) { this.weeklyLimit = w; }
    public void setMonthlyLimit(BigDecimal m) { this.monthlyLimit = m; }
}

