package com.knoldus.controller

import java.sql._
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object DBController {

  /**
    *connect() will use to make connection with database
    */
  def connect(cls:String):Connection={
    val logger:Logger = LoggerFactory.getLogger(this.getClass)
    val driver:String = "com.mysql.jdbc.Driver"
    val path:String = "jdbc:mysql://localhost/studentdb"
    val username:String = "root"
    val password:String = "knoldus"

    try {
      Class.forName(driver)
      val connection: Connection = DriverManager.getConnection(path, username, password)
      logger.info("Connected to Database for " + cls)
      connection
    }
    finally{
      logger.info("Finally Run DataBase")
    }
  }

}
