package flexvz.tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Debugging {
	
	public static void logSQLException (SQLException e, String location) 
			throws IOException {
		Logger logger = Logger.getAnonymousLogger();
		FileHandler fh;
		SimpleFormatter formatter = new SimpleFormatter();
		
		fh = new FileHandler(location);  
        logger.addHandler(fh);
        fh.setFormatter(formatter);  
		logger.log(Level.SEVERE, "an exception was thrown", e);
	}
	
	public static void createLocalHtml(String name, StringBuilder sb)
			throws IOException {
		try {
			Files.write(Paths.get("../webapps/flexvz/log_files/"+name+".txt"), sb.toString().getBytes());
	
		} catch (IOException e) {
			Logger logger = Logger.getAnonymousLogger();
			FileHandler fh;
			SimpleFormatter formatter = new SimpleFormatter();
			
			fh = new FileHandler("../webapps/flexvz/log_files/"+name+"IOError.log");  
			logger.addHandler(fh);
			fh.setFormatter(formatter);  
			logger.log(Level.SEVERE, "an exception was thrown", e);
		}
	}
}
