package com.github.marceloedudev.unit.domain.entity.Cpf;

import com.github.marceloedudev.domain.entity.Cpf;

public class CpfObjectMother {
    public static Cpf valid() {
        return CpfDataBuilder.create().withValidCpf().build();
    }

    public static Cpf invalid() {
        return CpfDataBuilder.create().withInvalidCpf().build();
    }

    public static Cpf sameDigits() {
        return CpfDataBuilder.create().withSameDigits().build();
    }
}
