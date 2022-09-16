package com.gdry.service.impl;

import com.gdry.entity.KeyWordInfo;
import com.gdry.mapper.KeyWordMapper;
import com.gdry.service.IKeyWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;


/**
 * 关键字 服务层
 *
 * @author ruoyi
 */
@Service
public class KeyWordServiceImpl implements IKeyWordService {

    @Autowired
    KeyWordMapper keyWordMapper;

    private static final String FAIL_PATH = "D:/CodeResource/rdms/rdms/gdry-elasticsearch/src/main/resources/ik_analyzer/ext.dic";


    /**
     * 查询关键字请求
     *
     * @param keyWord 关键字
     * @param type 类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     */
    @Override
    public List<KeyWordInfo> selectKeyWordInfoList(String keyWord, Integer type, String startTime, String endTime) {
        List<KeyWordInfo> keyWordInfos = keyWordMapper.selectKeyWordInfoList(keyWord, type, startTime, endTime);
        return keyWordInfos;
    }

    /**
     * 新增关键字请求
     *
     * @param req 关键字请求体
     */
    @Override
    @Transactional(rollbackFor = IOException.class)
    public int addKeyWordInfo(KeyWordInfo req) throws IOException {
        // 1.写入数据库
        req.setCreateTime(new Date());
        int i = keyWordMapper.addKeyWordInfo(req);

        // 2.写入关键字文件夹ext.dic
        String keyWord = req.getKeyWord();
        writeToDocument(FAIL_PATH, keyWord);
        return i;
    }

    /**
     * 修改关键字请求
     *
     * @param req 关键字请求体
     */
    @Override
    public int updateKeyWordInfo(KeyWordInfo req) {
        req.setUpdateTime(new Date());
        int i = keyWordMapper.updateKeyWordInfo(req);
        return i;
    }

    public void writeToDocument(String path, String content) throws IOException {
        File file = new File(path);
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("\r\n");
        bufferedWriter.write(content);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

}
