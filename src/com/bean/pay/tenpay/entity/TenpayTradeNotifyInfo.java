package com.bean.pay.tenpay.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import com.bean.pay.tenpay.TenpayConfig;
import com.bean.pay.tenpay.util.WXUtil;
import com.bean.pay.tenpay.util.XMLUtil;
import com.bean.util.BeanMapUtils;

/**
 * 微信app支付 - 支付结果通用通知
 * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_7&index=3
 * <p/>
 * 示例：
     <xml>
     <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
     <attach><![CDATA[支付测试]]></attach>
     <bank_type><![CDATA[CFT]]></bank_type>
     <fee_type><![CDATA[CNY]]></fee_type>
     <is_subscribe><![CDATA[Y]]></is_subscribe>
     <mch_id><![CDATA[10000100]]></mch_id>
     <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>
     <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>
     <out_trade_no><![CDATA[1409811653]]></out_trade_no>
     <result_code><![CDATA[SUCCESS]]></result_code>
     <return_code><![CDATA[SUCCESS]]></return_code>
     <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>
     <sub_mch_id><![CDATA[10000100]]></sub_mch_id>
     <time_end><![CDATA[20140903131540]]></time_end>
     <total_fee>1</total_fee>
     <trade_type><![CDATA[JSAPI]]></trade_type>
     <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>
     </xml>
 *
 * @author FXW
 *         2016年4月5日
 */
public class TenpayTradeNotifyInfo extends TenpayFromWeChatBaseInfo implements Serializable {

    private static final long serialVersionUID = 484432416581071085L;

    /**
     * 返回状态码 	return_code 	是 	String(16) 	SUCCESS
     * SUCCESS/FAIL
     * 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
     */
    private String return_code;
    /**
     * 返回信息 	return_msg 	否 	String(128) 	签名失败
     * 返回信息，如非空，为错误原因
     * 签名失败
     * 参数格式校验错误
     */
    private String return_msg;

    /**
     * 以下字段在return_code为SUCCESS的时候有返回
     */
    //应用ID 	appid 	是 	String(32) 	wx8888888888888888 	微信开放平台审核通过的应用APPID
    private String appid;
    //商户号 	mch_id 	是 	String(32) 	1900000109 	微信支付分配的商户号
    private String mch_id;
    //设备号 	device_info 	否 	String(32) 	013467007045764 	微信支付分配的终端设备号，
    private String device_info;
    //随机字符串 	nonce_str 	是 	String(32) 	5K8264ILTKCH16CQ2502SI8ZNMTM67VS 	随机字符串，不长于32位
    private String nonce_str;
    //签名 	sign 	是 	String(32) 	C380BEC2BFD727A4B6845133519F3AD6 	签名，详见签名算法
    private String sign;
    //业务结果 	result_code 	是 	String(16) 	SUCCESS 	SUCCESS/FAIL
    private String result_code;
    //错误代码 	err_code 	否 	String(32) 	SYSTEMERROR 	错误返回的信息描述
    private String err_code;
    //错误代码描述 	err_code_des 	否 	String(128) 	系统错误 	错误返回的信息描述
    private String err_code_des;
    //用户标识 	openid 	是 	String(128) 	wxd930ea5d5a258f4f 	用户在商户appid下的唯一标识
    private String openid;
    //是否关注公众账号 	is_subscribe 	否 	String(1) 	Y 	用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
    private String is_subscribe;
    //交易类型 	trade_type 	是 	String(16) 	APP 	APP
    private String trade_type;
    //付款银行 	bank_type 	是 	String(16) 	CMC 	银行类型，采用字符串类型的银行标识，银行类型见银行列表
    private String bank_type;
    //总金额 	total_fee 	是 	Int 	100 	订单总金额，单位为分
    private Integer total_fee;
    //货币种类 	fee_type 	否 	String(8) 	CNY 	货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    private String fee_type;
    //现金支付金额 	cash_fee 	是 	Int 	100 	现金支付金额订单现金支付金额，详见支付金额
    private Integer cash_fee;
    //现金支付货币类型 	cash_fee_type 	否 	String(16) 	CNY 	货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    private String cash_fee_type;
    //代金券或立减优惠金额 	coupon_fee 	否 	Int 	10 	代金券或立减优惠金额<=订单总金额，订单总金额-代金券或立减优惠金额=现金支付金额，详见支付金额
    private Integer coupon_fee;
    //代金券或立减优惠使用数量 	coupon_count 	否 	Int 	1 	代金券或立减优惠使用数量
    private Integer coupon_count;
    //代金券或立减优惠ID 	coupon_id_$n 	否 	String(20) 	10000 	代金券或立减优惠ID,$n为下标，从0开始编号
    private String coupon_id_0;
    private String coupon_id_1;
    private String coupon_id_2;
    private String coupon_id_3;
    //单个代金券或立减优惠支付金额 	coupon_fee_$n 	否 	Int 	100 	单个代金券或立减优惠支付金额,$n为下标，从0开始编号
    private Integer coupon_fee_0;
    private Integer coupon_fee_1;
    private Integer coupon_fee_2;
    private Integer coupon_fee_3;
    //微信支付订单号 	transaction_id 	是 	String(32) 	1217752501201407033233368018 	微信支付订单号
    private String transaction_id;
    //商户订单号 	out_trade_no 	是 	String(32) 	1212321211201407033568112322 	商户系统的订单号，与请求一致。
    private String out_trade_no;
    //商家数据包 	attach 	否 	String(128) 	123456 	商家数据包，原样返回
    private String attach;
    //支付完成时间 	time_end 	是 	String(14) 	20141030133525 	支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
    private String time_end;

