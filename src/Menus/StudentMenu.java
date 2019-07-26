/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import dao.AssignmentsPerCourseDaoImplement;
import dao.AssignmentsPerStudentDaoImplement;
import dao.SchedulePerCourseDaoImplement;
import dao.StudentsPerCourseDaoImplement;
import java.util.Scanner;
import model.User;

/**
 *
 * @author creoo
 */
public class StudentMenu {

    public static void StudentMenu(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome " + user.getFirstname() + " " + user.getLastname());
        char quit='n';
        String input;
        int choice;
        while (quit!='y') {
            System.out.println("These are your  options.Choose one using the appropriate number");
            System.out.println("1.Enroll to a Course\n2.See your daily schedule per course\n3.See the dates of sumbission of the Assignments\n4.Submit one of your assignments\n ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    StudentsPerCourseDaoImplement spcdao = new StudentsPerCourseDaoImplement();
                    spcdao.enrollCourse(user);
                    break;
                case 2:
                    SchedulePerCourseDaoImplement scpcdao = new SchedulePerCourseDaoImplement();
                    System.out.println(scpcdao.getSchedulePerCoursePerStudentById(user));
                    break;
                case 3:
                    AssignmentsPerCourseDaoImplement apcdao = new AssignmentsPerCourseDaoImplement();
                    apcdao.getDatesOfAssignmentsPerCourse();
                    break;
                case 4:
                    AssignmentsPerStudentDaoImplement apsdao = new AssignmentsPerStudentDaoImplement();
                    apsdao.getAssignmentsPerStudentByIdandSubmit(user);
                    break;
                default:
                    System.out.println("Not eligible input.");
            }System.out.println("Would you like to quit? y/n");
             input=sc.next().toLowerCase();
             quit=input.charAt(0);
        }
    }
}
