package com.xyueji.traffic.core

import com.xyueji.traffic.core.util.PropertiesUtil

/**
  * @author xiongzhigang
  * @date 2021-07-01 14:14
  * @description
  */
object Constants {
  // Application 参数类型：socket,serverSocket,spark,flink
  lazy val SOCKET_NAME = "Socket"
  lazy val SERVER_SOCKET_NAME = "ServerSocket"
  lazy val SPARK_NAME = "Spark"
  lazy val FLINK_NAME = "Flink"
  lazy val FLINK_STREAM_NAME = "FlinkStream"

  lazy val SOCKET_SERVER_HOST = PropertiesUtil.getValue("socket.server.host")
  lazy val SOCKET_SERVER_PORT = PropertiesUtil.getValue("socket.server.port")
  lazy val SERVER_SOCKET_PORT = PropertiesUtil.getValue("server.socket.port")

  lazy val OPEN_DATA_ROAD_REAL_TIME_URL = PropertiesUtil.getValue("opendata.road.real.time.url")
  lazy val OPEN_DATA_ROAD_INFO_URL = PropertiesUtil.getValue("opendata.road.info.url")
  lazy val OPEN_DATA_ROAD_SECT_SPEED_URL = PropertiesUtil.getValue("opendata.road.sect.speed.url")
  lazy val OPEN_DATA_ROAD_SECT_INFO_URL = PropertiesUtil.getValue("opendata.road.sect.info.url")
  lazy val OPEN_DATA_APP_KEY = PropertiesUtil.getValue("opendata.appkey")

  lazy val ROAD_REAL_TIME_FILE = PropertiesUtil.getValue("road.real.time.file")
  lazy val ROAD_INFO_FILE = PropertiesUtil.getValue("road.info.file")
  lazy val ROAD_SECT_SPEED_FILE = PropertiesUtil.getValue("road.sect.speed.file")
  lazy val ROAD_SECT_INFO_FILE = PropertiesUtil.getValue("road.sect.info.file")

  lazy val HTTP_POST = "post"
  lazy val HTTP_GET = "get"
  lazy val HTTP_PUT = "put"
  lazy val HTTP_DELETE = "delete"

  lazy val PAGE = "1"
  lazy val ROWS = "10000"

  lazy val SLASH = "/"
}
