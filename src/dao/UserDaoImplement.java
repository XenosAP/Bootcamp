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
import model.User;
import org.mindrot.jbcrypt.BCrypt;
import utilities.Dbutils;

/**
 *
 * @author creoo
 */
public class UserDaoImplement implements UserDaoInterface {

    @Override
    public User authentication() {//NA GYRNAEI PISW OTAN EINAI LATHOS USERNAME,BECRYPT
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        String sql = "Select* from user where username=? and password=?";
        PreparedStatement pst = null;
        User user = new User();
        String hashedpassword;
        try {
            while (true) {

                pst = con.prepareStatement(sql);

                System.out.println("Enter username:");
                pst.setString(1, sc.nextLine());
                System.out.println("Enter password:");
                String inputpassword = sc.nextLine();
                pst.setString(2, inputpassword);
                hashedpassword = BCrypt.hashpw(inputpassword, BCrypt.gensalt());
                ResultSet rs = pst.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    user.setId(id);
                    String firstname = rs.getString(2);
                    user.setFirstname(firstname);
                    String lastname = rs.getString(3);
                    user.setLastname(lastname);
                    String username = rs.getString(4);
                    user.setUsername(username);
                    String password = rs.getString(5);
                    user.setPassword(password);
                    Date dateofbirth = rs.getDate(6);
                    user.setDateofbirth(dateofbirth);
                    int roleid = rs.getInt(7);
                    user.setRoleid(roleid);}
                    if (BCrypt.checkpw(user.getPassword(), hashedpassword)) {
                       break; 
                    } else {
                        System.out.println("User doesnt'exit. Try Again!");
                    }
                
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        return user;

    }

    @Override
    public ArrayList<User> getStudents() {
        Connection con = Dbutils.getconnection();
        String sql = "Select* from user where roleid=3";
        PreparedStatement pst = null;
        ArrayList<User> students = new ArrayList<User>();
        try {
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User();
                int id = rs.getInt(1);
                user.setId(id);
                String firstname = rs.getString(2);
                user.setFirstname(firstname);
                String lastname = rs.getString(3);
                user.setLastname(lastname);
                String username = rs.getString(4);
                user.setUsername(username);
                String password = rs.getString(5);
                user.setPassword(password);
                Date dateofbirth = rs.getDate(6);
                user.setDateofbirth(dateofbirth);
                int roleid = rs.getInt(7);
                user.setRoleid(roleid);

                students.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        return students;
    }

    @Override
    public ArrayList<User> getTrainers() {
        Connection con = Dbutils.getconnection();
        String sql = "Select* from user where roleid=2";
        PreparedStatement pst = null;
        ArrayList<User> trainers = new ArrayList<User>();
        try {
            pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User();
                int id = rs.getInt(1);
                user.setId(id);
                String firstname = rs.getString(2);
                user.setFirstname(firstname);
                String lastname = rs.getString(3);
                user.setLastname(lastname);
                String username = rs.getString(4);
                user.setUsername(username);
                String password = rs.getString(5);
                user.setPassword(password);
                Date dateofbirth = rs.getDate(6);
                user.setDateofbirth(dateofbirth);
                int roleid = rs.getInt(7);
                user.setRoleid(roleid);

                trainers.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        return trainers;
    }

    @Override
    public void insertStudent() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();

        String sql = "insert into user(firstname,lastname,username,password,dateofbirth,roleid) values(?,?,?,?,?,?)";//
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            System.out.println("Assign the student's first name");
            pst.setString(1, sc.nextLine());
            System.out.println("Assign the student's last name");
            pst.setString(2, sc.nextLine());
            System.out.println("Assign the student's username");
            pst.setString(3, sc.nextLine());
            System.out.println("Assign the student's password");
            pst.setString(4, sc.nextLine());
            System.out.println("Assign the student's date of birth(yyyy-MM-dd)");
            String stringdate = sc.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = null;
            try {
                date = format.parse(stringdate);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            java.sql.Date wrongdateofbirth = new java.sql.Date(date.getTime());
            java.sql.Date dateofbirth = new java.sql.Date(wrongdateofbirth.getTime() + 24 * 60 * 60 * 1000);
            pst.setDate(5, dateofbirth);
            pst.setInt(6, 3);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("Student inserted");

    }

    @Override
    public void insertTrainer() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();

        String sql = "insert into user(firstname,lastname,username,password,dateofbirth,roleid) values(?,?,?,?,?,?)";//
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            System.out.println("Assign the trainer's first name");
            pst.setString(1, sc.nextLine());
            System.out.println("Assign the trainer's last name");
            pst.setString(2, sc.nextLine());
            System.out.println("Assign the trainer's  username");
            pst.setString(3, sc.nextLine());
            System.out.println("Assign the trainer's password");
            pst.setString(4, sc.nextLine());
            System.out.println("Assign the trainer's date of birth(yyyy-MM-dd)");
            String stringdate = sc.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = null;
            try {
                date = format.parse(stringdate);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            java.sql.Date wrongdateofbirth = new java.sql.Date(date.getTime());
            java.sql.Date dateofbirth = new java.sql.Date(wrongdateofbirth.getTime() + 24 * 60 * 60 * 1000);
            pst.setDate(5, dateofbirth);
            pst.setInt(6, 2);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("Trainer inserted");

    }

    @Override
    public void updateStudent() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        UserDaoImplement user = new UserDaoImplement();
        ArrayList<User> students = user.getStudents();
        System.out.println("Replace one of the following students with a new one using the appropriate number");
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).getId() + " " + students.get(i).getFirstname() + " " + students.get(i).getLastname());
        }
        System.out.println("Scroll up");//small console window
        int deleteid = sc.nextInt();
        String str1 = sc.nextLine();//φτιαχνει το προβλημα της nextInt() - nextLine()
        String sql = "update user set firstname=?,lastname=?,username=?,password=?,dateofbirth=?, roleid=? where id=?";//
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            System.out.println("Assign the students's first name");
            pst.setString(1, sc.nextLine());
            System.out.println("Assign the students's last name");
            pst.setString(2, sc.nextLine());
            System.out.println("Assign the students's  username");
            pst.setString(3, sc.nextLine());
            System.out.println("Assign the students's password");
            pst.setString(4, sc.nextLine());
            System.out.println("Assign the students's date of birth(yyyy-MM-dd)");
            String stringdate = sc.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = null;
            try {
                date = format.parse(stringdate);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            java.sql.Date wrongdateofbirth = new java.sql.Date(date.getTime());
            java.sql.Date dateofbirth = new java.sql.Date(wrongdateofbirth.getTime() + 24 * 60 * 60 * 1000);
            pst.setDate(5, dateofbirth);
            pst.setInt(6, 3);
            pst.setInt(7, deleteid);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("Student inserted!");

    }

