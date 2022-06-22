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
 * @Date 2022-04-18 
 */

@Entity
@Data
@ApiModel(value = "Entity Of ill_schedule")
@Table ( name ="ill_schedule" )
public class IllSchedule implements Serializable {

	private static final long serialVersionUID = 4419826911360010605L;

	/**
	 * 编号
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
   	@ApiModelProperty(value = "编号")
	private String id;

	/**
	 * 卡口编号
	 */
   	@ApiModelProperty(value = "卡口编号")
	private String serialNumber;

	/**
	 * 卡口名称
	 */
   	@ApiModelProperty(value = "卡口名称")
	private String serialName;

	/**
	 * 执法人员信息 张三（13812341234）、李四（13812341234）
	 */
   	@ApiModelProperty(value = "执法人员信息 张三（13812341234）、李四（13812341234）")
	private String lawInfo;

	/**
	 * 排班时间
	 */
   	@ApiModelProperty(value = "排班时间")
	private LocalDateTime workTime;

	/**
	 * 创建人
	 */
   	@ApiModelProperty(value = "创建人")
	private String createdBy;

	/**
	 * 创建时间
	 */
   	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createdTime;

	/**
	 * 最后修改人
	 */
   	@ApiModelProperty(value = "最后修改人")
	private String lastModifiedBy;

	/**
	 * 最后修改时间
	 */
   	@ApiModelProperty(value = "最后修改时间")
	private LocalDateTime lastModifiedTime;

	/**
	 * 是否逻辑删除
	 */
   	@ApiModelProperty(value = "是否逻辑删除")
	private Integer removed;

}
