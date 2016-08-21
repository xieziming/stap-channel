package com.xieziming.stap.channel.services.execution;

import com.xieziming.stap.core.model.execution.dao.ExecutionOutputTextDao;
import com.xieziming.stap.core.model.execution.pojo.ExecutionOutputText;
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
@RequestMapping("execution/output")
public class ExecutionOutputService {
    private static Logger logger = LoggerFactory.getLogger(ExecutionOutputService.class);
    private final String UTF8 = ";charset=UTF-8";

    @Autowired
    private ExecutionOutputTextDao executionOutputTextDao;

    @RequestMapping(value = "text/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public void requestExecution(@RequestBody ExecutionOutputText executionOutputText) {
        executionOutputTextDao.add(executionOutputText);
    }
}
