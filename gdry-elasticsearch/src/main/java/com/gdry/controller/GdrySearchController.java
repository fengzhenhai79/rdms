package com.gdry.controller;

import com.gdry.common.enums.ExceptionEnum;
import com.gdry.entity.SearchResponse;
import com.gdry.service.IRiverInfoService;
import com.gdry.utils.StringUtils;
import com.ruoyi.common.core.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class GdrySearchController {

    private static final Logger log = LoggerFactory.getLogger(GdrySearchController.class);


    @Autowired
    private IRiverInfoService riverInfoService;


    /**
     * 查询信息请求
     *
     * @param str http请求对象
     */
    @GetMapping("/info")
    public AjaxResult SearchInfo(@RequestParam(name = "str") String str) {
        if (StringUtils.isEmpty(str)) {
            return AjaxResult.error(ExceptionEnum.NULL_REQUEST_PARAM.getErrorMsg());
        }
        SearchResponse resp = riverInfoService.selectRiverInfoByCondition(str);
        if (StringUtils.isNotEmpty(resp.getContent())) {
            return AjaxResult.success(resp);
        } else {
            return AjaxResult.error(ExceptionEnum.NULL_RESULT.getErrorMsg());
        }
    }

}


