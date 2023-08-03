package com.madatouriste.modele;

import java.io.Serializable;

public class Token implements Serializable {
    String token;

    public Token() {
    }

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    public String getBearerToken() {
        return "Bearer " + token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                '}';
    }
}
