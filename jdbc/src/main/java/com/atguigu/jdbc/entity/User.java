package com.atguigu.jdbc.entity;

public class User {
    private int id;
    private String url;
    private String username;
    private String pwd;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public User() {
    }

    public User(int id, String url, String username, String pwd) {
        this.id = id;
        this.url = url;
        this.username = username;
        this.pwd = pwd;
    }
}
