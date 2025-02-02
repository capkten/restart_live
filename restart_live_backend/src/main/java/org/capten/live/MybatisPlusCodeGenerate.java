package org.capten.live;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.annotation.IdType;

import java.util.Collections;

public class MybatisPlusCodeGenerate {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:postgresql://123.60.89.0:15432/restart_live", "postgres", "2366restart_live")
                .globalConfig(builder -> {
                    builder.author("capkin") // 设置作者
                            .outputDir("E:/code/restart_live/restart_live_backend/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("org.capten.live")
                            .mapper("mapper")
                            .xml("mapper.xml")
                            .entity("model");
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder()
                            .naming(NamingStrategy.underline_to_camel) // 设置实体类名称转换为驼峰命名
                            .columnNaming(NamingStrategy.underline_to_camel) // 设置字段名称转换为驼峰命名
                            .idType(IdType.ASSIGN_UUID) // 设置主键策略为UUID
                            .entityBuilder().enableFileOverride().logicDeleteColumnName("is_delete").versionColumnName("version")
                            .mapperBuilder().enableFileOverride()
                    ;
                })
                .execute();
    }
}