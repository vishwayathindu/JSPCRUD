package connector;

import db.Db;
import hash.Hash;
import model.StudentModel;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentConnector {


    public StudentConnector() {
    }

    public static int registerUser(StudentModel s1) throws SQLException, NoSuchAlgorithmException {
        int result = 0;
        Connection connection = null;
        PreparedStatement PreparedStatement = null;
        String sql = "INSERT INTO `student`(`StudentName`, `nIC`, `password`, `gender`) VALUES (?,?,?,?)";

        try {
            connection = new Db().getConnection();
            PreparedStatement = connection.prepareStatement(sql);
            String password = new Hash().hashPassword(s1.getPassword());
            PreparedStatement.setString(1, s1.getStudentName());
            PreparedStatement.setString(2, s1.getNic());
            PreparedStatement.setString(3, password);
            PreparedStatement.setString(4, s1.getGender());
            result = PreparedStatement.executeUpdate();
            return result;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return result;
        } finally {
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }


    }

    public static int studentLogin(StudentModel s1) throws SQLException, ClassNotFoundException {
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM `student` WHERE StudentName= ? and password = ?";


        try {
            connection = new Db().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            String password = new Hash().hashPassword(s1.getPassword());

            //System.out.println("student login stared working" + password);

            preparedStatement.setString(1, s1.getStudentName());
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                //System.out.println("Login Success");
                result = 1;
            } else {
                //System.out.println("Login Error");
                result = 0;
            }


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }


        return result;
    }

    public static int deleteStudent(StudentModel s1) throws SQLException {
        int result = 0;
        Connection connection = null;
        PreparedStatement PreparedStatement = null;
        String sql = "DELETE  FROM `student` WHERE studentId = ? ";

        try {
            connection = new Db().getConnection();
            PreparedStatement = connection.prepareStatement(sql);
            PreparedStatement.setInt(1, s1.getStudentId());
            result = PreparedStatement.executeUpdate();
            //System.out.printf("delte function got executed" + result);
            return result;


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (PreparedStatement != null) {
                PreparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }


        return result;
    }

    public static int studentUpdated(StudentModel s1) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {

        int result = 0;
        Connection connection = null;
        PreparedStatement PreparedStatement = null;
        String sql = "UPDATE student SET StudentName= ?,nIC= ?,gender= ? WHERE studentId= ?";

        try {
            connection = new Db().getConnection();
            PreparedStatement = connection.prepareStatement(sql);
            PreparedStatement.setString(1, s1.getStudentName());
            PreparedStatement.setString(2, s1.getNic());
            PreparedStatement.setString(3, s1.getGender());
            PreparedStatement.setInt(4, s1.getStudentId());
            result = PreparedStatement.executeUpdate();
            //System.out.println("student updated working");
            return result;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return result;
    }

    public static int NoOfRecords() {
        int noOfRecords = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "select count(*) from student";
        try {
            connection = new Db().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                noOfRecords = resultSet.getInt(1);
                //System.out.println(noOfRecords);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return noOfRecords;
    }

    public List<StudentModel> ShowTable(String stName) throws SQLException {
        List<StudentModel> Student = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String sql = "select * from student where StudentName =? or nIC= ?";
        //System.out.printf("searched student name is"+stName);

        try {
            connection = new Db().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,stName);
            preparedStatement.setString(2,stName);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int studentId = rs.getInt("studentId");
                String StudentName = rs.getString("StudentName");
                String nIC = rs.getString("nIC");
                String password = rs.getString("password");
                String gender = rs.getString("gender");
                Student.add(new StudentModel(studentId, StudentName, nIC, password, gender));

                //System.out.println("data return" + studentId + "" + StudentName + "" + nIC + "" + password + "" + gender);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }
        return Student;


    }

    public StudentModel updateStudentList(StudentModel s1) throws SQLException, ClassNotFoundException {


        Connection connection = null;
        PreparedStatement preparedStatement = null;
        final String sql = "select * from student where studentId= ?";

        try {
            connection = new Db().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, s1.getStudentId());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int studentId = rs.getInt("studentId");
                String StudentName = rs.getString("StudentName");
                String nIC = rs.getString("nIC");
                String password = rs.getString("password");
                String gender = rs.getString("gender");
                s1 = new StudentModel(studentId, StudentName, nIC, password, gender);


                //System.out.println("data return in update " + studentId + "" + StudentName + "" + nIC + "" + password + "" + gender);

            }
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }


        return s1;
    }

    public List<StudentModel> getRecords(int start, int total, String columnName) throws SQLException, ClassNotFoundException {
        List<StudentModel> Stu = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "select * from student ORDER BY "+ columnName +" DESC limit " + (start - 1) + "," + total;

        try {
            connection = new Db().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int studentId = rs.getInt("studentId");
                String StudentName = rs.getString("StudentName");
                String nIC = rs.getString("nIC");
                String password = rs.getString("password");
                String gender = rs.getString("gender");
                Stu.add(new StudentModel(studentId, StudentName, nIC, password, gender));

                //System.out.println("data return" + studentId + "" + StudentName + "" + nIC + "" + password + "" + gender);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println(e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }

        }
        return Stu;
    }


}

