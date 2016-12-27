package com.tools.http.nohttp;

import com.yolanda.nohttp.rest.Response;

/**
 * 项目名称：SecondPay
 * 类描述：请求结果接口
 * 创建人：Liangtao
 * 创建时间：2016/7/30 19:33
 * 修改人：Administrator
 * 修改时间：2016/7/30 19:33
 * 修改备注：
 */
public interface HttpListener<T> {
    /**
     * 请求成功
     */
    void onSucceed(int what, Response<T> response);
    /**
     * 请求失败
     */
    void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis);

}
