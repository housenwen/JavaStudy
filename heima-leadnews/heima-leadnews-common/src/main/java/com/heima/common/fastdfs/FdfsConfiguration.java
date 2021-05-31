package com.heima.common.fastdfs;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jmx.support.RegistrationPolicy;

@Configuration
@Import(FdfsClientConfig.class) // 导入FastDFS-Client组件
@PropertySource("fast_dfs.properties")
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING) // 解决jmx重复注册bean的问题
public class FdfsConfiguration {
}