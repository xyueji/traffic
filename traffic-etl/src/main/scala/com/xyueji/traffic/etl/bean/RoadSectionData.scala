package com.xyueji.traffic.etl.bean

/**
  * @author xiongzhigang
  * @date 2021-07-07 11:11
  * @description 路段速度数据
  */
case class RoadSectionData(
                            ROADSECT_ID: String, // 中路段id
                            GOTIME: String, // 总行驶时间（s）
                            GOCOUNT: String, // 总样本车辆数
                            TIME: String, // 日期
                            GOLEN: String, // 该路段上车辆总行驶距离(m)
                            PERIOD: String // 时间片
                          ) {
  override def toString: String = {
    (if (this.ROADSECT_ID == null) "" else this.ROADSECT_ID).concat(",")
      .concat(if (this.GOTIME == null) "" else this.GOTIME).concat(",")
      .concat(if (this.GOCOUNT == null) "" else this.GOCOUNT).concat(",")
      .concat(if (this.TIME == null) "" else this.TIME).concat(",")
      .concat(if (this.GOLEN == null) "" else this.GOLEN).concat(",")
      .concat(if (this.PERIOD == null) "" else this.PERIOD)
  }
}
