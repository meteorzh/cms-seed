package com.github.wenzhencn.cmsseed.dev.controller;

import com.github.wenzhencn.cmsseed.common.CommonResponse;
import com.github.wenzhencn.cmsseed.dev.model.TestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * @author wenzhen
 * @since : Created in 2019/11/4 10:27
 */
@RestController
@Slf4j
public class TestController {

    /**
     * 测试接口
     * @param dto
     * @return
     */
    @PostMapping("/test")
    public CommonResponse<TestDTO> test(HttpServletRequest request, @RequestBody TestDTO dto) {
        log.debug(dto.toString());
        dto.setCreateTime(LocalDateTime.now());
        return CommonResponse.newSuccessResponse(dto);
    }

}
