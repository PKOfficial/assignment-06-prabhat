package com.knoldus.controller

import java.sql.Connection

import com.knoldus.model.Subject_Allocation

class StudentAllocationOperation {

  /**
    *allocateSubject will allocate a subject to a student
    * It will take object of Subject_Allocation as a argument
    * It will return Boolean - True if success and false if fail
    */
  def allocateSubject(sub:Subject_Allocation):Boolean= {
    val connection: Connection = DBController.connect(this.getClass.toString)
    val query: String = "insert into subject_allocation values (Null,'" + sub.stuId + "','" + sub.subId + "')"
    val res = connection.createStatement()
    val result = res.executeUpdate(query)
    result match {
      case 0 => false
      case _ => true
    }
  }

  /**
    * deallocateSubject will remove entry/ deallocate subject for any student
    * It will take id as argument and return boolean value
    */
  def deallocateSubject(id:Int):Boolean= {
    val connection: Connection = DBController.connect(this.getClass.toString)
    val query: String = "delete from subject_allocation where id = '" + id + "'"
    val res = connection.createStatement()
    val result = res.executeUpdate(query)
    result match {
      case 0 => false
      case _ => true
    }
  }

  /**
    * updateAllocatedSubject will update the existing records
    * It will take id, and object of Student Allocation as argument
    * It will return Boolean
    */
  def updateAllocatedSubject(id:Int,stu:Subject_Allocation):Boolean= {
    val connection: Connection = DBController.connect(this.getClass.toString)
    val query: String = "UPDATE subject_allocation SET student_id = '" + stu.stuId + "', subject_id = '" + stu.subId + "' where id = '" + id + "'"
    val res = connection.createStatement()
    val result = res.executeUpdate(query)
    result match {
      case 0 => false
      case _ => true
    }
  }

  /**
    *readAllocatedSubject will fetch the record
    * it will take id as argument and return List of string
    */
  def readAllocatedSubject(id:Int):List[String]={
    val connection: Connection = DBController.connect(this.getClass.toString)
    val query: String = "select student.id,student.name,student.email,subject.name " +
      "from subject_allocation,subject,student where subject_allocation.subject_id = subject.id" +
      " AND subject_allocation.student_id = student.id AND subject_allocation.id = " + id
    val statement = connection.createStatement()
    val resultSet = statement.executeQuery(query)
    val buf = scala.collection.mutable.ListBuffer[String]()
    while(resultSet.next()) {
      buf += resultSet.getString("student.id")
      buf += resultSet.getString("student.name")
      buf += resultSet.getString("student.email")
      buf += resultSet.getString("subject.name")
    }
    buf.toList
  }

}
