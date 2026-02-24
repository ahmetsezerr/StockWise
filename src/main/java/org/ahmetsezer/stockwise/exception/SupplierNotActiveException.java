package org.ahmetsezer.stockwise.exception;

public class SupplierNotActiveException extends RuntimeException {
    public SupplierNotActiveException(String message) {
        super(message);
    }
}
