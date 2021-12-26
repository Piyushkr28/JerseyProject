package com.jerseycrud;
import java.sql.*;
import java.util.*;

public class StudentRepository {
    Connection con = null;

    public StudentRepository() {
        String url = "jdbc:mysql://localhost:3306/student";
        String user = "root";
        String pass = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,user,pass);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public List<Student> getStudents()
    {
        List<Student> students = new ArrayList<>();
        String sql = "select * from student";
        try
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                Student s = new Student();
                s.setS_id(rs.getInt(1));
                s.setStudent_no(rs.getInt(2));
                s.setStudent_name(rs.getString(3));
                s.setStudent_dob(rs.getString(4));
                s.setStudent_doj(rs.getString(5));
                students.add(s);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student getStudent(int id)
    {
        String sql = "select * from student where s_id="+id;
        Student s = new Student();
        try
        {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next())
            {
                s.setS_id(rs.getInt(1));
                s.setStudent_no(rs.getInt(2));
                s.setStudent_name(rs.getString(3));
                s.setStudent_dob(rs.getString(4));
                s.setStudent_doj(rs.getString(5));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return s;
    }

    public void insertStudent(Student a)
    {
        String sql = "insert into student (student_no, student_name, student_dob, student_doj) values(?, ?, ?, ?)";

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);

            //ps.setInt(1,a.getId());
            ps.setInt(1,a.getStudent_no());
            ps.setString(2,a.getStudent_name());
            ps.setString(3,a.getStudent_dob());
            ps.setString(4,a.getStudent_doj());
            ps.executeUpdate();
        }catch (SQLException e) {
           System.out.println(e);
        }
    }
    public void updateStudent(Student a)
    {
        String sql = "update student set student_no=?,student_name=?,student_dob=?,student_doj=? where s_id=?";

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);

            //ps.setInt(1,a.getId());
            ps.setInt(1,a.getStudent_no());
            ps.setString(2,a.getStudent_name());
            ps.setString(3,a.getStudent_dob());
            ps.setString(4,a.getStudent_doj());
            ps.setInt(5,a.getS_id());
            ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void delete(int id) {
        String sql = "delete from student where s_id = ?";

        try
        {
            PreparedStatement ps = con.prepareStatement(sql);

            //ps.setInt(1,a.getId());
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e);
        }
    }
}
