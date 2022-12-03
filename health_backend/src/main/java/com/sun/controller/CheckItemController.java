package com.sun.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sun.constant.MessageConstant;
import com.sun.entity.PageResult;
import com.sun.entity.QueryPageBean;
import com.sun.entity.Result;
import com.sun.pojo.CheckItem;
import com.sun.service.CheckItemService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.Query;

/**
 * 检查项管理
 *@RestController,自动注入、且自动将返回值转化为json对象
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    //到服务注册中心查询CheckItemService的服务
    @Reference
    private CheckItemService checkItemService;

    //新增检查项
    //客户端发送的为json，需要用@RequestBody注解解析json数据，封装为对象
    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        try {
            checkItemService.add(checkItem);
        }catch (Exception e){
            //远程服务调用失败
            System.out.println("到底是哪里错了啊");
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkItemService.pageQuery(queryPageBean);
        return pageResult;

    }

    @RequestMapping("/delete")
    public Result delete(Integer id){
        try{
            checkItemService.deleteById(id);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);

    }

    @RequestMapping("/edit")
    public Result edit(@RequestBody CheckItem checkItem){
        try{
            checkItemService.edit(checkItem);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);

    }

    @RequestMapping("/findById")
    public Result findById(Integer id){
        try{
            CheckItem checkItem =checkItemService.findById(id);
            return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
//            前端的res.data上这个返回值，它有flag、message、data属性
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }

    }


}









