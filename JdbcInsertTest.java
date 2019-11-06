import java.sql.*;

public class JdbcInsertTest {
    public static Connection conn = null;
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/EXAMPLE","root","");
            Statement stmt = conn.createStatement();

            boolean execute = stmt.execute("insert into Students values(0,17,'misaki')");
            if (execute) {
                System.out.println("插入失败");
            } else{
                System.out.println("插入成功");
            }

           //批量插入，关闭自动提交
           conn.setAutoCommit(false);
           String sql = "insert into Students values(?,?,?)";
           PreparedStatement preparedStatement = conn.prepareStatement(sql);
           //set values to insert
           for (int i=1;i<=10;i++) {
               preparedStatement.setInt(1,i);
               preparedStatement.setInt(2, i+10);
               preparedStatement.setString(3, "Misaki");
               preparedStatement.addBatch();
           }
           //批量执行插入 使用executeBatch 方法
           preparedStatement.executeBatch();
           //submit to database
           conn.commit();
           System.out.println("commit successfully!");
        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
