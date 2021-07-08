package com.xyueji.traffic.etl.application

import com.xyueji.traffic.core.Constants
import com.xyueji.traffic.core.framework.TApplication
import com.xyueji.traffic.etl.controller.HttpToFileController

/**
  * @author xiongzhigang
  * @date 2021-07-07 10:25
  * @description
  */
object HttpToFile extends App with TApplication {
  private val httpToFileController: HttpToFileController = new HttpToFileController

  start(Constants.FLINK_STREAM_NAME) {
    httpToFileController.execute()
  }
}
