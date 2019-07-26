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
import model.Schedule;
import model.SchedulePerCourse;
import model.User;
import utilities.Dbutils;

/**
 *
 * @author USER
 */
public class SchedulePerCourseDaoImplement implements SchedulePerCourseDaoInterface{

    @Override
    public void insertScheduleInCourse() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        ScheduleDaoImplement sdao = new ScheduleDaoImplement();
        CourseDaoImplement cdao = new CourseDaoImplement();
        ArrayList<Schedule>schedules=sdao.getSchedules();
        
        System.out.println("Assign one of the following schedules to a course using the appropriate number");
        for (int i = 0; i < schedules.size(); i++) {
            System.out.println(schedules.get(i).getId() + " " + schedules.get(i).getSubject()+" "+schedules.get(i).getDate());
        }
         int scheduleid = sc.nextInt();
         ArrayList<Course> courses = cdao.getCourses();
         System.out.println("Assign the schedule  to a course using the appropriate number");
         for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i).getId() + " " + courses.get(i).getTitle());
        }
         int courseid=sc.nextInt();
         
         String sql = "insert into schedulespercourse(courseid,scheduleid) values(?,?)";
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, courseid);
            pst.setInt(2, scheduleid);
            pst.executeUpdate();       
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("Schedule inserted in course");
    }

    @Override
    public ArrayList<SchedulePerCourse> getSchedulesPerCourse() {
        Connection con = Dbutils.getconnection();
        String sql = "SELECT c.title,u.date,u.subject from(schedule u INNER JOIN schedulespercourse s ON u.id=s.scheduleid)INNER JOIN course c ON c.id=s.courseid";
        PreparedStatement pst = null;
        ArrayList<SchedulePerCourse> schedulespercourse = new ArrayList<SchedulePerCourse>();
        try {
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                SchedulePerCourse spc = new SchedulePerCourse();
                String courseTitle = rs.getString(1);
                spc.setCourseTitle(courseTitle);
                Date scheduleDate = rs.getDate(2);
                spc.setScheduleDate(scheduleDate);
                String scheduleSubject = rs.getString(3);
                spc.setScheduleSubject(scheduleSubject);

                schedulespercourse.add(spc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        return schedulespercourse;
    }

    @Override
    public void updateScheduleInCourse() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        ScheduleDaoImplement sdao=new ScheduleDaoImplement();
        CourseDaoImplement cdao=new CourseDaoImplement();
        ArrayList<Schedule> schedules=sdao.getSchedules();
        
        ArrayList<Course> courses=cdao.getCourses();
        System.out.println("Select the course whose schedule you want to change");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i).getId() + " " + courses.get(i).getTitle());
        }
        int courseid = sc.nextInt();
        ArrayList <Schedule>schedulesPerCourseById=sdao.getSchedulesPerCourseById(courseid);
         System.out.println("Change the schedule of the course using the appropriate number");
         for (int i = 0; i < schedulesPerCourseById.size(); i++) {
            System.out.println(schedulesPerCourseById.get(i).getId() + " " + schedulesPerCourseById.get(i).getSubject()+" "+schedulesPerCourseById.get(i).getDate());
        }
         int deleteid=sc.nextInt();
        System.out.println("Assign one of the following schedules to the course");
        for (int i = 0; i < schedules.size(); i++) {
            System.out.println(schedules.get(i).getId() + " " + schedules.get(i).getSubject()+" "+schedules.get(i).getDate());
        }
        int scheduleid=sc.nextInt();
        String sql = "update schedulespercourse set scheduleid=? where courseid=? and scheduleid=?";//
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,scheduleid);
            pst.setInt(2,courseid);
            pst.setInt(3,deleteid);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("Course's schedule updated!");

    
    }

    @Override
    public void deleteScheduleFromCourse() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        ScheduleDaoImplement sdao=new ScheduleDaoImplement();
        CourseDaoImplement cdao=new CourseDaoImplement();
        ArrayList <Course>courses=cdao.getCourses();
        
        System.out.println("Select the course in which you want to delete a schedule");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i).getId() + " " + courses.get(i).getTitle());
        }
        int courseid = sc.nextInt();
        ArrayList<Schedule> schedulesPerCourseById=sdao.getSchedulesPerCourseById(courseid);
         System.out.println("Delete one of the following schedules in the course "+courses.get(courseid-1).getTitle()+" using the appropriate number");
         for (int i = 0; i < schedulesPerCourseById.size(); i++) {
            System.out.println(schedulesPerCourseById.get(i).getId() + " " + schedulesPerCourseById.get(i).getSubject()+" "+schedulesPerCourseById.get(i).getDate());
        }
         int deleteid=sc.nextInt();
        String sql = "delete from schedulespercourse where courseid=? and scheduleid=?";//
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
        System.out.println("Schedule deleted from course!");

    
    }

    

   

    @Override
    public ArrayList<Schedule> getSchedulePerCoursePerStudentById(User user) {
       UserDaoImplement dao = new UserDaoImplement();
        ArrayList<Schedule> schedulesPerCourseById = new ArrayList<Schedule>();
        Connection con = Dbutils.getconnection();
        PreparedStatement pst = null;
        String sql = "SELECT s.id,s.subject,s.date  FROM(((schedule s INNER JOIN schedulespercourse b ON s.id=b.scheduleid)INNER JOIN course c ON b.courseid=c.id)INNER JOIN studentspercourse att ON c.id=att.courseid)INNER JOIN user u ON att.userid=u.id where u.id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, user.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Schedule schedule = new Schedule();
                schedule.setId(rs.getInt(1));
                schedule.setSubject(rs.getString(2));
                schedule.setDate(rs.getDate(3));
                schedulesPerCourseById.add(schedule);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        return schedulesPerCourseById;
    }
    

    
}
