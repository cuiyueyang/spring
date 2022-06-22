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
 * @Date 2022-01-21 
 */

@Entity
@Data
@ApiModel(value = "Entity Of zjga_acd_file")
@Table ( name ="zjga_acd_file" )
public class ZjgaAcdFile implements Serializable {

	private static final long serialVersionUID = 3139574253949473325L;

	/**
	 * 事故编号
	 */
   	@ApiModelProperty(value = "事故编号")
	private String sgbh;

	/**
	 * 行政区划
	 */
   	@ApiModelProperty(value = "行政区划")
	private String xzqh;

	/**
	 * 登记编号
	 */
   	@ApiModelProperty(value = "登记编号")
	private String djbh;

	/**
	 * 开始勘查时间
	 */
   	@ApiModelProperty(value = "开始勘查时间")
	private LocalDateTime kskcsj;

	/**
	 * 结束勘查时间
	 */
   	@ApiModelProperty(value = "结束勘查时间")
	private LocalDateTime jskcsj;

	/**
	 * 星期
	 */
   	@ApiModelProperty(value = "星期")
	private Double xq;

	/**
	 * 事故发生时间
	 */
   	@ApiModelProperty(value = "事故发生时间")
	private LocalDateTime sgfssj;

	/**
	 * 路号
	 */
   	@ApiModelProperty(value = "路号")
	private String lh;

	/**
	 * 路名
	 */
   	@ApiModelProperty(value = "路名")
	private String lm;

	/**
	 * 公里数
	 */
   	@ApiModelProperty(value = "公里数")
	private Double gls;

	/**
	 * 米数
	 */
   	@ApiModelProperty(value = "米数")
	private Double ms;

	/**
	 * 起点米数
	 */
   	@ApiModelProperty(value = "起点米数")
	private Double qdms;

	/**
	 * 绝对位置
	 */
   	@ApiModelProperty(value = "绝对位置")
	private Double jdwz;

	/**
	 * 事故地点
	 */
   	@ApiModelProperty(value = "事故地点")
	private String sgdd;

	/**
	 * 在道路横断面位置
	 */
   	@ApiModelProperty(value = "在道路横断面位置")
	private String zhdmwz;

	/**
	 * 中央隔离设施
	 */
   	@ApiModelProperty(value = "中央隔离设施")
	private String zyglss;

	/**
	 * 道路安全属性
	 */
   	@ApiModelProperty(value = "道路安全属性")
	private String dlaqsx;

	/**
	 * 交通信号方式（控制）
	 */
   	@ApiModelProperty(value = "交通信号方式（控制）")
	private String jtxhfs;

	/**
	 * 路侧防护设施类型
	 */
   	@ApiModelProperty(value = "路侧防护设施类型")
	private String fhsslx;

	/**
	 * 道路物理隔离
	 */
   	@ApiModelProperty(value = "道路物理隔离")
	private String dlwlgl;

	/**
	 * 路面状况
	 */
   	@ApiModelProperty(value = "路面状况")
	private String lmzk;

	/**
	 * 路表情况
	 */
   	@ApiModelProperty(value = "路表情况")
	private String lbqk;

	/**
	 * 路面结构
	 */
   	@ApiModelProperty(value = "路面结构")
	private String lmjg;

	/**
	 * 路口路段类型 
	 */
   	@ApiModelProperty(value = "路口路段类型 ")
	private String lkldlx;

	/**
	 * 道路线型 
	 */
   	@ApiModelProperty(value = "道路线型 ")
	private String dlxx;

	/**
	 * 道路类型 
	 */
   	@ApiModelProperty(value = "道路类型 ")
	private String dllx;

	/**
	 * 勘查人1
	 */
   	@ApiModelProperty(value = "勘查人1")
	private String kcr1;

	/**
	 * 勘查人2
	 */
   	@ApiModelProperty(value = "勘查人2")
	private String kcr2;

	/**
	 * 办案人1
	 */
   	@ApiModelProperty(value = "办案人1")
	private String bar1;

	/**
	 * 办案人2
	 */
   	@ApiModelProperty(value = "办案人2")
	private String bar2;

	/**
	 * 当场死亡人数
	 */
   	@ApiModelProperty(value = "当场死亡人数")
	private Double swrs;

