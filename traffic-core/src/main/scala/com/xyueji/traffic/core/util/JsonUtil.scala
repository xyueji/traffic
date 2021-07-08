package com.xyueji.traffic.core.util

import java.io.InputStream

import com.fasterxml.jackson.databind.{DeserializationFeature, ObjectMapper, SerializationFeature}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.xyueji.traffic.core.bean.OpenDataResponse
import org.apache.commons.lang.StringEscapeUtils

import scala.reflect._

object JsonUtil {
  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)
  mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
  mapper.configure(SerializationFeature.INDENT_OUTPUT, true)

  def toJson(value: Map[Symbol, Any]): String = {
    toJson(value map { case (k, v) => k.name -> v })
  }

  def toJson(value: Any): String = {
    StringEscapeUtils.unescapeJava(mapper.writeValueAsString(value))
  }

  def fromJson[T: ClassTag](json: String): T = {
    mapper.readValue[T](json, classTag[T].runtimeClass.asInstanceOf[Class[T]])
  }

  def fromJson[T: ClassTag](is: InputStream): T = {
    mapper.readValue[T](is, classTag[T].runtimeClass.asInstanceOf[Class[T]])
  }

  def toAnyMap(json: String): Map[String, Any] = {
    mapper.readValue(json, classOf[Map[String, Any]])
  }
}
