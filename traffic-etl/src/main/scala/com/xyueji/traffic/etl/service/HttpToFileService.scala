package com.xyueji.traffic.etl.service

import com.xyueji.traffic.core.Constants
import com.xyueji.traffic.core.framework.TService
import com.xyueji.traffic.core.util.EnvUtil
import com.xyueji.traffic.etl.bean.RoadObject
import org.apache.flink.api.common.serialization.SimpleStringEncoder
import org.apache.flink.core.fs.Path
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.DefaultRollingPolicy
import org.apache.flink.streaming.api.functions.sink.filesystem.{OutputFileConfig, StreamingFileSink}
import org.apache.flink.streaming.api.functions.source.SourceFunction

import scala.actors.threadpool.TimeUnit
import scala.reflect._

/**
  * @author xiongzhigang
  * @date 2021-07-07 10:49
  * @description
  */
class HttpToFileService extends TService {

  def dealData[T: ClassTag](url: String, path: String): Unit = {
    val env = EnvUtil.getFlinkStreamEnv().setParallelism(5)
    env.enableCheckpointing(TimeUnit.SECONDS.toMillis(10))

    // http source
    val dataStream = env.addSource(new SourceFunction[String] {
      private var page = Constants.PAGE
      private val rows = Constants.ROWS
      private var total = 0
      private var isRunning = true

      override def run(sourceContext: SourceFunction.SourceContext[String]): Unit = {
        while (isRunning) {
          val openDataResponse = RoadObject.HTTP_TO_FILE_DAO.fetchData[T](url, page, rows)
          if (openDataResponse != null && openDataResponse.data != null) {
            val openData = openDataResponse.data.asInstanceOf[Array[T]]
            openData.foreach(data => {
              if (data != null) {
                sourceContext.collect(data.toString)
              }
            })

            total += openData.length

            println("request: ".concat(url).concat(", total: ").concat(openDataResponse.total.toString).concat(", deal num: ").concat(total.toString))
            if (total >= openDataResponse.total) {
              isRunning = false
            }
            page = (page.toInt + 1).toString
          }
        }
      }

      override def cancel(): Unit = {
        isRunning = false
      }
    })

    // 输出文件名称设置
    val config = OutputFileConfig
      .builder()
      .withPartPrefix(path)
      .withPartSuffix(".txt")
      .build()

    // file sink
    val sink = StreamingFileSink.forRowFormat(new Path(path), new SimpleStringEncoder[String]("UTF-8"))
      .withRollingPolicy(
        DefaultRollingPolicy.builder()
          .withRolloverInterval(TimeUnit.MINUTES.toMinutes(15))
          .withInactivityInterval(TimeUnit.MINUTES.toMinutes(5))
          .withMaxPartSize(1024 * 1024 * 128)
          .build()
      ).withOutputFileConfig(config).build()

    dataStream.addSink(sink)
    env.execute()
  }
}
