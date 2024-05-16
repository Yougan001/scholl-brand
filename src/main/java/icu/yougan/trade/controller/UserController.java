package icu.yougan.trade.controller;

import icu.yougan.trade.entity.User;
import icu.yougan.trade.enums.ErrorMsg;
import icu.yougan.trade.service.UserService;
import icu.yougan.trade.vo.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 用户相关 控制层
 *
 * @author: YougGan
 * @date: 2024-05-15
 */
@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 注册账号
     *
     * @param user 用户信息
     * @return 结果
     */
    @PostMapping("sign-in")
    public R signIn(@RequestBody User user) {
        user.setSignInTime(new Timestamp(System.currentTimeMillis()));
        if (user.getAvatar() == null || "".equals(user.getAvatar())) {
            user.setAvatar("https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png");
        }
        if (userService.userSignIn(user)) {
            return R.success(user);
        }
        return R.fail(ErrorMsg.REGISTER_ERROR);
    }

    /**
     * 登录
     *
     * @param accountNumber 账号
     * @param userPassword  密码
     * @return 登录结果
     */
    @RequestMapping("login")
    public R login(
            @RequestParam("accountNumber") @NotEmpty @NotNull String accountNumber,
            @RequestParam("userPassword") @NotEmpty @NotNull String userPassword,
            HttpServletResponse response
    ) {
        User user = userService.userLogin(accountNumber, userPassword);
        if (null == user) {
            return R.fail(ErrorMsg.EMAIL_LOGIN_ERROR);
        }
        if (user.getUserStatus() != null && user.getUserStatus().equals((byte) 1)) {
            return R.fail(ErrorMsg.ACCOUNT_Ban);
        }
        Cookie cookie = new Cookie("shUserId", String.valueOf(user.getId()));
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        response.addCookie(cookie);
        return R.success(user);
    }

    /**
     * 退出登录
     *
     * @param shUserId 用户id
     * @return 结果
     */
    @RequestMapping("logout")
    public R logout(
            @CookieValue("shUserId")
            @NotNull(message = "登录异常 请重新登录")
            @NotEmpty(message = "登录异常 请重新登录") String shUserId, HttpServletResponse response
    ) {
        Cookie cookie = new Cookie("shUserId", shUserId);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return R.success();
    }

    /**
     * 获取用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    @GetMapping("info")
    public R getOneUser(
            @CookieValue("shUserId") @NotNull(message = "登录异常 请重新登录")
            @NotEmpty(message = "登录异常 请重新登录")
            String id
    ) {
        return R.success(userService.getUser(Long.valueOf(id)));
    }

    /**
     * 修改用户公开信息
     *
     * @param id   用户id
     * @param user 用户信息
     * @return 修改结果
     */
    @PostMapping("/info")
    public R updateUserPublicInfo(@CookieValue("shUserId") @NotNull(message = "登录异常 请重新登录")
                                  @NotEmpty(message = "登录异常 请重新登录")
                                  String id, @RequestBody User user) {
        user.setId(Long.valueOf(id));
        if (userService.updateUserInfo(user)) {
            return R.success();
        }
        return R.fail(ErrorMsg.SYSTEM_ERROR);
    }


    /**
     * 修改密码
     *
     * @param id          用户id
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    @GetMapping("/password")
    public R updateUserPassword(
            @CookieValue("shUserId") @NotNull(message = "登录异常 请重新登录")
            @NotEmpty(message = "登录异常 请重新登录") String id,
            @RequestParam("oldPassword") @NotEmpty @NotNull String oldPassword,
            @RequestParam("newPassword") @NotEmpty @NotNull String newPassword) {
        if (userService.updatePassword(newPassword, oldPassword, Long.valueOf(id))
        ) {
            return R.success();
        }
        return R.fail(ErrorMsg.PASSWORD_RESET_ERROR);
    }
}
