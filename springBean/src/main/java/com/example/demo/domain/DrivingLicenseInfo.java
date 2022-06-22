package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>Description: 行驶证数据</p>
 * <p>Date: 2021/8/5 15:40 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@Data
@Entity
public class DrivingLicenseInfo {
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    /** xh*/
    @ApiModelProperty(name = "机动车序号")
    private String serialNumber;

    /** cllx*/
    @ApiModelProperty(name = "车辆类型")
    private String typeName;

    /** zzl*/
    @ApiModelProperty(name = "总质量")
    private Long totalMass;

    /** fzjg*/
    @ApiModelProperty(name = "发证机关")
    private String issuingAuthority;

    /** fzrq*/
    @ApiModelProperty(name = "发行驶证日期")
    private LocalDate issuanceDate;

    /** syxz*/
    @ApiModelProperty(name = "使用性质")
    private String natureName;

    /** sfzmhm*/
    @ApiModelProperty(name = "身份证明号码")
    private String idNumber;

    /** cwkg*/
    @ApiModelProperty(name = "车外廓高")
    private Long high;

    /** zbzl*/
    @ApiModelProperty(name = "整备质量")
    private Long equipmentQuality;

    /** cwkk*/
    @ApiModelProperty(name = "车外廓宽")
    private Long width;

    /** syr*/
    @ApiModelProperty(name = "机动车所有人")
    private String vehicleOwner;

    /** hphm*/
    @ApiModelProperty(name = "号牌号码")
    private String plateNumber;

    /** hpzl*/
    @ApiModelProperty(name = "号牌种类")
    private String plateType;

    /** hdzk*/
    @ApiModelProperty(name = "核定载客")
    private Integer approveSeat;

    /** cwkc*/
    @ApiModelProperty(name = "车外廓长")
    private Long length;

    /** zsxxdz*/
    @ApiModelProperty(name = "住所详细地址")
    private String dwellAddress;

    /** yxqz*/
    @ApiModelProperty(name = "检验有效期止")
    private LocalDate inspectionExpirationDate;

    /** fprq*/
    @ApiModelProperty(name = "发牌日期")
    private LocalDate licensePlateDate;

    /** ccdjrq*/
    @ApiModelProperty(name = "初次登记日期")
    private LocalDate registrationDate;

    /** clxh*/
    @ApiModelProperty(name = "车辆型号")
    private String vehicleModel;

    /** zt*/
    @ApiModelProperty(name = "机动车状态")
    private String status;

    /** sfzmmc*/
    @ApiModelProperty(name = "身份证明名称")
    private String idNumberName;

    /** clpp1*/
    @ApiModelProperty(name = "中文品牌")
    private String brand;

    /** clsbdh*/
    @ApiModelProperty(name = "车辆识别代号")
    private String vehicleIdentificationNumber;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    @Column(name = "CREATED_TIME")
    private LocalDateTime createdTime;

    @ApiModelProperty(name = "创建人姓名")
    private String createdBy;

    @ApiModelProperty(value = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_TIME")
    private LocalDateTime lastModifiedTime;

    @ApiModelProperty(name = "修改人姓名")
    private String lastModifiedBy;

}
