package com.kexinxin.util.job;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;



/**
 * The type Job.
 */
@Service
public class Job implements org.quartz.Job {

	/**
	 * 中间的调度类，从数据源获取数据传到邮箱模块
	 * 
	 * 
	 */
	@Transactional
	public void execute(JobExecutionContext context)
			throws JobExecutionException {

	}

	/**
	 * Gets specific message.
	 *
	 * @param context     the context
	 * @param placeholder the placeholder
	 * @return the specific message
	 */
// 替换占位符
	public String getSpecificMessage(String context,
			Map<String, String> placeholder) {
		Set<String> place = placeholder.keySet();
		for (String s : place) {
			String holder = placeholder.get(s);
			context = context.replace("{" + s.toLowerCase() + "}", holder);
		}
		return context;
	}
}
