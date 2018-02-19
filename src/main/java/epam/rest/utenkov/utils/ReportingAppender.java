package epam.rest.utenkov.utils;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

public class ReportingAppender extends AppenderSkeleton {

	public void close() {
		// TODO Auto-generated method stub
		
	}

	public boolean requiresLayout() {
		return true;
	}

	@Override
	protected void append(LoggingEvent arg0) {
		String message = (this.name + ": " + this.layout.format(arg0)).replaceAll("\n", "<br>");
		Reporter.log(message);
		
	}

}
