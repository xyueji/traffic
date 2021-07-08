package com.xyueji.traffic.etl.bean

/**
  * @author xiongzhigang
  * @date 2021-07-07 11:13
  * @description 路段数据
  */
case class RoadSectionInfo(
                            TYPE: String, // 道路类型
                            ROADSECT_NAME: String, //道路名称
                            SECT10: String, // 行政区编号
                            SECT10_NAME: String, // 行政区名
                            ROADSECT_FROM: String, // 中路段道路起点名称
                            ROAD_NO: String, // 道路编号
                            ROADSECT_TO: String, // 中路段道路终点名称
                            ROADSECT_ID: String, // 中路段编号
                            ROADLENGTH: String, // 中路段长度
                            DIRNAME: String, // 方向名
                            JAM_SPEED: String // 拥堵车速阈值
                          ) {
  override def toString: String = {
    (if (this.TYPE == null) "" else this.TYPE).concat(",")
      .concat(if (this.ROADSECT_NAME == null) "" else this.ROADSECT_NAME).concat(",")
      .concat(if (this.SECT10 == null) "" else this.SECT10).concat(",")
      .concat(if (this.SECT10_NAME == null) "" else this.SECT10_NAME).concat(",")
      .concat(if (this.ROADSECT_FROM == null) "" else this.ROADSECT_FROM).concat(",")
      .concat(if (this.ROAD_NO == null) "" else this.ROAD_NO).concat(",")
      .concat(if (this.ROADSECT_TO == null) "" else this.ROADSECT_TO).concat(",")
      .concat(if (this.ROADSECT_ID == null) "" else this.ROADSECT_ID).concat(",")
      .concat(if (this.ROADLENGTH == null) "" else this.ROADLENGTH).concat(",")
      .concat(if (this.DIRNAME == null) "" else this.DIRNAME).concat(",")
      .concat(if (this.JAM_SPEED == null) "" else this.JAM_SPEED)
  }
}
