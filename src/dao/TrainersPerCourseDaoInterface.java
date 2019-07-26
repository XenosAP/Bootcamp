/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.TrainersPerCourse;
import model.User;

/**
 *
 * @author USER
 */
public interface TrainersPerCourseDaoInterface {
    public void insertTrainerInCourse();
    public ArrayList<TrainersPerCourse> getTrainersPerCourse();
    public void updateTrainerInCourse();
    public void deleteTrainerFromCourse();
    public void getCoursesPerTrainer(User user);//trainer menu
}
