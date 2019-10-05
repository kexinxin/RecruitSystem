package com.kexinxin.util.job.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


@Service
public class JobListener implements ServletContextListener{
	

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		//SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		// for(int i=0;i<remindConfigList.size();i++){
		// System.out.println(remindConfigList.get(i).getName());
		// try {
		// JobUtil.addJob(remindConfigList.get(i).getType(),
		// remindConfigList.get(i).getTime(), remindConfigList.get(i).getId());
		// } catch (SchedulerException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		
		System.out.println("服务跑起来了");
	}
	
}	
