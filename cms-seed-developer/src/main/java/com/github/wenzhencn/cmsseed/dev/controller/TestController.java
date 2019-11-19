package com.github.wenzhencn.cmsseed.dev.controller;

import com.github.wenzhencn.cmsseed.common.CommonResponse;
import com.github.wenzhencn.cmsseed.dev.model.TestDTO;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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
     * @param request
     * @return
     */
    @GetMapping("/test")
    public CommonResponse<TestDTO> test(HttpServletRequest request) {
        log.debug("debug log");
        log.warn("warn log");
        log.error("error log");
        TestDTO dto = new TestDTO();
        dto.setCreateTime(LocalDateTime.now());
        return CommonResponse.newSuccessResponse(dto);
    }

}
