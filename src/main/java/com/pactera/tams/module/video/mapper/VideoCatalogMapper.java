package com.pactera.tams.module.video.mapper;

import java.util.List;

import com.pactera.tams.module.video.model.VideoCatalog;

import tk.mybatis.mapper.common.Mapper;
/**
 * 视频目录
 * @author ztx
 *2018.1.9
 */
public interface VideoCatalogMapper extends Mapper<VideoCatalog>{
	public List getList(VideoCatalog bean);
}
