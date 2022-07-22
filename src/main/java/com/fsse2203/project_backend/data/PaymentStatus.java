package com.fsse2203.project_backend.data;

public enum PaymentStatus {
    PREPARE,
    PROCESSING,
    SUCCESS;

    @Override
    public String toString() {
        return name();
    }
}
