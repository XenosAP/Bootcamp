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
public class StudentsPerCourse {
    private String courseTitle;
    private String studentFirstname;
    private String studentLastname;

    public StudentsPerCourse() {
    }

    public StudentsPerCourse(String courseTitle, String studentFirstname, String studentLastname) {
        this.courseTitle = courseTitle;
        this.studentFirstname = studentFirstname;
        this.studentLastname = studentLastname;
    }

   

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getStudentFirstname() {
        return studentFirstname;
    }

    public void setStudentFirstname(String studentFirstname) {
        this.studentFirstname = studentFirstname;
    }

    public String getStudentLastname() {
        return studentLastname;
    }

    public void setStudentLastname(String studentLastname) {
        this.studentLastname = studentLastname;
    }

    @Override
    public String toString() {
        return "StudentsPerCourse{" + "courseTitle=" + courseTitle + ", studentFirstname=" + studentFirstname + ", studentLastname=" + studentLastname + '}';
    }
    
}
