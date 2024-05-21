package icu.yougan.trade.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:油柑
 * @Date:2024/5/21 10:36
 * @Version:v1.0.0
 * @Description:收藏 实体类
 **/
@Data
public class Favorite implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 自增主键id
     */
    private Long id;

    /**
     * 加入收藏的时间
     */
    private Date createTime;

    /**
     * 用户主键id
     */
    private Long userId;

    /**
     * 闲置物主键id
     */
    private Long idleId;

    private IdleItem idleItem;



}
