package com.github.marceloedudev.unit.domain.entity.Cpf;

import com.github.marceloedudev.domain.entity.Cpf;

public class CpfDataBuilder {

    private Cpf cpf;

    public CpfDataBuilder() {
        cpf = new Cpf();
    }

    public static CpfDataBuilder create() {
        return new CpfDataBuilder();
    }

    public CpfDataBuilder withValidCpf() {
        cpf.setValue("139.543.310-04");
        return this;
    }

    public CpfDataBuilder withInvalidCpf() {
        cpf.setValue("151.141.111-01");
        return this;
    }

    public CpfDataBuilder withSameDigits() {
        cpf.setValue("151.141.111-01");
        return this;
    }

    public Cpf build() {
        return cpf;
    }
}
