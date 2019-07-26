/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import model.Assignment;
import model.AssignmentsPerStudent;
import model.User;
import utilities.Dbutils;

/**
 *
 * @author USER
 */
public class AssignmentsPerStudentDaoImplement implements AssignmentsPerStudentDaoInterface {

    @Override
    public void getAssignmentsPerStudentByIdandSubmit(User user) {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        ArrayList<Assignment> assignmentsPerStudentById = new ArrayList<Assignment>();
        PreparedStatement pst = null;
        String sql = "select a.id, a.title, a.submissiondate from (assignment a INNER JOIN assignmentsperstudent s on a.id=s.assignmentid)INNER JOIN user u on u.id=s.userid where u.id=? and a.submissiondate >=CURDATE()";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, user.getId());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Assignment assignment = new Assignment();
                assignment.setId(rs.getInt(1));
                assignment.setTitle(rs.getString(2));
                assignment.setSubmissionDate(rs.getDate(3));
                assignmentsPerStudentById.add(assignment);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("The assignments designated to you,  to be submitted are:");
        for (int i = 0; i < assignmentsPerStudentById.size(); i++) {

            System.out.println(assignmentsPerStudentById.get(i).getId() + " " + assignmentsPerStudentById.get(i).getTitle() + " " + assignmentsPerStudentById.get(i).getSubmissionDate());
        }
        System.out.println("Submit an assignment using the appropriate number");
        sc.nextInt();
        System.out.println("Assignment submitted");
    }

    @Override
    public ArrayList<AssignmentsPerStudent> getAssignmentsPerStudentPerCourse() {
        Connection con = Dbutils.getconnection();
        ArrayList<AssignmentsPerStudent> assignmentsPerStudent = new ArrayList<AssignmentsPerStudent>();
        PreparedStatement pst = null;
        String sql = "select a.id,a.title,u.id,u.firstname,u.lastname,c.title from (((assignment a INNER JOIN assignmentsperstudent aps on a.id=aps.assignmentid)INNER JOIN user u on aps.userid=u.id) INNER JOIN studentspercourse spc on spc.userid=u.id)INNER JOIN course c on c.id=spc.courseid ";
        try {
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                AssignmentsPerStudent aps = new AssignmentsPerStudent();
                aps.setAssignmentId(rs.getInt(1));
                aps.setAssignmentTitle(rs.getString(2));
                aps.setStudentId(rs.getInt(3));
                aps.setStudentFirstName(rs.getString(4));
                aps.setStudentLastName(rs.getString(5));
                aps.setCourseTitle(rs.getString(6));

                assignmentsPerStudent.add(aps);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        return assignmentsPerStudent;
    }

    @Override
    public void MarkAssignmentsPerStudentPerCourse() {
        AssignmentsPerStudentDaoImplement dao = new AssignmentsPerStudentDaoImplement();
        ArrayList<AssignmentsPerStudent> assignments = dao.getAssignmentsPerStudentPerCourse();
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        PreparedStatement pst = null;
        String sql = " update assignmentsperstudent set mark=? where userid=? and assignmentid=? ";
        try {
            pst = con.prepareStatement(sql);
            for (int i = 0; i < assignments.size(); i++) {
                System.out.println(assignments.get(i));
                System.out.println("Mark the assignment using a number from 1-100");
                pst.setInt(1, sc.nextInt());
                pst.setInt(2, assignments.get(i).getStudentId());
                pst.setInt(3, assignments.get(i).getAssignmentId());
                pst.executeUpdate();
                System.out.println("Mark was registered!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
    }

}
