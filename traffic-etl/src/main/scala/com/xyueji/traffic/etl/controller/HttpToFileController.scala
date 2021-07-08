package com.xyueji.traffic.etl.controller

import com.xyueji.traffic.core.Constants
import com.xyueji.traffic.core.framework.TController
import com.xyueji.traffic.etl.bean.{RoadInfo, RoadRealTimeData, RoadSectionData, RoadSectionInfo}
import com.xyueji.traffic.etl.service.HttpToFileService

import scala.reflect.ClassTag

/**
  * @author xiongzhigang
  * @date 2021-07-07 10:48
  * @description
  */
class HttpToFileController extends TController{
  private val httpToFileService: HttpToFileService = new HttpToFileService

  def execute[T: ClassTag](url: String, path: String): Unit = {
    httpToFileService.dealData[T](url, path)
  }

  override def execute(): Unit = {
    // 街道信息
    execute[RoadInfo](Constants.OPEN_DATA_ROAD_INFO_URL, Constants.ROAD_INFO_FILE)

    // 街道实时数据
    execute[RoadRealTimeData](Constants.OPEN_DATA_ROAD_REAL_TIME_URL, Constants.ROAD_REAL_TIME_FILE)

    // 路段速度数据
    execute[RoadSectionData](Constants.OPEN_DATA_ROAD_SECT_SPEED_URL, Constants.ROAD_SECT_SPEED_FILE)

    // 路段数据
    execute[RoadSectionInfo](Constants.OPEN_DATA_ROAD_SECT_INFO_URL, Constants.ROAD_SECT_INFO_FILE)
  }
}
