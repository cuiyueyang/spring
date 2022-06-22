package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * <p>Description: </p>
 * <p>Date: 2021/8/5 17:26 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */

@Data
@Entity
public class Vehicle {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @ApiModelProperty("车牌号码")
    private String plateNumber;

    @ApiModelProperty("车牌颜色")
    private Integer plateColor;

    @ApiModelProperty("车辆类型")
    private Integer vehicleType;

    @ApiModelProperty("登记机构")
    private String registrationOrg;

    @ApiModelProperty("数据来源")
    private String dataSource;

    @ApiModelProperty("创建时间")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    @ApiModelProperty("更新时间")
    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedTime;

    @ApiModelProperty("非法营运车辆现在的等级（2:疑似；3:重点监控；4:待抓捕）")
    private String level;

    @ApiModelProperty("创建人")
    @CreatedBy
    private String createdBy;

    @ApiModelProperty("更新人")
    @LastModifiedBy
    private String lastModifiedBy;

    @ApiModelProperty("使用性质")
    private String useCharacter;

    @ApiModelProperty("企业ID")
    private String companyId;

    @ApiModelProperty("企业姓名")
    private String companyName;

    @ApiModelProperty("车辆状态CODE")
    private String vehicleStatus;

    @ApiModelProperty("车辆状态")
    private String vehicleStatusName;

    @ApiModelProperty("车辆类型CODE")
    private String vehicleTypeCode;

    @ApiModelProperty("车辆类型")
    private String vehicleTypeName;

    @ApiModelProperty("数据监测渠道")
    private String dataChannels;

    @ApiModelProperty("涉及地市")
    private String city;

}

