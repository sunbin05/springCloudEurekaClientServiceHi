package com.cloud.test.eurekaclient_hi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class HiController {

	@Value("${server.port}")
	private String port;
	
	@RequestMapping("/hi/{fallback}")
	@HystrixCommand(fallbackMethod="hiFallbackMethod")
	@ResponseBody
	public String hi(@PathVariable(value="fallback") String name){
		if("1".equals(name)){
			throw new RuntimeException("....");
		}
		return "当前客户端端口：" + port + ", name: " + name;
	}
	
	public String hiFallbackMethod(String fallback){
		return "fallback 参数值为： " + fallback;
	}
}
