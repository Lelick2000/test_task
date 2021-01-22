package aqa.framework.logger;

/**
 * This class is using for a creating extended log. It implements a Singleton pattern
 */
public final class Logger {

    private static final String DEF_LOCALE = "EN";
    private static final String AQA_LOCALE = "logger.lang";
    private static final String FORMATTED_TEMPLATE_WITH_HOOKS = "--------==[ %1$s ]==--------";
    private static final String FORMATTED_TEMPLATE = "=====================  %1$s: '%2$s' =====================";
    private static final org.apache.log4j.Logger LOG4J = org.apache.log4j.Logger.getLogger(Logger.class);
    private static final ThreadLocal<Logger> instance = new InheritableThreadLocal<>();
    // This flag allows/restricts logging step names
    private static final boolean logSteps = true;

    private Logger() {
    }


    /**
     * Implementation of the Singleton pattern
     *
     * @return aqa_logger.Logger instance
     */
    public static Logger getInstance() {
        Logger localInstance = instance.get();
        if (localInstance == null) {
            synchronized (Logger.class) {
                localInstance = instance.get();
                if (localInstance == null) {
                    instance.set(localInstance = new Logger());
                }
            }
        }
        return localInstance;
    }

    /**
     * Logging a step number
     *
     * @param step - step number
     */
    public void step(final int step) {
        logDelimMsg("loc.logger.step" + step);
    }

    /**
     * Logging a step number
     *
     * @param step - step number
     */
    public void step(final String step) {
        logDelimMsg("loc.logger.step " + step);
    }

    /**
     * Logging a several steps in a one action
     *
     * @param fromStep - the first step number to be logged
     * @param toStep   - the last step number to be logged
     */
    public void step(final int fromStep, final int toStep) {
        logDelimMsg("loc.logger.steps" + fromStep + "-" + toStep);
    }

    /**
     * This method is using for formatting almost all log records
     *
     * @param msg Formatted message
     */
    public void logDelimMsg(final String msg) {
        if (logSteps) {
            info(String.format(FORMATTED_TEMPLATE_WITH_HOOKS, msg));
        }
    }

    public void logDelimMsg(final String msg, final LogLevel logLevel) {
        if (logSteps) {
            switch (logLevel) {
                case INFO:
                    info(String.format(FORMATTED_TEMPLATE_WITH_HOOKS, msg));
                    break;
                case DEBUG:
                    debug(String.format(FORMATTED_TEMPLATE_WITH_HOOKS, msg));
                    break;
                case WARN:
                    warn(String.format(FORMATTED_TEMPLATE_WITH_HOOKS, msg));
                    break;
                case ERROR:
                    error(String.format(FORMATTED_TEMPLATE_WITH_HOOKS, msg));
                    break;
                default:
                    error("loc.aqa.logger.not_supported_log_level");
            }
        }
    }

    /**
     * This method logs test's name
     *
     * @param testName test's name
     */
    public void logTestName(final String testName) {
        if (logSteps) {
            String formattedName = String.format(FORMATTED_TEMPLATE, "loc.logger.test.case", testName);
            int nChars = formattedName.length();
            info(getDelimiter('-', nChars));
            info(formattedName);
            info(getDelimiter('-', nChars));
        }
    }

    /**
     * Returns string of N delimeters
     *
     * @param delimiter: character used as delimeter
     * @param count      Amount of delimeters
     */
    private String getDelimiter(char delimiter, int count) {
        StringBuilder delimiters = new StringBuilder();
        for (int i = 0; i < count; i++) {
            delimiters.append(delimiter);
        }
        return delimiters.toString();
    }

    /**
     * log Test End
     *
     * @param testName test's name
     */
    public void logTestEnd(final String testName, final String status) {
        if (logSteps) {
            info("");
            String formattedEnd = String.format("***** %1$s: '%2$s' %3$s! *****", "loc.logger.test.case", testName, status);
            int nChars = formattedEnd.length();
            info(getDelimiter('*', nChars));
            info(formattedEnd);
            info(getDelimiter('*', nChars));
            info("");
        }
    }

    /**
     * log test preconditions
     */
    public void logPreconditions() {
        if (logSteps) {
            logDelimMsg("loc.logger.test.preconditions");
        }
    }

    /**
     * Debug log
     *
     * @param message Message
     */
    public void debug(final String message) {
        LOG4J.debug(message);
    }

    public void debug(Object message) {
        LOG4J.debug(message);
    }

    public void debug(Object message, Throwable throwable) {
        LOG4J.debug(message, throwable);
    }

    public void info(Object message, Throwable throwable) {
        LOG4J.info(message, throwable);
    }

    public void info(Object message) {
        LOG4J.info(message);
    }

    /**
     * Info log
     *
     * @param message Message
     */
    public void info(final String message) {
        LOG4J.info(message);
    }

    /**
     * Warning log
     *
     * @param message Message
     */
    public void warn(final String message) {
        LOG4J.warn(message);
    }

    /**
     * Error log
     *
     * @param message Message
     */
    public void error(final String message) {
        LOG4J.error(message);
    }

    /**
     * Fatal log
     *
     * @param message   Message
     * @param throwable Throwable
     */
    public void fatal(final String message, Throwable throwable) {
        LOG4J.fatal(message + ": " + throwable.toString());
    }

    /**
     * Logging a step number and info message
     *
     * @param step    - step number
     * @param message - step number
     */
    public void step(final int step, final String message) {
        logDelimMsg(String.format("%s : %s", "loc.logger.step" + step, message));
    }

    /**
     * Logging a step number and info message
     *
     * @param step    - step number
     * @param message - step number
     */
    public void step(final String step, final String message) {
        logDelimMsg(String.format("%s : %s", "loc.logger.step" + step, message));
    }

    public void logFormattedMessage(String template, Object... args) {
        info(getFormattedMessage(template, args));
    }

    public String getFormattedMessage(String template, Object... args) {
        return String.format(template, args);
    }
}
