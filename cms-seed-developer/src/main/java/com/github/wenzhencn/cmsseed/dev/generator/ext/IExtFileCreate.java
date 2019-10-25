package com.github.wenzhencn.cmsseed.dev.generator.ext;

import com.baomidou.mybatisplus.generator.config.rules.FileType;

import java.io.File;

/**
 * @author wenzhen
 * @Description
 * @Date: Created in 2019/10/25 17:20
 */
public interface IExtFileCreate {

    /**
     * 自定义判断是否创建文件
     *
     * @param configBuilder 配置构建器
     * @param fileType      文件类型
     * @param filePath      文件路径
     * @return ignore
     */
    boolean isCreate(ExtConfigBuilder configBuilder, FileType fileType, String filePath);

    /**
     * 检查文件目录，不存在自动递归创建
     *
     * @param filePath 文件路径
     */
    default void checkDir(String filePath) {
        File file = new File(filePath);
        boolean exist = file.exists();
        if (!exist) {
            file.getParentFile().mkdir();
        }
    }

}
