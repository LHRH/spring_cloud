package com.lh.cloud.enums;

/**
 * Created by linghu on 2018/3/9
 */
public enum EnErrorCode {
    // 初始值
    DEFAULT(0, "成功"),
    // 成功
    SUCCESS(0, "成功"),
    // 链接网络失败
    LINK_FAILURE(1, "链接网络失败"),
    // token过期
    TOKEN_EXPIRED(2, "token过期"),
    /* 数据不存在 */
    DATA_NONEXIST(3, "数据不存在"),
    /* SQL错误 */
    DATA_SQL_ERR(4, "SQL错误"),
    /* 参数存在不能为空的字段 */
    NONEMPTY(5, "参数存在不能为空的字段"),
    /* 数据已存在 */
    DATA_EXIST(6, "数据已存在"),
    /* 数据不匹配 */
    DATA_NO_MATCH(7, "数据不匹配"),
    /* 请求超时 */
    LINK_TIME_OUT(8, "请求超时"),
    /* 校验码错误 */
    WRONG_AUTHCODE(9, "校验码错误"),
    /* 无访问权限 */
    PERMISSION_ERROR(10, "无访问权限"),
    /*无效*/
    INVALID(11, "无效"),
    /*参数不能为空*/
    CODE_NONEMPTY(12, "参数不能为空"),

    NO_MATCH(13,"没有匹配项"),
    /*已被自己绑定*/
    SELFBINDED(101, "请勿重复绑定"),
    /* 特殊 */
    SPECIAL(999, "特殊"),
    INSERT_NONE(998, "创建失败"),
    DELETE_NONE(997, "删除失败"),
    UPDATE_NONE(996, "更新失败");


    private int code;
    private String msg;

    EnErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * Getter for property 'code'.
     *
     * @return Value for property 'code'.
     */
    public int getCode() {
        return code;
    }

    /**
     * Getter for property 'msg'.
     *
     * @return Value for property 'msg'.
     */
    public String getMsg() {
        return msg;
    }
}
