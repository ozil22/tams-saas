package com.pactera.tams.module.report.controller;

import com.pactera.tams.common.entity.RestResult;
import com.pactera.tams.common.entity.ResultUtils;
import com.pactera.tams.module.report.service.ReportService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 报表
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;



    /**
     * 企业产量统计
     */
    @ApiOperation(value = "1企业产量统计", notes = "1企业产量统计")
    @RequestMapping(value = "/makeAmount", method = RequestMethod.GET)
    public RestResult makeAmount(@RequestParam(required = false) String begin, @RequestParam(required = false) String end,
                                 @RequestParam(required = false) String group) {
        return ResultUtils.genSuccesResult(reportService.makeAmount(begin, end,group));
    }

    /**
     * 产品产量统计
     */
    @ApiOperation(value = "2产品产量统计", notes = "2产品产量统计")
    @RequestMapping(value = "/makeAmountTrendByProduct", method = RequestMethod.GET)
    public RestResult makeAmountTrendByProduct(@RequestParam(required = false) String begin, @RequestParam(required = false) String end,
                                               @RequestParam(required = false) String group,@RequestParam(required = false) String product_id) {
        return ResultUtils.genSuccesResult(reportService.makeAmountTrendByProduct(begin, end,group,product_id));
    }


    /**
     * 加工参数对比
     */
    @ApiOperation(value = "9.1,9.2加工参数对比", notes = "9.1,9.2加工参数对比")
    @RequestMapping(value = "/processParamCompare", method = RequestMethod.GET)
    public RestResult processParam(@RequestParam(required = false) String tool_id, @RequestParam(required = false) String begin,
                                   @RequestParam(required = false) String end, @RequestParam(required = false) String material_names,
                                   @RequestParam(required = false) String product_id, @RequestParam(required = false) String scheme_id,
                                   @RequestParam(required = false) String process_dates) {
        return ResultUtils.genSuccesResult(reportService.processParamCompare(tool_id, begin, end, material_names, product_id, scheme_id,process_dates));
    }

    /**
     * 加工参数范围
     */
    @ApiOperation(value = "10.2加工参数范围", notes = "10.2加工参数范围")
    @RequestMapping(value = "/processParamScope", method = RequestMethod.GET)
    public RestResult processParamScope(@RequestParam(required = false) String tool_id, @RequestParam(required = false) String begin,
                                        @RequestParam(required = false) String end, @RequestParam(required = false) String material_names) {
        return ResultUtils.genSuccesResult(reportService.processParamScope(tool_id, begin, end, material_names));
    }

    /**
     * 加工记录统计
     */
    @ApiOperation(value = "10.1加工记录统计", notes = "10.1加工记录统计")
    @RequestMapping(value = "/processRecord", method = RequestMethod.GET)
    public RestResult processRecord(@RequestParam(required = false) String tool_id, @RequestParam(required = false) String begin, @RequestParam(required = false) String end, @RequestParam(required = false) String material_names) {
        return ResultUtils.genSuccesResult(reportService.processRecord(tool_id, begin, end, material_names));
    }

    /**
     * 产品工艺方案对比
     */
    @ApiOperation(value = "8产品工艺方案对比", notes = "8产品工艺方案对比")
    @RequestMapping(value = "/productSchemeCompare", method = RequestMethod.GET)
    public RestResult productSchemeCompare(@RequestParam(required = false) String begin, @RequestParam(required = false) String end,
                                           @RequestParam(required = false) String scheme_ids,@RequestParam(required = false) String product_id) {
        return ResultUtils.genSuccesResult(reportService.productSchemeCompare(begin, end,scheme_ids,product_id));
    }

    /**
     * 加工方案推荐
     */
    @ApiOperation(value = "9.3加工方案推荐", notes = "9.3加工方案推荐")
    @RequestMapping(value = "/schemeRecommend", method = RequestMethod.GET)
    public RestResult schemeRecommend(@RequestParam(required = false) String tool_id, @RequestParam(required = false) String begin,
                                      @RequestParam(required = false) String end, @RequestParam(required = false) String material_names,
                                      @RequestParam(required = false) String product_id, @RequestParam(required = false) String process_dates) {
        return ResultUtils.genSuccesResult(reportService.schemeRecommend(tool_id, begin, end, material_names, product_id, process_dates));
    }
    /**
     * 刀具消耗排名
     */
    @ApiOperation(value = "6,7刀具消耗排名", notes = "6,7刀具消耗排名")
    @RequestMapping(value = "/toolConsumption", method = RequestMethod.GET)
    public RestResult toolConsumption(@RequestParam(required = false) String tool_label, @RequestParam(required = false) String begin,
                                      @RequestParam(required = false) String end,@RequestParam(required = false) String product_id,
                                      @RequestParam(required = false) String scheme_id,@RequestParam(required = false) String group,
                                      @RequestParam(required = false) String date) {
        return ResultUtils.genSuccesResult(reportService.toolConsumption(tool_label, begin, end,product_id,scheme_id,group,date));
    }



    /**
     * 产品刀具消耗
     */
    @ApiOperation(value = "3产品刀具消耗", notes = "3产品刀具消耗")
    @RequestMapping(value = "/toolConsumptionByProduct", method = RequestMethod.GET)
    public RestResult toolConsumptionByProduct(@RequestParam(required = false) String begin, @RequestParam(required = false) String end,
                                               @RequestParam(required = false) String group,@RequestParam(required = false) String product_id) {
        return ResultUtils.genSuccesResult(reportService.toolConsumptionByProduct(begin, end,group,product_id));
    }

    /**
     * 工艺刀具消耗排名
     */
    @ApiOperation(value = "5工艺刀具消耗排名", notes = "5工艺刀具消耗排名")
    @RequestMapping(value = "/toolConsumptionScheme", method = RequestMethod.GET)
    public RestResult toolConsumptionScheme(@RequestParam(required = false) String begin,@RequestParam(required = false) String end,
                                            @RequestParam(required = false) String scheme_id,@RequestParam(required = false) String group,
                                            @RequestParam(required = false) String date) {
        return ResultUtils.genSuccesResult(reportService.toolConsumptionScheme(begin, end,scheme_id,group,date));
    }

    /**
     * 刀具消耗
     */
    @ApiOperation(value = "4刀具消耗", notes = "4刀具消耗")
    @RequestMapping(value = "/toolConsumptionTrend", method = RequestMethod.GET)
    public RestResult toolConsumptionTrend(@RequestParam(required = false) String begin, @RequestParam(required = false) String end,
                                            @RequestParam(required = false) String group) {
        return ResultUtils.genSuccesResult(reportService.toolConsumptionTrend(begin, end,group));
    }

    /**
     * 刀具价格趋势
     */
    @ApiOperation(value = "11刀具价格趋势", notes = "11刀具价格趋势")
    @RequestMapping(value = "/toolPrice", method = RequestMethod.GET)
    public RestResult toolPrice(@RequestParam String tool_id, @RequestParam(required = false) String begin, @RequestParam(required = false) String end) {
        return ResultUtils.genSuccesResult(reportService.toolPrice(tool_id, begin, end));
    }

}
