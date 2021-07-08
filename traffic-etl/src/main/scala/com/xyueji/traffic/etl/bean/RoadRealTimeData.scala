package com.xyueji.traffic.etl.bean

/**
  * @author xiongzhigang
  * @date 2021-07-07 11:01
  * @description 街道实时数据
  */
case class RoadRealTimeData (
                            TIME1: String, // 时间
                            EXPONENT: String, // 交通指数
                            PERIOD: String, // 时间片
                            GOLEN: String, // 通过样本总行驶长度(m)
                            BLOCKID: String, // 街道ID
                            SPEED: String, // 平均行程车速（km/h）
                            GOTIME: String // 通过样本总行程时间(s)
                            ) {
  override def toString: String = {
    if (this.TIME1 == null) {
      return null
    }
    (if(this.TIME1 == null) "" else this.TIME1).concat(",")
      .concat(if(this.EXPONENT == null) "" else this.EXPONENT).concat(",")
      .concat(if(this.PERIOD == null) "" else this.PERIOD).concat(",")
      .concat(if(this.GOLEN == null) "" else this.GOLEN).concat(",")
      .concat(if(this.BLOCKID == null) "" else this.BLOCKID).concat(",")
      .concat(if(this.SPEED == null) "" else this.SPEED).concat(",")
      .concat(if(this.GOTIME == null) "" else this.GOTIME)
  }
}
