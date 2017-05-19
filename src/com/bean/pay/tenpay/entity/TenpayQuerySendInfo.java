package com.bean.pay.tenpay.entity;

import java.io.Serializable;

import com.bean.pay.tenpay.TenpayConfig;
import com.bean.pay.tenpay.util.WXUtil;

/**
 * tenpay  查询订单请求参数
 * 示例：
     <xml>
     <appid>wx2421b1c4370ec43b</appid>
     <mch_id>10000100</mch_id>
     <nonce_str>ec2316275641faa3aacf3cc599e8730f</nonce_str>
     <transaction_id>1008450740201411110005820873</transaction_id>
     <sign>FDD167FAA73459FD921B144BAF4F4CA2</sign>
     </xml>
 * @author FXW
 *         2016年4月5日
 */
public class TenpayQuerySendInfo extends TenpayToWeChatBaseInfo implements Serializable {


    private static final long serialVersionUID = 1460233041547273647L;

    /**
     * 应用ID 	appid 	是 	String(32) 	wxd678efh567hg6787 	微信开放平台审核通过的应用APPID
     */
    private String appid;
    /**
     * 商户号 	mch_id 	是 	String(32) 	1230000109 	微信支付分配的商户号
     */
    private String mch_id;
    /**
     * 微信订单号 	transaction_id 	二选一 	String(32) 	1009660380201506130728806387 	微信的订单号，优先使用
     */
    private String transaction_id;
    /**
     * 商户订单号 	out_trade_no  二选一 	String(32) 	20150806125346 	商户系统内部的订单号，当没提供transaction_id时需要传这个。
     */
    private String out_trade_no;

    /**
     * 随机字符串 	nonce_str 	是 	String(32) 	5K8264ILTKCH16CQ2502SI8ZNMTM67VS 	随机字符串，不长于32位。推荐随机数生成算法
     */
    private String nonce_str;
    /**
     * 签名 	sign 	是 	String(32) 	C380BEC2BFD727A4B6845133519F3AD6 	签名，详见签名生成算法
     */
    private String sign;


    public static TenpayQuerySendInfo defaultInit(String out_trade_no, String transaction_id) {
        TenpayQuerySendInfo sendInfo = new TenpayQuerySendInfo();
        sendInfo.setAppid(TenpayConfig.appid);
        sendInfo.setMch_id(TenpayConfig.mchId);
        sendInfo.setNonce_str(WXUtil.getNonceStr());
        sendInfo.setOut_trade_no(out_trade_no);
        sendInfo.setTransaction_id(transaction_id);
        return sendInfo;
    }


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


}
