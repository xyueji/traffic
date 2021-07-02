package com.xyueji.traffic.core.util

import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author xiongzhigang
  * @date 2021-07-01 11:25
  * @description
  */
object EnvUtil {
  // 创建spark线程共享数据
  private val scLocal = new ThreadLocal[SparkContext]
  // 构建flink线程共享数据
  private val seeLocal = new ThreadLocal[StreamExecutionEnvironment]

  /**
    * 获取spark执行环境
    */
  def getSparkEnv(conf: SparkConf = null): SparkContext = {
    // 从当前线程中获取spark环境对象
    var sc: SparkContext = scLocal.get()
    if (sc == null) {
      if (conf == null) {
        val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkApplication")
        sc = new SparkContext(sparkConf)
      } else {
        sc = new SparkContext(conf)
      }
      scLocal.set(sc)
    }

    sc
  }

  /**
    * 清除spark执行环境
    */
  def cleanSparkEnv(): Unit = {
    getSparkEnv().stop()
    scLocal.remove()
  }

  /**
    * 获取flink执行环境
    */
  def getFlinkEnv(parallelism: Int = Runtime.getRuntime.availableProcessors, conf: Configuration = new Configuration): StreamExecutionEnvironment = {
    var see:StreamExecutionEnvironment = seeLocal.get()

    if (see == null) {
      see = StreamExecutionEnvironment.createLocalEnvironment(parallelism, conf)
      seeLocal.set(see)
    }

    see
  }

  /**
    * 清除flink执行环境
    */
  def cleanFlinkEnv(): Unit = {
    seeLocal.remove()
  }

}
