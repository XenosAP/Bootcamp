/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.Schedule;

/**
 *
 * @author USER
 */
public interface ScheduleDaoInterface {
    public ArrayList<Schedule> getSchedules();
    public void insertSchedule();
    public void updateSchedule();
    public void deleteSchedule();
    public ArrayList<Schedule> getSchedulesPerCourseById(int courseid);
}
