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
 * @Date 2021-12-10 
 */

@Entity
@Data
@ApiModel(value = "Entity Of ill_netaxi_operate_pay")
@Table ( name ="ill_netaxi_operate_pay" )
public class IllNetaxiOperatePay implements Serializable {

	private static final long serialVersionUID = 6115453802028684187L;

	/**
	 * 主键（订单号+公司标识）
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
   	@ApiModelProperty(value = "主键（订单号+公司标识）")
	private String id;

	/**
	 * 网约车公司标识
	 */
   	@ApiModelProperty(value = "网约车公司标识")
	private String companyId;

	/**
	 * 订单编号
	 */
   	@ApiModelProperty(value = "订单编号")
	private String orderId;

	/**
	 * 上车位置行政区划编号
	 */
   	@ApiModelProperty(value = "上车位置行政区划编号")
	private String areaCode;

	/**
	 * 机动车驾驶员姓名
	 */
   	@ApiModelProperty(value = "机动车驾驶员姓名")
	private String driverName;

	/**
	 * 机动车驾驶证号
	 */
   	@ApiModelProperty(value = "机动车驾驶证号")
	private String licenseId;

	/**
	 * 运价类型编码
	 */
   	@ApiModelProperty(value = "运价类型编码")
	private String fareType;

	/**
	 * 车辆号牌
	 */
   	@ApiModelProperty(value = "车辆号牌")
	private String vehicleNo;

	/**
	 * 预计上车时间
	 */
   	@ApiModelProperty(value = "预计上车时间")
	private LocalDateTime bookDepTime;

	/**
	 * 等待时间
	 */
   	@ApiModelProperty(value = "等待时间")
	private Double waitTime;

	/**
	 * 车辆出发经度
	 */
   	@ApiModelProperty(value = "车辆出发经度")
	private Double depLongitude;

	/**
	 * 车辆出发纬度
	 */
   	@ApiModelProperty(value = "车辆出发纬度")
	private Double depLatitude;

	/**
	 * 上车地点
	 */
   	@ApiModelProperty(value = "上车地点")
	private String depArea;

	/**
	 * 上车时间
	 */
   	@ApiModelProperty(value = "上车时间")
	private LocalDateTime depTime;

	/**
	 * 车辆到达经度
	 */
   	@ApiModelProperty(value = "车辆到达经度")
	private Double destLongitude;

	/**
	 * 车辆到达纬度
	 */
   	@ApiModelProperty(value = "车辆到达纬度")
	private Double destLatitude;

	/**
	 * 下车地点
	 */
   	@ApiModelProperty(value = "下车地点")
	private String destArea;

	/**
	 * 下车时间
	 */
   	@ApiModelProperty(value = "下车时间")
	private LocalDateTime destTime;

	/**
	 * 预定车型
	 */
   	@ApiModelProperty(value = "预定车型")
	private String bookModel;

	/**
	 * 实际使用车型
	 */
   	@ApiModelProperty(value = "实际使用车型")
	private String model;

	/**
	 * 载客里程
	 */
   	@ApiModelProperty(value = "载客里程")
	private Double driveMile;

	/**
	 * 载客时间
	 */
   	@ApiModelProperty(value = "载客时间")
	private Double driveTime;

	/**
	 * 空驶里程
	 */
   	@ApiModelProperty(value = "空驶里程")
	private Double waitMile;

	/**
	 * 实收金额
	 */
   	@ApiModelProperty(value = "实收金额")
	private Double factPrice;

	/**
	 * 应收金额
	 */
   	@ApiModelProperty(value = "应收金额")
	private Double price;

	/**
	 * 现金支付金额
	 */
   	@ApiModelProperty(value = "现金支付金额")
	private Double cashPrice;

	/**
	 * 电子支付机构
	 */
   	@ApiModelProperty(value = "电子支付机构")
	private String lineName;

	/**
	 * 电子支付金额
	 */
   	@ApiModelProperty(value = "电子支付金额")
	private Double linePrice;

	/**
	 * POS机支付机构
	 */
   	@ApiModelProperty(value = "POS机支付机构")
	private String posName;

	/**
	 * POS机支付金额
	 */
   	@ApiModelProperty(value = "POS机支付金额")
	private Double posPrice;

	/**
	 * 优惠金额
	 */
   	@ApiModelProperty(value = "优惠金额")
	private Double benfitPrice;

	/**
	 * 预约服务费
	 */
   	@ApiModelProperty(value = "预约服务费")
	private Double bookTip;

	/**
	 * 附加费
	 */
   	@ApiModelProperty(value = "附加费")
	private Double passengerTip;

	/**
	 * 高峰时段时间加价金额
	 */
   	@ApiModelProperty(value = "高峰时段时间加价金额")
	private Double peakUpPrice;

	/**
	 * 夜间时段里程加价金额
	 */
   	@ApiModelProperty(value = "夜间时段里程加价金额")
	private Double nightUpPrice;

	/**
	 * 远途加价金额
	 */
   	@ApiModelProperty(value = "远途加价金额")
	private Double farUpPrice;

	/**
	 * 其他加价金额
	 */
   	@ApiModelProperty(value = "其他加价金额")
	private Double otherUpPrice;

	/**
	 * 结算状态
	 */
   	@ApiModelProperty(value = "结算状态")
	private String payState;

	/**
	 * 乘客结算时间
	 */
   	@ApiModelProperty(value = "乘客结算时间")
	private LocalDateTime payTime;

	/**
	 * 订单完成时间
	 */
   	@ApiModelProperty(value = "订单完成时间")
	private LocalDateTime orderMatchTime;

	/**
	 * 发票状态
	 */
   	@ApiModelProperty(value = "发票状态")
	private Double invoiceStatus;

	/**
	 * 创建时间
	 */
   	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createdTime;

}
