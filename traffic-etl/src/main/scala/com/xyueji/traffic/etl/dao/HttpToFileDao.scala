package com.xyueji.traffic.etl.dao

import com.xyueji.traffic.core.Constants
import com.xyueji.traffic.core.framework.TDao
import com.xyueji.traffic.core.util.HttpUtil.doHttpRequest
import com.xyueji.traffic.core.util.JsonUtil
import com.xyueji.traffic.etl.bean.OpenDataResponse
import org.apache.http.HttpStatus

import scala.reflect._

/**
  * @author xiongzhigang
  * @date 2021-07-07 10:50
  * @description
  */
class HttpToFileDao extends TDao {
  def fetchData[T: ClassTag](url: String, page: String = Constants.PAGE, rows: String = Constants.ROWS): OpenDataResponse = {
    val method = Constants.HTTP_POST
    val appKey = Constants.OPEN_DATA_APP_KEY
    val params = Map("appKey" -> appKey, "page" -> page, "rows" -> rows)
    val headers = Map("Content-type" -> "application/json")

    val res = doHttpRequest(url, method, params, headers)

    if (res != null && res._1 == HttpStatus.SC_OK) {
      val openData = JsonUtil.fromJson[OpenDataResponse](res._2)
      if (openData != null) {
        openData.data = JsonUtil.fromJson[Array[T]](JsonUtil.toJson(openData.data))
      }
      return openData
    }
    null
  }
}
