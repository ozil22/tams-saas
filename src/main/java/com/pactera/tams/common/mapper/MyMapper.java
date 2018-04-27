package com.pactera.tams.common.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;



/**
* Mapper基础接口
* @Author: mjh
* @Date: 2018-03-19 16:05:39
*/
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
    //TODO
    //FIXME 特别注意，该接口不能被扫描到，否则会出错
}
