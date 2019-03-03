package com.insign.clean.data

abstract class Mapper<FROM, TO> where FROM : Any, TO : Any {

  abstract fun toEntity(value: TO): FROM

  abstract fun fromEntity(value: FROM): TO

  fun toEntity(values: Collection<TO>): Collection<FROM> {
    val returnValues = ArrayList<FROM>(values.size)
    for (value in values) {
      if (value != null)
        returnValues.add(toEntity(value))
    }
    return returnValues
  }

  fun fromEntity(values: Collection<FROM>): Collection<TO> {
    val returnValues = ArrayList<TO>(values.size)
    for (value in values) {
      if (value != null)
        returnValues.add(fromEntity(value))
    }
    return returnValues
  }
}