/**
 *
 */
package com.lh.cloud.msg;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lh.cloud.enums.EnErrorCode;

import java.io.Serializable;

/**
 * @author linghu
 */
public class Msg<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5068272108270221337L;

    /* API调用结果 true:成功，false：失败 */
    private boolean success = true;
    // 错误码 默认成功
    private int code = EnErrorCode.DEFAULT.getCode();

    // 返回的数据，可以任意集合或对象
    private T data;

    // 结果说明
    private String msg = EnErrorCode.DEFAULT.getMsg();

    /**
     * @return the success true:成功，false：失败
     */
    public boolean getSuccess() {
        return success;
    }

    /**
     * @param success the success to set true:成功，false：失败
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the code
     * @see ApiUtils
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     * @see ApiUtils
     */
    public void setCode(int code) {
        this.code = code;
    }

    public void setCode(String code) {
        this.code = Integer.valueOf(code);
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setResult(EnErrorCode result) {
        this.code = result.getCode();
        this.msg = result.getMsg();
        this.success = result.getCode() == 0;
    }

    public String toJson() {
        Msg msg = new Msg();
        msg.setData(this.getData());
        msg.setCode(this.getCode());
        msg.setSuccess(this.getSuccess());
        msg.setMsg(this.getMsg());
        return JSONObject.toJSONString(msg);
    }
}
