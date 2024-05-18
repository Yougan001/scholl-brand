package icu.yougan.trade.controller;

import icu.yougan.trade.entity.IdleItem;
import icu.yougan.trade.enums.ErrorMsg;
import icu.yougan.trade.service.IdleItemService;
import icu.yougan.trade.vo.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author:油柑
 * @Date:2024/5/18 22:29
 * @Version:v1.0.0
 * @Description:接口访问控制
 **/

@CrossOrigin
@RestController
@RequestMapping("idle")
public class IdleItemController {

    @Resource
    private IdleItemService idleItemService;

    /**
     * 添加闲置商品
     *
     * @param shUserId 用户id
     * @param idleItem 闲置id
     * @return 结果
     */
    @PostMapping("add")
    public R addIdleItem(
            @CookieValue("shUserId")
            @NotNull(message = "登录异常 请重新登录")
            @NotEmpty(message = "登录异常 请重新登录") String shUserId,
            @RequestBody IdleItem idleItem
    ) {
        idleItem.setUserId(Long.valueOf(shUserId));
        idleItem.setIdleStatus((byte) 1);
        idleItem.setReleaseTime(new Date());
        if (idleItemService.addIdleItem(idleItem)) {
            return R.success(idleItem);
        }
        return R.fail(ErrorMsg.SYSTEM_ERROR);
    }

    /**
     * 获取闲置信息
     *
     * @param id 闲置id
     * @return 闲置信息
     */
    @GetMapping("info")
    public R getIdleItem(@RequestParam Long id) {
        return R.success(idleItemService.getIdleItem(id));
    }

    /**
     * 查询所有闲置信息
     *
     * @param shUserId 用户id
     * @return 闲置信息
     */
    @GetMapping("all")
    public R getAllIdleItem(
            @CookieValue("shUserId")
            @NotNull(message = "登录异常 请重新登录")
            @NotEmpty(message = "登录异常 请重新登录") String shUserId
    ) {
        return R.success(idleItemService.getAllIdelItem(Long.valueOf(shUserId)));
    }

    /**
     * 查询限制
     *
     * @param findValue 值
     * @param page      分页大小
     * @param nums      页数
     * @return 闲置商品信息
     */
    @GetMapping("find")
    public R findIdleItem(
            @RequestParam(value = "findValue", required = false) String findValue,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "nums", required = false) Integer nums
    ) {
        if (null == findValue) {
            findValue = "";
        }
        int p = 1;
        int n = 8;
        if (null != page) {
            p = page > 0 ? page : 1;
        }
        if (null != nums) {
            n = nums > 0 ? nums : 8;
        }
        return R.success(idleItemService.findIdleItem(findValue, p, n));
    }

    /**
     * 查询标签
     *
     * @return 结果
     */
    @GetMapping("lable")
    public R findIdleItemByLable(
            @RequestParam(value = "idleLabel", required = true) Integer idleLabel,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "nums", required = false) Integer nums
    ) {
        int p = 1;
        int n = 8;
        if (null != page) {
            p = page > 0 ? page : 1;
        }
        if (null != nums) {
            n = nums > 0 ? nums : 8;
        }
        return R.success(idleItemService.findIdleItemByLable(idleLabel, p, n));
    }

    /**
     * 更新信息
     *
     * @param shUserId 用户id
     * @param idleItem 限制id
     * @return 更新结果
     */
    @PostMapping("update")
    public R updateIdleItem(
            @CookieValue("shUserId")
            @NotNull(message = "登录异常 请重新登录")
            @NotEmpty(message = "登录异常 请重新登录") String shUserId,
            @RequestBody IdleItem idleItem
    ) {
        idleItem.setUserId(Long.valueOf(shUserId));
        if (idleItemService.updateIdleItem(idleItem)) {
            return R.success();
        }
        return R.fail(ErrorMsg.SYSTEM_ERROR);
    }

}
