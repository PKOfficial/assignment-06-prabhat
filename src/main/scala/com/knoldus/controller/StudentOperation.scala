package com.knoldus.controller

import com.knoldus.model.Student

import java.sql._

class StudentOperation {

  /**
    *addStudent will add a student in database
    * It will take object of Student as a argument
    * It will return Boolean - True if success and false if fail
    */
  def addStudent(stu:Student):Boolean= {
    val connection: Connection = DBController.connect(this.getClass.toString)
    val query: String = "insert into student values (Null,'" + stu.name + "','" + stu.email + "','" + stu.mobile + "')"
    val res = connection.createStatement()
    val result = res.executeUpdate(query)
    result match {
      case 0 => false
      case _ => true
    }
  }

  /**
    * deleteStudent will delete a student entry from database
    * It will take id as argument and return Boolean
    */
  def deleteStudent(id:Int):Boolean= {
    val connection: Connection = DBController.connect(this.getClass.toString)
    val query: String = "delete from student where id = '" + id + "'"
    val res = connection.createStatement()
    val result = res.executeUpdate(query)
    result match {
      case 0 => false
      case _ => true
    }
  }

  /**
    *updateStudent will update the existing student entry
    * it will take id and object of Student as argument and return Boolean
    */
  def updateStudent(id:Int,stu:Student):Boolean={
      val connection: Connection = DBController.connect(this.getClass.toString)
      val query: String = "UPDATE student SET name = '" + stu.name + "', email = '" + stu.email + "', mobile_no = " + stu.mobile + " where id = '" + id + "'"
      val res = connection.createStatement()
      val result = res.executeUpdate(query)
      result match {
        case 0 => false
        case _ => true
      }
  }

  /**
    * readStudent will fetch the result of particular ID
    * it will take id as argument and return the list of string
    */
  def readStudent(id:Int):List[String]= {
    val connection: Connection = DBController.connect(this.getClass.toString)
    val query: String = "select * from student where id = " + id
    val statement = connection.createStatement()
    val resultSet = statement.executeQuery(query)
    val buf = scala.collection.mutable.ListBuffer[String]()
    while(resultSet.next()) {
      buf += resultSet.getString("id")
      buf += resultSet.getString("name")
      buf += resultSet.getString("email")
      buf += resultSet.getString("mobile_no")
    }
    buf.toList
  }

}
