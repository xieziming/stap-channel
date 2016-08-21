package com.xieziming.stap.channel.services.execution;

import com.xieziming.stap.core.execution.ExecutionRequest;
import com.xieziming.stap.core.execution.ExecutionRequestController;
import com.xieziming.stap.core.execution.ExecutionRequestResult;
import com.xieziming.stap.core.model.execution.converter.ExecutionCandidateConverter;
import com.xieziming.stap.core.model.execution.dao.ExecutionDao;
import com.xieziming.stap.core.model.execution.dto.ExecutionCandidateDto;
import com.xieziming.stap.core.model.execution.pojo.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Suny on 5/22/16.
 */
@Controller
@RequestMapping("execution/distributor")
public class ExecutionDistributor {
    private static Logger logger = LoggerFactory.getLogger(ExecutionDistributor.class);
    private final String UTF8 = ";charset=UTF-8";

    @Autowired
    private ExecutionDao executionDao;
    @Autowired
    private ExecutionRequestController executionRequestController;
    @Autowired
    private ExecutionCandidateConverter executionCandidateConverter;

    @RequestMapping(value = "candidate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public List<ExecutionCandidateDto> getExecutionResponseDtoList() {
        List<Execution> executionResponseDtoList = executionDao.findAll();
        return executionCandidateConverter.convertAll(executionResponseDtoList);
    }

    @RequestMapping(value = "request", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE+UTF8, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public ExecutionRequestResult requestExecution(@RequestBody ExecutionRequest executionRequest) {
        return executionRequestController.handleRequest(executionRequest);
    }
}
