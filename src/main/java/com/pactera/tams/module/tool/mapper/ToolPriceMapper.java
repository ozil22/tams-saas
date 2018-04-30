package com.pactera.tams.module.tool.mapper;

import com.alibaba.fastjson.JSON;
import com.pactera.tams.module.report.vo.ReportQuery;
import com.pactera.tams.module.tool.model.ToolPrice;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
/**
 * 刀具
 * @author js
 *2018.2.8
 */
public interface ToolPriceMapper extends Mapper<ToolPrice>{
	List getList(ToolPrice bean);
	int getTotal(ToolPrice bean);

	/**
	 * 查询刀具价格趋势
	 * @param query
	 * @return
	 */
	List<JSON> findPriceHistory(ReportQuery query);
}
