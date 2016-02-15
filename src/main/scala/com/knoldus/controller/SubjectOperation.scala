package com.knoldus.controller

import java.sql.Connection

import com.knoldus.model.Subject

class SubjectOperation {

  /**
    *addSubject will add a subject in database
    * It will take object of Subject as a argument
    * It will return Boolean - True if success and false if fail
    */
  def addSubject(sub:Subject):Boolean= {
    val connection: Connection = DBController.connect(this.getClass.toString)
    val query: String = "insert into subject values (Null,'" + sub.name + "')"
    val res = connection.createStatement()
    val result = res.executeUpdate(query)
    result match {
      case 0 => false
      case _ => true
    }
  }

  /**
    * deleteSubject will delete a subject entry from database
    * It will take id as argument and return Boolean
    */
  def deleteSubject(id:Int):Boolean= {
    val connection: Connection = DBController.connect(this.getClass.toString)
    val query: String = "delete from subject where id = '" + id + "'"
    val res = connection.createStatement()
    val result = res.executeUpdate(query)
    result match {
      case 0 => false
      case _ => true
    }
  }

  /**
    *updateSubject will update the existing subject entry
    * it will take id and object of Subject as argument and return Boolean
    */
  def updateSubject(id:Int,sub:Subject):Boolean= {
    val connection: Connection = DBController.connect(this.getClass.toString)
    val query: String = "UPDATE subject SET name = '" + sub.name + "' where id = '" + id + "'"
    val res = connection.createStatement()
    val result = res.executeUpdate(query)
    result match {
      case 0 => false
      case _ => true
    }
  }

  /**
    * readSubject will fetch the result of particular ID
    * it will take id as argument and return the list of string
    */
  def readSubject(id:Int):List[String]= {
    val connection: Connection = DBController.connect(this.getClass.toString)
    val query: String = "select * from subject where id = " + id
    val statement = connection.createStatement()
    val resultSet = statement.executeQuery(query)
    val buf = scala.collection.mutable.ListBuffer[String]()
    while(resultSet.next()) {
      buf += resultSet.getString("id")
      buf += resultSet.getString("name")
    }
    buf.toList
  }

}
