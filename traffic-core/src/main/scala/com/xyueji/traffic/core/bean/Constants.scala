package com.xyueji.traffic.core

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

  lazy val SOCKET_SERVER_HOST = "socket.server.host"
  lazy val SOCKET_SERVER_PORT = "socket.server.port"
  lazy val SERVER_SOCKET_PORT = "server.socket.port"
}
