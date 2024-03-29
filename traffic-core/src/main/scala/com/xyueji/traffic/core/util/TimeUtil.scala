package com.xyueji.traffic.core.util

import scala.util.{Failure, Success, Try}
import scala.util.matching.Regex

object TimeUtil {

  private object Units {
    case class TimeUnit(name: String, shortName: String, ut: Long, regex: Regex) {
      def toMs(t: Long): Long = t * ut
      def fromMs(ms: Long): Long = ms / ut
      def fitUnit(ms: Long): Boolean = ms % ut == 0
    }

    val dayUnit: TimeUnit = TimeUnit("day", "d", 24 * 60 * 60 * 1000, """^(?i)d(?:ay)?$""".r)
    val hourUnit: TimeUnit = TimeUnit("hour", "h", 60 * 60 * 1000, """^(?i)h(?:our|r)?$""".r)
    val minUnit: TimeUnit = TimeUnit("minute", "m", 60 * 1000, """^(?i)m(?:in(?:ute)?)?$""".r)
    val secUnit: TimeUnit = TimeUnit("second", "s", 1000, """^(?i)s(?:ec(?:ond)?)?$""".r)
    val msUnit: TimeUnit =
      TimeUnit("millisecond", "ms", 1, """^(?i)m(?:illi)?s(?:ec(?:ond)?)?$""".r)

    val timeUnits: List[TimeUnit] = dayUnit :: hourUnit :: minUnit :: secUnit :: msUnit :: Nil
  }
  import Units._

//  final val TimeRegex = """^([+\-]?\d+)(ms|s|m|h|d)$""".r
  final val TimeRegex = """^([+\-]?\d+)([a-zA-Z]+)$""".r
  final val PureTimeRegex = """^([+\-]?\d+)$""".r

  def milliseconds(timeString: String): Option[Long] = {
    val value: Option[Long] = {
      Try {
        timeString match {
          case TimeRegex(time, unit) =>
            val t = time.toLong
            unit match {
              case dayUnit.regex() => dayUnit.toMs(t)
              case hourUnit.regex() => hourUnit.toMs(t)
              case minUnit.regex() => minUnit.toMs(t)
              case secUnit.regex() => secUnit.toMs(t)
              case msUnit.regex() => msUnit.toMs(t)
              case _ => throw new Exception(s"$timeString is invalid time format")
            }
          case PureTimeRegex(time) =>
            val t = time.toLong
            msUnit.toMs(t)
          case _ => throw new Exception(s"$timeString is invalid time format")
        }
      } match {
        case Success(v) => Some(v)
        case Failure(_) => None
      }
    }
    value
  }

  def timeToUnit(ms: Long, unit: String): Long = {
    unit match {
      case dayUnit.regex() => dayUnit.fromMs(ms)
      case hourUnit.regex() => hourUnit.fromMs(ms)
      case minUnit.regex() => minUnit.fromMs(ms)
      case secUnit.regex() => secUnit.fromMs(ms)
      case msUnit.regex() => msUnit.fromMs(ms)
      case _ => ms
    }
  }

  def timeFromUnit(t: Long, unit: String): Long = {
    unit match {
      case dayUnit.regex() => dayUnit.toMs(t)
      case hourUnit.regex() => hourUnit.toMs(t)
      case minUnit.regex() => minUnit.toMs(t)
      case secUnit.regex() => secUnit.toMs(t)
      case msUnit.regex() => msUnit.toMs(t)
      case _ => t
    }
  }

  def time2String(t: Long): String = {
    val matchedUnitOpt = timeUnits.foldLeft(None: Option[TimeUnit]) { (retOpt, unit) =>
      if (retOpt.isEmpty && unit.fitUnit(t)) Some(unit) else retOpt
    }
    val unit = matchedUnitOpt.getOrElse(msUnit)
    val unitTime = unit.fromMs(t)
    val unitStr = unit.shortName
    s"$unitTime$unitStr"
  }

}
