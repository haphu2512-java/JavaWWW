package fit.se.haxuanphu_tuan2;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/uploadFileDB")
@MultipartConfig(maxFileSize = 16177215) // 16MB
public class UpFileDatabaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Config DB (MariaDB)
    private String dbURL = "jdbc:mariadb://localhost:3307/UploadFileServletDB";
    private String dbUser = "root";
    private String dbPass = "sa";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        InputStream inputStream = null;
        Part filePart = req.getPart("photo");
        if (filePart != null) {
            inputStream = filePart.getInputStream();
        }

        Connection conn = null;
        String message = null;

        try {
            // load MariaDB driver
            Class.forName("org.mariadb.jdbc.Driver");

            conn = DriverManager.getConnection(dbURL, dbUser, dbPass);

            String sql = "INSERT INTO StudentPhotos (first_name, last_name, photo) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);

            if (inputStream != null) {
                statement.setBinaryStream(3, inputStream, (int) filePart.getSize());
            } else {
                statement.setNull(3, Types.BLOB);
            }

            int row = statement.executeUpdate();
            if (row > 0) {
                message = "Upload and save into MariaDB successful!";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            message = "ERROR: " + ex.getMessage();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ignore) {
                }
            }
        }

        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().println("<h3>" + message + "</h3>");
    }
}
