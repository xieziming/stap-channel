package com.xieziming.stap.channel.services;

import com.xieziming.stap.core.model.testcase.converter.TestDataDefinitionConverter;
import com.xieziming.stap.core.model.testcase.dao.TestDataDefinitionDao;
import com.xieziming.stap.core.model.testcase.dto.TestDataDefinitionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Suny on 5/22/16.
 */
@Controller
@RequestMapping("test_data")
public class TestDataService {
    private static Logger logger = LoggerFactory.getLogger(TestDataService.class);
    private final String UTF8 = ";charset=UTF-8";

    @Autowired
    private TestDataDefinitionDao testDataDefinitionDao;
    @Autowired
    private TestDataDefinitionConverter testDataDefinitionConverter;

    @RequestMapping(value = "definition", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public List<TestDataDefinitionDto> getTestDataDefinitionList() {
        return testDataDefinitionConverter.convertAll(testDataDefinitionDao.findAll());
    }
}
