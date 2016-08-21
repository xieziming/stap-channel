package com.xieziming.stap.channel.services.execution;

import com.xieziming.stap.core.model.comment.converter.CommentConverter;
import com.xieziming.stap.core.model.comment.dao.CommentDao;
import com.xieziming.stap.core.model.comment.dto.CommentDto;
import com.xieziming.stap.core.model.execution.converter.ExecutionBriefConverter;
import com.xieziming.stap.core.model.execution.converter.ExecutionPlanConverter;
import com.xieziming.stap.core.model.execution.converter.ExecutionRevisionDtoBuilder;
import com.xieziming.stap.core.model.execution.dao.ExecutionDao;
import com.xieziming.stap.core.model.execution.dao.ExecutionPlanDao;
import com.xieziming.stap.core.model.execution.dao.ExecutionPlanMetaDao;
import com.xieziming.stap.core.model.execution.dto.ExecutionBriefDto;
import com.xieziming.stap.core.model.execution.dto.ExecutionPlanDto;
import com.xieziming.stap.core.model.execution.dto.ExecutionRevisionDto;
import com.xieziming.stap.core.model.execution.pojo.ExecutionPlan;
import com.xieziming.stap.core.model.execution.pojo.ExecutionPlanMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Suny on 5/22/16.
 */
@Controller
@RequestMapping("execution_plan")
public class ExecutionPlanService {
    private static Logger logger = LoggerFactory.getLogger(ExecutionPlanService.class);
    private final String UTF8 = ";charset=UTF-8";

    @Autowired
    private ExecutionPlanDao executionPlanDao;
    @Autowired
    private ExecutionPlanMetaDao executionPlanMetaDao;
    @Autowired
    private ExecutionPlanConverter executionPlanConverter;
    @Autowired
    private ExecutionBriefConverter executionBriefConverter;
    @Autowired
    private ExecutionDao executionDao;
    @Autowired
    private ExecutionRevisionDtoBuilder executionRevisionDtoBuilder;
    @Autowired
    private CommentConverter commentConverter;
    @Autowired
    private CommentDao commentDao;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public List<ExecutionPlanDto> getAllExecutionPlans() {
        return executionPlanConverter.convertAll(executionPlanDao.findAll());
    }

    @RequestMapping(value = "{execution_plan_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public ExecutionPlanDto getExecutionPlan(@PathVariable("execution_plan_id") int executionPlanId) {
        return executionPlanConverter.convert(executionPlanDao.findById(executionPlanId));
    }

    @RequestMapping(value = "{execution_plan_id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE+UTF8, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public ExecutionPlan updateExecutionPlan(@RequestBody ExecutionPlan executionPlan) {
        logger.info("A put distribute for execution plan: "+executionPlan.toString());
        if(executionPlan.getId() != null){
            return executionPlanDao.update(executionPlan);
        }else{
            return executionPlanDao.add(executionPlan);
        }
    }

    @RequestMapping(value = "{execution_plan_id}/execution_plan_meta", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE+UTF8, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public ExecutionPlanMeta updateExecutionPlanMeta(@RequestBody ExecutionPlanMeta executionPlanMeta) {
        logger.info("A put distribute for execution plan meta: "+executionPlanMeta.toString());
        if(executionPlanMeta.getId() != null) {
            return executionPlanMetaDao.update(executionPlanMeta);
        }else{
            return executionPlanMetaDao.add(executionPlanMeta);
        }
    }

    @RequestMapping(value = "{execution_plan_id}/revision", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public ExecutionRevisionDto getExecutionPlanRevision(@PathVariable("execution_plan_id") int executionPlanId) {
        return executionRevisionDtoBuilder.convert(executionPlanId);
    }

    @RequestMapping(value = "{execution_plan_id}/execution_plan_meta/{execution_plan_meta_id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteExecutionPlanMeta(@PathVariable("execution_plan_meta_id") int executionPlanMetaId) {
        logger.info("A delete distribute for execution plan meta: "+executionPlanMetaId);
        executionPlanMetaDao.delete(executionPlanMetaId);
    }

    @RequestMapping(value = "{execution_plan_id}/execution_plan_meta/{execution_plan_meta_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public ExecutionPlanMeta getExecutionPlanMeta(@PathVariable("execution_plan_meta_id") int executionPlanMetaId) {
        return executionPlanMetaDao.findById(executionPlanMetaId);
    }

    @RequestMapping(value = "{execution_plan_id}/execution_list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public List<ExecutionBriefDto> getExecutions(@PathVariable("execution_plan_id") int executionPlanId) {
        return executionBriefConverter.convertAll(executionDao.findAllByExecutionPlanId(executionPlanId));
    }

    @RequestMapping(value = "{execution_plan_id}/comment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public List<CommentDto> getComments(@PathVariable("execution_plan_id") int executionPlanId) {
        return commentConverter.convertAll(commentDao.findAllByExecutionPlanId(executionPlanId));
    }

    @RequestMapping(value = "{execution_plan_id}/clean", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public void cleanPlan(@PathVariable("execution_plan_id") int executionPlanId) {
        executionPlanDao.clean(executionPlanId);
    }

    @RequestMapping(value = "{execution_plan_id}/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public void deletePlan(@PathVariable("execution_plan_id") int executionPlanId) {
        executionPlanDao.delete(executionPlanId);
    }
}
