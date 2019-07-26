/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.AssignmentsPerCourse;

/**
 *
 * @author USER
 */
public interface AssignmentsPerCourseDaoInterface {
    public void insertAssignmentInCourse();
    public ArrayList<AssignmentsPerCourse> getAssignmentsPerCourse();
    public void updateAssignmentInCourse();
    public void deleteAssignmentFromCourse();
    public void getDatesOfAssignmentsPerCourse();//student menu
}
