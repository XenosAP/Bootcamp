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
import model.Assignment;
import model.AssignmentsPerCourse;
import model.Course;
import utilities.Dbutils;

/**
 *
 * @author USER
 */
public class AssignmentsPerCourseDaoImplement implements AssignmentsPerCourseDaoInterface{

    @Override
    public void insertAssignmentInCourse() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        AssignmentDaoImplement sdao = new AssignmentDaoImplement();
        CourseDaoImplement cdao = new CourseDaoImplement();
        ArrayList<Assignment>assignments=sdao.getAssignments();
        
        System.out.println("Assign one of the following assignments to a course using the appropriate number");
        for (int i = 0; i < assignments.size(); i++) {
            System.out.println(assignments.get(i).getId() + " " + assignments.get(i).getTitle());
        }
         int assignmentid = sc.nextInt();
         ArrayList<Course> courses = cdao.getCoursesNotAssignedToAssignmentById(assignmentid);
         System.out.println("Assign the assignment  to a course using the appropriate number");
         for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i).getId() + " " + courses.get(i).getTitle());
        }
         int courseid=sc.nextInt();
         
         String sql = "insert into assignmentspercourse(courseid,assignmentid) values(?,?)";
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, courseid);
            pst.setInt(2, assignmentid);
            pst.executeUpdate();       
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("Assignment inserted in course");
    }

    @Override
    public ArrayList<AssignmentsPerCourse> getAssignmentsPerCourse() {
        Connection con = Dbutils.getconnection();
        String sql = "SELECT c.title,u.title,u.submissiondate from(assignment u INNER JOIN assignmentspercourse s ON u.id=s.assignmentid)INNER JOIN course c ON c.id=s.courseid";
        PreparedStatement pst = null;
        ArrayList<AssignmentsPerCourse> assignmentsPerCourse = new ArrayList<AssignmentsPerCourse>();
        try {
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                AssignmentsPerCourse apc = new AssignmentsPerCourse();
                String courseTitle = rs.getString(1);
                apc.setCourseTitle(courseTitle);
                String assignmentTitle = rs.getString(2);
                apc.setAssignmentTitle(assignmentTitle);
                Date submissionDate = rs.getDate(3);
                apc.setSubmissionDate(submissionDate);
                
                assignmentsPerCourse.add(apc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        return assignmentsPerCourse;
    }

    @Override
    public void updateAssignmentInCourse() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        AssignmentDaoImplement adao=new AssignmentDaoImplement();
        CourseDaoImplement cdao=new CourseDaoImplement();
        ArrayList<Assignment> schedules=adao.getAssignments();
        
        ArrayList<Course> courses=cdao.getCourses();
        System.out.println("Select the course whose assignment you want to change");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i).getId() + " " + courses.get(i).getTitle());
        }
        int courseid = sc.nextInt();
        ArrayList <Assignment>AssignmentsPerCourseById=adao.getAssignmentsPerCourseById(courseid);
         System.out.println("Change the assignment of the course using the appropriate number");
         for (int i = 0; i < AssignmentsPerCourseById.size(); i++) {
            System.out.println(AssignmentsPerCourseById.get(i).getId() + " " + AssignmentsPerCourseById.get(i).getTitle()+" "+AssignmentsPerCourseById.get(i).getSubmissionDate());
        }
         int deleteid=sc.nextInt();
        System.out.println("Assign one of the following assignments to the course");
        ArrayList<Assignment> assignmentsNotAssignedToCourseById=adao.getAssignmentsNotAssignedToCourseById(courseid);
        for (int i = 0; i < assignmentsNotAssignedToCourseById.size(); i++) {
            System.out.println(assignmentsNotAssignedToCourseById.get(i).getId() + " " + assignmentsNotAssignedToCourseById.get(i).getTitle()+" "+assignmentsNotAssignedToCourseById.get(i).getSubmissionDate());
        }
        int assignmentid=sc.nextInt();
        String sql = "update assignmentspercourse set assignmentid=? where courseid=? and assignmentid=?";//
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,assignmentid);
            pst.setInt(2,courseid);
            pst.setInt(3,deleteid);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("Course's assignment updated!");

    
    }

    @Override
    public void deleteAssignmentFromCourse() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        AssignmentDaoImplement adao=new AssignmentDaoImplement();
        CourseDaoImplement cdao=new CourseDaoImplement();
        ArrayList <Course>courses=cdao.getCourses();
        
        System.out.println("Select the course in which you want to delete an assignment");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i).getId() + " " + courses.get(i).getTitle());
        }
        int courseid = sc.nextInt();
        ArrayList<Assignment> assignmentsPerCourseById=adao.getAssignmentsPerCourseById(courseid);
         System.out.println("Delete one of the following assignments in the course "+courses.get(courseid-1).getTitle()+" using the appropriate number");
         for (int i = 0; i < assignmentsPerCourseById.size(); i++) {
            System.out.println(assignmentsPerCourseById.get(i).getId() + " " + assignmentsPerCourseById.get(i).getTitle()+" "+assignmentsPerCourseById.get(i).getSubmissionDate());
        }
         int deleteid=sc.nextInt();
        String sql = "delete from assignmentspercourse where courseid=? and assignmentid=?";//
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
        System.out.println("Assignment deleted from course!");

    
    }

    @Override
    public void getDatesOfAssignmentsPerCourse() {
        Connection con = Dbutils.getconnection();
        String sql = "SELECT c.title,u.title,u.submissiondate from(assignment u INNER JOIN assignmentspercourse s ON u.id=s.assignmentid)INNER JOIN course c ON c.id=s.courseid group  by u.id";
        PreparedStatement pst = null;
        ArrayList<AssignmentsPerCourse> assignmentsPerCourse = new ArrayList<AssignmentsPerCourse>();
        
        try {
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                AssignmentsPerCourse apc = new AssignmentsPerCourse();
                String courseTitle = rs.getString(1);
                apc.setCourseTitle(courseTitle);
                String assignmentTitle = rs.getString(2);
                apc.setAssignmentTitle(assignmentTitle);
                Date submissionDate = rs.getDate(3);
                apc.setSubmissionDate(submissionDate);
                
                assignmentsPerCourse.add(apc);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        for (int i = 0; i < assignmentsPerCourse.size(); i++) {
            System.out.println("Assignment title: "+assignmentsPerCourse.get(i).getAssignmentTitle()+" ,Submission date: "+assignmentsPerCourse.get(i).getSubmissionDate());
        }
    }
    
}
