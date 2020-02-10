package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;
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





}
