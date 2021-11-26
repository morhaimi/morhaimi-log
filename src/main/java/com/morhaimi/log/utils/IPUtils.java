package com.morhaimi.log.utils;

import cn.hutool.core.util.StrUtil;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

/**
 * @author xu
 * @date 2019/10/9 14:23
 */
public class IPUtils {
    /**
     * 获取请求的公网ip
     * @return
     */
    public static String getRequestPublicIp(){
        HttpServletRequest request = ServletUtils.getRequest();

        String ip = request.getHeader("x-forwarded-for");
        if(StrUtil.isNotEmpty(ip)){
            ip = ip.split(",")[0];
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取本地IP
     * @return
     */
    public static String getLocalIp(){
        try {
            InetAddress addr = InetAddress.getLocalHost();
            return addr.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}
