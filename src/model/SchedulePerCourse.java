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
public class SchedulePerCourse {
   private String courseTitle;
   private Date scheduleDate;
   private String scheduleSubject;

    public SchedulePerCourse() {
    }

    public SchedulePerCourse(String courseTitle, Date scheduleDate, String scheduleSubject) {
        this.courseTitle = courseTitle;
        this.scheduleDate = scheduleDate;
        this.scheduleSubject = scheduleSubject;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleSubject() {
        return scheduleSubject;
    }

    public void setScheduleSubject(String scheduleSubject) {
        this.scheduleSubject = scheduleSubject;
    }

    @Override
    public String toString() {
        return "SchedulePerCourse{" + "courseTitle=" + courseTitle + ", scheduleDate=" + scheduleDate + ", scheduleSubject=" + scheduleSubject + '}';
    }
   
}
