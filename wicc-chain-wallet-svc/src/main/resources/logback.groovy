import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.classic.filter.LevelFilter
import ch.qos.logback.core.spi.FilterReply
import ch.qos.logback.core.util.FileSize

import java.nio.charset.Charset

import static ch.qos.logback.classic.Level.DEBUG

def logname = "chain-svc"
def env = Environment

statusListener(OnConsoleStatusListener)

appender('CONSOLE', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = '%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n'
        charset = Charset.forName("UTF-8")
    }
}

appender('FILE', RollingFileAppender) {
    file = "/tmp/wicc/logs/${logname}.log"
    encoder(PatternLayoutEncoder) {
        pattern = '%d{yyyy-MM-dd HH:mm:ss.SSS} %level %thread %mdc %logger - %m%n'
        charset = Charset.forName("UTF-8")
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "/tmp/wicc/logs/back/${logname}.%d{yyyy-MM-dd}-%i.log"
        maxHistory = 15
        timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
            maxFileSize = FileSize.valueOf("300mb")
        }
    }
}

appender("ERROR_FILE", RollingFileAppender) {
    file = "/tmp/wicc/logs/${logname}-error.log"
    encoder(PatternLayoutEncoder) {
        pattern = '%d{yyyy-MM-dd HH:mm:ss.SSS} %level %thread %mdc %logger - %m%n'
        charset = Charset.forName("UTF-8")
    }
    rollingPolicy(TimeBasedRollingPolicy) {
        fileNamePattern = "/tmp/wicc/logs/back/${logname}-error.%d{yyyy-MM-dd}-%i.log"
        maxHistory = 15
        timeBasedFileNamingAndTriggeringPolicy(SizeAndTimeBasedFNATP) {
            maxFileSize = FileSize.valueOf("300mb")
        }
    }
    filter(LevelFilter) {
        level = ERROR
        onMatch = FilterReply.ACCEPT
        onMismatch = FilterReply.DENY
    }
}

logger("druid.sql.Statement", WARN)
logger("druid.sql.Connection", WARN)
logger("com.querydsl.jpa.impl", WARN)
logger("com.waykichain.message.MessageContainer", WARN)
logger("org.mongodb.driver.cluster", WARN)
logger("org.mongodb.driver.connection", WARN)
logger("o.s.a.r.l.BlockingQueueConsumer", WARN)

if (env == null || "dev".equalsIgnoreCase(env)) {
    root(DEBUG, ["CONSOLE", "FILE", "ERROR_FILE"])
} else {
    root(DEBUG, ["CONSOLE", "FILE", "ERROR_FILE"])
}

