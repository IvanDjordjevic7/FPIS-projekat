package com.example.FPIS.Art.Expo._4.dto;



public class IzmenaPrijaveDTO {

    String email;
    String token;
    String novaTipPrijave;
    int noviBrojOsoba;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNovaTipPrijave() {
        return novaTipPrijave;
    }

    public void setNovaTipPrijave(String novaTipPrijave) {
        this.novaTipPrijave = novaTipPrijave;
    }

    public int getNoviBrojOsoba() {
        return noviBrojOsoba;
    }

    public void setNoviBrojOsoba(int noviBrojOsoba) {
        this.noviBrojOsoba = noviBrojOsoba;
    }
}
