package ca.sait.cprg352lab6.dataaccess;

import ca.sait.cprg352lab6.models.Role;
import ca.sait.cprg352lab6.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDB 
{

    public List<User> getAll() throws Exception 
    {
        List<User> users = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM user INNER JOIN role ON role.role_id = user.role;";
        
        try 
        {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) 
            {
                String email = rs.getString(1);
                boolean active = rs.getBoolean(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String password = rs.getString(5);
                int roleId = rs.getInt(6);
                String roleName = rs.getString(7);

                Role role = new Role(roleId, roleName);
                User user = new User(email, active, firstName, lastName, password, role);

                users.add(user);
            }
        } 
        finally 
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return users;
    }

    public User get(String email) throws Exception 
    {
        User user = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM user INNER JOIN role ON role.role_id = user.role WHERE email = ? LIMIT 1";
        
        try 
        {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) 
            {
                boolean active = rs.getBoolean(2);
                String firstName = rs.getString(3);
                String lastName = rs.getString(4);
                String password = rs.getString(5);
                int roleId = rs.getInt(6);
                String roleName = rs.getString(7);

                Role role = new Role(roleId, roleName);
                user = new User(email, active, firstName, lastName, password, role);
            }
        } 
        finally 
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return user;
    }

    public boolean insert(User user) throws Exception 
    {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO `userdb`.`user`(`email`, `first_name`, `last_name`, `password`, `role`) VALUES (?, ?, ?, ?, ?)";
        
        boolean inserted = false;

        try 
        {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRole().getId());

            inserted = ps.executeUpdate() != 0;

            /*if(ps.executeUpdate() != 0)
            {
                inserted = true;
            }
            else
            {
                inserted = false;
            }*/
        } 
        finally 
        {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return inserted;
    }

    public void update(User user) throws Exception 
    {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE user SET first_name=?, contents=? WHERE note_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, note.getTitle());
            ps.setString(2, note.getContents());
            ps.setInt(3, note.getNoteId());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void delete(Note note) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM note WHERE note_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, note.getNoteId());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

}
