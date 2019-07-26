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
import model.Assignment;
import utilities.Dbutils;

/**
 *
 * @author USER
 */
public class AssignmentDaoImplement implements AssignmentDaoInterface{

    @Override
    public ArrayList<Assignment> getAssignments() {
       Connection con = Dbutils.getconnection();
        String sql = "Select* from assignment";
        PreparedStatement pst = null;
        ArrayList<Assignment> assignments = new ArrayList<Assignment>();
        try {
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Assignment assignment = new Assignment();
                int id = rs.getInt(1);
                assignment.setId(id);
                String title = rs.getString(2);
                assignment.setTitle(title);
                String details=rs.getString(3);
                assignment.setDetails(details);
                Date submissiondate=rs.getDate(4);
                assignment.setSubmissionDate(submissiondate);
                assignments.add(assignment);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        return assignments;
       
    }

    @Override
    public void insertAssignment() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();

        String sql = "insert into assignment(title,details,submissiondate) values(?,?,?)";//
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            System.out.println("Assign the assignment's title");
            pst.setString(1, sc.nextLine());
            System.out.println("Assign the assignment's details");
            pst.setString(2, sc.nextLine());
            System.out.println("Assign the assignment's submission date(yyyy-MM-dd)");
            String stringdate = sc.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = null;
            try {
                date = format.parse(stringdate);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            java.sql.Date wrongdate = new java.sql.Date(date.getTime());
            java.sql.Date correctdate = new java.sql.Date(wrongdate.getTime() + 24 * 60 * 60 * 1000);
            pst.setDate(3, correctdate);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("Assignment inserted");

    }

    @Override
    public void updateAssignment() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        AssignmentDaoImplement adao = new AssignmentDaoImplement();
        ArrayList<Assignment>assignments=adao.getAssignments();
        System.out.println("Replace one of the following assignments with a new one using the appropriate number");
        for (int i = 0; i < assignments.size(); i++) {
            System.out.println(assignments.get(i).getId() + " " + assignments.get(i).getTitle());
        }
        System.out.println("Scroll up");//small console window
        int deleteid = sc.nextInt();
        String str1 = sc.nextLine();//φτιαχνει το προβλημα της nextInt() - nextLine()
        String sql = "update assignment set title=?,details=?,submissiondate=? where id=?";//
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            System.out.println("Assign the assignment's title");
            pst.setString(1, sc.nextLine());
            System.out.println("Assign the assignment's details");
            pst.setString(2, sc.nextLine());
             System.out.println("Assign the assignments's submission date(yyyy-MM-dd)");
             String stringdate = sc.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = null;
            java.util.Date date1=null;
            try {
                date = format.parse(stringdate);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            java.sql.Date wrongdate = new java.sql.Date(date.getTime());
            java.sql.Date correctdate = new java.sql.Date(wrongdate.getTime() + 24 * 60 * 60 * 1000);
            pst.setDate(3, correctdate);
            pst.setInt(4, deleteid);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("Assignment inserted");

    }

    @Override
    public void deleteAssignment() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        AssignmentDaoImplement adao = new AssignmentDaoImplement();
        PreparedStatement pst = null;
        ArrayList<Assignment> assignments = adao.getAssignments();
        System.out.println("Delete one of the following assignments using the appropriate number");
        for (int i = 0; i < assignments.size(); i++) {
            System.out.println(assignments.get(i).getId() + " " + assignments.get(i).getTitle());
        }
        System.out.println("Scroll up");//small console window
        int deleteid = sc.nextInt();
        String sql1 = "Delete from assignment where id=?";
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
        System.out.println("Assignment deleted!");
    }

    @Override
    public ArrayList<Assignment> getAssignmentsPerCourseById(int courseid) {
        UserDaoImplement dao = new UserDaoImplement();
        ArrayList<Assignment> assignmentsPerCourseById = new ArrayList<Assignment>();
        Connection con = Dbutils.getconnection();
        PreparedStatement pst = null;
        String sql = "SELECT u.id,u.title,u.submissiondate from (assignment u INNER JOIN assignmentspercourse s ON u.id=s.assignmentid)INNER JOIN course c ON c.id=s.courseid where c.id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, courseid);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Assignment assignment = new Assignment();
                assignment.setId(rs.getInt(1));
                assignment.setTitle(rs.getString(2));
                assignment.setSubmissionDate(rs.getDate(3));
                assignmentsPerCourseById.add(assignment);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        return assignmentsPerCourseById;
    }
    @Override
    public ArrayList<Assignment> getAssignmentsNotAssignedToCourseById(int courseid) {
        AssignmentDaoImplement adao=new AssignmentDaoImplement();
      ArrayList assignments= adao.getAssignments();
      Connection con = Dbutils.getconnection();
        PreparedStatement pst = null;
            String sql = "SELECT u.id from (course c INNER JOIN assignmentspercourse s ON c.id=s.courseid)INNER JOIN assignment u ON u.id=s.assignmentid where c.id=? order by u.id DESC";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, courseid);
            ResultSet rs=pst.executeQuery();
            while (rs.next()){
            int assignmentid=rs.getInt(1);
            assignments.remove(assignmentid-1);
            }
            
        } catch (SQLException ex) {
          ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        return assignments;
    }

}
