/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.StudentsPerCourse;
import model.User;

/**
 *
 * @author USER
 */
public interface StudentsPerCourseDaoInterface {
    public void insertStudentInCourse();
    public ArrayList<StudentsPerCourse> getStudentsPerCourse();
    public void updateStudentInCourse();
    public void deleteStudentFromCourse();
    public void enrollCourse(User user);//student menu
    
}
