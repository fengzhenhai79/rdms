package com.gdry.mapper;

import com.gdry.entity.SearchRequest;
import com.gdry.entity.RiverInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 河流信息 数据层
 *
 */
@Repository
@MapperScan
public interface RiverInfoMapper {

    /**
     * 条件查询
     *
     * @param req 查询参数
     * @return 信息集合
     */
    public List<RiverInfo> selectRiverInfoList(SearchRequest req);
}
