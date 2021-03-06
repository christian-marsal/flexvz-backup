package flexvz.student;
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

import flexvz.tools.*;
import flexvz.welcome.MainPage;

public class Pruefungsanmeldung_result extends HttpServlet {

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
			String pruefungsid = request.getParameter("pruefungsid");

			// generate SQL statement //
			String strInsert = "Insert into CHRISTIANMARSAL.PRUEFUNGSANMELDUNG (PRUEFUNGSID,STUDENTENID,ANMELDEDATUM,PRUEFUNGSDATUM,RAUM) values ("+pruefungsid+","+MainPage.matrikelnummer+",SYSDATE,null,null)";

			rset = stmt.executeQuery(strInsert);
			
			// Build HTML source //
			response.setContentType("text/html");
			
			StringBuilder sb = new StringBuilder();
			// start of appends //

			sb.append(HtmlBuilder.createDocumentHeader("Confirmation - FlexVZ")); 
			
			sb.append("<h2>Prüfungsanmeldung</h2>\n" + 
					"<p>Die Prüfungsanmeldung wurde erfolgreich durchgeführt.</p></br>\n" + 
					"<form action=\"../Prüfungsanmeldung\" method=\"get\">\n" +
					HtmlBuilder.createSubmitButton("Zurück zur Prüfungsanmeldung") +
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
