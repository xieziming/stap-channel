package com.xieziming.stap.channel.services;

import com.xieziming.stap.core.model.notification.dao.NotificationDtoDao;
import com.xieziming.stap.core.model.notification.dto.NotificationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Suny on 5/22/16.
 */
@Controller
@RequestMapping("notification")
public class NotificationService {
    private static Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final String UTF8 = ";charset=UTF-8";

    @Autowired
    private NotificationDtoDao notificationDtoDao;


    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE+UTF8)
    @ResponseBody
    public List<NotificationDto> getNotificationList(@RequestHeader("Stap-User") String userName) {
        return notificationDtoDao.findAllByUserName(userName);
    }
}
