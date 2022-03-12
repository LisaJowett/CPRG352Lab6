package ca.sait.cprg352lab6.servlets;

import ca.sait.cprg352lab6.models.Role;
import ca.sait.cprg352lab6.models.User;
import ca.sait.cprg352lab6.services.UserService;
import ca.sait.cprg352lab6.services.RoleService;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lisa Jowett
 */

public class UserServlet extends HttpServlet 
{
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        UserService serviceUser = new UserService();
        RoleService serviceRole = new RoleService();
        String email = request.getParameter("email");
        String action = request.getParameter("action");

        try
        {
            List<User> users = serviceUser.getAll();

            request.setAttribute("users", users);    
        }

        catch(Exception ex)
        {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }  

        try
        {
            List<Role> roles = serviceRole.getAll();

            request.setAttribute("roles", roles);
        }

        catch(Exception ex)
        {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 

        if (action.equals("delete") && action != null)
        {
            try
            {
                serviceUser.delete(email);
            }
        
            catch (Exception ex)
            {
                request.setAttribute("message", "Something went wrong, please try again.");
            }
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
        UserService serviceUser = new UserService();
        RoleService serviceRole = new RoleService();
        
        String action = request.getParameter("action");

        try
        {
            List<User> users = serviceUser.getAll();

            request.setAttribute("users", users);    
        }

        catch(Exception ex)
        {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }  

        try
        {
            List<Role> roles = serviceRole.getAll();

            request.setAttribute("roles", roles);
        }

        catch(Exception ex)
        {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        } 

        if (action.equals("edit") && action != null)
        {

            try
            {
                String email = request.getParameter("editEmail");
                String firstName = request.getParameter("editFirstName");
                String lastName = request.getParameter("editLastName");
                String password = request.getParameter("editPassword");
                String roleName = request.getParameter("editRoleName");

                int roleId = serviceRole.getRoleId(roleName);

                serviceUser.update(email, true, firstName, lastName, password, new Role(roleId, roleName));
            }

            catch(Exception ex)
            {
                request.setAttribute("message", "Something went wrong, please try again.");
            }
        }

        if (action.equals("add") && action != null)
        {

            try
            {
                String email = request.getParameter("addEmail");
                String firstName = request.getParameter("addFirstName");
                String lastName = request.getParameter("addLastName");
                String password = request.getParameter("addPassword");
                String roleName = request.getParameter("addRoleName");

                int roleId = serviceRole.getRoleId(roleName);

                serviceUser.insert(email, true, firstName, lastName, password, new Role(roleId, roleName));
            }

            catch(Exception ex)
            {
                request.setAttribute("message", "Something went wrong, please try again.");
            }
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
    }  
}