    public static TenpayTradeNotifyInfo parseFromXML(String notifyXML) {
        TenpayTradeNotifyInfo tradeNotifyInfo = null;
        try {
            Map map = XMLUtil.doXMLParse(notifyXML);
            //System.out.println("TenpayTradeNotifyInfo parse from xml parameters : "+map);
            if (map != null) {
                tradeNotifyInfo = BeanMapUtils.toBean(TenpayTradeNotifyInfo.class, map);
            }
        } catch (Exception e) {
            System.out.println("TenpayTradeNotifyInfo 127:"+e.getMessage());
        }
        return tradeNotifyInfo;
    }

    public static TenpayTradeNotifyInfo testInit(String out_trade_no, String transaction_id, int total_fee)
    {
        TenpayTradeNotifyInfo tradeNotifyInfo = new TenpayTradeNotifyInfo();
        tradeNotifyInfo.setAppid(TenpayConfig.appid);
        tradeNotifyInfo.setMch_id(TenpayConfig.mchId);
        tradeNotifyInfo.setNonce_str(WXUtil.getNonceStr());
        tradeNotifyInfo.setTrade_type(TenpayConfig.tradeType);
        tradeNotifyInfo.setDevice_info(TenpayConfig.deviceInfo);
        tradeNotifyInfo.setTotal_fee(total_fee);
        tradeNotifyInfo.setAttach("notify附加信息-attach");
        if (out_trade_no != null)
            tradeNotifyInfo.setOut_trade_no(out_trade_no);
        if (transaction_id != null)
            tradeNotifyInfo.setTransaction_id(transaction_id);
        return tradeNotifyInfo;
    }

    /**
     * 生成支付交易号  eg:20150914100250-9545
     *
     * @return
     */
    private static String generatePayBatchNo() {
        String batchNo = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String datetime = sdf.format(new Date());
        int randomNo = new Random().nextInt(10000);
        batchNo = datetime + "-" + randomNo;
        return batchNo;
    }