	/**
	 * 抢救无效死亡人数
	 */
   	@ApiModelProperty(value = "抢救无效死亡人数")
	private Double swrsq;

	/**
	 * 24小时死亡人数
	 */
   	@ApiModelProperty(value = "24小时死亡人数")
	private Double swrs24;

	/**
	 * 24小时受伤人数
	 */
   	@ApiModelProperty(value = "24小时受伤人数")
	private Double ssrs24;

	/**
	 * 3日内死亡人数
	 */
   	@ApiModelProperty(value = "3日内死亡人数")
	private Double swrs3;

	/**
	 * 3日内受伤人数
	 */
   	@ApiModelProperty(value = "3日内受伤人数")
	private Double ssrs3;

	/**
	 * 7日内死亡人数
	 */
   	@ApiModelProperty(value = "7日内死亡人数")
	private Double swrs7;

	/**
	 * 7日内受伤人数
	 */
   	@ApiModelProperty(value = "7日内受伤人数")
	private Double ssrs7;

	/**
	 * 30日内死亡人数
	 */
   	@ApiModelProperty(value = "30日内死亡人数")
	private Double swrs30;

	/**
	 * 30日内受伤人数
	 */
   	@ApiModelProperty(value = "30日内受伤人数")
	private Double ssrs30;

	/**
	 * 失踪人数
	 */
   	@ApiModelProperty(value = "失踪人数")
	private Double szrs;

	/**
	 * 重伤人数
	 */
   	@ApiModelProperty(value = "重伤人数")
	private Double zsrs;

	/**
	 * 轻伤人数
	 */
   	@ApiModelProperty(value = "轻伤人数")
	private Double qsrs;

	/**
	 * 受伤人数
	 */
   	@ApiModelProperty(value = "受伤人数")
	private Double ssrs;

	/**
	 * 机动车数量
	 */
   	@ApiModelProperty(value = "机动车数量")
	private Double jdcsl;

	/**
	 * 非机动车数量
	 */
   	@ApiModelProperty(value = "非机动车数量")
	private Double fjdcsl;

	/**
	 * 行人数量
	 */
   	@ApiModelProperty(value = "行人数量")
	private Double xrsl;

	/**
	 * 刑事管理部门
	 */
   	@ApiModelProperty(value = "刑事管理部门")
	private String xsglbm;

	/**
	 * 刑事办案单位
	 */
   	@ApiModelProperty(value = "刑事办案单位")
	private String xsbadw;

	/**
	 * 刑事办案人
	 */
   	@ApiModelProperty(value = "刑事办案人")
	private String xsbar;

	/**
	 * 图片张数
	 */
   	@ApiModelProperty(value = "图片张数")
	private Double tpzs;

	/**
	 * 现场图张数
	 */
   	@ApiModelProperty(value = "现场图张数")
	private Double xctzs;

	/**
	 * 现场照片张数
	 */
   	@ApiModelProperty(value = "现场照片张数")
	private Double xczpzs;

	/**
	 * 直接财产损失
	 */
   	@ApiModelProperty(value = "直接财产损失")
	private Double zjccss;

	/**
	 * 事故类型
	 */
   	@ApiModelProperty(value = "事故类型")
	private String sglx;

	/**
	 * 路外事故类型
	 */
   	@ApiModelProperty(value = "路外事故类型")
	private String lwsglx;

	/**
	 * 事故初查原因分类
	 */
   	@ApiModelProperty(value = "事故初查原因分类")
	private String ccyyfl;

	/**
	 * 事故认定原因分类
	 */
   	@ApiModelProperty(value = "事故认定原因分类")
	private String rdyyfl;

	/**
	 * 事故初查原因
	 */
   	@ApiModelProperty(value = "事故初查原因")
	private String sgccyy;

	/**
	 * 事故认定原因
	 */
   	@ApiModelProperty(value = "事故认定原因")
	private String sgrdyy;

	/**
	 * 简要案情
	 */
   	@ApiModelProperty(value = "简要案情")
	private String jyaq;

	/**
	 * 天气
	 */
   	@ApiModelProperty(value = "天气")
	private String tq;

	/**
	 * 能见度
	 */
   	@ApiModelProperty(value = "能见度")
	private String njd;

	/**
	 * 现场
	 */
   	@ApiModelProperty(value = "现场")
	private String xc;

