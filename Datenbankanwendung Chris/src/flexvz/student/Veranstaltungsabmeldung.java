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

import flexvz.tools.Debugging;
import flexvz.tools.HtmlBuilder;
import flexvz.welcome.MainPage;

public class Veranstaltungsabmeldung extends HttpServlet {

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
			String strSelect = 	"SELECT INS_VERANSTALTUNG.ID, KON_VERANSTALTUNG.NAME, DOZENT.VORNAME, DOZENT.NACHNAME, INS_VERANSTALTUNG.LVNR, INS_VERANSTALTUNG.SEMESTER,  KON_VERANSTALTUNG.ART, KON_VERANSTALTUNG.SWS, KON_VERANSTALTUNG.MINTEILNEHMER,  KON_VERANSTALTUNG.ERWTEILNEHMER\n" + 
								"FROM KON_VERANSTALTUNG, INS_VERANSTALTUNG, DOZENT \n" + 
								"WHERE INS_VERANSTALTUNG.LVNR = KON_VERANSTALTUNG.LVNR\n" + 
								"AND DOZENT = DOZENT.ID\n" + 
								"AND INS_VERANSTALTUNG.ID IN (\n" + 
								"SELECT VERANSTALTUNGSID\n" + 
								"FROM TEILNEHMERLISTE\n" + 
								"WHERE STUDENTID = "+MainPage.matrikelnummer+"\n" + 
								")";

			rset = stmt.executeQuery(strSelect);
			
			// Build HTML source //
			response.setContentType("text/html");
			
			StringBuilder sb = new StringBuilder();
			
			sb.append(HtmlBuilder.createDocumentHeader("Veranstaltungsabmeldung - FlexVZ")); 
			sb.append(
					"<h2>Veranstaltungsabmeldung</h2>\n" + 
					"<p>Wähle einen Eintrag aus der folgenden Tabelle, für den du dich abmelden möchtest:</p></br>\n" + 
					"<form action=\"./Veranstaltungsabmeldung/confirmation\" method=\"get\">\n");
			
			String[] columnNames = {"Veranstaltung", "Dozent", "Veranstaltungsnummer", "Semester", "Art", "Semesterwochenstunden", "Mindestteilnehmerzahl", "Erwartete Teilnehmerzahl"};
			sb.append(HtmlBuilder.createRadioTable(columnNames, rset));
			if (rset.first()) {
				sb.append(
					HtmlBuilder.createSubmitButton("Veranstaltung abmelden"));
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
