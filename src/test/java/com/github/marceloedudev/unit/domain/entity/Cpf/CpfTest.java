package com.github.marceloedudev.unit.domain.entity.Cpf;

import com.github.marceloedudev.domain.entity.Cpf;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CpfTest {

    @Test
    @DisplayName("should be create invalid cpf")
    public void createInvalidCpf() {
        Cpf cpf = CpfObjectMother.invalid();
        boolean valid = cpf.validate();
        Assertions.assertEquals(false, valid);
    }

    @Test
    @DisplayName("should be create invalid cpf with all digits the same")
    public void createInvalidCpfwithAllDigitsTheSame() {
        Cpf cpf = CpfObjectMother.sameDigits();
        boolean valid = cpf.validate();
        Assertions.assertEquals(false, valid);
    }

    @Test
    @DisplayName("should be create valid cpf")
    public void createValidCpf() {
        Cpf cpf = CpfObjectMother.valid();
        boolean valid = cpf.validate();
        Assertions.assertEquals(true, valid);
    }

}
