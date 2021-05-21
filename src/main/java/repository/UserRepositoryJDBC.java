package repository;

import domain.User;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryJDBC implements UserRepository{

    private static String className = "com.mysql.cj.jdbc.Driver";
    private static String dbUrl = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
    private static String dbUser = "root";
    private static String DbPassword = "tladydgus1!";

    public String test(){
        return "";
    }

    public User getUserById(Long id) {

        User user = new User();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(className);
            conn = DriverManager.getConnection(dbUrl, dbUser, DbPassword);
            pstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            pstmt.setString(1, Long.toString(id));
            rs = pstmt.executeQuery();

            while (rs.next()) {
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("Email"));
            }

            rs.close();
            pstmt.close();
            conn.close();
            //이하는 발생 가능 오류 try catch입니다.
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return user;
    }

    public List<User> getUsers() {
        List<User> ret = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName(className);
            conn = DriverManager.getConnection(dbUrl, dbUser, DbPassword);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM users");

            while (rs.next()) {
                User temp = new User();
                temp.setId(rs.getLong("id"));
                temp.setName(rs.getString("name"));
                temp.setEmail(rs.getString("Email"));
                ret.add(temp);
            }

            rs.close();
            stmt.close();
            conn.close();
            //이하는 발생 가능 오류 try catch입니다.
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return ret;
    }

    public void insertUser(User users) {
        String name = users.getName();
        String email = users.getEmail();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(className);
            conn = DriverManager.getConnection(dbUrl, dbUser, DbPassword);
            pstmt = conn.prepareStatement("INSERT INTO users(name, email) values(?, ?) ");
            pstmt.setString(1, users.getName());
            pstmt.setString(2, users.getEmail());
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            //이하는 발생 가능 오류 try catch입니다.
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return;
    }

    public void deleteById(Long id){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(className);
            conn = DriverManager.getConnection(dbUrl, dbUser, DbPassword);

            pstmt = conn.prepareStatement("DELETE FROM users WHERE id = ?");
            pstmt.setString(1, Long.toString(id));
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            //이하는 발생 가능 오류 try catch입니다.
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return;
    }

    public void updateUser(User users) {
        String name = users.getName();
        String email = users.getEmail();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName(className);
            conn = DriverManager.getConnection(dbUrl, dbUser, DbPassword);
            pstmt = conn.prepareStatement("UPDATE users SET name = ?, email = ? WHERE id = ?");
            pstmt.setString(1, users.getName());
            pstmt.setString(2, users.getEmail());
            pstmt.setString(3, users.getId().toString());
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();
            //이하는 발생 가능 오류 try catch입니다.
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return;
    }
}