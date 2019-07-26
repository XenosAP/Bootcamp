/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.Schedule;
import model.SchedulePerCourse;
import model.User;

/**
 *
 * @author USER
 */
public interface SchedulePerCourseDaoInterface {
    public void insertScheduleInCourse();
    public ArrayList<SchedulePerCourse> getSchedulesPerCourse();
    public void updateScheduleInCourse();
    public void deleteScheduleFromCourse();
    public ArrayList<Schedule> getSchedulePerCoursePerStudentById(User user);//student menu
}
