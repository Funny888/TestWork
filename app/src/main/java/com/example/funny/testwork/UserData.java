package com.example.funny.testwork;

/* Класс модели для получение данных json-объектов*/

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class UserData {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("avatar_url")
    @Expose
    private String avatar_url;

    @SerializedName("login")
    @Expose
    private String login;

    @SerializedName("name")
    @Expose
    private String Name;

    @SerializedName("company")
    @Expose
    private String Company;

    @SerializedName("html_url")
    @Expose
    private String Aboutmyself;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public String getAboutmyself() {
        return Aboutmyself;
    }

    public void setAboutmyself(String aboutmyself) {
        Aboutmyself = aboutmyself;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @SerializedName("email")
    @Expose
    private String Email;


    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getLogin() {

        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

// "login": "mojombo",
//         "id": 1,
//         "node_id": "MDQ6VXNlcjE=",
//         "avatar_url": "https://avatars0.githubusercontent.com/u/1?v=4",
//         "gravatar_id": "",
//         "url": "https://api.github.com/users/mojombo",
//         "html_url": "https://github.com/mojombo",
//         "followers_url": "https://api.github.com/users/mojombo/followers",
//         "following_url": "https://api.github.com/users/mojombo/following{/other_user}",
//         "gists_url": "https://api.github.com/users/mojombo/gists{/gist_id}",
//         "starred_url": "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
//         "subscriptions_url": "https://api.github.com/users/mojombo/subscriptions",
//         "organizations_url": "https://api.github.com/users/mojombo/orgs",
//         "repos_url": "https://api.github.com/users/mojombo/repos",
//         "events_url": "https://api.github.com/users/mojombo/events{/privacy}",
//         "received_events_url": "https://api.github.com/users/mojombo/received_events",
//         "type": "User",
//         "site_admin": false

}
