package com.example.CodeJudge.modelDTOs;

public class APIResponce {

    public String message;
    private Boolean status;

    public APIResponce(String message, boolean status) {
        this.message = message;
        this.status = status;
    }
}
