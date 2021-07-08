package com.xyueji.traffic.etl.bean

/**
  * @author xiongzhigang
  * @date 2021-07-07 11:06
  * @description 街道信息
  */
case class RoadInfo(
                     ID: String, // id
                     QS: String, // 区属
                     LX: String, // 类型
                     DQD: String, // 段起点
                     DZD: String, // 段止点
                     DJ: String, // 等级
                     CD: String, // 长度
                     LDMJ: String, // 路段面积
                     RXDMJ: String, // 人行道面积
                     LM: String, // 路名
                     SJC: String // 时间戳
                   ) {
  override def toString: String = {
    (if(this.ID == null)  "" else this.ID).concat(",")
      .concat(if(this.QS == null)  "" else this.QS).concat(",")
      .concat(if(this.LX == null)  "" else this.LX).concat(",")
      .concat(if(this.DQD == null)  "" else this.DQD).concat(",")
      .concat(if(this.DZD == null)  "" else this.DZD).concat(",")
      .concat(if(this.DJ == null)  "" else this.DJ).concat(",")
      .concat(if(this.CD == null)  "" else this.CD).concat(",")
      .concat(if(this.LDMJ == null)  "" else this.LDMJ).concat(",")
      .concat(if(this.RXDMJ == null)  "" else this.RXDMJ).concat(",")
      .concat(if(this.LM == null)  "" else this.LM).concat(",")
      .concat(if(this.SJC == null)  "" else this.SJC)
  }
}