	/**
	 * 涉外事故1是2否
	 */
   	@ApiModelProperty(value = "涉外事故1是2否")
	private String swsg;

	/**
	 * 事故形态 
	 */
   	@ApiModelProperty(value = "事故形态 ")
	private String sgxt;

	/**
	 * 是否逃逸 1-否 2-驾车逃逸 3-弃车逃逸 X
	 */
   	@ApiModelProperty(value = "是否逃逸 1-否 2-驾车逃逸 3-弃车逃逸 X")
	private String sfty;

	/**
	 * 车辆间事故碰撞形态
	 */
   	@ApiModelProperty(value = "车辆间事故碰撞形态")
	private String cljsg;

	/**
	 * 单车事故
	 */
   	@ApiModelProperty(value = "单车事故")
	private String dcsg;

	/**
	 * 碰撞方式 1-单车 2-双车 3-多车 4-车人事故
	 */
   	@ApiModelProperty(value = "碰撞方式 1-单车 2-双车 3-多车 4-车人事故")
	private String pzfs;

	/**
	 * 逃逸事故侦破1-是2-否
	 */
   	@ApiModelProperty(value = "逃逸事故侦破1-是2-否")
	private String tysgzp;

	/**
	 * 逃逸事故侦破时间
	 */
   	@ApiModelProperty(value = "逃逸事故侦破时间")
	private LocalDateTime tyzpsj;

	/**
	 * 地形 
	 */
   	@ApiModelProperty(value = "地形 ")
	private String dx;

	/**
	 * 照明条件
	 */
   	@ApiModelProperty(value = "照明条件")
	private String zmtj;

	/**
	 * 调解人1
	 */
   	@ApiModelProperty(value = "调解人1")
	private String tjr1;

	/**
	 * 调解人2
	 */
   	@ApiModelProperty(value = "调解人2")
	private String tjr2;

	/**
	 * 是否运载危险物品1-是2否
	 */
   	@ApiModelProperty(value = "是否运载危险物品1-是2否")
	private String yzwxp;

	/**
	 * 运载危险品事故后果
	 */
   	@ApiModelProperty(value = "运载危险品事故后果")
	private String yzwxphg;

	/**
	 * 初次录入时间
	 */
   	@ApiModelProperty(value = "初次录入时间")
	private LocalDateTime cclrsj;

	/**
	 *  1-快报记录 2-快报/全项记录 3全项记录 9 不立案
	 */
   	@ApiModelProperty(value = " 1-快报记录 2-快报/全项记录 3全项记录 9 不立案")
	private String jllx;

	/**
	 * 上传时间段(天数)
	 */
   	@ApiModelProperty(value = "上传时间段(天数)")
	private Double scsjd;

	/**
	 * 经办人
	 */
   	@ApiModelProperty(value = "经办人")
	private String jbr;

	/**
	 * 提交时间
	 */
   	@ApiModelProperty(value = "提交时间")
	private LocalDateTime tjsj;

	/**
	 * 更新时间
	 */
   	@ApiModelProperty(value = "更新时间")
	private LocalDateTime gxsj;

	/**
	 * 所属中队
	 */
   	@ApiModelProperty(value = "所属中队")
	private String sszd;

	/**
	 * 公路行政等级
	 */
   	@ApiModelProperty(value = "公路行政等级")
	private String glxzdj;

	/**
	 * 档案号
	 */
   	@ApiModelProperty(value = "档案号")
	private String dah;

	/**
	 * 卷内号
	 */
   	@ApiModelProperty(value = "卷内号")
	private Double jnh;

	/**
	 * 所辖乡镇
	 */
   	@ApiModelProperty(value = "所辖乡镇")
	private String sxxz;

	/**
	 * 上报 1-是 2-否  为空3-上报但更新不成功
	 */
   	@ApiModelProperty(value = "上报 1-是 2-否  为空3-上报但更新不成功")
	private String sb;

	/**
	 * 统计事故编号
	 */
   	@ApiModelProperty(value = "统计事故编号")
	private String tjsgbh;

	/**
	 * 管理部门
	 */
   	@ApiModelProperty(value = "管理部门")
	private String glbm;

	/**
	 * 预留字段1
	 */
   	@ApiModelProperty(value = "预留字段1")
	private String ylzd1;

