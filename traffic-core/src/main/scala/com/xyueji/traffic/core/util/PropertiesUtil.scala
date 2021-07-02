package com.xyueji.traffic.core.util

import java.util.ResourceBundle

/**
  * @author xiongzhigang
  * @date 2021-07-01 11:26
  * @description
  */
object PropertiesUtil {
  // 绑定配置文件
  val config: ResourceBundle = ResourceBundle.getBundle("config")

  def getValue(key: String): String = {
    config.getString(key)
  }
}
