package com.gdry.controller;

import com.gdry.common.enums.ExceptionEnum;
import com.gdry.entity.KeyWordInfo;
import com.gdry.service.IKeyWordService;
import com.ruoyi.common.core.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/key/word")
public class GdryKeyWordController {

    @Autowired
    IKeyWordService keyWordService;

    private static final Logger log = LoggerFactory.getLogger(GdrySearchController.class);

    /**
     * 查询关键字请求
     *
     * @param keyWord 关键字
     * @param type 类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     */
    @GetMapping("/select")
    public AjaxResult selectKeyword(@RequestParam(value = "key_word", required = false) String keyWord,
                                 @RequestParam(value = "type", required = false) Integer type,
                                 @RequestParam(value = "start_time", required = false) String startTime,
                                 @RequestParam(value = "end_time", required = false) String endTime) {
        List<KeyWordInfo> keyWordInfos = keyWordService.selectKeyWordInfoList(keyWord, type, startTime, endTime);
        if (CollectionUtils.isEmpty(keyWordInfos)) {
            return AjaxResult.error(ExceptionEnum.NULL_RESULT.getErrorMsg());
        } else {
            return AjaxResult.success(keyWordInfos);
        }
    }

    /**
     * 添加关键字请求
     *
     * @param req 请求体
     */
    @PostMapping("/add")
    public AjaxResult addKeyword(@Validated @RequestBody KeyWordInfo req) {
        try {
            int i = keyWordService.addKeyWordInfo(req);
            if (i > 0) {
                return AjaxResult.success();
            } else {
                return AjaxResult.error(ExceptionEnum.ADD_FAILED.getErrorMsg());
            }
        } catch (IOException e) {
            log.error(ExceptionEnum.WRITE_TO_DOCUMENT_FAILED.getErrorMsg(), e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改关键字请求
     *
     * @param req 请求体
     */
    @PostMapping("/update")
    public AjaxResult updateKeyword(@Validated @RequestBody KeyWordInfo req) {
            int i = keyWordService.updateKeyWordInfo(req);
            if (i > 0) {
                return AjaxResult.success();
            } else {
                return AjaxResult.error(ExceptionEnum.UPDATE_FAILED.getErrorMsg());
            }
    }

}
