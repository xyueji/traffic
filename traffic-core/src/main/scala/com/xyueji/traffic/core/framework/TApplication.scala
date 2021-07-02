package com.xyueji.traffic.core.framework

import java.net.{ServerSocket, Socket}

import com.xyueji.traffic.core.Constants
import com.xyueji.traffic.core.util.{EnvUtil, PropertiesUtil}
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment

/**
  * @author xiongzhigang
  * @date 2021-07-01 11:26
  * @description
  * * 主程序，是个特质，只需要传递执行的逻辑，获取环境和关闭环境自动完成
  * *
  * * 开发原则：OCP Open-Close(开闭原则)
  * *    open:开发的程序代码应该对功能扩展开放
  * *    close:在扩展的同时不应该对原有的代码进行修改
  */
trait TApplication {
  // 初始化环境
  var envData: Any = _

  /**
    * 启动应用
    * 1. 函数柯里化
    * 2. 控制抽象
    */
  def start (t: String)(op: => Unit): Unit = {
    // TODO 1. 初始化缓存
    t match {
      case Constants.SOCKET_NAME =>
        envData = new Socket(PropertiesUtil.getValue(Constants.SOCKET_SERVER_HOST), PropertiesUtil.getValue(Constants.SOCKET_SERVER_PORT).toInt)
      case Constants.SERVER_SOCKET_NAME =>
        envData = new ServerSocket(PropertiesUtil.getValue(Constants.SERVER_SOCKET_PORT).toInt)
      case Constants.SPARK_NAME =>
        envData = EnvUtil.getSparkEnv()
      case Constants.FLINK_NAME =>
        envData = StreamExecutionEnvironment.createLocalEnvironment()
    }

    // TODO 2. 业务逻辑
    try {
      op
    } catch {
      case ex: Exception => println("业务执行失败：" + ex.getMessage)
    }

    // TODO 3. 清除环境
    t match {
      case Constants.SOCKET_NAME =>
        val socket:Socket = envData.asInstanceOf[Socket]
        if (!socket.isClosed) {
          socket.close()
        }
      case Constants.SERVER_SOCKET_NAME =>
        val serverSocket: ServerSocket = envData.asInstanceOf[ServerSocket]
        if (!serverSocket.isClosed) {
          serverSocket.close()
        }
      case Constants.SPARK_NAME =>
        EnvUtil.cleanSparkEnv()
      case Constants.FLINK_NAME =>
        EnvUtil.cleanFlinkEnv()
    }
  }

}
