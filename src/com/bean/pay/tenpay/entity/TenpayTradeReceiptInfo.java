package com.bean.pay.tenpay.entity;

import java.io.Serializable;


/**
 * tenpay - 商户处理后同步返回给微信参数
 * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_7&index=3
 *
 * @author FXW
 *         2016年4月5日
 */
public class TenpayTradeReceiptInfo extends TenpayToWeChatBaseInfo implements Serializable {

    private static final long serialVersionUID = -57094103835426081L;

    /**
     * 返回状态码 	return_code 	是 	String(16) 	SUCCESS
     * SUCCESS/FAIL
     * SUCCESS表示商户接收通知成功并校验成功
     */
    private String return_code;
    /**
     * 返回信息 	return_msg 	否 	String(128) 	OK
     * 返回信息，如非空，为错误原因：
     * 签名失败
     * 参数格式校验错误
     */
    private String return_msg;


    /**
     * @param return_code
     * @param return_msg
     */
    private TenpayTradeReceiptInfo(String return_code, String return_msg) {
        super();
        this.return_code = return_code;
        this.return_msg = return_msg;
    }


    public static TenpayTradeReceiptInfo initSuccessInfo() {
        return new TenpayTradeReceiptInfo("SUCCESS", "OK");
    }

    public static TenpayTradeReceiptInfo initFailInfo() {
        String errMsg = "系统错误";
        return initFailInfo(errMsg);
    }

    public static TenpayTradeReceiptInfo initFailInfo(String errMsg) {
        return new TenpayTradeReceiptInfo("FAIL", errMsg);
    }

    public TenpayTradeReceiptInfo changeErrMsg(String errMsg) {
        this.return_msg = errMsg;
        return this;
    }

    @Override
    public String createRequestXML() {
        StringBuilder sbXML = new StringBuilder();
        sbXML.append("<xml>");
        sbXML.append(createXMLElement("return_code", this.return_code));
        sbXML.append(createXMLElement("return_msg", this.return_msg));
        sbXML.append("</xml>");
        return sbXML.toString();
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    @Override
    public String toString() {

        return "return_code:" + return_code + ",return_msg:" + return_msg;
    }

}
