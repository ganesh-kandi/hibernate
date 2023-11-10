package in.project.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.project.Model.Student;
import in.project.factory.StudentServiceFactory;
import in.project.service.IStudentService;


@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doProcess(request, response);
	}
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IStudentService studentService= StudentServiceFactory.getStudentService();
		String URI=request.getRequestURI();
		System.out.println(URI);
		RequestDispatcher rd=null;
		if(URI.endsWith("layout")) {
			rd=request.getRequestDispatcher("../Layout.html");
			rd.forward(request, response);
		}
		if(URI.endsWith("addform")) {
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			String city=request.getParameter("city");
			String country=request.getParameter("country");
			
			Student student=new Student();
			student.setName(name);
			student.setEmail(email);
			student.setCity(city);
			student.setCountry(country);
			String status=studentService.save(student);
			System.out.println(status);
			if(status.equalsIgnoreCase("s")) {
				rd=request.getRequestDispatcher("../insertresult.jsp");
				request.setAttribute("insert", "s");
				rd.forward(request, response);
			}else if(status.equalsIgnoreCase("f")) {
				rd=request.getRequestDispatcher("../insertresult.jsp");
				request.setAttribute("insert", "f");
				rd.forward(request, response);
			}else {
				System.out.println("something went wrong...");
			}
			
			
		}
		if(URI.endsWith("search")) {
			
			Integer sid=Integer.parseInt(request.getParameter("sid"));
			Student student=studentService.findById(sid);
			request.setAttribute("student", student);
			rd=request.getRequestDispatcher("../searchresult.jsp");
			rd.forward(request, response);
		}
		
		
		if (URI.endsWith("delete")) {
			String sid = request.getParameter("sid");
			String status = studentService.deleteById(Integer.parseInt(sid));

			if (status.equals("s")) {
				rd = request.getRequestDispatcher("../deleteresult.jsp");
				request.setAttribute("delete", "s");
				rd.forward(request, response);
			} else if (status.equals("f")) {
				rd = request.getRequestDispatcher("../deleteresult.jsp");
				request.setAttribute("delete", "f");
				rd.forward(request, response);
			} else {
				rd = request.getRequestDispatcher("../deleteresult.jsp");
				request.setAttribute("delete", "fnf");
				rd.forward(request, response);
			}
		}
		
		if(URI.endsWith("editform")) {
			Integer sid=Integer.parseInt(request.getParameter("sid"));
			Student student=studentService.findById(sid);
			if(student!=null) {
			request.setAttribute("student", student);
			rd=request.getRequestDispatcher("../editform.jsp");
			rd.forward(request, response);
			}else
			{
				request.setAttribute("delete", "fnf");
				rd=request.getRequestDispatcher("");
				rd.forward(request, response);
			}
//			if(student!=null) {
//				
//				PrintWriter out = response.getWriter();
//				out.println("<html><head><title>OUTPUT</title></head>");
//				out.println("<body bgcolor='lightblue'>");
//				out.println("<br/><br/><br/>");
//				out.println("<form method='post' action='./update'>");
//				out.println("<table align='center'>");
//				
//				out.println("<tr><th>ID</th><td>" + student.getSid() + "</td></tr>");
//				out.println("<input type='hidden' name='sid' value='" + student.getSid() + "'/>");
//				
//				out.println("<tr><th>NAME</th><td><input type='text' name='name' value='" + student.getName()
//						+ "'/></td></tr>");
//				out.println("<tr><th>EMAIL</th><td><input type='text' name='email' value='" + student.getEmail()
//				+ "'/></td></tr>");
//				out.println("<tr><th>CITY</th><td><input type='text' name='city' value='" + student.getCity()
//				+ "'/></td></tr>");
//				out.println("<tr><th>COUNTRY</th><td><input type='text' name='country' value='" + student.getCountry()
//				+ "'/></td></tr>");
//				
//				out.println("<tr><td></td><td><input type='submit' value='update'/></td></tr>");
//				out.println("</table>");
//				out.println("</form>");
//				out.println("</body>");
//				out.println("</html>");
//				out.close();
//				
//			}else {
//				rd=request.getRequestDispatcher("../filenotfound.html");
//				rd.forward(request, response);
//			}
		}
	
		if(URI.endsWith("update")) {
			
			Student student=new Student();
			String sid=request.getParameter("sid");
			String name=request.getParameter("name");
			String email=request.getParameter("email");
			String city=request.getParameter("city");
			String country=request.getParameter("country");
			
			student.setSid(Integer.parseInt(sid));
			student.setName(name);
			student.setEmail(email);
			student.setCity(city);
			student.setCountry(country);
			
			String status=studentService.updateById(student);
			if(status.equalsIgnoreCase("s")) {
				System.out.println("record Updated Successfully");
				rd=request.getRequestDispatcher("../updateresult.jsp");
				request.setAttribute("update", "s");
				rd.forward(request, response);
			}
			else {
				System.out.println("record updation failed...");
				rd=request.getRequestDispatcher("../updateresult.jsp");
				request.setAttribute("update", "f");
				rd.forward(request, response);
			}
		}
		
		
		}	
		
	

}
