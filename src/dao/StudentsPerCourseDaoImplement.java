package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Course;
import model.StudentsPerCourse;
import model.User;
import utilities.Dbutils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author USER
 */
public class StudentsPerCourseDaoImplement implements StudentsPerCourseDaoInterface{

    @Override
    public void insertStudentInCourse() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        UserDaoImplement user = new UserDaoImplement();
        CourseDaoImplement cdao = new CourseDaoImplement();
        ArrayList<User>students=user.getStudents();
        
        System.out.println("Assign one of the following students to a course using the appropriate number");
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).getId() + " " + students.get(i).getFirstname() + " " + students.get(i).getLastname());
        }
        System.out.println("Scroll up");//small console window
         int studentid = sc.nextInt();
         ArrayList<Course> courses = cdao.getCoursesNotAssignedToStudentById(studentid);
         System.out.println("Assign the student  to a course using the appropriate number");
         for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i).getId() + " " + courses.get(i).getTitle());
        }
         int courseid=sc.nextInt();
         
         String sql = "insert into studentspercourse(courseid,userid) values(?,?)";
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, courseid);
            pst.setInt(2, studentid);
            pst.executeUpdate();       
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("Student inserted in course");
    }

    @Override
    public ArrayList<StudentsPerCourse> getStudentsPerCourse() {
        Connection con = Dbutils.getconnection();
        String sql = "SELECT c.title,u.firstname,u.lastname,c.id from (user u INNER JOIN studentspercourse s ON u.id=s.userid)INNER JOIN course c ON c.id=s.courseid";
        PreparedStatement pst = null;
        ArrayList<StudentsPerCourse> studentsPerCourse = new ArrayList<StudentsPerCourse>();
        try {
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                StudentsPerCourse spc = new StudentsPerCourse();
                String coursetitle = rs.getString(1);
                spc.setCourseTitle(coursetitle);
                String studentfirstname = rs.getString(2);
                spc.setStudentFirstname(studentfirstname);
                String lastname = rs.getString(3);
                spc.setStudentLastname(lastname);

                studentsPerCourse.add(spc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        return studentsPerCourse;
    }

    @Override
    public void updateStudentInCourse() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        UserDaoImplement udao=new UserDaoImplement();
        CourseDaoImplement cdao=new CourseDaoImplement();
        ArrayList <User>students=udao.getStudents();
        
        System.out.println("Select the student whose course you want to change");
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).getId() + " " + students.get(i).getFirstname()+" "+students.get(i).getLastname());
        }
        int studentid = sc.nextInt();
        ArrayList<Course> coursesPerStudentById=cdao.getCoursesPerStudentById(studentid);
         System.out.println("Change the course of the student using the appropriate number");
         for (int i = 0; i < coursesPerStudentById.size(); i++) {
            System.out.println(coursesPerStudentById.get(i).getId() + " " + coursesPerStudentById.get(i).getTitle());
        }
         int deleteid=sc.nextInt();
        System.out.println("Assign one of the following courses to the student");
        ArrayList<Course> coursesNotAssignedToStudentById=cdao.getCoursesNotAssignedToStudentById(studentid);
        for (int i = 0; i < coursesNotAssignedToStudentById.size(); i++) {
            System.out.println(coursesNotAssignedToStudentById.get(i).getId() + " " + coursesNotAssignedToStudentById.get(i).getTitle());
        }
        int courseid=sc.nextInt();
        String sql = "update studentspercourse set courseid=? where courseid=? and userid=?";//
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,courseid);
            pst.setInt(2,deleteid);
            pst.setInt(3,studentid);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("Student's course updated!");

    
    }

    @Override
    public void deleteStudentFromCourse() {
      Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        UserDaoImplement udao=new UserDaoImplement();
        CourseDaoImplement cdao=new CourseDaoImplement();
        ArrayList <Course>courses=cdao.getCourses();
        
        System.out.println("Select the course in which you want to delete a student");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i).getId() + " " + courses.get(i).getTitle());
        }
        int courseid = sc.nextInt();
        ArrayList<User> studentsPerCourseById=udao.getStudentsPerCourseById(courseid);
         System.out.println("Delete one of the following students in the course "+courses.get(courseid-1).getTitle()+" using the appropriate number");
         for (int i = 0; i < studentsPerCourseById.size(); i++) {
            System.out.println(studentsPerCourseById.get(i).getId() + " " + studentsPerCourseById.get(i).getFirstname()+" "+studentsPerCourseById.get(i).getLastname());
        }
         int deleteid=sc.nextInt();
        String sql = "delete from studentspercourse where courseid=? and userid=?";//
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,courseid);
            pst.setInt(2,deleteid);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("Student deleted from course!");

    
    }

    @Override
    public void enrollCourse(User user) {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        CourseDaoImplement cdao=new CourseDaoImplement();
        ArrayList<Course> courses=cdao.getCoursesNotAssignedToStudentById(user.getId());
        System.out.println("Select a course you want to enroll to ,using the appropriate number");  
         for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i).getId() + " " + courses.get(i).getTitle());
        }
        int courseid=sc.nextInt();
        String sql = "insert into studentspercourse(courseid,userid) values(?,?)";
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, courseid);
            pst.setInt(2, user.getId());
            pst.executeUpdate();       
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("You succesfully enrolled to the course");
    }

   
    
    
}
