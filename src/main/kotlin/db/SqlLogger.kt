package db

import org.apache.logging.log4j.LogManager
import org.jetbrains.exposed.sql.SqlLogger
import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.statements.StatementContext
import org.jetbrains.exposed.sql.statements.expandArgs

object SqlLogger : SqlLogger {
  private val logger = LogManager.getLogger(this.javaClass.simpleName)

  override fun log(context: StatementContext, transaction: Transaction) {
    logger.info(context.expandArgs(transaction))
  }
}