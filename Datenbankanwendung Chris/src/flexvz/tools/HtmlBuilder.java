package flexvz.tools;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HtmlBuilder {
	
	public static String createDocumentHeader(String title) {
		StringBuilder sb = new StringBuilder();
		sb.append("<!DOCTYPE HTML PUBLIC ").append(
							   "\"-//W3C//DTD HTML 4.0 Transitional//EN\">\n");
		sb.append("<html>\n<head><title>").append(title).append(
									"</title>\r\n" +
									"<style>\r\n" + 
									// content //
									"#content {" +
									"padding: 10px;" +
									"}" +
									// flextable //
									"#flextable {\r\n" + 
									"  font-family: \"Trebuchet MS\", Arial, Helvetica, sans-serif;\r\n" + 
									"  border-collapse: collapse;\r\n" + 
									"  width: 100%;\r\n" + 
									"}\r\n" + 
									"\r\n" + 
									"#flextable td, #flextable th {\r\n" + 
									"  border: 1px solid #ddd;\r\n" + 
									"  padding: 8px;\r\n" + 
									"}\r\n" + 
									"\r\n" + 
									"#flextable tr:nth-child(even){background-color: #f2f2f2;}\r\n" + 
									"\r\n" + 
									"#flextable tr:hover {background-color: #ddd;}\r\n" + 
									"\r\n" + 
									"#flextable th {\r\n" + 
									"  padding-top: 12px;\r\n" + 
									"  padding-bottom: 12px;\r\n" + 
									"  text-align: left;\r\n" + 
									"  background-color: #4CAF50;\r\n" + 
									"  color: white;\r\n" + 
									"}\r\n" + 
									"</style>" +
									// style sheets //
									"<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\r\n" + 
									"<link rel=\"stylesheet\" href=\"https://www.w3schools.com/lib/w3-theme-black.css\">\r\n" + 
									"<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\r\n" + 
									"</head>\n").append("<body>\r\n" +
									// Nav Bar //
									"<div class=\"w3-top\">\r\n" + 
									" <div class=\"w3-bar w3-theme-d2 w3-left-align\">\r\n" + 
									"  <a class=\"w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-hover-white w3-theme-d2\" href=\"javascript:void(0);\" onclick=\"openNav()\"><i class=\"fa fa-bars\"></i></a>\r\n" + 
									"  <a href=\"#\" class=\"w3-bar-item w3-button w3-teal\"><i class=\"fa fa-home w3-margin-right\"></i>Logo</a>\r\n" + 
									"  <a href=\"#team\" class=\"w3-bar-item w3-button w3-hide-small w3-hover-white\">Team</a>\r\n" + 
									"  <a href=\"#work\" class=\"w3-bar-item w3-button w3-hide-small w3-hover-white\">Work</a>\r\n" + 
									"  <a href=\"#pricing\" class=\"w3-bar-item w3-button w3-hide-small w3-hover-white\">Price</a>\r\n" + 
									"  <a href=\"#contact\" class=\"w3-bar-item w3-button w3-hide-small w3-hover-white\">Contact</a>\r\n" + 
									"    <div class=\"w3-dropdown-hover w3-hide-small\">\r\n" + 
									"    <button class=\"w3-button\" title=\"Notifications\">Dropdown <i class=\"fa fa-caret-down\"></i></button>     \r\n" + 
									"    <div class=\"w3-dropdown-content w3-card-4 w3-bar-block\">\r\n" + 
									"      <a href=\"#\" class=\"w3-bar-item w3-button\">Link</a>\r\n" + 
									"      <a href=\"#\" class=\"w3-bar-item w3-button\">Link</a>\r\n" + 
									"      <a href=\"#\" class=\"w3-bar-item w3-button\">Link</a>\r\n" + 
									"    </div>\r\n" + 
									"  </div>\r\n" + 
									"  <a href=\"#\" class=\"w3-bar-item w3-button w3-hide-small w3-right w3-hover-teal\" title=\"Search\"><i class=\"fa fa-search\"></i></a>\r\n" + 
									" </div>");
	
		return sb.toString();
    }
	
	public static String createDocumentFooter() {
    	return "</body>\n</html>\n";
    }
	
	public static String convertDate(String s) {
		if ((s == null) || (s.length()!=16	)) {
			return null;
		}
		String result = s.substring(0, 10)+" "+s.substring(11, 16)+":00";
		result = "(TO_DATE('"+result+"', 'YYYY-MM-DD HH24:MI:SS'))";
		return result;
	}
	
	/* Creates an HTML-table with a given resultset
	 * @param columnNames Headline for each column - has to align with the order specified in rset
	 * @param formActionName Name (URL) for the formaction that is performed upon submitting the clicked entry
	 * @param submitButtonName Name displayed on the button for form submission
	 * @param rset Resultset providing the information, the ID must be in column 1 and is not included in the table
	 */
	public static StringBuilder createRadioTable(String[] columnNames, ResultSet rset) 
			throws SQLException {
		StringBuilder sb = new StringBuilder();
		// table header //
		sb.append(
				"<fieldset>\n" + 
				"	<table id=\"flextable\" class=\"table table-responsive\">\n" + 
				"	<thead>\n" + 
				"		<tr>\n");
		// table headlines //
		for ( int i = 0; i<columnNames.length; i++ ) {
			sb.append("			<th>"+columnNames[i]+"</th>\n");
		}
		sb.append(
				"		</tr>\n" + 
				"	</thead>\n" + 
				"	<tbody>\n");
		int rowCount = 0;
		// table data //
		while(rset.next()) {
			rowCount++;
			for ( int i = 2; i<columnNames.length+2; i++ ) {
				
				// create column 1 //
				if (i == 2) {
					sb.append(
							"		<tr>\n" + 
							"			<td>\n" + 
							"				<div class=\"radio\">\n");
					// set "checked" //
					if (rowCount == 1) {
						sb.append(
								"					<label><input type=\"radio\" id=\""+rset.getString(1)+"\" name=\"pruefungsid\" value=\""+rset.getString(1)+"\" checked=\"checked\">"+rset.getString(i)+"</label>\n");
					}
					else {
						sb.append(
								"					<label><input type=\"radio\" id=\""+rset.getString(1)+"\" name=\"pruefungsid\" value=\""+rset.getString(1)+"\">"+rset.getString(i)+"</label>\n");
					}
					sb.append(
							"				</div>\n" + 
							"			</td>\n");
				}
				
				// create additional columns //
				else {
					sb.append(
							"			<td>\n" + 
							"				<div class=\"radiotext\">\n" + 
							"					<label for=\""+rset.getString(1)+"\">"+rset.getString(i)+"</label>\n" + 
							"				</div>\n" + 
							"			</td>\n");
				}
			}
			sb.append(
					"		</tr>");
		}
		// table footer //
		sb.append(
				"	</tbody>\n" +
				"	</table>\n" +
				"</fieldset></br>\n");
		return sb;
	}
	
	public static StringBuilder createSubmitButton(String value) {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"<input type=\"submit\" value=\""+value+"\"/>\n");
		return sb;
	}
}
