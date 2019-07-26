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
public class Course {

    private int id;
    private String title;
    private String stream;
    private String typeof;
    private Date startdate;
    private Date enddate;

    public Course() {
    }

    public Course(int id, String title, String stream, String typeof, Date startdate, Date enddate) {
        this.id = id;
        this.title = title;
        this.stream = stream;
        this.typeof = typeof;
        this.startdate = startdate;
        this.enddate = enddate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStream() {
        return stream;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getTypeof() {
        return typeof;
    }

    public void setTypeof(String typeof) {
        this.typeof = typeof;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    @Override
    public String toString() {
        return "Course{" + "id=" + id + ", title=" + title + ", stream=" + stream + ", typeof=" + typeof + ", startdate=" + startdate + ", enddate=" + enddate + '}';
    }

   
}
