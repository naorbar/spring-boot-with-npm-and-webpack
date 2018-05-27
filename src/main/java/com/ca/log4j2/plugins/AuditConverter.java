package com.ca.log4j2.plugins;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;

/**
 * This class overrides the log4j PatternLayout class in order to record log messages and audit them<BR>
 * Use it by declaring %audit pattern in the log4j2.xml file:<BR>
 * E.g. 
 * <pre>
	&ltConfiguration packages="com.ca.log4j2.plugins" monitorinterval="30" status="info" strict="true">
	    &ltProperties>
	        &ltProperty name="filename">log/app.log</Property>
	    &lt/Properties>
	    &ltAppenders>
	        &ltAppender type="Console" name="Console">
	            &ltPatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %C.%L - %msg%n" />
	        &lt/Appender>
	        &ltAppender type="Console" name="FLOW">
	            &ltLayout type="PatternLayout" pattern="%C{1}.%M %m %ex%n" />
	        &lt/Appender>
	        &ltAppender type="File" name="File" fileName="${filename}">
	        	&lt!-- Use %audit to invoke AuditConverter format method each time the logger is printing a message -->
	            &ltLayout type="PatternLayout" pattern="%audit %d %p %C.%L [%t] %m%n" />
	        &lt/Appender>
	    &lt/Appenders>
	    &ltLoggers>
	        &ltRoot level="info">
	            &ltAppenderRef ref="File" />
	            &ltAppenderRef ref="Console" level="error"/>
	            &lt!-- Use FLOW to trace down exact method sending the msg -->
	            &lt!-- <AppenderRef ref="FLOW" /> -->
	        &lt/Root>
	    &lt/Loggers>
	&lt/Configuration>
 * </pre> 
 * @author barna10
 * 
 */
@Plugin(name = "AuditConverter", category = "Converter")
@ConverterKeys({"audit"})
public class AuditConverter extends LogEventPatternConverter {
	
	// Audit Summary Map:
	// <Level, Counter>
	public static Map<Level, Integer> auditMap = new HashMap<Level, Integer>();

    protected AuditConverter() {
        super("AuditConverter", "AuditConverter");
    }
    
    public static AuditConverter newInstance() {
        return new AuditConverter();
      }

    @Override 
    public void format(LogEvent event, StringBuilder toAppendTo) {
    	if (event.getMessage() != null) {
			// Check the message level and update the audit object accordingly:
			if (!auditMap.containsKey(event.getLevel())) {
				auditMap.put(event.getLevel(), 1); 
			} else {
				int i = auditMap.get(event.getLevel());
				i++;
				auditMap.put(event.getLevel(), i); 
			}
		}
    }
}

