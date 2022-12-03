package com.sun.service;

import com.sun.entity.PageResult;
import com.sun.entity.QueryPageBean;
import com.sun.entity.Result;
import com.sun.pojo.CheckItem;

//服务接口
public interface CheckItemService {

    public void add(CheckItem checkItem);

    public PageResult pageQuery(QueryPageBean queryPageBean);

    public void deleteById(Integer id);

    public void edit(CheckItem checkItem);

    public CheckItem findById(Integer id);




}