    public static void main(String[] args) {
        String xml = "<xml><appid><![CDATA[wx12a61b52628dfff1]]></appid>"
                + " <attach><![CDATA[用户发布约单交易信息]]></attach>"
                + " <bank_type><![CDATA[CMB_DEBIT]]></bank_type>"
                + " <cash_fee><![CDATA[1]]></cash_fee>"
                + " <device_info><![CDATA[WEB]]></device_info>"
                + " <fee_type><![CDATA[CNY]]></fee_type>"
                + " <is_subscribe><![CDATA[N]]></is_subscribe>"
                + " <mch_id><![CDATA[1326673901]]></mch_id>"
                + " <nonce_str><![CDATA[8a94ecfa54dcb88a2fa993bfa6388f9e]]></nonce_str>"
                + " <openid><![CDATA[odj03uHhN_SoK3_KWv_92cCa-cKo]]></openid>"
                + " <out_trade_no><![CDATA[20160414142122-882936832]]></out_trade_no>"
                + " <result_code><![CDATA[SUCCESS]]></result_code>"
                + " <return_code><![CDATA[SUCCESS]]></return_code>"
                + " <sign><![CDATA[4345298D040DA26894AFD559C3D4D9D1]]></sign>"
                + " <time_end><![CDATA[20160414142155]]></time_end>"
                + " <total_fee>1</total_fee>"
                + " <trade_type><![CDATA[APP]]></trade_type>"
                + " <transaction_id><![CDATA[4005362001201604144828115875]]></transaction_id>"
                + " </xml>";
        TenpayTradeNotifyInfo notify = TenpayTradeNotifyInfo.parseFromXML(xml);

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

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
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

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getIs_subscribe() {
        return is_subscribe;
    }

    public void setIs_subscribe(String is_subscribe) {
        this.is_subscribe = is_subscribe;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getBank_type() {
        return bank_type;
    }

    public void setBank_type(String bank_type) {
        this.bank_type = bank_type;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Integer getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(Integer cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public Integer getCoupon_fee() {
        return coupon_fee;
    }

    public void setCoupon_fee(Integer coupon_fee) {
        this.coupon_fee = coupon_fee;
    }

    public Integer getCoupon_count() {
        return coupon_count;
    }

    public void setCoupon_count(Integer coupon_count) {
        this.coupon_count = coupon_count;
    }

    public String getCoupon_id_0() {
        return coupon_id_0;
    }

    public void setCoupon_id_0(String coupon_id_0) {
        this.coupon_id_0 = coupon_id_0;
    }

    public String getCoupon_id_1() {
        return coupon_id_1;
    }

    public void setCoupon_id_1(String coupon_id_1) {
        this.coupon_id_1 = coupon_id_1;
    }

    public String getCoupon_id_2() {
        return coupon_id_2;
    }

    public void setCoupon_id_2(String coupon_id_2) {
        this.coupon_id_2 = coupon_id_2;
    }

    public String getCoupon_id_3() {
        return coupon_id_3;
    }

    public void setCoupon_id_3(String coupon_id_3) {
        this.coupon_id_3 = coupon_id_3;
    }

    public Integer getCoupon_fee_0() {
        return coupon_fee_0;
    }

    public void setCoupon_fee_0(Integer coupon_fee_0) {
        this.coupon_fee_0 = coupon_fee_0;
    }

    public Integer getCoupon_fee_1() {
        return coupon_fee_1;
    }

    public void setCoupon_fee_1(Integer coupon_fee_1) {
        this.coupon_fee_1 = coupon_fee_1;
    }

    public Integer getCoupon_fee_2() {
        return coupon_fee_2;
    }

    public void setCoupon_fee_2(Integer coupon_fee_2) {
        this.coupon_fee_2 = coupon_fee_2;
    }

    public Integer getCoupon_fee_3() {
        return coupon_fee_3;
    }

    public void setCoupon_fee_3(Integer coupon_fee_3) {
        this.coupon_fee_3 = coupon_fee_3;
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

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

}
