package com.pactera.tams.module.machine.mapper;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;

import com.pactera.tams.module.machine.entity.MachineManagementEntity;
import com.pactera.tams.module.machine.entity.TSDict;
import com.pactera.tams.common.mapper.MyMapper;

import tk.mybatis.mapper.provider.ExampleProvider;

public interface TSDictMapper extends MyMapper<TSDict> {

	List<TSDict> getDicList(TSDict bean);
}