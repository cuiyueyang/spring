package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <p>Description: </p>
 * <p>@date 2022/3/2 13:32</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@NoArgsConstructor
@Data
public class Test {

    @JsonProperty("status")
    private Integer status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("data")
    private List<DataDTO> data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("hazardBill")
        private HazardBillDTO hazardBill;
        @JsonProperty("hazardBillGoods")
        private List<HazardBillGoodsDTO> hazardBillGoods;
        @JsonProperty("billProblemBase")
        private List<?> billProblemBase;

        @NoArgsConstructor
        @Data
        public static class HazardBillDTO {
            @JsonProperty("id")
            private String id;
            @JsonProperty("eventId")
            private Object eventId;
            @JsonProperty("dispatchEventId")
            private String dispatchEventId;
            @JsonProperty("loadEventId")
            private Object loadEventId;
            @JsonProperty("unloadEventId")
            private Object unloadEventId;
            @JsonProperty("repEventId")
            private Object repEventId;
            @JsonProperty("creTime")
            private String creTime;
            @JsonProperty("updTime")
            private Object updTime;
            @JsonProperty("uploadTime")
            private String uploadTime;
            @JsonProperty("dvserion")
            private String dvserion;
            @JsonProperty("functionCode")
            private String functionCode;
            @JsonProperty("status")
            private String status;
            @JsonProperty("loadPlatform")
            private Object loadPlatform;
            @JsonProperty("unloadPlatform")
            private Object unloadPlatform;
            @JsonProperty("dispatchNum")
            private String dispatchNum;
            @JsonProperty("billNum")
            private String billNum;
            @JsonProperty("traIndCode")
            private String traIndCode;
            @JsonProperty("busiTypeCode")
            private String busiTypeCode;
            @JsonProperty("carEntName")
            private String carEntName;
            @JsonProperty("carUniIde")
            private Object carUniIde;
            @JsonProperty("carTel")
            private String carTel;
            @JsonProperty("carContact")
            private String carContact;
            @JsonProperty("licNum")
            private String licNum;
            @JsonProperty("shipCompName")
            private String shipCompName;
            @JsonProperty("shipTel")
            private String shipTel;
            @JsonProperty("consCompName")
            private String consCompName;
            @JsonProperty("consTel")
            private String consTel;
            @JsonProperty("loadBillNum")
            private Object loadBillNum;
            @JsonProperty("loadCreIde")
            private Object loadCreIde;
            @JsonProperty("loadInhIde")
            private Object loadInhIde;
            @JsonProperty("loaPlaceCode")
            private Object loaPlaceCode;
            @JsonProperty("loadPlace")
            private String loadPlace;
            @JsonProperty("loadArea")
            private String loadArea;
            @JsonProperty("loadEntName")
            private String loadEntName;
            @JsonProperty("loadContact")
            private String loadContact;
            @JsonProperty("loadTel")
            private String loadTel;
            @JsonProperty("loadDate")
            private Object loadDate;
            @JsonProperty("loaPerName")
            private Object loaPerName;
            @JsonProperty("loaPerIde")
            private Object loaPerIde;
            @JsonProperty("longDegr")
            private Object longDegr;
            @JsonProperty("latiDegr")
            private Object latiDegr;
            @JsonProperty("unlBillNum")
            private Object unlBillNum;
            @JsonProperty("uniCreIde")
            private Object uniCreIde;
            @JsonProperty("unlInhIde")
            private Object unlInhIde;
            @JsonProperty("unlPlace")
            private String unlPlace;
            @JsonProperty("unlArea")
            private String unlArea;
            @JsonProperty("unlEntName")
            private String unlEntName;
            @JsonProperty("unlContact")
            private String unlContact;
            @JsonProperty("unlTel")
            private String unlTel;
            @JsonProperty("transModeCode")
            private String transModeCode;
            @JsonProperty("transStatusCode")
            private Object transStatusCode;
            @JsonProperty("disVehDate")
            private String disVehDate;
            @JsonProperty("deptActDate")
            private String deptActDate;
            @JsonProperty("drvName")
            private String drvName;
            @JsonProperty("drvTel")
            private String drvTel;
            @JsonProperty("drvIde")
            private String drvIde;
            @JsonProperty("drvCert")
            private String drvCert;
            @JsonProperty("supName")
            private String supName;
            @JsonProperty("supIde")
            private String supIde;
            @JsonProperty("supTel")
            private String supTel;
            @JsonProperty("supCert")
            private String supCert;
            @JsonProperty("vehType")
            private Object vehType;
            @JsonProperty("vehNum")
            private String vehNum;
            @JsonProperty("plateType")
            private String plateType;
            @JsonProperty("transCerNum")
            private String transCerNum;
            @JsonProperty("vehRemark")
            private String vehRemark;
            @JsonProperty("totalWei")
            private Object totalWei;
            @JsonProperty("totalWeiUnit")
            private Object totalWeiUnit;
            @JsonProperty("gooClaName")
            private String gooClaName;
            @JsonProperty("signType")
            private Object signType;
            @JsonProperty("goodsName")
            private String goodsName;
            @JsonProperty("gooItemWei")
            private Double gooItemWei;
            @JsonProperty("vehId")
            private String vehId;
            @JsonProperty("carEntId")
            private String carEntId;
            @JsonProperty("areaCode")
            private String areaCode;
            @JsonProperty("vehTon")
            private Double vehTon;
            @JsonProperty("vehBillNum")
            private Object vehBillNum;
            @JsonProperty("traVehNum")
            private String traVehNum;
            @JsonProperty("traTransNum")
            private String traTransNum;
            @JsonProperty("traVehType")
            private String traVehType;
            @JsonProperty("traVehTon")
            private Double traVehTon;
            @JsonProperty("conerNum")
            private String conerNum;
            @JsonProperty("cube")
            private String cube;
            @JsonProperty("cubeUnitCode")
            private String cubeUnitCode;
            @JsonProperty("resLoadDate")
            private String resLoadDate;
            @JsonProperty("resUnlDate")
            private String resUnlDate;
            @JsonProperty("recDate")
            private Object recDate;
            @JsonProperty("remark")
            private Object remark;
            @JsonProperty("reptGooPla")
            private Object reptGooPla;
            @JsonProperty("reptArea")
            private Object reptArea;
            @JsonProperty("streetPost")
            private Object streetPost;
            @JsonProperty("consignee")
            private Object consignee;
            @JsonProperty("conName")
            private Object conName;
            @JsonProperty("phone")
            private Object phone;
            @JsonProperty("senderCode")
            private Object senderCode;
            @JsonProperty("actSenderCode")
            private String actSenderCode;
            @JsonProperty("unlDate")
            private String unlDate;
            @JsonProperty("unlPerIde")
            private Object unlPerIde;
            @JsonProperty("unlPerName")
            private Object unlPerName;
            @JsonProperty("hasPro")
            private Integer hasPro;
            @JsonProperty("dispatchHasPro")
            private Integer dispatchHasPro;
            @JsonProperty("loadHasPro")
            private Integer loadHasPro;
            @JsonProperty("unloadHasPro")
            private Integer unloadHasPro;
            @JsonProperty("repHasPro")
            private Integer repHasPro;
            @JsonProperty("checkFlag2")
            private String checkFlag2;
            @JsonProperty("checkFlag3")
            private String checkFlag3;
            @JsonProperty("loadLogName")
            private String loadLogName;
            @JsonProperty("loadLogCode")
            private String loadLogCode;
            @JsonProperty("unloadLogName")
            private String unloadLogName;
            @JsonProperty("unloadLogCode")
            private String unloadLogCode;
            @JsonProperty("shippingStatus")
            private Object shippingStatus;
            @JsonProperty("waybillType")
            private String waybillType;
            @JsonProperty("checkSafetyCode")
            private String checkSafetyCode;
            @JsonProperty("colorsLog")
            private String colorsLog;
        }

