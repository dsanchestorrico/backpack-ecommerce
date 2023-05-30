package com.postgrado.ecommerce.exception;

public class RoleAlreadyExist extends RuntimeException{
    private static final String ERROR_MESSAGE = "Rol %s is Already Exist";
    private static final String ERROR_NAME = "Rol Name is Already Exist";
    public RoleAlreadyExist(String rolName) {
        super(String.format(ERROR_MESSAGE,rolName));
    }

    @Override
    public String toString() {
        return ERROR_NAME;
    }
}
