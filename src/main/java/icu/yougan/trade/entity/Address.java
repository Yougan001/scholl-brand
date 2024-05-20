package icu.yougan.trade.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author:油柑
 * @Date:2024/5/20 8:52
 * @Version:v1.0.0
 * @Description:地址实体类
 **/
@Data
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 收货人姓名
     */
    private String consigneeName;

    /**
     * 收货人手机号
     */
    private String consigneePhone;

    /**
     * 省
     */
    private String provinceName;

    /**
     * 市
     */
    private String cityName;

    /**
     * 区
     */
    private String regionName;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 是否默认地址
     */
    private Boolean defaultFlag;

    /**
     * 用户主键id
     */
    private Long userId;
}
