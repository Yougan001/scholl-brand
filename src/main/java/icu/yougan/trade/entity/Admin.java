package icu.yougan.trade.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 管理员 实体对象
 *
 * @author: YouGan
 * @date: 2024-05-17
 */
@Data
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    private Long id;

    /**
     * 管理员账号
     */
    private String accountNumber;

    /**
     * 密码
     */
    private String adminPassword;

    /**
     * 管理员名字
     */
    private String adminName;

}