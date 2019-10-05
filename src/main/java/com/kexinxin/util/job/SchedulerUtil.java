package com.kexinxin.util.job;


import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 * The type Scheduler util.
 *
 * @author user scheduler 是单例，保证内存只有一个scheduler,所有的作业由统一的一个scheduler管理
 */
public class SchedulerUtil {
	private static Scheduler scheduler;

	/**
	 * Instantiates a new Scheduler util.
	 */
	public SchedulerUtil() {

	}

	/**
	 * Gets scheduler.
	 *
	 * @return the scheduler
	 * @throws SchedulerException the scheduler exception
	 */
	public static Scheduler getScheduler() throws SchedulerException {
		if (scheduler == null) {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			scheduler.start();
		}
		return scheduler;
	}
	
	
}
