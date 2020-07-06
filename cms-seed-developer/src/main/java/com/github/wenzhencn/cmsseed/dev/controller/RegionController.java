package com.github.wenzhencn.cmsseed.dev.controller;

import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.common.CommonResponse;
import com.github.wenzhencn.cmsseed.dev.entity.RegionPO;
import com.github.wenzhencn.cmsseed.dev.model.RegionDTO;
import com.github.wenzhencn.cmsseed.dev.service.IRegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wenzhen
 * @since Created in 2020/7/3 13:25
 */
@Slf4j
@RestController
public class RegionController {

    @Resource
    private IRegionService regionService;

    /**
     * 保存地区信息
     * @param region
     * @return
     */
    @PostMapping("/sys/region/save")
    public CommonResponse<Object> save(@Validated @RequestBody RegionDTO region) throws BusinessException {
        log.debug("保存地区信息: role={}", region.toString());
        if (region.getId() == null) {
            regionService.create(region.toPO());
        } else {
            regionService.update(region.toPO());
        }
        return CommonResponse.newSuccessResponse();
    }

    /**
     * 删除角色
     * @param id
     * @return {@code CommonResponse<Object>}
     */
    @DeleteMapping("/sys/region/del/{id}")
    public CommonResponse<Object> delete(@PathVariable("id") Long id) throws BusinessException {
        log.debug("删除地区信息: id={}", id);
        regionService.delete(id);
        return CommonResponse.newSuccessResponse();
    }

    /**
     * 根据地区名查询地区
     * @param name 地区名
     * @return {@code CommonResponse<List<RegionPO>>}
     */
    @GetMapping("/sys/region/querylist")
    public CommonResponse<List<RegionPO>> queryList(String name) {
        log.debug("查询地区信息: name={}", name);
        return CommonResponse.newSuccessResponse(regionService.queryList(name));
    }

    /**
     * 查询下级地区信息
     * @param pcode
     * @return {@code CommonResponse<List<RegionPO>>}
     */
    @GetMapping("/sys/region/children/{pcode}")
    public CommonResponse<List<RegionPO>> children(@PathVariable("pcode") Integer pcode) {
        log.debug("查询下级地区信息: pcode={}", pcode);
        return CommonResponse.newSuccessResponse(regionService.queryByPcode(pcode));
    }
}
