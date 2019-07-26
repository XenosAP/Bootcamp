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
import model.Schedule;
import utilities.Dbutils;

/**
 *
 * @author USER
 */
public class ScheduleDaoImplement implements ScheduleDaoInterface{

    @Override
    public ArrayList<Schedule> getSchedules() {
        Connection con = Dbutils.getconnection();
        String sql = "Select* from schedule";
        PreparedStatement pst = null;
        ArrayList<Schedule> schedules = new ArrayList<Schedule>();
        try {
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Schedule schedule = new Schedule();
                int id = rs.getInt(1);
                schedule.setId(id);
                String subject = rs.getString(2);
                schedule.setSubject(subject);
                Date date = rs.getDate(3);
                schedule.setDate(date);
                schedules.add(schedule);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        return schedules;
       
    }

    @Override
    public void insertSchedule() {
       Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();

        String sql = "insert into schedule(subject,date) values(?,?)";//
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            System.out.println("Assign the schedule's subject");
            pst.setString(1, sc.nextLine());
            System.out.println("Assign the schedule's date(yyyy-MM-dd)");
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
            pst.setDate(2, correctdate);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("Schedule inserted");

    }

    @Override
    public void updateSchedule() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        ScheduleDaoImplement sdao = new ScheduleDaoImplement();
        ArrayList<Schedule>schedules=sdao.getSchedules();
        System.out.println("Replace one of the following schedules with a new one using the appropriate number");
        for (int i = 0; i < schedules.size(); i++) {
            System.out.println(schedules.get(i).getId() + " " + schedules.get(i).getSubject()+" "+schedules.get(i).getDate());
        }
        int deleteid = sc.nextInt();
        String str1 = sc.nextLine();//φτιαχνει το προβλημα της nextInt() - nextLine()
        String sql = "update schedule set subject=?,date=? where id=?";//
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            System.out.println("Assign the schedule's subject");
            pst.setString(1, sc.nextLine());
            System.out.println("Assign the schedule's date(yyyy-MM-dd)");
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
            pst.setDate(2, correctdate);
            pst.setInt(3,deleteid);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("Schedule inserted!");

    }

    @Override
    public void deleteSchedule() {
     Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        ScheduleDaoImplement sdao = new ScheduleDaoImplement();
        PreparedStatement pst = null;
        ArrayList<Schedule> schedules = sdao.getSchedules();
        System.out.println("Delete one of the following schedules using the appropriate number");
        for (int i = 0; i < schedules.size(); i++) {
            System.out.println(schedules.get(i).getId() + " " + schedules.get(i).getSubject()+" "+schedules.get(i).getDate());
        }
        int deleteid = sc.nextInt();
        String sql1 = "Delete from schedule where id=?";
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
        System.out.println("Schedule deleted!");
    }

 @Override
    public ArrayList<Schedule> getSchedulesPerCourseById(int courseid) {
        UserDaoImplement dao = new UserDaoImplement();
        ArrayList<Schedule> schedulesPerCourseById = new ArrayList<Schedule>();
        Connection con = Dbutils.getconnection();
        PreparedStatement pst = null;
        String sql = "SELECT u.id,u.subject,u.date from (schedule u INNER JOIN schedulespercourse s ON u.id=s.scheduleid)INNER JOIN course c ON c.id=s.courseid where c.id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, courseid);
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
