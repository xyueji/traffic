package com.xyueji.traffic.core.util

import com.xyueji.traffic.core.Constants
import org.apache.http.client.methods.{HttpDelete, HttpGet, HttpPost, HttpPut}
import org.apache.http.client.utils.URIBuilder
import org.apache.http.entity.{ContentType, StringEntity}
import org.apache.http.impl.client.{BasicResponseHandler, HttpClientBuilder}

object HttpUtil {
  def doHttpRequest(
                     url: String,
                     method: String,
                     params: Map[String, Object],
                     headers: Map[String, Object],
                     data: String = ""): (Integer, String) = {
    val client = HttpClientBuilder.create.build
    val uriBuilder = new URIBuilder(url)
    convertObjMap2StrMap(params) foreach (param => uriBuilder.setParameter(param._1, param._2))
    val handler = new BasicResponseHandler()
    val request = method match {
      case Constants.HTTP_POST =>
        val post = new HttpPost(uriBuilder.build())
        post.setEntity(new StringEntity(data, ContentType.APPLICATION_JSON))
        post
      case Constants.HTTP_PUT =>
        val put = new HttpPut(uriBuilder.build())
        put.setEntity(new StringEntity(data, ContentType.APPLICATION_JSON))
        put
      case Constants.HTTP_GET =>
        new HttpGet(uriBuilder.build())
      case Constants.HTTP_DELETE =>
        new HttpDelete(uriBuilder.build())
      case _ => throw new UnsupportedOperationException("Unsupported http method error!")
    }
    convertObjMap2StrMap(headers) foreach (header => request.addHeader(header._1, header._2))
    val response = client.execute(request)
    (response.getStatusLine.getStatusCode, handler.handleResponse(response).trim)
  }

  private def convertObjMap2StrMap(map: Map[String, Object]): Map[String, String] = {
    map.map(pair => pair._1 -> pair._2.toString)
  }

  def main(args: Array[String]): Unit = {
    val url = Constants.OPEN_DATA_ROAD_INFO_URL
    val method = Constants.HTTP_POST
    val appKey = Constants.OPEN_DATA_APP_KEY
    val params = Map("appKey" -> appKey, "page" -> "1", "rows" -> "10")
    val headers = Map("Content-type" -> "application/json")

    val res = doHttpRequest(url, method, params, headers)

    println(res._2)
  }
}
