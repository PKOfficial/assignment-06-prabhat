package com.knoldus.controller

import com.knoldus.model.Student
import org.scalatest.FunSuite


/**
  * Created by prabhat on 12/2/16.
  */
class StudentOperationTest extends FunSuite{

  val stuOper = new StudentOperation

  test("Read Student"){
    val list = stuOper.readStudent(5)
    assert(list === List("5", "Tarun Kashyap", "tk@kas.com", "882651608"))
  }

  test("Inserting Student"){
    val result = stuOper.addStudent(Student("Prabhat","prabhatkashyap@gmail.com","98776622"))
    assert(result)
  }

  test("Invaild Insert Student") {
    intercept[java.sql.SQLException] {
      stuOper.addStudent(Student("Prabhat Kashyap", "prabhatkashyap@gmail.com", "987sadasassaasdsa76622"))
    }
  }

  test("Delete Student"){
    val result = stuOper.deleteStudent(64)
    assert(result)
  }

  test("Delete Invalid Student"){
    val result = stuOper.deleteStudent(12)
    println(result)
    assert(!result)
  }
  test("Updating Student"){
    val result = stuOper.updateStudent(5,Student("Tarun Kashyap","tk@kas.com","882651608"))
    assert(result)
  }

  test("Updating Invalid Student"){
    intercept[com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException] {
      val result = stuOper.updateStudent(5, Student("TK", "tk@kas.com", "ThisIsNoMobile"))
      assert(!result)
    }
  }

}
