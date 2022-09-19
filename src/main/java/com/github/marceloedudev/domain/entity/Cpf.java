package com.github.marceloedudev.domain.entity;

public class Cpf {

    private final int CpfValidLength = 11;
    private final int FactorVerifierDigit1 = 10;
    private final int FactorVerifierDigit2 = 11;
    private String value;

    public Cpf() {
    }

    public Cpf(String cpf) {
        this.value = cpf;
    }

    public boolean validate() {
        if (value == null || value.length() == 0 || value.trim().length() == 0) {
            return false;
        }
        String cpf = cleanCpf(value);
        if (cpf.length() != CpfValidLength) {
            return false;
        }
        int digit1 = calculateDigit(cpf, FactorVerifierDigit1);
        int digit2 = calculateDigit(cpf, FactorVerifierDigit2);
        return extractVerifierDigit(cpf).equals(String.format("%d%d", digit1, digit2));
    }

    private int calculateDigit(String cpf, int factor) {
        int total = 0;
        for (int numCount = 1; numCount < cpf.length() - 1; numCount++) {
            if (factor > 1) {
                int num = Integer.valueOf(cpf.substring(numCount - 1, numCount)).intValue();
                total += num * factor--;
            }
        }
        int rest = total % 11;
        if (rest > 1) {
            return 11 - rest;
        }
        return 0;
    }

    private String extractVerifierDigit(String cpf) {
        return cpf.substring(cpf.length() - 2, cpf.length());
    }

    public String cleanCpf(String cpf) {
        return cpf.replaceAll("\\D", "");
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
