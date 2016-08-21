package com.xieziming.stap.channel.services;

import com.xieziming.stap.core.model.comment.converter.CommentConverter;
import com.xieziming.stap.core.model.comment.dao.CommentDao;
import com.xieziming.stap.core.model.comment.dto.CommentDto;
import com.xieziming.stap.core.model.testcase.converter.TestCaseConverter;
import com.xieziming.stap.core.model.testcase.converter.TestCaseRevisionConverter;
import com.xieziming.stap.core.model.testcase.dao.TestCaseDao;
import com.xieziming.stap.core.model.testcase.dto.TestCaseDto;
import com.xieziming.stap.core.model.testcase.dto.TestCaseRevisionDto;
import com.xieziming.stap.core.model.testcase.pojo.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Suny on 5/22/16.
 */
@Controller
@RequestMapping("test_case")
public class TestCaseService {
    private static Logger logger = LoggerFactory.getLogger(TestCaseService.class);
    private final String UTF8 = ";charset=UTF-8";

    @Autowired
    private TestCaseConverter testCaseConverter;
    @Autowired
    private TestCaseDao testCaseDao;
    @Autowired
    private TestCaseRevisionConverter testCaseRevisionConverter;
    @Autowired
    private CommentConverter commentConverter;
    @Autowired
    private CommentDao commentDao;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public List<TestCase> getTestCaseList() {
        return testCaseDao.findAll();
    }

    @RequestMapping(value = "{test_case_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public TestCaseDto getTestCase(@PathVariable("test_case_id") int testCaseId) {
        return testCaseConverter.convert(testCaseDao.findById(testCaseId));
    }

    @RequestMapping(value = "{test_case_id}/revision", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public TestCaseRevisionDto getTestCaseRevisions(@PathVariable("test_case_id") int testCaseId) {
        return testCaseRevisionConverter.convert(testCaseId);
    }
    @RequestMapping(value = "{test_case_id}/comment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public List<CommentDto> getComments(@PathVariable("test_case_id") int testCaseId) {
        return commentConverter.convertAll(commentDao.findAllByTestCaseId(testCaseId));
    }
}
