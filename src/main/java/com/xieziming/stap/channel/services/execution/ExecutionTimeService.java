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

import java.util.Calendar;

/**
 * Created by Suny on 5/22/16.
 */
@Controller
@RequestMapping("execution/{executionId}/time")
public class ExecutionTimeService {
    private static Logger logger = LoggerFactory.getLogger(ExecutionTimeService.class);
    private final String UTF8 = ";charset=UTF-8";

    @Autowired
    private ExecutionDao executionDao;

    @RequestMapping(value = "start/mark", method = RequestMethod.PUT)
    @ResponseBody
    public void markStartTime(@PathVariable("executionId") Integer executionId) {
        Execution execution = executionDao.findById(executionId);
        execution.setStartTime(Calendar.getInstance().getTime());
        executionDao.update(execution);
    }

    @RequestMapping(value = "end/mark", method = RequestMethod.PUT)
    @ResponseBody
    public void markEndTime(@PathVariable("executionId") Integer executionId) {
        Execution execution = executionDao.findById(executionId);
        execution.setEndTime(Calendar.getInstance().getTime());
        executionDao.update(execution);
    }

}
