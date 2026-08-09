package com.example.nobsv2.exceptions;

public enum ErrorMessages {
    PRODUCT_NOT_FOUND("Product Not Found"),
    NAME_REQUIRED("Name IS Required"),
    DESCRIPTION_LENGTH("Description must be 20 characters"),
    PRICE_CANNOT_BE_NEGATIVE("Price cannot be null");

    private String message;
    ErrorMessages(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
