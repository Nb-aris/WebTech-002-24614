package com.example;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet(name = "AdmissionServlet", value = "/AdmissionServlet")
public class AdmissionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = (String) request.getSession().getAttribute("email");
        String studentName = request.getParameter("student_name");
        String course = request.getParameter("course");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student_admission", "root", "<yourPassWord>");
            String query = "INSERT INTO admissions (email, student_name, course) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, studentName);
            ps.setString(3, course);

            int i = ps.executeUpdate();
            if (i > 0) {
                String userEmail = null;
                MailUtil.sendEmail(null, "Admission Submission Confirmation", "Your admission form has been submitted successfully.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ServletFileUpload.isMultipartContent(request)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                List<FileItem> items = upload.parseRequest(request);
                for (FileItem item : items) {
                    if (!item.isFormField()) {
                        // Process the uploaded file
                        String fieldName = item.getFieldName();
                        String fileName = item.getName();
                        long sizeInBytes = item.getSize();

                        // Use item.getInputStream() to read the file's content
                    } else {
                        // Process regular form field (input type="text|radio|checkbox|etc", here).
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString();
                        // Handle form fields like student_name and course
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

