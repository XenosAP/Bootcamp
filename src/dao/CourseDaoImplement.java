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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import model.Course;
import utilities.Dbutils;

/**
 *
 * @author USER
 */
public class CourseDaoImplement implements CourseDaoInterface{

    @Override
    public ArrayList<Course> getCourses() {
       Connection con = Dbutils.getconnection();
        String sql = "Select* from course";
        PreparedStatement pst = null;
        ArrayList<Course> courses = new ArrayList<Course>();
        try {
            pst = con.prepareStatement(sql);
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
        return courses;
       
    }

    @Override
    public void insertCourse() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();

        String sql = "insert into course(title,stream,typeof,startdate,enddate) values(?,?,?,?,?)";//
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            System.out.println("Assign the course's title");
            pst.setString(1, sc.nextLine());
            System.out.println("Assign the course's stream");
            pst.setString(2, sc.nextLine());
            System.out.println("Assign the course's type");
            pst.setString(3, sc.nextLine());
            System.out.println("Assign the courses's start date(yyyy-MM-dd)");
            String stringdate = sc.nextLine();
             System.out.println("Assign the courses's end date(yyyy-MM-dd)");
             String stringdate1 = sc.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = null;
            java.util.Date date1=null;
            try {
                date = format.parse(stringdate);
                date1 = format.parse(stringdate1);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            java.sql.Date wrongdate = new java.sql.Date(date.getTime());
            java.sql.Date correctdate = new java.sql.Date(wrongdate.getTime() + 24 * 60 * 60 * 1000);
            pst.setDate(4, correctdate);
            java.sql.Date wrongdate1 = new java.sql.Date(date1.getTime());
            java.sql.Date correctdate1 = new java.sql.Date(wrongdate1.getTime() + 24 * 60 * 60 * 1000);
            pst.setDate(5, correctdate1);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("Course inserted");

    }
    

    @Override
    public void updateCourse() {
       Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        CourseDaoImplement cdao = new CourseDaoImplement();
        ArrayList<Course>courses=cdao.getCourses();
        System.out.println("Replace one of the following courses with a new one using the appropriate number");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i).getId() + " " + courses.get(i).getTitle());
        }
        int deleteid = sc.nextInt();
        String str1 = sc.nextLine();//φτιαχνει το προβλημα της nextInt() - nextLine()
        String sql = "update course set title=?,stream=?,typeof=?,startdate=?,enddate=? where id=?";//
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            System.out.println("Assign the course's title");
            pst.setString(1, sc.nextLine());
            System.out.println("Assign the course's stream");
            pst.setString(2, sc.nextLine());
            System.out.println("Assign the course's type");
            pst.setString(3, sc.nextLine());
            System.out.println("Assign the courses's start date(yyyy-MM-dd)");
            String stringdate = sc.nextLine();
             System.out.println("Assign the courses's end date(yyyy-MM-dd)");
             String stringdate1 = sc.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = null;
            java.util.Date date1=null;
            try {
                date = format.parse(stringdate);
                date1 = format.parse(stringdate1);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            java.sql.Date wrongdate = new java.sql.Date(date.getTime());
            java.sql.Date correctdate = new java.sql.Date(wrongdate.getTime() + 24 * 60 * 60 * 1000);
            pst.setDate(4, correctdate);
            java.sql.Date wrongdate1 = new java.sql.Date(date1.getTime());
            java.sql.Date correctdate1 = new java.sql.Date(wrongdate1.getTime() + 24 * 60 * 60 * 1000);
            pst.setDate(5, correctdate1);
            pst.setInt(6, deleteid);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("Course inserted");

    }

    @Override
    public void deleteCourse() {
       Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        CourseDaoImplement cdao = new CourseDaoImplement();
        PreparedStatement pst = null;
        ArrayList<Course> courses = cdao.getCourses();
        System.out.println("Delete one of the following courses using the appropriate number");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i).getId() + " " + courses.get(i).getTitle());
        }
        int deleteid = sc.nextInt();
        String sql1 = "Delete from course where id=?";
        try {
            pst = con.prepareStatement(sql1);
            pst.setInt(1, deleteid);
            pst.executeUpdate();
        } catch (SQLException ex) {
          ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("Course deleted!");
    }

    @Override
    public ArrayList<Course> getCoursesNotAssignedToStudentById(int studentid) {
        CourseDaoImplement cdao=new CourseDaoImplement();
      ArrayList courses= cdao.getCourses();
      Connection con = Dbutils.getconnection();
        PreparedStatement pst = null;
            String sql = "SELECT c.id from (user u INNER JOIN studentspercourse s ON u.id=s.userid)INNER JOIN course c ON c.id=s.courseid where u.id=? order by c.id DESC";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, studentid);
            ResultSet rs=pst.executeQuery();
            while (rs.next()){
            int courseid=rs.getInt(1);
            courses.remove(courseid-1);
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        return courses;
    }

    @Override
    public ArrayList<Course> getCoursesNotAssignedToTrainerById(int trainerid) {
        CourseDaoImplement cdao=new CourseDaoImplement();
      ArrayList courses= cdao.getCourses();
      Connection con = Dbutils.getconnection();
        PreparedStatement pst = null;
            String sql = "SELECT c.id from (user u INNER JOIN trainerspercourse s ON u.id=s.userid)INNER JOIN course c ON c.id=s.courseid where u.id=? order by c.id DESC";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, trainerid);
            ResultSet rs=pst.executeQuery();
            while (rs.next()){
            int courseid=rs.getInt(1);
            courses.remove(courseid-1);
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        return courses;
    }

    @Override
    public ArrayList<Course> getCoursesPerStudentById(int studentid) {
        CourseDaoImplement cdao=new CourseDaoImplement();
      Connection con = Dbutils.getconnection();
      ArrayList<Course> coursesPerStudentById=new ArrayList<Course>();
        PreparedStatement pst = null;
            String sql = "SELECT c.id,c.title from (user u INNER JOIN studentspercourse s ON u.id=s.userid)INNER JOIN course c ON c.id=s.courseid where u.id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, studentid);
            ResultSet rs=pst.executeQuery();
            while (rs.next()){
            Course course=new Course();
            course.setId(rs.getInt(1));
             course.setTitle(rs.getString(2));
             coursesPerStudentById.add(course);
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        return coursesPerStudentById;
    }

    @Override
    public ArrayList<Course> getCoursesPerTrainerById(int trainerid) {
        CourseDaoImplement cdao=new CourseDaoImplement();
      ArrayList coursesPerTrainerById= new ArrayList<Course>();
      Connection con = Dbutils.getconnection();
        PreparedStatement pst = null;
            String sql = "SELECT c.id,c.title from (user u INNER JOIN trainerspercourse s ON u.id=s.userid)INNER JOIN course c ON c.id=s.courseid where u.id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, trainerid);
            ResultSet rs=pst.executeQuery();
            while (rs.next()){
            Course course=new Course();
            course.setId(rs.getInt(1));
             course.setTitle(rs.getString(2));
             coursesPerTrainerById.add(course);
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        return coursesPerTrainerById;
    }
    public ArrayList<Course> getCoursesNotAssignedToAssignmentById(int assignmentid) {
        CourseDaoImplement cdao=new CourseDaoImplement();
      ArrayList courses= cdao.getCourses();
      Connection con = Dbutils.getconnection();
        PreparedStatement pst = null;
            String sql = "SELECT c.id from (assignment u INNER JOIN assignmentspercourse s ON u.id=s.assignmentid)INNER JOIN course c ON c.id=s.courseid where u.id=? order by c.id DESC";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, assignmentid);
            ResultSet rs=pst.executeQuery();
            while (rs.next()){
            int courseid=rs.getInt(1);
            courses.remove(courseid-1);
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        return courses;
    }
}
