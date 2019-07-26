/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import model.AssignmentsPerStudent;
import model.User;

/**
 *
 * @author USER
 */
public interface AssignmentsPerStudentDaoInterface {
    public void getAssignmentsPerStudentByIdandSubmit(User user);//student menu
    public ArrayList<AssignmentsPerStudent> getAssignmentsPerStudentPerCourse();//trainer menu #3
    public void MarkAssignmentsPerStudentPerCourse();//trainer menu #4
    
}
