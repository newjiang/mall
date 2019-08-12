package com.jiang.mall.user.api.model;

import com.jiang.mall.common.model.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: newjiang
 * @date: 2019/8/9 16:02
 * @description: todo
 **/
@Setter
@Getter
@ToString
public class User extends Entity implements Serializable {

    // 用户ID
    private String userId;

    // 用户名
    private String username;

    // 密码
    private String password;

    // 手机
    private String phone;

    // 邮箱
    private String email;

    // 昵称
    private String nickname;

    // 头像
    private String profile;

}
