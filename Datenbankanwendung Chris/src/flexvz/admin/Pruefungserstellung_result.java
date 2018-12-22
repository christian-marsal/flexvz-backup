package flexvz.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import flexvz.tools.Debugging;
import flexvz.tools.HtmlBuilder;
import flexvz.welcome.MainPage;

public class Pruefungserstellung_result extends HttpServlet {

	private static final long serialVersionUID = -931780142598691503L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException { 
		final String database_url = "jdbc:oracle:thin:@//oracle12c.informatik.uni-goettingen.de:1521/dbis";
		final String user = "christianmarsal";
		final String pass = "iid5eH0U";
	
		ResultSet rset = null;
		Connection conn = null;

		try {

			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			conn = DriverManager.getConnection(database_url, user, pass);
			Statement stmt = conn.createStatement();
			
			// set variables //
			String veranstaltungsid = request.getParameter("veranstaltungsid");
			String pruefungsart = request.getParameter("pruefungsart");
			String pruefungsbeginn = HtmlBuilder.convertDate(request.getParameter("pruefungsbeginn"));
			String pruefungsende = HtmlBuilder.convertDate(request.getParameter("pruefungsende"));
			String anmeldefrist = HtmlBuilder.convertDate(request.getParameter("anmeldefrist"));
			String abmeldefrist = HtmlBuilder.convertDate(request.getParameter("abmeldefrist"));

			// generate SQL statement //
			String strInsert = "Insert into CHRISTIANMARSAL.PRUEFUNG (VERANSTALTUNGSID,ART,PRUEFUNGSBEGINN,PRUEFUNGSENDE,ANMELDEFRIST,ABMELDEFRIST) values "
								+ "("+veranstaltungsid+",'"+pruefungsart+"',"+pruefungsbeginn+","+pruefungsende+","+anmeldefrist+","+abmeldefrist+")";

			rset = stmt.executeQuery(strInsert);
			
			// Build HTML source //
			response.setContentType("text/html");
			
			StringBuilder sb = new StringBuilder();
			// start of appends //

			sb.append(HtmlBuilder.createDocumentHeader("Confirmation - FlexVZ")); 
			
			sb.append("<h2>Prüfungserstellung</h2>\n" + 
					"<p>Die Prüfungserstellung wurde erfolgreich durchgeführt.</p></br>\n" + 
					"<form action=\"../Prüfungserstellung\" method=\"get\">\n" +
					HtmlBuilder.createSubmitButton("Zurück zur Prüfungserstellung") +
					"</form>");
			// end of appends //
			sb.append(HtmlBuilder.createDocumentFooter());
			
			// Save in file for debugging //
			if(MainPage.debugMode) {
				Debugging.createLocalHtml(this.getClass().getSimpleName(), sb);
			}
			
			// Send HTML source //
			PrintWriter out = response.getWriter();	
					
			out.print(sb.toString());
			out.flush();
			out.close();
			
		} catch (SQLException e) {
			if(MainPage.debugMode) {
				Debugging.logSQLException(e, "../webapps/flexvz/log_files/"+this.getClass().getSimpleName()+"SQLError.log");  
			}
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				if(MainPage.debugMode) {
					Debugging.logSQLException(e, "../webapps/flexvz/log_files/"+this.getClass().getSimpleName()+"SQLConnError.log");  
				}
			}
		}
	}	
}
