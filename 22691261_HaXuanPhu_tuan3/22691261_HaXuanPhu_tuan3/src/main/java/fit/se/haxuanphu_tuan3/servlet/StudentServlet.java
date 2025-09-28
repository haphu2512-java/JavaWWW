package fit.se.haxuanphu_tuan3.servlet;

import fit.se.haxuanphu_tuan3.model.Student;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "student",value = "/student")
public class StudentServlet extends HttpServlet {
    public StudentServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        Student student = new Student();
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String pinCode = request.getParameter("pinCode");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        String[] hobbies = request.getParameterValues("hobbies");
        String course = request.getParameter("course");

        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setDob(dateOfBirth);
        student.setEmail(email);
        student.setMobile(mobile);
        student.setGender(gender);
        student.setAddress(address);
        student.setCity(city);
        student.setPinCode(pinCode);
        student.setState(state);
        student.setCountry(country);
        student.setHobbies(hobbies);
        student.setCourse(course);

        request.setAttribute("student",student);
        RequestDispatcher rd = request.getRequestDispatcher("student-result.jsp");
        rd.forward(request,resp);
    }
}


//// Generate registration ID and timestamp
//String registrationId = "REG" + System.currentTimeMillis();
//String registrationTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
