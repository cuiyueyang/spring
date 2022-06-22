package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Table(name = "ill_vehicle_record_detail")
@Entity
@ApiModel(value = "过车详细数据（标准模型）")
public class IllVehicleRecordDetail implements Serializable {
    private static final long serialVersionUID = 9213775912136966822L;
    @Id
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    private String id;

    /**
     * 车牌号码
     */
    @ApiModelProperty(value = "车牌号码")
    private String plateNumber;

    /**
     * 车牌颜色(0蓝，1黄)
     */
    @ApiModelProperty(value = "车牌颜色(0蓝，1黄)")
    private Integer plateColor;

    /**
     * 车辆类型(客3客4)
     */
    @ApiModelProperty(value = "车辆类型(客3客4)")
    private String vehicleType;

    /**
     * 通行时间
     */
    @ApiModelProperty(value = "通行时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime passTime;

    /**
     * 流水ID
     */
    @ApiModelProperty(value = "流水ID")
    private String sid;

    /**
     * 收费站（门架）名称
     */
    @ApiModelProperty(value = "收费站（门架）名称")
    private String gantryName;

    /**
     * 收费站（门架）ID
     */
    @ApiModelProperty(value = "收费站（门架）ID")
    private String gantryId;

    /**
     * 入口收费站名称
     */
    @ApiModelProperty(value = "入口收费站名称")
    private String enStationName;

    /**
     * 入口收费站ID
     */
    @ApiModelProperty(value = "入口收费站ID")
    private String enStationId;

    /**
     * 流水类型（1入口，2出口，3门架）
     */
    @ApiModelProperty(value = "流水类型（1入口，2出口，3门架）")
    private String type;


    private String imgUrl;



}
