/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author creoo
 */
public class AssignmentsPerCourse {
   private String courseTitle;
   private String assignmentTitle;
   private Date submissionDate;

    public AssignmentsPerCourse() {
    }

    public AssignmentsPerCourse(String courseTitle, String assignmentTitle, Date submissionDate) {
        this.courseTitle = courseTitle;
        this.assignmentTitle = assignmentTitle;
        this.submissionDate = submissionDate;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getAssignmentTitle() {
        return assignmentTitle;
    }

    public void setAssignmentTitle(String assignmentTitle) {
        this.assignmentTitle = assignmentTitle;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    @Override
    public String toString() {
        return "AssignmentsPerCourse{" + "courseTitle=" + courseTitle + ", assignmentTitle=" + assignmentTitle + ", submissionDate=" + submissionDate + '}';
    }
   
}
