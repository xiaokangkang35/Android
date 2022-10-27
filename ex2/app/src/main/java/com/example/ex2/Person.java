package com.example.ex2;

public class Person {
    private String title;
    private String cont;
    private int pic;

    public Person(String title, String cont, int pic) {
        this.title = title;
        this.cont = cont;
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }
}
