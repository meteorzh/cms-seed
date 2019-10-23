package com.github.wenzhencn.cmsseed.test.service.impl;

import com.github.wenzhencn.cmsseed.test.entity.TestPO;
import com.github.wenzhencn.cmsseed.test.mapper.TestMapper;
import com.github.wenzhencn.cmsseed.test.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wenzhen
 * @since 2019-10-23
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, TestPO> implements ITestService {

}
