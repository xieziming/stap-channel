package com.xieziming.stap.channel.services.execution;

import com.xieziming.stap.core.model.execution.dao.ExecutionLogDao;
import com.xieziming.stap.core.model.execution.pojo.ExecutionLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Suny on 5/22/16.
 */
@Controller
@RequestMapping("execution/logger")
public class ExecutionLogService {
    private static Logger logger = LoggerFactory.getLogger(ExecutionLogService.class);
    private final String UTF8 = ";charset=UTF-8";

    @Autowired
    private ExecutionLogDao executionLogDao;

    @RequestMapping(value = "add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public void requestExecution(@RequestBody ExecutionLog executionLog) {
        executionLogDao.add(executionLog);
    }
}
