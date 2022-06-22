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
 * @Date 2022-04-26 
 */

@Entity
@Data
@ApiModel(value = "Entity Of ill_much_vehicle")
@Table ( name ="ill_much_vehicle" )
public class IllMuchVehicle implements Serializable {

	private static final long serialVersionUID = 2009074778984972197L;

	/**
	 * 编号
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
   	@ApiModelProperty(value = "编号")
	private String id;

	/**
	 * 车牌号
	 */
   	@ApiModelProperty(value = "车牌号")
	private String plateNumber;

	/**
	 * 车牌颜色
	 */
   	@ApiModelProperty(value = "车牌颜色")
	private Long plateColor;

	/**
	 * 道路运输证号
	 */
   	@ApiModelProperty(value = "道路运输证号")
	private String licenseNumber;

	/**
	 * 所属公司id
	 */
   	@ApiModelProperty(value = "所属公司id")
	private String companyId;

	/**
	 * 所属公司名称
	 */
   	@ApiModelProperty(value = "所属公司名称")
	private String companyName;

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
	 * 修改人
	 */
   	@ApiModelProperty(value = "修改人")
	private String lastModifiedBy;

	/**
	 * 修改时间
	 */
   	@ApiModelProperty(value = "修改时间")
	private LocalDateTime lastModifiedTime;

	/**
	 * 是否删除
	 */
   	@ApiModelProperty(value = "是否删除")
	private Integer removed;

}
