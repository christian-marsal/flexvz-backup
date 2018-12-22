package flexvz.welcome;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import flexvz.tools.HtmlBuilder;

public class MainPage extends HttpServlet {
	
	private static final long serialVersionUID = -629633472258359973L;
	public static String matrikelnummer;
	public static String status;
	public static Boolean userNotSet = true;
	public static Boolean debugMode = true;
    
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException { 
			
			// set variables //
			if (userNotSet) {
				matrikelnummer = request.getParameter("matrikelnummer");	
				status = request.getParameter("account");
				userNotSet = false;
			}
			
			// prepare if debug is on //
			if (debugMode) {
				new File("../webapps/flexvz/log_files").mkdirs();
			}
						
			// Build HTML source //
			response.setContentType("text/html");
			
			StringBuilder sb = new StringBuilder();
			sb.append(HtmlBuilder.createDocumentHeader("FlexVZ-Hauptseite"));
			
			switch (status) {
				case "Student":
					sb.append("<div id=\"content\">" +
							"<h2>FlexVZ-Hauptseite</h2> \r\n" + 
							"<h4>Aktueller Benutzer: "+MainPage.matrikelnummer+"</h4> \n" +
							"<p>Wählen sie die Aktion aus, welche sie durchführen möchten: </p> \r\n" + 
							"<form action=\"./Prüfungsanmeldung\"> \r\n" + 
							"	<input type=\"submit\" value=\"Prüfungsanmeldung\"> " +
							"</form> " +
							"<br> \r\n" + 
							"<form action=\"./Prüfungsabmeldung\"> \r\n" + 
							"	<input type=\"submit\" value=\"Prüfungsabmeldung\"> " +
							"</form> " +
							"<br> \r\n" + 
							"<form action=\"./Veranstaltungsanmeldung\"> \r\n" + 
							"	<input type=\"submit\" value=\"Veranstaltungsanmeldung\"> " +
							"</form> " +
							"<br> \r\n" + 
							"<form action=\"./Veranstaltungsabmeldung\"> \r\n" + 
							"	<input type=\"submit\" value=\"Veranstaltungsabmeldung\"> " +
							"</form> " +
							"<br> \r\n <br> \r\n" + 
							"<form action=\"./logout_successful\"> \r\n" + 
							"	<input type=\"submit\" value=\"Ausloggen\"> " +
							"</form> "
							+ "</div>");
					break;
				case "Dozent":
					sb.append("<h2>FlexVZ-Hauptseite</h2>\r\n" + 
							"<p>Wählen sie die Aktion aus, welche sie durchführen möchten: </p>\r\n" + 
							"\r\n" + 
							"<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
							"<tr>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Veranstaltungserstellung\">\r\n" + 
							"  <input type=\"submit\" value=\"Veranstaltungserstellung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Veranstaltungsänderung\">\r\n" + 
							"  <input type=\"submit\" value=\"Veranstaltungsaenderung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Veranstaltungslöschung\">\r\n" + 
							"  <input type=\"submit\" value=\"Veranstaltungsloeschung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"</tr>\r\n" + 
							"</table>\r\n" + 
							"\r\n" + 
							"<br>\r\n" + 
							"\r\n" + 
							"<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
							"<tr>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Terminerstellung\">\r\n" + 
							"  <input type=\"submit\" value=\"Terminerstellung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Terminänderung\">\r\n" + 
							"  <input type=\"submit\" value=\"Terminaenderung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Terminlöschung\">\r\n" + 
							"  <input type=\"submit\" value=\"Terminloeschung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"</tr>\r\n" + 
							"</table>\r\n" + 
							"\r\n" + 
							"<br>\r\n" + 
							"\r\n" + 
							"<form action=\"./Veranstaltungseinsicht\">\r\n" + 
							"  <input type=\"submit\" value=\"Veranstaltungseinsicht\">\r\n" + 
							"</form>");
					break;
				case "Admin":
					sb.append("<h2>FlexVZ-Hauptseite</h2>\r\n" + 
							"<p>Wählen sie die Aktion aus, welche sie durchführen möchten: </p>\r\n" + 
							"\r\n" + 
							"<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
							"<tr>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Prüfungsanmeldungserstellung\">\r\n" + 
							"  <input type=\"submit\" value=\"Pruefungsanmeldungserstellung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Prüfungsanmeldungsänderung\">\r\n" + 
							"  <input type=\"submit\" value=\"Pruefungsanmeldungsaenderung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Prüfungsanmeldungslöschung\">\r\n" + 
							"  <input type=\"submit\" value=\"Pruefungsanmeldungsloeschung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"</tr>\r\n" + 
							"</table>\r\n" + 
							"\r\n" + 
							"<br>\r\n" + 
							"\r\n" + 
							"<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
							"<tr>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Prüfungserstellung\">\r\n" + 
							"  <input type=\"submit\" value=\"Pruefungserstellung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Prüfungsänderung\">\r\n" + 
							"  <input type=\"submit\" value=\"Pruefungsänderung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Prüfungslöschung\">\r\n" + 
							"  <input type=\"submit\" value=\"Pruefungslöschung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"</tr>\r\n" + 
							"</table>\r\n" + 
							"\r\n" + 
							"<br>\r\n" + 
							"\r\n" + 
							"<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
							"<tr>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Modulerstellung\">\r\n" + 
							"  <input type=\"submit\" value=\"Modulerstellung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Moduländerung\">\r\n" + 
							"  <input type=\"submit\" value=\"Modulaenderung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Modullöschung\">\r\n" + 
							"  <input type=\"submit\" value=\"Modulloeschung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"</tr>\r\n" + 
							"</table>\r\n" + 
							"\r\n" + 
							"<br>\r\n" + 
							"\r\n" + 
							"<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
							"<tr>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Veranstaltungskonzepterstellung\">\r\n" + 
							"  <input type=\"submit\" value=\"Veranstaltungskonzepterstellung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Veranstaltungskonzeptänderung\">\r\n" + 
							"  <input type=\"submit\" value=\"Veranstaltungskonzeptaenderung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Veranstaltungskonzeptlöschung\">\r\n" + 
							"  <input type=\"submit\" value=\"Veranstaltungskonzeptloeschung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"</tr>\r\n" + 
							"</table>\r\n" + 
							"\r\n" + 
							"<br>\r\n" + 
							"\r\n" + 
							"<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
							"<tr>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Veranstaltungserstellung\">\r\n" + 
							"  <input type=\"submit\" value=\"Veranstaltungserstellung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Veranstaltungsänderung\">\r\n" + 
							"  <input type=\"submit\" value=\"Veranstaltungsaenderung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Veranstaltungslöschung\">\r\n" + 
							"  <input type=\"submit\" value=\"Veranstaltungsloeschung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"</tr>\r\n" + 
							"</table>\r\n" + 
							"\r\n" + 
							"<br>\r\n" + 
							"\r\n" + 
							"<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
							"<tr>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Terminerstellung\">\r\n" + 
							"  <input type=\"submit\" value=\"Terminerstellung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Terminänderung\">\r\n" + 
							"  <input type=\"submit\" value=\"Terminaenderung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Terminlöschung\">\r\n" + 
							"  <input type=\"submit\" value=\"Terminloeschung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"</tr>\r\n" + 
							"</table>\r\n" + 
							"\r\n" + 
							"<br>\r\n" + 
							"\r\n" + 
							"<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
							"<tr>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Usererstellung\">\r\n" + 
							"  <input type=\"submit\" value=\"Usererstellung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Useränderung\">\r\n" + 
							"  <input type=\"submit\" value=\"Useraenderung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Userlöschung\">\r\n" + 
							"  <input type=\"submit\" value=\"Userloeschung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"</tr>\r\n" + 
							"</table>\r\n" + 
							"\r\n" + 
							"<br>\r\n" + 
							"\r\n" + 
							"<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
							"<tr>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Studiengangerstellung\">\r\n" + 
							"  <input type=\"submit\" value=\"Studiengangerstellung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Studiengangänderung\">\r\n" + 
							"  <input type=\"submit\" value=\"Studiengangaenderung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"<td>\r\n" + 
							"<form action=\"./Studienganglöschung\">\r\n" + 
							"  <input type=\"submit\" value=\"Studiengangloeschung\">\r\n" + 
							"</form>\r\n" + 
							"</td>\r\n" + 
							"</tr>\r\n" + 
							"</table>\r\n");
					break;
				default:
					sb.append("<h2>Ungültige Anmeldung</h2>\n "
							+ "<p>Ihre Anmeldung war leider ungültig. Bitte geben sie ihren Status korrekt an.</p>\n");
			}		
			sb.append(HtmlBuilder.createDocumentFooter());
			
			// Send HTML source //
			PrintWriter out = response.getWriter();			
			out.print(sb.toString());
			out.flush();
			out.close();
		}
}