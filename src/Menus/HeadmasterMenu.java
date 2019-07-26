/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import dao.AssignmentDaoImplement;
import dao.AssignmentsPerCourseDaoImplement;
import dao.CourseDaoImplement;
import dao.SchedulePerCourseDaoImplement;
import dao.StudentsPerCourseDaoImplement;
import dao.TrainersPerCourseDaoImplement;
import dao.UserDaoImplement;
import java.util.Scanner;
import model.User;

/**
 *
 * @author creoo
 */
public class HeadmasterMenu {

    public static void HeadmasterMenu(User user) {
        CourseDaoImplement cdao=new CourseDaoImplement();
        UserDaoImplement udao=new UserDaoImplement();
        AssignmentDaoImplement adao=new AssignmentDaoImplement();
        TrainersPerCourseDaoImplement tpcdao=new TrainersPerCourseDaoImplement();
        StudentsPerCourseDaoImplement spcdao=new StudentsPerCourseDaoImplement();
        AssignmentsPerCourseDaoImplement apcdao=new AssignmentsPerCourseDaoImplement();
        SchedulePerCourseDaoImplement scpcdao=new SchedulePerCourseDaoImplement();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome " + user.getFirstname() + " " + user.getLastname());
        char quit = 'n';
        String input;
        int choice;
        while (quit != 'y') {
            System.out.println("These are your  options.Choose one using the appropriate number");
            System.out.println("1.CREATE\n2.READ\n3.UPDATE\n4.DELETE\n ");
            choice = sc.nextInt();
            switch (choice) {
                case 1://CREATE
                    while (quit != 'y') {
                        System.out.println("In which table you wish to perform CREATE");
                        System.out.println("1.Courses 2.Students 3.Assignments 4.Trainers 5.Students per Courses\n"
                                + "6.Trainers per Courses 7.Assignments per Courses 8.Schedule per Courses");
                        choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                cdao.insertCourse();
                                break;
                            case 2:
                                udao.insertStudent();
                                break;
                            case 3:
                                adao.insertAssignment();
                                break;
                            case 4:
                                udao.insertTrainer();
                                break;
                            case 5:
                                spcdao.insertStudentInCourse();
                                break;
                            case 6:
                                tpcdao.insertTrainerInCourse();
                                break;
                            case 7:
                                apcdao.insertAssignmentInCourse();
                                break;
                            case 8:
                                scpcdao.insertScheduleInCourse();
                                break;
                            default:
                                System.out.println("Not eligible input");
                        }
                        System.out.println("Would you like to go back to the previous menu? y/n");
                        input = sc.next().toLowerCase();
                        quit = input.charAt(0);
                    }

                    break;
                case 2://READ
                    while (quit != 'y') {
                        System.out.println("In which table you wish to perform READ");
                        System.out.println("1.Courses 2.Students 3.Assignments 4.Trainers 5.Students per Courses\n"
                                + "6.Trainers per Courses 7.Assignments per Courses 8.Schedule per Courses");
                        choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                System.out.println(cdao.getCourses());
                                break;
                            case 2:
                                System.out.println(udao.getStudents());
                                break;
                            case 3:
                                System.out.println(adao.getAssignments());
                                break;
                            case 4:
                                System.out.println(udao.getTrainers());
                                break;
                            case 5:
                                System.out.println(spcdao.getStudentsPerCourse());
                                break;
                            case 6:
                                System.out.println(tpcdao.getTrainersPerCourse());
                                break;
                            case 7:
                                System.out.println(apcdao.getAssignmentsPerCourse());
                                break;
                            case 8:
                                System.out.println(scpcdao.getSchedulesPerCourse());
                                break;
                            default:
                                System.out.println("Not eligible input");
                        }
                        System.out.println("Would you like to go back to the previous menu? y/n");
                        input = sc.next().toLowerCase();
                        quit = input.charAt(0);
                    }
                    break;
                case 3://UPDATE
                    while (quit != 'y') {
                        System.out.println("In which table you wish to perform UPDATE");
                        System.out.println("1.Courses 2.Students 3.Assignments 4.Trainers 5.Students per Courses\n"
                                + "6.Trainers per Courses 7.Assignments per Courses 8.Schedule per Courses");
                        choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                cdao.updateCourse();
                                break;
                            case 2:
                                udao.updateStudent();
                                break;
                            case 3:
                                adao.updateAssignment();
                                break;
                            case 4:
                                udao.updateTrainer();
                                break;
                            case 5:
                                spcdao.updateStudentInCourse();
                                break;
                            case 6:
                                tpcdao.updateTrainerInCourse();
                                break;
                            case 7:
                                apcdao.updateAssignmentInCourse();
                                break;
                            case 8:
                                scpcdao.updateScheduleInCourse();
                                break;
                            default:
                                System.out.println("Not eligible input");
                        }
                        System.out.println("Would you like to go back to the previous menu? y/n");
                        input = sc.next().toLowerCase();
                        quit = input.charAt(0);
                    }
                    break;
                case 4://DELETE
                    while (quit != 'y') {
                        System.out.println("In which table you wish to perform DELETE");
                        System.out.println("1.Courses 2.Students 3.Assignments 4.Trainers 5.Students per Courses\n"
                                + "6.Trainers per Courses 7.Assignments per Courses 8.Schedule per Courses");
                        choice = sc.nextInt();
                        switch (choice) {
                            case 1:
                                cdao.deleteCourse();
                                break;
                            case 2:
                                udao.deleteStudent();
                                break;
                            case 3:
                                adao.deleteAssignment();
                                break;
                            case 4:
                                udao.deleteTrainer();
                                break;
                            case 5:
                                spcdao.deleteStudentFromCourse();
                                break;
                            case 6:
                                tpcdao.deleteTrainerFromCourse();
                                break;
                            case 7:
                                apcdao.deleteAssignmentFromCourse();
                                break;
                            case 8:
                                scpcdao.deleteScheduleFromCourse();
                                break;
                            default:
                                System.out.println("Not eligible input");
                        }
                        System.out.println("Would you like to go back to the previous menu? y/n");
                        input = sc.next().toLowerCase();
                        quit = input.charAt(0);
                    }
                    break;
                default:
                    System.out.println("Not eligible input.Try again!");
            }
            System.out.println("Would you like to quit? y/n");
            input = sc.next().toLowerCase();
            quit = input.charAt(0);
        }
    }
}
