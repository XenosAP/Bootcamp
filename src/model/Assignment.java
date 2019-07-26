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
public class Assignment {
    private int id;
    private String title;
    private String details;
    private Date submissionDate;

    public Assignment() {
    }

    public Assignment(int id, String title, String details, Date submissionDate) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.submissionDate = submissionDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    @Override
    public String toString() {
        return "Assignment{" + "id=" + id + ", title=" + title + ", details=" + details + ", submissionDate=" + submissionDate + '}';
    }
}
