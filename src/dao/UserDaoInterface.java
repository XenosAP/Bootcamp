/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.User;

/**
 *
 * @author creoo
 */
public interface UserDaoInterface {
    public User authentication();
    public ArrayList<User> getStudents();
    public ArrayList<User> getTrainers();
    public void insertStudent();
    public void insertTrainer();
    public void updateStudent();
    public void updateTrainer();
    public void deleteStudent();
    public void deleteTrainer();
    public ArrayList<User> getStudentsPerCourseById(int courseid);
    public ArrayList<User> getTrainersPerCourseById(int courseid);
    
    
}
