package com.bean.pay.tenpay.entity;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.jdom2.JDOMException;

import com.bean.pay.tenpay.util.XMLUtil;
import com.bean.util.BeanMapUtils;

/**
 * tenpay - 查询订单返回参数
 * https://pay.weixin.qq.com/wiki/doc/api/app/app.php?chapter=9_2&index=4
 * 示例：
     <xml>
     <return_code><![CDATA[SUCCESS]]></return_code>
     <return_msg><![CDATA[OK]]></return_msg>
     <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
     <mch_id><![CDATA[10000100]]></mch_id>
     <device_info><![CDATA[1000]]></device_info>
     <nonce_str><![CDATA[TN55wO9Pba5yENl8]]></nonce_str>
     <sign><![CDATA[BDF0099C15FF7BC6B1585FBB110AB635]]></sign>
     <result_code><![CDATA[SUCCESS]]></result_code>
     <openid><![CDATA[oUpF8uN95-Ptaags6E_roPHg7AG0]]></openid>
     <is_subscribe><![CDATA[Y]]></is_subscribe>
     <trade_type><![CDATA[APP]]></trade_type>
     <bank_type><![CDATA[CCB_DEBIT]]></bank_type>
     <total_fee>1</total_fee>
     <fee_type><![CDATA[CNY]]></fee_type>
     <transaction_id><![CDATA[1008450740201411110005820873]]></transaction_id>
     <out_trade_no><![CDATA[1415757673]]></out_trade_no>
     <attach><![CDATA[订单额外描述]]></attach>
     <time_end><![CDATA[20141111170043]]></time_end>
     <trade_state><![CDATA[SUCCESS]]></trade_state>
     </xml>
 * @author FXW
 *         2016年4月5日
 */
