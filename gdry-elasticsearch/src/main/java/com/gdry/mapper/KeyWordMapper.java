package com.gdry.mapper;

import com.gdry.entity.KeyWordInfo;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 关键字 数据层
 *
 */
@Repository
@MapperScan
public interface KeyWordMapper {

    /**
     * 查询关键字请求
     *
     * @param keyWord 关键字
     * @param type 类型
     * @param startTime 开始时间
     * @param endTime 结束时间
     */
    public List<KeyWordInfo> selectKeyWordInfoList(@Param("keyWord")String keyWord, @Param("type")Integer type,
                                                   @Param("startTime")String startTime, @Param("endTime")String endTime);

    /**
     * 添加关键字信息
     *
     * @param req 请求体
     * @return 关键字信息集合
     */
    public int addKeyWordInfo(KeyWordInfo req);

    /**
     * 修改关键字信息
     *
     * @param req 请求体
     * @return 关键字信息集合
     */
    public int updateKeyWordInfo(KeyWordInfo req);
}
