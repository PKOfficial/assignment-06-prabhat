package com.knoldus.controller

import com.knoldus.model.Subject
import org.scalatest.FunSuite

class SubjectOperationTest extends FunSuite{

  val subOperation = new SubjectOperation
  test("Read Subject"){
    val list = subOperation.readSubject(1)
    assert(list === List("1", "Scala"))
  }

  test("Add Subject"){
    val result = subOperation.addSubject(Subject("J2EE"))
    assert(result)
  }

  test("Delete Subject"){
    val result = subOperation.deleteSubject(20)
    assert(result)
  }

  test("Delete Invalid Subject"){
    val result = subOperation.deleteSubject(4)
    assert(!result)
  }

  test("Updating Subject"){
    val result = subOperation.updateSubject(5,Subject("Play"))
    assert(result)
  }

}
