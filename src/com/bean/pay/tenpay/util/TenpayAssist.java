package com.bean.pay.tenpay.util;

import com.bean.pay.tenpay.TenpayConfig;
import com.bean.pay.tenpay.TenpayErrCode;
import com.bean.pay.tenpay.TenpayErrCode.TenpayTradeErrCodeEnum;
import com.bean.pay.tenpay.client.TenpayHttpClient;
import com.bean.pay.tenpay.entity.TenpayQueryResultInfo;
import com.bean.pay.tenpay.entity.TenpayQuerySendInfo;
import com.bean.pay.tenpay.entity.TenpayTradeResultInfo;
import com.bean.pay.tenpay.entity.TenpayTradeSendInfo;

/**
 * @author FXW 2016年4月7日
 *         Baoxin 2016年9月8日
 */
public class TenpayAssist {

    /**
     * tenpay 统一下单  并返回会话信息
     *
     * @param tradeSendInfo
     * @return
     * @throws Exception
     */
    public static TenpayTradeResultInfo createTradeOrder(TenpayTradeSendInfo tradeSendInfo) throws Exception {
        String xmlParams = tradeSendInfo.createRequestXML();
        if (xmlParams == null || xmlParams.replaceAll(" ", "").length() == 0) {
            throw new Exception("封装xml失败");
        }
        TenpayHttpClient httpClient = new TenpayHttpClient();
        httpClient.charset = TenpayConfig.charsetName;
        if (httpClient.callHttpPost(TenpayConfig.createTradeUrl, xmlParams)) {
            String resContent = httpClient.getResContent();
//            System.out.println(" create tenpay trade -> response xml : \r\n" + resContent);
            TenpayTradeResultInfo tradeResult = TenpayTradeResultInfo.parseFromXML(resContent);
            if (null != tradeResult) {
                if (tradeResult.getReturn_code().equals("SUCCESS")) {
                    boolean isValid = tradeResult.verifySign();
                    if (isValid) {
                        if (!tradeResult.getResult_code().equals("SUCCESS")) {

                            TenpayTradeErrCodeEnum code = TenpayErrCode.TenpayTradeErrCodeEnum.valueOfFromCode(tradeResult.getErr_code());
                            throw new Exception(code.getDesc());
                        } else {
//                            String json = JSONObject.toJSONString(tradeResult);
//                            System.out.println("tenpay response json = " + json);
                            return tradeResult;
                        }
                    } else {
                        System.err.println(" create tenpay trade 验证签名失败,不是有效的tenpay返回信息 out_trade_no: " + tradeSendInfo.getOut_trade_no());
                        throw new Exception("验证签名失败,不是有效的tenpay返回信息");
                    }
                } else {
                    System.err.println(" create tenpay trade return_msg:" + tradeResult.getReturn_msg() + " out_trade_no: " + tradeSendInfo.getOut_trade_no());
                    throw new Exception(tradeResult.getReturn_msg());
                }
            } else {
                System.err.println(" create tenpay trade 解析tenpay返回的XML失败 out_trade_no: " + tradeSendInfo.getOut_trade_no());
                throw new Exception("解析tenpay返回的XML失败");
            }
        } else {
            System.err.println(" create tenpay trade 创建tenpay交易失败 out_trade_no: " + tradeSendInfo.getOut_trade_no());
            throw new Exception("创建tenpay交易失败");
        }
    }

    /**
     * 查询订单交易信息 并返回查询结果
     */
    public static TenpayQueryResultInfo queryTradeInfo(String out_trade_no, String transaction_id) throws Exception {
        System.out.println("query tenpay trade -> request parameters  out_trade_no : " + out_trade_no);
        TenpayQuerySendInfo sendInfo = TenpayQuerySendInfo.defaultInit(out_trade_no, transaction_id);
        String xmlParams = sendInfo.createRequestXML();
        if (xmlParams == null || xmlParams.replaceAll(" ", "").length() == 0) {
            throw new Exception("query tenpay trade 封装xml失败");
        }
        TenpayHttpClient httpClient = new TenpayHttpClient();
        httpClient.charset = TenpayConfig.charsetName;
        if (httpClient.callHttpPost(TenpayConfig.queryTradeUrl, xmlParams)) {
            String resContent = httpClient.getResContent();

            TenpayQueryResultInfo queryResult = TenpayQueryResultInfo.parseFromXML(resContent);
            if (null != queryResult) {
                if (queryResult.getReturn_code().equals("SUCCESS")) {
                    boolean isValid = queryResult.verifySign();
                    if (isValid) {
                        if (!queryResult.getResult_code().equals("SUCCESS")) {
                            TenpayTradeErrCodeEnum code = TenpayErrCode.TenpayTradeErrCodeEnum.valueOfFromCode(queryResult.getErr_code());
                            throw new Exception(code.getDesc());
                        } else {
                            return queryResult;
                        }
                    } else {
                        throw new Exception("query tenpay trade 不是有效的返回信息---验证签名失败");
                    }
                } else {
                    throw new Exception("query tenpay trade " + queryResult.getReturn_msg());
                }
            } else {
                throw new Exception("query tenpay trade 解析XML失败");
            }
        } else {
            throw new Exception("query tenpay trade 查询订单失败");
        }

    }



}