	/**
	 * 预留字段2
	 */
   	@ApiModelProperty(value = "预留字段2")
	private String ylzd2;

	/**
	 * 预留字段3
	 */
   	@ApiModelProperty(value = "预留字段3")
	private String ylzd3;

	/**
	 * 预留字段4
	 */
   	@ApiModelProperty(value = "预留字段4")
	private String ylzd4;

	/**
	 * 预留字段5
	 */
   	@ApiModelProperty(value = "预留字段5")
	private String ylzd5;

	/**
	 * 电子坐标 格式为地址坐标X,地址坐标Y,地址经度,地址纬度
	 */
   	@ApiModelProperty(value = "电子坐标 格式为地址坐标X,地址坐标Y,地址经度,地址纬度")
	private String dzzb;

	/**
	 * 特大预留1
	 */
   	@ApiModelProperty(value = "特大预留1")
	private String tdyl1;

	/**
	 * 特大预留2
	 */
   	@ApiModelProperty(value = "特大预留2")
	private String tdyl2;

	/**
	 * 特大预留3
	 */
   	@ApiModelProperty(value = "特大预留3")
	private String tdyl3;

	/**
	 * 特大预留4
	 */
   	@ApiModelProperty(value = "特大预留4")
	private String tdyl4;

	/**
	 * 特大预留5
	 */
   	@ApiModelProperty(value = "特大预留5")
	private String tdyl5;

	/**
	 * 特大预留6
	 */
   	@ApiModelProperty(value = "特大预留6")
	private String tdyl6;

	/**
	 * 特大预留7
	 */
   	@ApiModelProperty(value = "特大预留7")
	private String tdyl7;

	/**
	 * 特大预留8
	 */
   	@ApiModelProperty(value = "特大预留8")
	private String tdyl8;

	/**
	 * 特大预留9
	 */
   	@ApiModelProperty(value = "特大预留9")
	private String tdyl9;

	/**
	 * 特大预留10
	 */
   	@ApiModelProperty(value = "特大预留10")
	private String tdyl10;

	/**
	 * 办案联系方式
	 */
   	@ApiModelProperty(value = "办案联系方式")
	private String balxfs;

	/**
	 * 办案单位
	 */
   	@ApiModelProperty(value = "办案单位")
	private String badw;

	/**
	 * 县以下行政区划
	 */
   	@ApiModelProperty(value = "县以下行政区划")
	private String xyxdm;

	/**
	 * 校验位
	 */
   	@ApiModelProperty(value = "校验位")
	private String jyw;

	/**
	 * 是否二次事故 1-是 2-否 * X
	 */
   	@ApiModelProperty(value = "是否二次事故 1-是 2-否 * X")
	private String sfecsg;

	/**
	 * 是否典型事故 1-是 2-否
	 */
   	@ApiModelProperty(value = "是否典型事故 1-是 2-否")
	private String sfdxsg;

	/**
	 * 道路安全隐患等级
	 */
   	@ApiModelProperty(value = "道路安全隐患等级")
	private String dlaqyhdj;

	/**
	 * 事故涉及人员总数
	 */
   	@ApiModelProperty(value = "事故涉及人员总数")
	private Double dsrzs;

	/**
	 * 发送机关
	 */
   	@ApiModelProperty(value = "发送机关")
	private String fsjg;

	/**
	 * 分发机关
	 */
   	@ApiModelProperty(value = "分发机关")
	private String ffjg;

	/**
	 * 隧道事故点所处位置030190
	 */
   	@ApiModelProperty(value = "隧道事故点所处位置030190")
	private String sdsgdscwz;

	/**
	 * 护栏/交通隔离栏状态030191
	 */
   	@ApiModelProperty(value = "护栏/交通隔离栏状态030191")
	private String hlzt;

	/**
	 * 坐标类型1：PGIS2：百度地图3：高德地图4：路名控件5：其他
	 */
   	@ApiModelProperty(value = "坐标类型1：PGIS2：百度地图3：高德地图4：路名控件5：其他")
	private String zblx;

	/**
	 * 案件名称
	 */
   	@ApiModelProperty(value = "案件名称")
	private String ajmc;

   	@ApiModelProperty(value = "null")
	private String dlaqyhlx;

   	@ApiModelProperty(value = "null")
	private String sgrdyyms;

}
