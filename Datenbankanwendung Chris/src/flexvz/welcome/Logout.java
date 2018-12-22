package flexvz.welcome;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class Logout extends HttpServlet {
	
	private static final long serialVersionUID = -629633472258359973L;
    
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException { 
			
			// reset flag //
			MainPage.userNotSet = true;
						
			// Build HTML source //
			response.setContentType("text/html");
			
			StringBuilder sb = new StringBuilder();
			sb.append("<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"<head>\r\n" + 
					"  <title>FlexVZ</title>\r\n" + 
					"  <meta charset=\"UTF-8\">\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"\r\n" + 
					"<h2>FlexVZ</h2>\r\n" + 
					"<p>Bitte melden Sie sich hier mit ihrem FlexVZ-Accountdaten an: </p>\r\n" + 
					"\r\n" + 
					"<form action=\"./main\" method=\"get\">\r\n" + 
					"	<fieldset>\r\n" + 
					"		Matrikelnummer:<br>\r\n" + 
					"		<input type=\"text\" name=\"matrikelnummer\" value=\"\">\r\n" + 
					"		<br><br>\r\n" + 
					"        <input type=\"radio\" name=\"account\" id=\"Student\" value=\"Student\" checked=\"checked\"> \r\n" + 
					"        <label for=\"Student\">Student</label><br>\r\n" + 
					"  		<input type=\"radio\" name=\"account\" id=\"Dozent\" value=\"Dozent\">\r\n" + 
					"        <label for=\"Dozent\">Dozent</label><br>\r\n" + 
					"  		<input type=\"radio\" name=\"account\" id=\"Admin\" value=\"Admin\">\r\n" + 
					"        <label for=\"Admin\">Admin</label><br>\r\n" + 
					"		<br>\r\n" + 
					"		<input type=\"submit\" value=\"Anmelden\"/>\r\n" + 
					"	</fieldset>\r\n" + 
					"</form>\r\n" + 
					"</body>\r\n" + 
					"</html>");
			
			// Send HTML source //
			PrintWriter out = response.getWriter();			
			out.print(sb.toString());
			out.flush();
			out.close();
		}
}