package com.github.wenzhencn.cmsseed.dev.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.wenzhencn.cmsseed.common.BusinessException;
import com.github.wenzhencn.cmsseed.common.CommonErrorCode;
import com.github.wenzhencn.cmsseed.common.CommonResponse;
import com.github.wenzhencn.cmsseed.common.PageParam;
import com.github.wenzhencn.cmsseed.dev.entity.DictPO;
import com.github.wenzhencn.cmsseed.dev.model.DictDTO;
import com.github.wenzhencn.cmsseed.dev.service.IDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author wenzhen
 * @since 2019-09-09
 */
@Slf4j
@RestController
public class DictController {

    @Autowired
    private IDictService dictService;

    /**
     * 保存字典信息
     * @param dict
     * @return
     * @throws BusinessException
     */
    @PostMapping("/sys/dict/save")
    public CommonResponse<Object> save(@Validated @RequestBody DictDTO dict) throws BusinessException {
        log.debug("保存字典条目: {}", dict.toString());
        if (dict.getId() == null) {
            dictService.create(dict.toPO());
        } else {
            dictService.update(dict.toPO());
        }

        return CommonResponse.newSuccessResponse();
    }

    /**
     * 删除字典信息
     * @param id
     * @return
     */
    @DeleteMapping("/sys/dict/del/{id}")
    public CommonResponse<Object> delete(@PathVariable("id") Integer id) throws BusinessException {
        log.debug("删除字典条目: id={}", id);
        dictService.delete(id);
        return CommonResponse.newSuccessResponse();
    }

    /**
     * 重新设置字典条目的顺序
     * @param id
     * @param order
     * @return
     * @throws BusinessException
     */
    @PostMapping("/sys/dict/reorder")
    public CommonResponse<Object> reorder(@RequestParam("id") @NotNull Integer id, @RequestParam("order") @NotNull Integer order) throws BusinessException {
        log.debug("重新设置字典条目的顺序: id={}, newOrder={}", id, order);
        dictService.reorder(id, order);
        return CommonResponse.newSuccessResponse();
    }

    /**
     * 分页查询字典信息
     * @param label
     * @param type
     * @return
     */
    @GetMapping("/sys/dict/page")
    public CommonResponse<IPage<DictPO>> page(PageParam page, String label, String type) {
        log.debug("分页查询字典条目: page={}, label={}, type={}", page.toString(), label, type);
        QueryWrapper<DictPO> wrapper = new QueryWrapper<DictPO>();
        if(!StringUtils.isEmpty(label)) {
            wrapper.like("label", label);
        }
        if(!StringUtils.isEmpty(type)) {
            wrapper.eq("type", type);
        }
        IPage<DictPO> dicts = dictService.page(new Page<>(page.getPageNo(), page.getPageSize()), wrapper);
        return CommonResponse.newSuccessResponse(dicts);
    }

    /**
     * 查询所有字典类型
     * @return
     */
    @GetMapping("/sys/dict/bytype/{type}")
    public CommonResponse<List<DictPO>> queryByType(@PathVariable("type") String type) {
        log.debug("查询某个字典类型的条目: type={}", type);
        List<DictPO> dicts = dictService.list(new QueryWrapper<DictPO>().eq("type", type).orderByAsc("`order`"));
        return CommonResponse.newSuccessResponse(dicts);
    }

    /**
     * 查询所有字典类型
     * @return
     */
    @GetMapping("/sys/dict/types")
    public CommonResponse<List<Object>> queryTypes(String key) {
        log.debug("根据关键字查询字典类型: key={}", key);
        QueryWrapper<DictPO> wrapper = new QueryWrapper<DictPO>().select("type");
        if(!StringUtils.isEmpty(key)) {
            wrapper.like("type", key + "%");
        }
        List<Object> types = dictService.listObjs(wrapper.groupBy("type").orderByAsc("type"));
        return CommonResponse.newSuccessResponse(types);
    }

}

