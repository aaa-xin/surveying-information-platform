package com.aaa.qy108.service;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author guohang
 * @Description 测绘管理中的api接口
 * @Date 2020/5/22 18:26
 */
@FeignClient(value ="MAPPING-PROVIDER")
public interface MappingApiService {


}
