<%@ page import="javax.servlet.http.HttpSession" %>
<%
    if(session == null || session.getAttribute("email") == null){
        response.sendRedirect("SignIn.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Admission Form</title>
    <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f0f0f0;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            form {
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            input[type=text], input[type=password], input[type=file], select {
                width: 100%;
                padding: 10px;
                margin: 10px 0;
                border: 1px solid #ddd;
                border-radius: 5px;
                box-sizing: border-box;
            }
            input[type=submit] {
                background-color: #4CAF50;
                color: white;
                padding: 10px;
                border: none;
                border-radius: 5px;
                cursor: pointer;
                width: 100%;
            }
            input[type=submit]:hover {
                opacity: 0.8;
            }
        </style>
</head>
<body>
    <h2>Admission Form</h2>
    <form action="AdmissionServlet" method="post" enctype="multipart/form-data">
        Student Name: <input type="text" name="student_name" required><br>
        Course: <select name="course" required>
                    <option value="Engineering">Engineering</option>
                    <option value="Medicine">Medicine</option>
                    <option value="Business">Business</option>
                </select><br>
        Passport Picture: <input type="file" name="passport_picture" accept=".jpg,.png" required><br>
        Certificates (Diploma): <input type="file" name="diploma_certificates" accept=".pdf" required><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
