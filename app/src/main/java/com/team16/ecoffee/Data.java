package com.team16.ecoffee;

public class Data {
    private int _id;
    private String name;
    private String pass;
    private String card;

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getExcard() {
        return excard;
    }

    public void setExcard(String excard) {
        this.excard = excard;
    }

    private String excard;

    public Data() {
    }

    public Data(int id, String name, String pass) {
        this._id = id;
        this.name = name;
        this.pass = pass;
    }

    public Data(int id, String name, String pass, String card, String excard) {
        this._id = id;
        this.name = name;
        this.pass = pass;
        this.card = card;
        this.excard = excard;
    }
    public Data(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public void setID(int id) {
        this._id = id;
    }

    public int getID() {
        return this._id;
    }

    public void setDataName(String name) {
        this.name = name;
    }

    public String getDataName() {
        return this.name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return this.pass;
    }
}
