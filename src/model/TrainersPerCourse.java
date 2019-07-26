/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;



/**
 *
 * @author creoo
 */
public class TrainersPerCourse {
    private String courseTitle;
    private String trainerFirstname;
    private String trainerLastname;

    public TrainersPerCourse() {
    }

    public TrainersPerCourse(String courseTitle, String trainerFirstname, String trainerLastname) {
        this.courseTitle = courseTitle;
        this.trainerFirstname = trainerFirstname;
        this.trainerLastname = trainerLastname;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getTrainerFirstname() {
        return trainerFirstname;
    }

    public void setTrainerFirstname(String trainerFirstname) {
        this.trainerFirstname = trainerFirstname;
    }

    public String getTrainerLastname() {
        return trainerLastname;
    }

    public void setTrainerLastname(String trainerLastname) {
        this.trainerLastname = trainerLastname;
    }

    @Override
    public String toString() {
        return "TrainersPerCourse{" + "courseTitle=" + courseTitle + ", trainerFirstname=" + trainerFirstname + ", trainerLastname=" + trainerLastname + '}';
    }
    
}
