package com.pactera.tams.module.video.mapper;


import java.util.List;

import com.pactera.tams.module.video.model.Video;

import tk.mybatis.mapper.common.Mapper;

/**
 * * @author ztx
 * 
 * @date ：2017.12.29 version 1.0
 **/
public interface VideoMapper extends Mapper<Video> {
	public List getVideoList(Video bean);
}
