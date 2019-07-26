/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author creoo
 */
public class Schedule {
    private int id;
    private String subject;
    private Date date;

    public Schedule() {
    }

    public Schedule(int id, String subject, Date date) {
        this.id = id;
        this.subject = subject;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Schedule{" + "id=" + id + ", subject=" + subject + ", date=" + date + '}';
    }
}
