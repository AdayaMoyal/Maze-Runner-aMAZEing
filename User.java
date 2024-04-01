package com.example.appbyadaya;

public class User {
    public int coins;
    public String mail, password, name;

    User()
    {

    }
    User(String mail, String password, String name) {
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.coins = 0;
    }

    User(int coins, String mail, String password, String name) {
        this.mail = mail;
        this.password = password;
        this.name = name;
        this.coins = coins;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public String getMail() {
        return mail;
    }

    public void setEmail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
