package com.kittycoder.student.po;

/**
 * Created by shucheng on 2020/8/26 17:07
 */
public class Student {

    private int sid;
    private String sname;
    private int sage;
    private String sbirthday;

    public Student() {
    }

    public Student(int sid, String sname, int sage, String sbirthday) {
        this.sid = sid;
        this.sname = sname;
        this.sage = sage;
        this.sbirthday = sbirthday;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getSage() {
        return sage;
    }

    public void setSage(int sage) {
        this.sage = sage;
    }

    public String getSbirthday() {
        return sbirthday;
    }

    public void setSbirthday(String sbirthday) {
        this.sbirthday = sbirthday;
    }
}
