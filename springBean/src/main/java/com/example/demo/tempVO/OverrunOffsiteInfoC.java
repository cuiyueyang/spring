package com.example.demo.tempVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 
 * @Author cuiyy 
 * @Date 2022-05-10 
 */

@Entity
@Data
@ApiModel(value = "Entity Of overrun_offsite_info_c")
@Table ( name ="overrun_offsite_info_c" )
public class OverrunOffsiteInfoC implements Serializable {

	private static final long serialVersionUID = 1082402063558085196L;

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
   	@ApiModelProperty(value = "ID")
	private String id;

	/**
	 * 检测单号
	 */
   	@ApiModelProperty(value = "检测单号")
	private String checkNo;

	/**
	 * 车牌号
	 */
   	@ApiModelProperty(value = "车牌号")
	private String vehicleId;

	/**
	 * 轴数
	 */
   	@ApiModelProperty(value = "轴数")
	private String axleSum;

	/**
	 * 总质量(单位：千克)
	 */
   	@ApiModelProperty(value = "总质量(单位：千克)")
	private String totalMass;

	/**
	 * 核定载质量(单位：千克)
	 */
   	@ApiModelProperty(value = "核定载质量(单位：千克)")
	private String ratedLoading;

	/**
	 * 超限量（单位：千克）按照轴数计算超限量，2轴18000,3轴27000,4轴36000,5轴43000,6轴49000
	 */
   	@ApiModelProperty(value = "超限量（单位：千克）按照轴数计算超限量，2轴18000,3轴27000,4轴36000,5轴43000,6轴49000")
	private String overLimited;

	/**
	 * 超载量
	 */
   	@ApiModelProperty(value = "超载量")
	private String overLoad;

	/**
	 * 超限率
	 */
   	@ApiModelProperty(value = "超限率")
	private String overLimitedRate;

	/**
	 * 是否超限   0：未超限  1：超限
	 */
   	@ApiModelProperty(value = "是否超限   0：未超限  1：超限")
	private String isLimited;

	/**
	 * 检测时间
	 */
   	@ApiModelProperty(value = "检测时间")
	private LocalDateTime offsiteTime;

	/**
	 * 车道
	 */
   	@ApiModelProperty(value = "车道")
	private String laneNo;

	/**
	 * 车货总重（单位：千克）
	 */
   	@ApiModelProperty(value = "车货总重（单位：千克）")
	private String totalWeight;

	/**
	 * 站点IP
	 */
   	@ApiModelProperty(value = "站点IP")
	private String stationIp;

	/**
	 * 分轴重1
	 */
   	@ApiModelProperty(value = "分轴重1")
	private String axle1;

	/**
	 * 分轴重2
	 */
   	@ApiModelProperty(value = "分轴重2")
	private String axle2;

	/**
	 * 分轴重3
	 */
   	@ApiModelProperty(value = "分轴重3")
	private String axle3;

	/**
	 * 分轴重4
	 */
   	@ApiModelProperty(value = "分轴重4")
	private String axle4;

	/**
	 * 分轴重5
	 */
   	@ApiModelProperty(value = "分轴重5")
	private String axle5;

	/**
	 * 分轴重6
	 */
   	@ApiModelProperty(value = "分轴重6")
	private String axle6;

	/**
	 * 分轴重7
	 */
   	@ApiModelProperty(value = "分轴重7")
	private String axle7;

	/**
	 * 分轴重8
	 */
   	@ApiModelProperty(value = "分轴重8")
	private String axle8;

	/**
	 * 站点名称
	 */
   	@ApiModelProperty(value = "站点名称")
	private String stationName;

	/**
	 * 长（单位：厘米）
	 */
   	@ApiModelProperty(value = "长（单位：厘米）")
	private String length;

	/**
	 * 超长（单位：厘米）
	 */
   	@ApiModelProperty(value = "超长（单位：厘米）")
	private String overLength;

	/**
	 * 宽（单位：厘米）
	 */
   	@ApiModelProperty(value = "宽（单位：厘米）")
	private String width;

	/**
	 * 超宽（单位：厘米）
	 */
   	@ApiModelProperty(value = "超宽（单位：厘米）")
	private String overWidth;

	/**
	 * 高（单位：厘米）
	 */
   	@ApiModelProperty(value = "高（单位：厘米）")
	private String height;

	/**
	 * 超高（单位：厘米）
	 */
   	@ApiModelProperty(value = "超高（单位：厘米）")
	private String overHeight;

	/**
	 * 是否几何超限
	 */
   	@ApiModelProperty(value = "是否几何超限")
	private String isGeoLimited;

	/**
	 * 车货限重
	 */
   	@ApiModelProperty(value = "车货限重")
	private String weightLimited;

	/**
	 * 短站
	 */
   	@ApiModelProperty(value = "短站")
	private String stationForShort;

	/**
	 * 轮廓编号
	 */
   	@ApiModelProperty(value = "轮廓编号")
	private String vehicleOutLineId;

	/**
	 * 轮廓
	 */
   	@ApiModelProperty(value = "轮廓")
	private String vehicleOutLine;

	/**
	 * 车型编号： 0--其他车辆，1--货车，11 --客车   10载货汽车 12中置轴挂车列车 13铰接列车 14全挂汽车列车 15全挂列车
	 */
   	@ApiModelProperty(value = "车型编号： 0--其他车辆，1--货车，11 --客车   10载货汽车 12中置轴挂车列车 13铰接列车 14全挂汽车列车 15全挂列车")
	private String vehicleTypeId;

	/**
	 * 车型(0-轿车，1-小汽车，其他数字表示轴型，157轴型表示6轴车)
	 */
   	@ApiModelProperty(value = "车型(0-轿车，1-小汽车，其他数字表示轴型，157轴型表示6轴车)")
	private String vehicleType;

	/**
	 * 车辆图片1
	 */
   	@ApiModelProperty(value = "车辆图片1")
	private String picture1;

	/**
	 * 车辆图片2
	 */
   	@ApiModelProperty(value = "车辆图片2")
	private String picture2;

	/**
	 * 车牌图片
	 */
   	@ApiModelProperty(value = "车牌图片")
	private String picture3;

	/**
	 * 视频地址
	 */
   	@ApiModelProperty(value = "视频地址")
	private String videoAddress;

	/**
	 * 方向
	 */
   	@ApiModelProperty(value = "方向")
	private String direction;

	/**
	 * 执法序号
	 */
   	@ApiModelProperty(value = "执法序号")
	private String oeId;

	/**
	 * 修正幅度
	 */
   	@ApiModelProperty(value = "修正幅度")
	private String correctRange;

	/**
	 * 原始总重
	 */
   	@ApiModelProperty(value = "原始总重")
	private String noCorrWeight;

	/**
	 * 原始超限量
	 */
   	@ApiModelProperty(value = "原始超限量")
	private String noCorrOverLoad;

	/**
	 * 接收时间
	 */
   	@ApiModelProperty(value = "接收时间")
	private LocalDateTime receiveTime;

	/**
	 * 创建时间
	 */
   	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createdTime;

}
