package com.petter.endpoint;

import com.petter.bean.MemoryInfo;
import org.springframework.boot.actuate.endpoint.Endpoint;

import java.util.Date;

/**
 * @author hongxf
 * @since 2017-09-14 9:51
 */
public class MyEndPoint implements Endpoint<MemoryInfo> {

    /**
     * (1)getId是EndPoint的唯一标识，
     * (2)MVC接口对外暴露的路径:http://localhost:8080/myendpoint
     */
    @Override
    public String getId() {
        return "myendpoint";
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isSensitive() {
        return false;
    }

    @Override
    public MemoryInfo invoke() {
        MemoryInfo memInfo = new MemoryInfo();
        Runtime runtime = Runtime.getRuntime();

        memInfo.setRecordTime(new Date());
        memInfo.setMaxMemory(runtime.maxMemory());
        memInfo.setTotalMemory(runtime.totalMemory());
        return memInfo;
    }
}
