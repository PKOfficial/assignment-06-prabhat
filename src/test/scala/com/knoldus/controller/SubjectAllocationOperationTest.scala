package com.knoldus.controller

import com.knoldus.model.Subject_Allocation
import org.scalatest.FunSuite

class SubjectAllocationOperationTest extends FunSuite{

  val allocator = new StudentAllocationOperation

  test("Fetch Data"){
    val result = allocator.readAllocatedSubject(41)
    assert(result == List("60", "Prabhat", "prabhatkashyap@gmail.com", "Scala"))
  }

  test("Add Data"){
      val result = allocator.allocateSubject(Subject_Allocation(5, 12))
      assert(result)
  }

  test("Add Duplicate Data") {
    intercept[com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException] {
      val result = allocator.allocateSubject(Subject_Allocation(5, 12))
      assert(result)
    }
  }

  test("Update Data"){
    val result = allocator.updateAllocatedSubject(27,Subject_Allocation(5,8))
    assert(result)
  }

  test("De allocation of subject"){
    val result = allocator.deallocateSubject(37)
    assert(result)
  }

}
