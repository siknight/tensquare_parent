package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部
     * @return
     */
    public List<Label> findAll(){
        return labelDao.findAll();
    }


    /**
     * 根据id查询标签
     * @return
     */
    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    /**
     * 增加标签
     * @return
     */
    public void add(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    /**
     * 修改标签
     * @return
     */
    public void update(Label label){

        labelDao.save(label);
    }

    /**
     * 修改标签
     * @return
     */
    public void deleteById(String id){
        labelDao.deleteById(id);
    }


    /**
     * 条件查询（不分页）
     * @param searchMap
     * @return
     */
    public List<Label> findSearch(Map searchMap){

        return labelDao.findAll(createSpecification(searchMap));
    }


    /**
     * 条件查询（分页）
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public Page<Label> findSearch(Map searchMap, int page, int size){
        Specification specification=createSpecification(searchMap);
        PageRequest pageRequest=PageRequest.of(page-1,size);
        return  labelDao.findAll(specification,pageRequest);
    }

    private Specification createSpecification(Map searchMap) {
        return new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder cb) {

                ArrayList<Predicate> predicateList = new ArrayList<>();
                if (searchMap.get("labelname") !=null && !"".equals(searchMap.get("labelname"))){
                    Predicate like = cb.like(root.get("labelname").as(String.class), "%" + searchMap.get("labelname") + "%");
                    predicateList.add(like);
                }

                if(searchMap.get("state")!=null && !"".equals(searchMap.get("state"))  ){
                    predicateList.add(cb.equal(root.get("state").as(String.class), searchMap.get("state")   ) );
                }
                if(searchMap.get("recommend")!=null && !"".equals(searchMap.get("recommend"))  ){
                    predicateList.add(  cb.equal(  root.get("recommend").as(String.class), searchMap.get("recommend")   ) );
                }

                return cb.and(predicateList.toArray( new Predicate[ predicateList.size()]));
            }
        };
    }







    }
