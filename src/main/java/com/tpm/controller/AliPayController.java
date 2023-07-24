package com.tpm.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.tpm.Utils.AlipayConfig;
import com.tpm.entity.JsonResult;
import com.tpm.entity.Ticket;
import com.tpm.service.SessionSeatService;
import com.tpm.service.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@RequestMapping("/alipay")
public class AliPayController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AlipayConfig alipayConfig;

    @Autowired
    TicketService ticketService;

    @Autowired
    SessionSeatService sessionSeatService;

    @PostMapping("pay")
    public JsonResult payController(Ticket ticket) throws IOException {
        System.out.println(ticket);
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(
                alipayConfig.gatewayUrl,
                alipayConfig.getAPP_ID(),
                alipayConfig.getAPP_PRIVATE_KEY(), "json",
                alipayConfig.CHARSET,
                alipayConfig.getALIPAY_PUBLIC_KEY(),
                alipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(alipayConfig.getReturn_url());
        alipayRequest.setNotifyUrl(alipayConfig.getNotify_url());

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(String.valueOf(ticket.getTid()).getBytes(StandardCharsets.UTF_8),"UTF-8");
        //付款金额，必填
        String total_amount = new String(String.valueOf(ticket.getTmoney()).getBytes(StandardCharsets.UTF_8),"UTF-8");
        //订单名称，必填
        String tn = "淘票猫订单编号" + ticket.getTid();
        String subject = new String(tn.getBytes(StandardCharsets.UTF_8),"UTF-8");
        //商品描述，可空
        String bd = "淘票猫购票："+ticket.getTcontext();
        String body = new String(bd.getBytes(StandardCharsets.UTF_8),"UTF-8");;
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\"2m\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求
        String alipayForm="";
        try {
            alipayForm = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
//        response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
//        response.getWriter().write(alipayForm);//直接将完整的表单html输出到页面
//        response.getWriter().flush();
//        response.getWriter().close();
        logger.info("支付宝支付 - 前往支付页面, alipayForm: \n{}"+ alipayForm);

        return new JsonResult(alipayForm);
    }

    /**
     * 支付成功后的支付宝异步通知
     */
    @RequestMapping(value="notify")
    public String alipay(HttpServletRequest request, HttpServletResponse response) throws Exception {

        logger.info("支付成功后的支付宝异步通知");

        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
//			valueStr = new String(valueStr.getBytes(StandardCharsets.UTF_8), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params,
                alipayConfig.getALIPAY_PUBLIC_KEY(),
                alipayConfig.CHARSET,
                alipayConfig.sign_type); //调用SDK验证签名

        if(signVerified) {//验证成功
            // 商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes(StandardCharsets.UTF_8),"UTF-8");
            // 支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes(StandardCharsets.UTF_8),"UTF-8");
            // 交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes(StandardCharsets.UTF_8),"UTF-8");
            // 付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes(StandardCharsets.UTF_8),"UTF-8");

            if (trade_status.equals("TRADE_SUCCESS")){
//                String merchantReturnUrl = paymentOrderService.updateOrderPaid(out_trade_no, CurrencyUtils.getYuan2Fen(total_amount));
//                notifyFoodieShop(out_trade_no,merchantReturnUrl);
            }

            logger.info("************* 支付成功(支付宝异步通知) - 时间: {} *************"+ System.currentTimeMillis());
            logger.info("* 订单号: {}"+ out_trade_no);
            logger.info("* 支付宝交易号: {}"+ trade_no);
            logger.info("* 实付金额: {}"+ total_amount);
            logger.info("* 交易状态: {}"+ trade_status);
            logger.info("*****************************************************************************");

            Ticket ticket = ticketService.getById(Integer.valueOf(out_trade_no));
            ticket.setStatus(1);
            boolean is = ticketService.updateById(ticket);
            System.out.println("status---->"+is);
            if(!is){
                logger.info("修改订单状态失败");
            }
            return "success";
        }else {
            //验证失败
            logger.info("验签失败, 时间: {}"+ System.currentTimeMillis());
            return "fail";
        }
    }


}
