package com.br.luizalabs.api.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

	private static final Logger logger = LoggerFactory.getLogger(Thread.currentThread().getName());

	public static void info(String format, Object...arguments) {
		logger.info(format, arguments);
	}
	
	public static void error(String format, Object...arguments) {
		logger.error(format, arguments);
	}
	
	public static void error(String msg, Throwable t) {
		logger.error(msg, t);
	}

	public static void debug(String format, Object...arguments) {
		logger.debug(format, arguments);
	}
	
	public static void warn(String format, Object...arguments) {
		logger.warn(format, arguments);
	}
}