    @Override
    public void updateTrainer() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        UserDaoImplement user = new UserDaoImplement();
        ArrayList<User> trainers = user.getTrainers();
        System.out.println("Replace one of the following trainers with a new one using the appropriate number");
        for (int i = 0; i < trainers.size(); i++) {
            System.out.println(trainers.get(i).getId() + " " + trainers.get(i).getFirstname() + " " + trainers.get(i).getLastname());
        }
        System.out.println("Scroll up");//small console window
        int deleteid = sc.nextInt();
        String str1 = sc.nextLine();//φτιαχνει το προβλημα της nextInt() - nextLine()
        String sql = "update user set firstname=?,lastname=?,username=?,password=?,dateofbirth=?, roleid=? where id=?";//
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
            System.out.println("Assign the trainer's first name");
            pst.setString(1, sc.nextLine());
            System.out.println("Assign the trainer's last name");
            pst.setString(2, sc.nextLine());
            System.out.println("Assign the trainer's  username");
            pst.setString(3, sc.nextLine());
            System.out.println("Assign the trainer's password");
            pst.setString(4, sc.nextLine());
            System.out.println("Assign the trainer's date of birth(yyyy-MM-dd)");
            String stringdate = sc.nextLine();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = null;
            try {
                date = format.parse(stringdate);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            java.sql.Date wrongdateofbirth = new java.sql.Date(date.getTime());
            java.sql.Date dateofbirth = new java.sql.Date(wrongdateofbirth.getTime() + 24 * 60 * 60 * 1000);
            pst.setDate(5, dateofbirth);
            pst.setInt(6, 2);
            pst.setInt(7, deleteid);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        System.out.println("Trainer inserted!");

    }

    @Override
    public void deleteStudent() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        PreparedStatement pst = null;

        UserDaoImplement udao = new UserDaoImplement();
        ArrayList<User> students = udao.getStudents();
        System.out.println("Delete one of the following students using the appropriate number");
        for (int i = 0; i < students.size(); i++) {
            System.out.println(students.get(i).getId() + " " + students.get(i).getFirstname() + " " + students.get(i).getLastname());
        }
        System.out.println("Scroll up");//small console window
        int deleteid = sc.nextInt();
        String sql1 = "Delete from user where id=?";
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
        System.out.println("Student deleted!");
    }

    @Override
    public void deleteTrainer() {
        Scanner sc = new Scanner(System.in);
        Connection con = Dbutils.getconnection();
        UserDaoImplement udao = new UserDaoImplement();
        PreparedStatement pst = null;
        ArrayList<User> trainers = udao.getTrainers();
        System.out.println("Delete one of the following trainers using the appropriate number");
        for (int i = 0; i < trainers.size(); i++) {
            System.out.println(trainers.get(i).getId() + " " + trainers.get(i).getFirstname() + " " + trainers.get(i).getLastname());
        }
        System.out.println("Scroll up");//small console window
        int deleteid = sc.nextInt();
        String sql1 = "Delete from user where id=?";
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
        System.out.println("Trainer deleted!");
    }

    @Override
    public ArrayList<User> getStudentsPerCourseById(int courseid) {
        UserDaoImplement dao = new UserDaoImplement();
        ArrayList<User> studentsPerCourseById = new ArrayList<User>();
        Connection con = Dbutils.getconnection();
        PreparedStatement pst = null;
        String sql = "SELECT u.id,u.firstname,u.lastname from (user u INNER JOIN studentspercourse s ON u.id=s.userid)INNER JOIN course c ON c.id=s.courseid where c.id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, courseid);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setFirstname(rs.getString(2));
                user.setLastname(rs.getString(3));
                studentsPerCourseById.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        return studentsPerCourseById;
    }
//

    @Override
    public ArrayList<User> getTrainersPerCourseById(int courseid) {
        UserDaoImplement dao = new UserDaoImplement();
        ArrayList<User> trainersPerCourseById = new ArrayList<User>();
        Connection con = Dbutils.getconnection();
        PreparedStatement pst = null;
        String sql = "SELECT u.id,u.firstname,u.lastname from (user u INNER JOIN trainerspercourse s ON u.id=s.userid)INNER JOIN course c ON c.id=s.courseid where c.id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, courseid);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setFirstname(rs.getString(2));
                user.setLastname(rs.getString(3));
                trainersPerCourseById.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            Dbutils.closeStatement(pst);
            Dbutils.closeConnection(con);
        }
        return trainersPerCourseById;
    }

}
