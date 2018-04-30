package com.pactera.tams.module.report.controller;

import com.alibaba.fastjson.JSON;
import com.pactera.tams.module.report.service.ReportService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 报表
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;

    /**
     * 刀具价格趋势
     */
    @ApiOperation(value = "刀具价格趋势", notes = "刀具价格趋势")
    @RequestMapping(value = "/toolPrice", method = RequestMethod.GET)
    public List<JSON> toolPrice(@RequestParam String tool_id, @RequestParam(required = false) String begin, @RequestParam(required = false) String end) {
        return reportService.toolPrice(tool_id, begin, end);
    }

    /**
     * 加工记录统计
     */
    @ApiOperation(value = "加工记录统计", notes = "加工记录统计")
    @RequestMapping(value = "/processRecord", method = RequestMethod.GET)
    public List<JSON> processRecord(@RequestParam(required = false) String tool_id, @RequestParam(required = false) String begin, @RequestParam(required = false) String end, @RequestParam(required = false) String material_names) {
        return reportService.processRecord(tool_id, begin, end, material_names);
    }

    /**
     * 加工参数范围
     */
    @ApiOperation(value = "加工参数范围", notes = "加工参数范围")
    @RequestMapping(value = "/processParamScope", method = RequestMethod.GET)
    public List<JSON> processParamScope(@RequestParam(required = false) String tool_id, @RequestParam(required = false) String begin,
                                        @RequestParam(required = false) String end, @RequestParam(required = false) String material_names) {
        return reportService.processParamScope(tool_id, begin, end, material_names);
    }

    /**
     * 加工参数对比
     */
    @ApiOperation(value = "加工参数对比", notes = "加工参数对比")
    @RequestMapping(value = "/processParamCompare", method = RequestMethod.GET)
    public List<JSON> processParam(@RequestParam(required = false) String tool_id, @RequestParam(required = false) String begin,
                                   @RequestParam(required = false) String end, @RequestParam(required = false) String material_names,
                                   @RequestParam(required = false) String product_id, @RequestParam(required = false) String scheme_id,
                                   @RequestParam(required = false) String process_dates) {
        return reportService.processParamCompare(tool_id, begin, end, material_names, product_id, scheme_id,process_dates);
    }


    /**
     * 加工方案推荐
     */
    @ApiOperation(value = "加工方案推荐", notes = "加工方案推荐")
    @RequestMapping(value = "/schemeRecommend", method = RequestMethod.GET)
    public List<JSON> schemeRecommend(@RequestParam(required = false) String tool_id, @RequestParam(required = false) String begin,
                                      @RequestParam(required = false) String end, @RequestParam(required = false) String material_names,
                                      @RequestParam(required = false) String product_id, @RequestParam(required = false) String process_dates) {
        return reportService.schemeRecommend(tool_id, begin, end, material_names, product_id, process_dates);
    }
    /**
     * 刀具消耗排名
     */
    @ApiOperation(value = "刀具消耗排名", notes = "刀具消耗排名")
    @RequestMapping(value = "/toolConsumption", method = RequestMethod.GET)
    public List<JSON> toolConsumption(@RequestParam(required = false) String tool_label, @RequestParam(required = false) String begin,
                                      @RequestParam(required = false) String end,@RequestParam(required = false) String product_id,
                                      @RequestParam(required = false) String scheme_id,@RequestParam(required = false) String group,
                                      @RequestParam(required = false) String date) {
        return reportService.toolConsumption(tool_label, begin, end,product_id,scheme_id,group,date);
    }

    /**
     * 工艺刀具消耗排名
     */
    @ApiOperation(value = "工艺刀具消耗排名", notes = "工艺刀具消耗排名")
    @RequestMapping(value = "/toolConsumptionScheme", method = RequestMethod.GET)
    public List<JSON> toolConsumptionScheme(@RequestParam(required = false) String begin,@RequestParam(required = false) String end,
                                      @RequestParam(required = false) String scheme_id,@RequestParam(required = false) String group,
                                      @RequestParam(required = false) String date) {
        return reportService.toolConsumptionScheme(begin, end,scheme_id,group,date);
    }
}
