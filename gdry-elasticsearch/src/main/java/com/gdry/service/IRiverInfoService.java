package com.gdry.service;

import com.gdry.entity.SearchResponse;

import javax.jws.WebService;

/**
 * 河流信息 服务层
 *
 */
@WebService
public interface IRiverInfoService {

    /**
     * 查询参数配置信息
     *
     * @param str 前台请求的字符串
     * @return 河道信息集合
     */
    public SearchResponse selectRiverInfoByCondition(String str);

}
