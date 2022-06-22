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
 * @Date 2021-11-30 
 */

@Entity
@Data
@ApiModel(value = "Entity Of yz_zf_ysl")
@Table ( name ="yz_zf_ysl" )
public class YzZfYsl implements Serializable {

	private static final long serialVersionUID = 3436349062814194435L;

	/**
	 * id
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
   	@ApiModelProperty(value = "id")
	private String id;

	/**
	 * 案件案号
	 */
   	@ApiModelProperty(value = "案件案号")
	private String caseNumber;

	/**
	 * 案件状态归档1，待结案提交2，调查审批3，结案报告4，待发处罚决定5，立案审批6，法制审核7，案件调查8，撤案9，立案登记10，不予立案11，案件预登记12，发违法通知书13，待当场处罚14
	 */
   	@ApiModelProperty(value = "案件状态归档1，待结案提交2，调查审批3，结案报告4，待发处罚决定5，立案审批6，法制审核7，案件调查8，撤案9，立案登记10，不予立案11，案件预登记12，发违法通知书13，待当场处罚14")
	private Integer caseStatus;

	/**
	 * 处罚结果
	 */
   	@ApiModelProperty(value = "处罚结果")
	private String punishResult;

	/**
	 * 处罚时间
	 */
   	@ApiModelProperty(value = "处罚时间")
	private LocalDateTime punishTime;

	/**
	 * 人员ID
	 */
   	@ApiModelProperty(value = "人员ID")
	private String personId;

	/**
	 * 业户ID
	 */
   	@ApiModelProperty(value = "业户ID")
	private String companyId;

	/**
	 * 创建时间
	 */
   	@ApiModelProperty(value = "创建时间")
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
   	@ApiModelProperty(value = "更新时间")
	private LocalDateTime updateTime;

	/**
	 * 创建人
	 */
   	@ApiModelProperty(value = "创建人")
	private String createdBy;

	/**
	 * 更新人
	 */
   	@ApiModelProperty(value = "更新人")
	private String updatedBy;

}
