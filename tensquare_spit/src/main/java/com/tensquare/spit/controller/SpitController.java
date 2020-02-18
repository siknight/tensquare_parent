package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin
//@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 查询全部记录
     * @return
     */
    @RequestMapping(path = "/split",method = RequestMethod.GET)
    public Result findAll(){
        List<Spit> list = spitService.findAll();
        return new Result(true, StatusCode.OK,"查询成功",list);
    }


    /**
     *  根据ID查询
     * @param id
     * @return
     */
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable String id){
        Spit spit = spitService.findById(id);
        return new Result(true,StatusCode.OK,"查询成功",spit);
    }


    /**
     * 增加
     * @param spit
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Spit spit){
        spitService.add(spit);

        return new Result(true,StatusCode.OK,"增加成功");
    }



    /**
     *  修改
     * @param spit
     * @param id
     * @return
     */
    @RequestMapping(value = "/split/{id}",method = RequestMethod.PUT)
    public Result update(@RequestBody Spit spit,@PathVariable String id){
        spit.setId(id);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     *  删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/split/{id}",method = RequestMethod.DELETE)
    public  Result deleteById(@PathVariable String id){
        spitService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }

    /**
     * 根据上级ID查询吐槽
     * @param parentId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/split/comment/{parentId}/{page}/{size}",method = RequestMethod.GET)
    public Result findByParentid( @PathVariable String parentId,@PathVariable int page,@PathVariable int size){
        Page<Spit> pageList = spitService.findByParentid(parentId,page,size);
        PageResult pageResult=new PageResult(pageList.getTotalElements(),pageList.getContent());
        return new Result(true,StatusCode.OK,"查询成功",pageResult);
    }




    @Autowired
    private HttpServletRequest request;

    /**
     * 点赞
     * @param id
     * @return
     */
    @RequestMapping(value = "/split/thumbup/{id}",method = RequestMethod.PUT)
    public Result updateThumnup(@PathVariable String id){
        //判断用户是否点过赞
         String userid="2023";
        if(redisTemplate.opsForValue().get("thumbup_"+userid+"_"+ id)!=null){
            return new Result(false,StatusCode.REPERROR,"你已经点过赞了");
        }
        // 后边我们会修改为当前登陆的用户
        spitService.updateThumnup(id);
        return new Result(true,StatusCode.OK,"点赞成功");
    }

}
