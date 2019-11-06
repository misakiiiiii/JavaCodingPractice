import java.sql.*;

public class QueryDemo {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/test";
    private static final String USER = "root";
    private static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null ;
        PreparedStatement stmt = null;

        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            String sql = "select id,name,age,sex from user where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1,1);

            ResultSet rs = stmt.executeQuery();
            printRS(rs);

            stmt = conn.prepareStatement("select id,name,age,sex from user where age=? and name=?");
            stmt.setInt(1,12);
            stmt.setString(2,"yan");
            rs = stmt.executeQuery();
            printRS(rs);

            stmt = conn.prepareStatement("select id,name,age,sex from user where age>?");
            stmt.setInt(1,20);
            rs = stmt.executeQuery();
            printRS(rs);

            rs.close();
            } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
    private static void printRS(ResultSet resultset) throws SQLException {
        while (resultset.next()){
            int id = resultset.getInt("id");
            String name = resultset.getString("name");
            int age = resultset.getInt("age");
            String sex = resultset.getString("sex");
            System.out.println("id:" + id + " name:" + name + " age:" + age + " sex:" + sex);
        }
    }
}
