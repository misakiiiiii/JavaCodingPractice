import java.sql.*;

public class JdbcTest {
    static final String JDBC_DRIVER = "com.masql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EXAMPLE";

    static final String USER = "root";
    static final String PASS = "";

    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            //注册JDBC驱动
            Class.forName("com.mysql.jdbc.Driver");
            
            System.out.println("Connection to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS); //打开连接

            System.out.println("Creating statement...");

            String sql = "UPDATE Students set age=? WHERE id=?";
            stmt = conn.prepareStatement(sql);
            
            //将值绑定到参数，参数从左至右序号为1，2...
            stmt.setInt(1,22);//绑定age的值，序号为1
            stmt.setInt(2,1);//绑定id的值，序号为2

            int rows = stmt.executeUpdate();
            System.out.println("被影响的行数："+ rows);

            sql = "SELECT id,name,age FROM Students";
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String name = rs.getString("name");

                //显示
               System.out.print("ID: " + id);
               System.out.print(", Age: " + age);
               System.out.print(", Name: " + name);
               System.out.println();
            }
            //清理
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch(SQLException se2) {
                se2.printStackTrace();
            }
            try {
                if(conn != null) {
                    conn.close();
                   }
                }catch(SQLException se) {
                    se.printStackTrace();
                 }
            }
                System.out.println("Goodbye!");
    }
 }