public class TenpayQueryResultInfo extends TenpayFromWeChatBaseInfo implements
        Serializable {

    private static final long serialVersionUID = -793886876367014538L;

    /**
     * 返回状态码 	return_code 	是 	String(16) 	SUCCESS
     * SUCCESS/FAIL
     * 此字段是通信标识，非交易标识，交易是否成功需要查看trade_state来判断
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
    //应用APPID 	appid 	是 	String(32) 	wxd678efh567hg6787 	微信开放平台审核通过的应用APPID
    private String appid;
    //商户号 	mch_id 	是 	String(32) 	1230000109 	微信支付分配的商户号
    private String mch_id;
    //随机字符串 	nonce_str 	是 	String(32) 	5K8264ILTKCH16CQ2502SI8ZNMTM67VS 	随机字符串，不长于32位。推荐随机数生成算法
    private String nonce_str;
    //签名 	sign 	是 	String(32) 	C380BEC2BFD727A4B6845133519F3AD6 	签名，详见签名生成算法
    private String sign;
    //业务结果 	result_code 	是 	String(16) 	SUCCESS 	SUCCESS/FAIL
    private String result_code;
    //错误代码 	err_code 	否 	String(32) 	SYSTEMERROR 	错误码
    private String err_code;
    //错误代码描述 	err_code_des 	否 	String(128) 	系统错误 	结果信息描述
    private String err_code_des;

    /**
     * 以下字段在return_code 和result_code都为SUCCESS的时候有返回
     */
    //设备号 	device_info 	否 	String(32) 	013467007045764 	微信支付分配的终端设备号，
    private String device_info;
    //用户标识 	openid 	是 	String(128) 	oUpF8uMuAJO_M2pxb1Q9zNjWeS6o 	用户在商户appid下的唯一标识
    private String openid;
    //是否关注公众账号 	is_subscribe 	否 	String(1) 	Y 	用户是否关注公众账号，Y-关注，N-未关注，仅在公众账号类型支付有效
    private String is_subscribe;
    //交易类型 	trade_type 	是 	String(16) 	APP 	调用接口提交的交易类型
    private String trade_type;
    /**
     * 交易状态 	trade_state 	是 	String(32) 	SUCCESS
     * SUCCESS—支付成功
     * REFUND—转入退款
     * NOTPAY—未支付
     * CLOSED—已关闭
     * REVOKED—已撤销（刷卡支付）
     * USERPAYING--用户支付中
     * PAYERROR--支付失败(其他原因，如银行返回失败)
     */
    private String trade_state;
    //付款银行 	bank_type 	是 	String(16) 	CMC 	银行类型，采用字符串类型的银行标识
    private String bank_type;
    //总金额 	total_fee 	是 	Int 	100 	订单总金额，单位为分
    private Integer total_fee;
    //货币种类 	fee_type 	否 	String(8) 	CNY 	货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    private String fee_type;
    //现金支付金额 	cash_fee 	是 	Int 	100 	现金支付金额订单现金支付金额，详见支付金额
    private Integer cash_fee;
    //现金支付货币类型 	cash_fee_type 	否 	String(16) 	CNY 	货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    private String cash_fee_type;
    //代金券或立减优惠金额 	coupon_fee 	否 	Int 	100 	“代金券或立减优惠”金额<=订单总金额，订单总金额-“代金券或立减优惠”金额=现金支付金额，详见支付金额
    private Integer coupon_fee;
    //代金券或立减优惠使用数量 	coupon_count 	否 	Int 	1 	代金券或立减优惠使用数量
    private Integer coupon_count;
    //代金券或立减优惠批次ID 	coupon_batch_id_$n 	否 	String(20) 	100 	代金券或立减优惠批次ID ,$n为下标，从0开始编号
    private String coupon_batch_id_0;
    private String coupon_batch_id_1;
    private String coupon_batch_id_2;
    private String coupon_batch_id_3;
    //代金券或立减优惠ID 	coupon_id_$n 	否 	String(20) 	10000  	代金券或立减优惠ID, $n为下标，从0开始编号
    private String coupon_id_0;
    private String coupon_id_1;
    private String coupon_id_2;
    private String coupon_id_3;
    //单个代金券或立减优惠支付金额 	coupon_fee_$n 	否 	Int 	100 	单个代金券或立减优惠支付金额, $n为下标，从0开始编号
    private Integer coupon_fee_0;
    private Integer coupon_fee_1;
    private Integer coupon_fee_2;
    private Integer coupon_fee_3;
    //微信支付订单号 	transaction_id 	是 	String(32) 	1009660380201506130728806387 	微信支付订单号
    private String transaction_id;
    //商户订单号 	out_trade_no 	是 	String(32) 	20150806125346 	商户系统的订单号，与请求一致。
    private String out_trade_no;
    //附加数据 	attach 	否 	String(128) 	深圳分店 	附加数据，原样返回
    private String attach;
    //支付完成时间 	time_end 	是 	String(14) 	20141030133525 	订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
    private String time_end;
    //交易状态描述 	trade_state_desc 	是 	String(256) 	支付失败，请重新下单支付 	对当前查询订单状态的描述和下一步操作的指引
    private String trade_state_desc;

    public static TenpayQueryResultInfo parseFromXML(String notifyXML) {
        TenpayQueryResultInfo queryResultInfo = null;
        try {
            Map map = XMLUtil.doXMLParse(notifyXML);
            queryResultInfo = BeanMapUtils.toBean(TenpayQueryResultInfo.class, map);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return queryResultInfo;
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

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
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

    public String getTrade_state() {
        return trade_state;
    }

    public void setTrade_state(String trade_state) {
        this.trade_state = trade_state;
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

    public String getCoupon_batch_id_0() {
        return coupon_batch_id_0;
    }

    public void setCoupon_batch_id_0(String coupon_batch_id_0) {
        this.coupon_batch_id_0 = coupon_batch_id_0;
    }

    public String getCoupon_batch_id_1() {
        return coupon_batch_id_1;
    }

    public void setCoupon_batch_id_1(String coupon_batch_id_1) {
        this.coupon_batch_id_1 = coupon_batch_id_1;
    }

    public String getCoupon_batch_id_2() {
        return coupon_batch_id_2;
    }

    public void setCoupon_batch_id_2(String coupon_batch_id_2) {
        this.coupon_batch_id_2 = coupon_batch_id_2;
    }

    public String getCoupon_batch_id_3() {
        return coupon_batch_id_3;
    }

    public void setCoupon_batch_id_3(String coupon_batch_id_3) {
        this.coupon_batch_id_3 = coupon_batch_id_3;
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

    public String getTrade_state_desc() {
        return trade_state_desc;
    }

    public void setTrade_state_desc(String trade_state_desc) {
        this.trade_state_desc = trade_state_desc;
    }

}
