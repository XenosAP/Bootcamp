/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import dao.AssignmentsPerStudentDaoImplement;
import dao.StudentsPerCourseDaoImplement;
import dao.TrainersPerCourseDaoImplement;
import java.util.Scanner;
import model.User;

/**
 *
 * @author creoo
 */
public class TrainerMenu {

    public static void TrainerMenu(User user) {
        TrainersPerCourseDaoImplement tpcdao = new TrainersPerCourseDaoImplement();
        StudentsPerCourseDaoImplement spcdao = new StudentsPerCourseDaoImplement();
        AssignmentsPerStudentDaoImplement apsdao = new AssignmentsPerStudentDaoImplement();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome " + user.getFirstname() + " " + user.getLastname());
        char quit = 'n';
        String input;
        int choice;
        while (quit != 'y') {
            System.out.println("These are your  options.Choose one using the appropriate number");
            System.out.println("1.View all the courses you are enrolled in\n2.View all the students per course\n"
                    + "3.View all the assignments per student per course\n4.Mark all the assignments per student per course\n ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    tpcdao.getCoursesPerTrainer(user);
                    break;
                case 2:
                    System.out.println(spcdao.getStudentsPerCourse());
                    break;
                case 3:
                    System.out.println(apsdao.getAssignmentsPerStudentPerCourse());
                    break;
                case 4:
                    apsdao.MarkAssignmentsPerStudentPerCourse();
                    break;
                default:
                    System.out.println("Not eligible input.");
            }
            System.out.println("Would you like to quit? y/n");
            input = sc.next().toLowerCase();
            quit = input.charAt(0);
        }
    }
}
