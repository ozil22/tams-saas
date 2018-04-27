package com.pactera.tams.module.video.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.pactera.tams.common.utils.DateUtils;
import com.pactera.tams.module.product.model.Product;
import com.pactera.tams.module.video.mapper.VideoMapper;
import com.pactera.tams.module.video.model.Video;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;


/** * @author  ztx
@date ：2017.12.29
@version 1.0     
**/
@Service
public class VideoService {
	@Autowired
	VideoMapper videoMapper;
	
	/**
	 * 保存
	 */
	public int saveVideo(Video bean) {
		
		return videoMapper.insert(bean);
	}
	/**
	 * 删除
	 */
	public void delete(String id) {
		videoMapper.deleteByPrimaryKey(id);
	}
	/**
	 * 修改
	 */
	public void update(Video video) {
		Example example = new Example(Product.class);
		Criteria criteria = example.createCriteria();
		if(StringUtils.isNotEmpty(video.getId())){
    		criteria.andEqualTo("id", video.getId());
    	}
		video.setUpdate_date(DateUtils.getCurrentTime());
		videoMapper.updateByExampleSelective(video, example);
	}
	/**
	 * 分页查询
	 */
	public List getpageList(Video bean) {
		if(bean.getPage() != null && bean.getRows() != null) {
			 PageHelper.startPage(bean.getPage(), bean.getRows());
		}
		Example example = new Example(Video.class);
//		Example.Criteria criteria = example.createCriteria();
//		if (StringUtils.isNotEmpty(bean.getStatus())) {
//        	criteria.andEqualTo("status", bean.getStatus());
//        }
//		if (StringUtils.isNotEmpty(bean.getVideo_name())) {
//        	criteria.andLike("video_name", "%" +bean.getVideo_name()+"%");
//        }
//		example.setOrderByClause("create_date desc ");
		return videoMapper.getVideoList(bean);
	}
	/**
	 * 查询列表
	 */
	public List getList(Video bean){
		Example example = new Example(Video.class);
		example.setOrderByClause("create_date desc ");
		return videoMapper.selectByExample(example);
	}
	/**
	 * 查询实体
	 * @param id
	 */
	public Video getObjectById(String id) {
		return videoMapper.selectByPrimaryKey(id);
	}
	/**
	 * 查询实体
	 * @param bean
	 */
	public Video getObjectByBean(Video bean) {
		return videoMapper.selectOne(bean);
	}
	/**
	 * 更新状态
	 */
	public void updateVideo(Video bean) {
		videoMapper.updateByPrimaryKey(bean);
	}
	/**
	 * 获得产品数量
	 */
	public int getTotal(Video bean) {
		return videoMapper.selectCount(bean);
	}
}
