package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{
    /**
     * 推荐 4条
     * @param status 2
     * @return
     */
    public List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String status);

    /**
     * 最新12条
     * @param status
     * @return
     */
    public List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String status);

}
