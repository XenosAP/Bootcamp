/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.Course;

/**
 *
 * @author USER
 */
public interface CourseDaoInterface {
    public ArrayList<Course> getCourses();
    public void insertCourse();
    public void updateCourse();
    public void deleteCourse();
    public ArrayList<Course> getCoursesNotAssignedToStudentById(int studentid);
    public ArrayList<Course> getCoursesNotAssignedToTrainerById(int trainerid);
    public ArrayList<Course> getCoursesPerStudentById(int studentid);
    public ArrayList<Course> getCoursesPerTrainerById(int trainerid);
}
