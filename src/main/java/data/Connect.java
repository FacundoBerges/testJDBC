package data;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * @author Facundo
 */
public class Connect
{
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWD = "admin";
    private static BasicDataSource dataSource = null;
    
    
    public static DataSource getDataSource()
    {
        if(Connect.dataSource == null)
        {
            Connect.dataSource = new BasicDataSource();
            Connect.dataSource.setUrl(JDBC_URL);
            Connect.dataSource.setUsername(JDBC_USER);
            Connect.dataSource.setPassword(JDBC_PASSWD);
            Connect.dataSource.setInitialSize(5);
        }
        
        return Connect.dataSource;
    }
    
    public static Connection getConnection() throws SQLException
    {
        return Connect.getDataSource().getConnection();
    }
    
    public static void close(ResultSet rs) throws SQLException
    {
        rs.close();
    }
    
    public static void close(Statement stmt) throws SQLException
    {
        stmt.close();
    }
    
    public static void close(PreparedStatement stmt) throws SQLException
    {
        stmt.close();
    }
    
    public static void close(Connection conn) throws SQLException
    {
        conn.close();
    }
    
}
