package com.xieziming.stap.channel.services.execution;

import com.xieziming.stap.core.model.execution.dao.ExecutionDao;
import com.xieziming.stap.core.model.execution.pojo.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Suny on 5/22/16.
 */
@Controller
@RequestMapping("execution/{executionId}/progress")
public class ExecutionStatusService {
    private static Logger logger = LoggerFactory.getLogger(ExecutionStatusService.class);
    private final String UTF8 = ";charset=UTF-8";

    @Autowired
    private ExecutionDao executionDao;

    @RequestMapping(value = "mark/{status}", method = RequestMethod.PUT)
    @ResponseBody
    public void requestExecution(@PathVariable("executionId") Integer executionId, @PathVariable("status") String status) {
        Execution execution = executionDao.findById(executionId);
        execution.setStatus(status);
        executionDao.update(execution);
    }
}
