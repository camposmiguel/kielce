package com.miguelcr.a01_duckgame;

import io.realm.RealmObject;

/**
 * Created by miguelcampos on 13/7/17.
 */

public class User extends RealmObject {
    String nick;
    int points;

    public User() {
    }

    public User(String nick, int points) {
        this.nick = nick;
        this.points = points;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
