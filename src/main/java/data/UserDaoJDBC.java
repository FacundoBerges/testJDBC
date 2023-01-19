package data;

import domain.UserDTO;
import java.util.*;
import java.sql.*;

/**
 * @author Facundo
 */
public class UserDaoJDBC implements IUserDAO
{
    private static final String SQL_SELECT = "SELECT `id_user`, `user`, `password` FROM `test`.`users`;";
    private static final String SQL_INSERT = "INSERT INTO `test`.`users` (`user`, `password`) VALUES (?, ?);";
    private static final String SQL_UPDATE = "UPDATE `test`.`users` SET `user` = ?, `password` = ? WHERE (`id_user` = ?);";
    private static final String SQL_DELETE = "DELETE FROM `test`.`users` WHERE `id_user` = ?;";
    private Connection transConnection = null;

    
    public UserDaoJDBC() {
    }
    
    public UserDaoJDBC(Connection transConnection) {
        this.transConnection = transConnection;
    }
    
    
    @Override
    public List<UserDTO> select() throws SQLException
    {
        List<UserDTO> userList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        UserDTO user = null;
        
        try
        {
            conn = this.transConnection != null ? this.transConnection : Connect.getConnection();
            pstmt = conn.prepareStatement(SQL_SELECT);
            rs = pstmt.executeQuery();
            
            while(rs.next())
            {
                int idUser = rs.getInt("id_user");
                String username = rs.getString("user");
                String password = rs.getString("password");
                
                user = new UserDTO(idUser, username, password);
                
                userList.add(user);
            }
        }
        finally
        {
            try 
            {
                Connect.close(rs);
                Connect.close(pstmt);
                if(this.transConnection == null)
                    Connect.close(conn);
            } 
            catch (SQLException ex) 
            {
                ex.printStackTrace(System.out);
            }
        }
        
        return userList;
    }
    
    @Override
    public int insert(UserDTO user) throws SQLException
    {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try
        {
            conn = this.transConnection != null ? this.transConnection : Connect.getConnection();
            pstmt = conn.prepareStatement(SQL_INSERT);
            
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            
            result = pstmt.executeUpdate();
        }
        finally
        {
            try 
            {
                Connect.close(pstmt);
                if(this.transConnection == null)
                    Connect.close(conn);
            } 
            catch (SQLException ex) 
            {
                ex.printStackTrace(System.out);
            }
        }
        
        return result;
    }
    
    @Override
    public int update(UserDTO user) throws SQLException
    {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try
        {
            conn = this.transConnection != null ? this.transConnection : Connect.getConnection();
            pstmt = conn.prepareStatement(SQL_UPDATE);
            
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getIdUser());
            
            result = pstmt.executeUpdate();
        }
        finally
        {
            try 
            {
                Connect.close(pstmt);
                if(this.transConnection == null)
                    Connect.close(conn);
            } 
            catch (SQLException ex) 
            {
                ex.printStackTrace(System.out);
            }
        }
        
        return result;
    }
    
    @Override
    public int delete(UserDTO user) throws SQLException
    {
        int result = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        
        try
        {
            conn = this.transConnection != null ? this.transConnection : Connect.getConnection();
            pstmt = conn.prepareStatement(SQL_DELETE);
            
            pstmt.setInt(1, user.getIdUser());
            
            result = pstmt.executeUpdate();
        }
        finally
        {
            try 
            {
                Connect.close(pstmt);
                if(this.transConnection == null)
                    Connect.close(conn);
            } 
            catch (SQLException ex) 
            {
                ex.printStackTrace(System.out);
            }
        }
        
        return result;
    }
    
}
