package com.example.demo.domain;


import javax.persistence.*;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.example.demo.web.authority.annotation.IsStandard;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description 学生信息类
 * @Author cuiyy 
 * @Date 2021-07-14 
 */

@Entity
@Data
@Table ( name ="student_info" )
@EntityListeners(AuditingEntityListener.class)
public class StudentInfo implements Serializable {

	private static final long serialVersionUID = 6554209935503812856L;

	@Excel(name="编号")
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	private String id;

	/**
	 * 学号
	 */
	@Excel(name="学号")
	private String stuNo;

	/**
	 * 学生姓名
	 */
	@Excel(name="姓名")
	private String stuName;

	/**
	 * 学生年龄
	 */
	@Excel(name="年龄")
	@IsStandard(describe = "该学生还未成年", checkAge = 18)
	private Long stuAge;

	/**
	 * 学生性别
	 */
	@Excel(name="性别",replace = {"男_1","女_2"})
	private Long stuSex;

	/**
	 * 创建时间
	 */
	@Excel(name="创建时间", format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreatedDate
	private LocalDateTime createTime;

	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@LastModifiedDate
	private LocalDateTime updateTime;

	/**
	 * 删除标识
	 */
	private Integer remove;

	public StudentInfo(String stuName, Long stuAge, Long stuSex, LocalDateTime createTime, LocalDateTime updateTime, Integer remove) {
		this.stuName = stuName;
		this.stuAge = stuAge;
		this.stuSex = stuSex;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.remove = remove;
	}

	public StudentInfo() {
	}

	@Override
	public String toString() {
		return "StudentInfo{" +
				"id='" + id + '\'' +
				", stuNo='" + stuNo + '\'' +
				", stuName='" + stuName + '\'' +
				", stuAge=" + stuAge +
				", stuSex=" + stuSex +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", remove='" + remove + '\'' +
				'}';
	}
}
