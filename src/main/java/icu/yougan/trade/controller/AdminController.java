package icu.yougan.trade.controller;


import icu.yougan.trade.entity.Admin;
import icu.yougan.trade.enums.ErrorMsg;
import icu.yougan.trade.service.AdminService;
import icu.yougan.trade.service.UserService;
import icu.yougan.trade.vo.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 管理员相关 控制层
 *
 * @author: YouGan
 * @date: 2024-05-17
 */
@CrossOrigin
@RestController
@RequestMapping("admin")
public class AdminController {

    @Resource
    private AdminService adminService;


    @Resource
    private UserService userService;

    /**
     * 管理员登录
     *
     * @param accountNumber 账号
     * @param adminPassword 密码
     * @param session       session
     * @return 登录结果
     */
    @GetMapping("login")
    public R login(
            @RequestParam("accountNumber") @NotNull @NotEmpty String accountNumber,
            @RequestParam("adminPassword") @NotNull @NotEmpty String adminPassword,
            HttpSession session
    ) {
        Admin admin = adminService.login(accountNumber, adminPassword);
        if (null == admin) {
            return R.fail(ErrorMsg.EMAIL_LOGIN_ERROR);
        }
        session.setAttribute("admin", admin);
        return R.success(admin);
    }

    /**
     * 管理员登出
     *
     * @param session session
     * @return 结果
     */
    @GetMapping("loginOut")
    public R loginOut(HttpSession session) {
        session.removeAttribute("admin");
        return R.success();
    }

    /**
     * 查询管理员列表
     *
     * @param session session
     * @param page    分页大小
     * @param nums    分页页数
     * @return 管理员列表
     */
    @GetMapping("list")
    public R getAdminList(
            HttpSession session,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "nums", required = false) Integer nums
    ) {
        if (session.getAttribute("admin") == null) {
            return R.fail(ErrorMsg.COOKIE_ERROR);
        }
        int p = 1;
        int n = 8;
        if (null != page) {
            p = page > 0 ? page : 1;
        }
        if (null != nums) {
            n = nums > 0 ? nums : 8;
        }
        return R.success(adminService.getAdminList(p, n));
    }

    /**
     * 添加管理员
     *
     * @param session session
     * @param admin   管理员信息
     * @return 结果
     */
    @PostMapping("add")
    public R addAdmin(
            HttpSession session,
            @RequestBody Admin admin
    ) {
        if (session.getAttribute("admin") == null) {
            return R.fail(ErrorMsg.COOKIE_ERROR);
        }
        if (adminService.addAdmin(admin)) {
            return R.success();
        }
        return R.fail(ErrorMsg.PARAM_ERROR);
    }


}
