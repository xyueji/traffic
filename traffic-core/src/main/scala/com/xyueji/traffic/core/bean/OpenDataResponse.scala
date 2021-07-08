package com.xyueji.traffic.core.bean

/**
  * @author xiongzhigang
  * @date 2021-07-07 12:02
  * @description http返回值
  */
case class OpenDataResponse(
                             total: Int,
                             data: String,
                             page: Int,
                             rows: Int
                           )
