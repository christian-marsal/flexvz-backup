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

import flexvz.tools.*;
import flexvz.welcome.MainPage;

public class Pruefungsaenderung extends HttpServlet {

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
			
			// generate SQL statement //
			String strSelect = 	"select *\n" + 
								"from pruefungview";

			rset = stmt.executeQuery(strSelect);
			
			// Build HTML source //
			response.setContentType("text/html");
			
			StringBuilder sb = new StringBuilder();
			
			sb.append(HtmlBuilder.createDocumentHeader("Prüfungsänderung - FlexVZ")); 
			sb.append(
					"<h2>Prüfungsänderung</h2>\n" + 
					"<p>Wählen Sie einen Eintrag aus der folgenden Tabelle, den Sie ändern möchten:</p></br>\n" + 
					"<form action=\"./Prüfungsänderung/confirmation\" method=\"get\">\n");
			
			String[] columnNames = {"Veranstaltung", "LVNR", "Dozent", "Semester", "Art", "Prüfungsbeginn", "Prüfungsende", "Anmeldefrist", "Abmeldefrist"};
			sb.append(HtmlBuilder.createRadioTable(columnNames, rset));
			sb.append("<br>\n" + 
					"Prüfungsart:<br>          \n" + 
					"<input type=\"text\" name=\"pruefungsart\" required=\"required\" value=\"\">\n" + 
					"<br>\n" + 
					"Prüfungsbeginn:<br>           \n" + 
					"<input type=\"datetime-local\" name=\"pruefungsbeginn\" value=\"\">\n" + 
					"<br>\n" + 
					"Prüfungsende:<br>            \n" + 
					"<input type=\"datetime-local\" name=\"pruefungsende\" value=\"\">\n" + 
					"<br>\n" + 
					"Anmeldefrist:<br>            \n" + 
					"<input type=\"datetime-local\" name=\"anmeldefrist\" value=\"\">\n" + 
					"<br>\n" + 
					"Abmeldefrist:<br>           \n" + 
					"<input type=\"datetime-local\" name=\"abmeldefrist\" value=\"\">\n" +
					"<br><br>\n");
			if (rset.first()) {
				sb.append(
					HtmlBuilder.createSubmitButton("Prüfung ändern"));
			}
			sb.append(
					"</form>\n" +
					"</br>\n" +
					"</br>\n"+
					"<form action=\"./main\" method=\"get\">\n" + 
					HtmlBuilder.createSubmitButton("Zurück zur Übersicht") +
					"</form>\n");
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
