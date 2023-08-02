package com.madatouriste.modele;

public class CustomResponse<T> {
    int status = 727;
    String message = "";
    T datas;

    public CustomResponse(int status, String message, T datas) {
        this.status = status;
        this.message = message;
        this.datas = datas;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }
}
