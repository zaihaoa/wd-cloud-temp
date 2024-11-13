package com.wd.common.dubbo.filter;

import com.alibaba.fastjson2.JSON;
import com.wd.common.core.constants.CommonConstant;
import com.wd.common.core.context.SystemContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * @author huangwenda
 * @date 2023/8/29 18:14
 **/
@Slf4j
@Activate(group = CommonConstants.CONSUMER)
public class DubboConsumerFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        RpcContext.getServerContext().setObjectAttachment(CommonConstant.COMMON_PARAM_CONTENT, SystemContext.get());

        log.info("consumer入参,方法:{}.{},参数:{}",
                invoker.getInterface().getName(),
                invocation.getMethodName(),
                JSON.toJSONString(invocation.getArguments()));
        long start = System.currentTimeMillis();
        Result result = invoker.invoke(invocation);
        if (result.hasException()) {
            log.info("consumer出参,接口耗时:{},异常:{},", System.currentTimeMillis() - start, result.getException().getMessage());
        } else {
            log.info("consumer出参,接口耗时:{},返回数据:{}", System.currentTimeMillis() - start, JSON.toJSONString(result.getValue()));
        }
        return result;
    }

    private void log(Invoker<?> invoker, Invocation invocation) {

    }
}
