package com.wickey;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.wickey.config.datasource.dynamic.DynamicDataSourceRegister;
import com.wickey.config.properties.MyTest;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ServletComponentScan
@EnableConfigurationProperties({MyTest.class})
@ComponentScan(basePackages = {"com.wickey","com.core"})
@Import({DynamicDataSourceRegister.class})
@EnableAsync
@EnableScheduling
@MapperScan("com.wickey.*.mapper")//mybatis 扫描包下的mapper
public class DemoApplication
//extends SpringBootServletInitializer
{
	/**
	 * 继承SpringBootServletInitializer，重写configure方法
	 * 然后在pom文件中修改jar 为 war
	 * <packaging>war</packaging>
	 * 最后执行mvn clean package 打成war包
	 * @param builder
	 * @return
     */
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(this.getClass());
	}*/

	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		fastConverter.setFastJsonConfig(fastJsonConfig);
		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
