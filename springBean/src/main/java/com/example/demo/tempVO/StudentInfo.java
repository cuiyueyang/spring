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
 * @Author jinjie 
 * @Date 2021-11-30 
 */

@Entity
@Data
@ApiModel(value = "Entity Of student_info")
@Table ( name ="student_info" )
public class StudentInfo implements Serializable {

	private static final long serialVersionUID = 5893915233372026606L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
   	@ApiModelProperty(value = "null")
	private String id;

	/**
	 * 学号
	 */
   	@ApiModelProperty(value = "学号")
	private Long stuNo;

	/**
	 * 学生姓名
	 */
   	@ApiModelProperty(value = "学生姓名")
	private String stuName;

	/**
	 * 学生年龄
	 */
   	@ApiModelProperty(value = "学生年龄")
	private Long stuAge;

	/**
	 * 学生性别
	 */
   	@ApiModelProperty(value = "学生性别")
	private Long stuSex;

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
	 * 删除标识
	 */
   	@ApiModelProperty(value = "删除标识")
	private Long remove;

}
