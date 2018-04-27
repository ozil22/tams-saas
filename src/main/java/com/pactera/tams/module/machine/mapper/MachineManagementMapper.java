package com.pactera.tams.module.machine.mapper;
import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import com.pactera.tams.module.machine.entity.MachineManagementEntity;
import com.pactera.tams.common.mapper.MyMapper;

import tk.mybatis.mapper.provider.ExampleProvider;
public interface MachineManagementMapper extends MyMapper<MachineManagementEntity> {
	List getList(MachineManagementEntity bean);

	@SelectProvider(type = ExampleProvider.class, method = "dynamicSQL")
	List<MachineManagementEntity> selectByExample(Object example);
	
}
