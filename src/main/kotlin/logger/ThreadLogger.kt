package logger

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object ThreadLogger {
  private val loggerThreadLocal: ThreadLocal<Logger> = ThreadLocal()

  fun getLogger(): Logger {
    var logger = loggerThreadLocal.get()
    if (logger == null) {
      logger = LogManager.getLogger(ThreadLogger.javaClass)//todo как инициализировать
      loggerThreadLocal.set(logger)
    }
    return logger
  }
}