/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Course;
import model.TrainersPerCourse;
import model.User;
import utilities.Dbutils;

/**
 *
 * @author USER
 */
public class TrainersPerCourseDaoImplement implements TrainersPerCourseDaoInterface{

    @Override
    public void insertTrainerInCourse() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        UserDaoImplement user = new UserDaoImplement();
        CourseDaoImplement cdao = new CourseDaoImplement();
        ArrayList<User>trainers=user.getTrainers();
        
        System.out.println("Assign one of the following trainers to a course using the appropriate number");
        for (int i = 0; i < trainers.size(); i++) {
            System.out.println(trainers.get(i).getId() + " " + trainers.get(i).getFirstname() + " " + trainers.get(i).getLastname());
        }
         int trainerid = sc.nextInt();
         ArrayList<Course> courses = cdao.getCoursesNotAssignedToTrainerById(trainerid);
         System.out.println("Assign the trainer  to a course using the appropriate number");
         for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i).getId() + " " + courses.get(i).getTitle());
        }
         int courseid=sc.nextInt();
         
         String sql = "insert into trainerspercourse(courseid,userid) values(?,?)";
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, courseid);
            pst.setInt(2, trainerid);
            pst.executeUpdate();       
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("Trainer inserted in course");
    
    }

    @Override
    public ArrayList<TrainersPerCourse> getTrainersPerCourse() {
        Connection con = Dbutils.getconnection();
        String sql = "SELECT c.title,u.firstname,u.lastname,c.id from (user u INNER JOIN trainerspercourse t ON u.id=t.userid)INNER JOIN course c ON c.id=t.courseid";
        PreparedStatement pst = null;
        ArrayList<TrainersPerCourse> trainersPerCourse = new ArrayList<TrainersPerCourse>();
        try {
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                TrainersPerCourse tpc = new TrainersPerCourse();
                String coursetitle = rs.getString(1);
                tpc.setCourseTitle(coursetitle);
                String trainerfirstname = rs.getString(2);
                tpc.setTrainerFirstname(trainerfirstname);
                String trainerlastname = rs.getString(3);
                tpc.setTrainerLastname(trainerlastname);

                trainersPerCourse.add(tpc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        return trainersPerCourse;
    }

    @Override
    public void updateTrainerInCourse() {
       Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        UserDaoImplement udao=new UserDaoImplement();
        CourseDaoImplement cdao=new CourseDaoImplement();
        ArrayList <User>trainers=udao.getTrainers();
        
        System.out.println("Select the trainer whose course you want to change");
        for (int i = 0; i < trainers.size(); i++) {
            System.out.println(trainers.get(i).getId() + " " + trainers.get(i).getFirstname()+" "+trainers.get(i).getLastname());
        }
        int trainerid = sc.nextInt();
        ArrayList<Course> coursesPerTrainerById=cdao.getCoursesPerTrainerById(trainerid);
         System.out.println("Change the course of the trainer using the appropriate number");
         for (int i = 0; i < coursesPerTrainerById.size(); i++) {
            System.out.println(coursesPerTrainerById.get(i).getId() + " " + coursesPerTrainerById.get(i).getTitle());
        }
         int deleteid=sc.nextInt();
        System.out.println("Assign one of the following courses to the trainer ");
        ArrayList<Course> coursesNotAssignedToTrainerById=cdao.getCoursesNotAssignedToTrainerById(trainerid);
        for (int i = 0; i < coursesNotAssignedToTrainerById.size(); i++) {
            System.out.println(coursesNotAssignedToTrainerById.get(i).getId() + " " + coursesNotAssignedToTrainerById.get(i).getTitle());
        }
        int courseid=sc.nextInt();
        String sql = "update trainerspercourse set courseid=? where courseid=? and userid=?";//
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,courseid);
            pst.setInt(2,deleteid);
            pst.setInt(3,trainerid);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("Trainer's course updated!");

    
    }

    @Override
    public void deleteTrainerFromCourse() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        UserDaoImplement udao=new UserDaoImplement();
        CourseDaoImplement cdao=new CourseDaoImplement();
        ArrayList <Course>courses=cdao.getCourses();
        
        System.out.println("Select the course in which you want to delete a trainer");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i).getId() + " " + courses.get(i).getTitle());
        }
        int courseid = sc.nextInt();
        ArrayList<User> trainersPerCourseById=udao.getTrainersPerCourseById(courseid);
         System.out.println("Delete one of the following trainers in the course "+courses.get(courseid-1).getTitle()+" using the appropriate number");
         for (int i = 0; i < trainersPerCourseById.size(); i++) {
            System.out.println(trainersPerCourseById.get(i).getId() + " " + trainersPerCourseById.get(i).getFirstname()+" "+trainersPerCourseById.get(i).getLastname());
        }
         int deleteid=sc.nextInt();
        String sql = "delete from trainerspercourse where courseid=? and userid=?";//
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
        System.out.println("Trainer deleted from course!");

    
    }

    @Override
    public void getCoursesPerTrainer(User user) {
        Connection con = Dbutils.getconnection();
        String sql = "SELECT c.id,c.title,c.stream,c.typeof,c.startdate,c.enddate from (user u INNER JOIN trainerspercourse s ON u.id=s.userid)INNER JOIN course c ON c.id=s.courseid where u.id=?";
        PreparedStatement pst = null;
        ArrayList<Course> courses = new ArrayList<Course>();
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, user.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                int id = rs.getInt(1);
                course.setId(id);
                String title = rs.getString(2);
                course.setTitle(title);
                String stream=rs.getString(3);
                course.setStream(stream);
                String typeof=rs.getString(4);
                course.setTypeof(typeof);
                Date startdate = rs.getDate(5);
                course.setStartdate(startdate);
                Date enddate=rs.getDate(6);
                course.setEnddate(enddate);
                courses.add(course);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println(courses);
       
    }

   
    
}
