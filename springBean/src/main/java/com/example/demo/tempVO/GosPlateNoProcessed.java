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
 * @Date 2021-12-29 
 */

@Entity
@Data
@ApiModel(value = "Entity Of gos_plate_no_processed")
@Table ( name ="gos_plate_no_processed" )
public class GosPlateNoProcessed implements Serializable {

	private static final long serialVersionUID = 771438002150756106L;

	/**
	 * 主键
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
   	@ApiModelProperty(value = "主键")
	private String id;

	/**
	 * 车牌
	 */
   	@ApiModelProperty(value = "车牌")
	private String plateNumber;

	/**
	 * 超限时间
	 */
   	@ApiModelProperty(value = "超限时间")
	private LocalDateTime checkTime;

	/**
	 * 总重（吨）
	 */
   	@ApiModelProperty(value = "总重（吨）")
	private Double totalMass;

	/**
	 * 超限（吨）
	 */
   	@ApiModelProperty(value = "超限（吨）")
	private Double overrunMass;

	/**
	 * 轴数
	 */
   	@ApiModelProperty(value = "轴数")
	private Long axisNum;

	/**
	 * 站点名称
	 */
   	@ApiModelProperty(value = "站点名称")
	private String stationName;

	/**
	 * 超限率
	 */
   	@ApiModelProperty(value = "超限率")
	private Double overrunRate;

	/**
	 * 短信|信函|公示
	 */
   	@ApiModelProperty(value = "短信|信函|公示")
	private String message;

	/**
	 * 企业
	 */
   	@ApiModelProperty(value = "企业")
	private String company;

	/**
	 * 状态
	 */
   	@ApiModelProperty(value = "状态")
	private String status;

}
