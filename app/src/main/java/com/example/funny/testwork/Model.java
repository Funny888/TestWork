package com.example.funny.testwork;

public class Model {
    private String login;
    private String url_avatar;

    public Model(String log,String avatar)
    {
        this.login = log;
        this.url_avatar = avatar;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUrl_avatar() {
        return url_avatar;
    }

    public void setUrl_avatar(String url_avatar) {
        this.url_avatar = url_avatar;
    }
}
