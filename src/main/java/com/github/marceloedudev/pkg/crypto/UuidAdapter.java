package com.github.marceloedudev.pkg.crypto;

import java.util.UUID;

public class UuidAdapter implements UuidCrypto {
    public String randomUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
