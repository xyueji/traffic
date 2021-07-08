package com.xyueji.traffic.etl.bean

/**
  * @author xiongzhigang
  * @date 2021-07-07 12:02
  * @description http返回值
  */
case class OpenDataResponse(
                             total: Int,
                             var data: Any,
                             page: Int,
                             rows: Int
                           )