        @NoArgsConstructor
        @Data
        public static class HazardBillGoodsDTO {
            @JsonProperty("id")
            private String id;
            @JsonProperty("creTime")
            private String creTime;
            @JsonProperty("updTime")
            private Object updTime;
            @JsonProperty("uploadTime")
            private String uploadTime;
            @JsonProperty("eventId")
            private String eventId;
            @JsonProperty("senderCode")
            private String senderCode;
            @JsonProperty("actSenderCode")
            private String actSenderCode;
            @JsonProperty("status")
            private String status;
            @JsonProperty("functionCode")
            private String functionCode;
            @JsonProperty("dispatchNum")
            private String dispatchNum;
            @JsonProperty("billNum")
            private String billNum;
            @JsonProperty("artNum")
            private String artNum;
            @JsonProperty("gooClaName")
            private String gooClaName;
            @JsonProperty("hazUndgNum")
            private String hazUndgNum;
            @JsonProperty("hazGooNum")
            private Object hazGooNum;
            @JsonProperty("goodsName")
            private String goodsName;
            @JsonProperty("gooSpec")
            private String gooSpec;
            @JsonProperty("hazItemNum")
            private String hazItemNum;
            @JsonProperty("pacTypeCode")
            private String pacTypeCode;
            @JsonProperty("pacSpec")
            private String pacSpec;
            @JsonProperty("pacQuan")
            private String pacQuan;
            @JsonProperty("gooItemWei")
            private Double gooItemWei;
            @JsonProperty("weiUnit")
            private String weiUnit;
            @JsonProperty("gooRemark")
            private String gooRemark;
            @JsonProperty("volUnit")
            private String volUnit;
            @JsonProperty("volume")
            private Double volume;
            @JsonProperty("gooBillNum")
            private Object gooBillNum;
            @JsonProperty("freetext")
            private Object freetext;
            @JsonProperty("gooHsNum")
            private Object gooHsNum;
            @JsonProperty("gooArtBat")
            private Object gooArtBat;
            @JsonProperty("gooItemWeiTne")
            private Double gooItemWeiTne;
            @JsonProperty("casNumber")
            private String casNumber;
            @JsonProperty("dangChemName")
            private String dangChemName;
        }
    }
}
