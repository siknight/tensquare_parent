package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;


/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    /**
     * 根据标签查询回复问题，问题按回复时间降序排列
     * @param labelId
     * @param pageable
     * @return
     */
    @Query(value = "select * from tb_problem pro,tb_pl pl" +
            " where pro.id = pl.problemid and pl.labelid =?1  " +
                   "ORDER BY pro.replytime DESC",nativeQuery = true)
    public Page<Problem> newList(String labelId, Pageable pageable);

    /**
     * 根据标签ID查询热门问题列表
     * @param labelId
     * @param pageable
     * @return
     */
    @Query(value ="select * from tb_problem pro,tb_pl pl" +
            " where pro.id = pl.problemid and pl.labelid = ?1  " +
            "ORDER BY pro.reply DESC",nativeQuery = true)
    public Page<Problem> findHotListByLabelId(String labelId, Pageable pageable);



    /**
     * 等待回答列表
     * @param labelId
     * @param pageable
     * @return
     */
    @Query(value ="select * from tb_problem pro,tb_pl pl " +
            "where pro.id = pl.problemid and pro.reply = 0 and pl.labelid = ? " +
            "ORDER BY pro.createtime DESC",nativeQuery = true)
    public Page<Problem> findWaitListByLabelId(String labelId, Pageable pageable);


}
