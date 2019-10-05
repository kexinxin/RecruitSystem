package com.kexinxin.util.job;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import java.sql.Timestamp;
import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//timeType的类型为0仅一次，1为每秒，2为每分鐘，3为每小时，4为每天，5为每周，6为每月,7为测试时间

@Service
public class JobUtil {


	public static int getTypeSecond(int type) {
		if (type == 1) {
			return 1;
		} else if (type == 2) {
			return 60;
		} else if (type == 3) {
			return 60 * 60;
		} else if (type == 4) {
			return 60 * 60 * 24;
		} else if (type == 5) {
			return 60 * 60 * 24 * 7;
		} else if (type == 6) {
			return 60 * 60 * 24 * 7 * 30;
		} else {
			return 5;
		}
	}

	/**
	 * 添加作业
	 * 
	 * @param timeType
	 *            时间类型 0仅一次，1为每秒，2为每分鐘，3为每小时，4为每天，5为每周，6为每月
	 * @param timestamp
	 *            时间戳
	 * @param RemindConfigID
	 *            提醒表的ID
	 * @throws SchedulerException
	 */
	public static void addJob(int timeType, Timestamp timestamp,
			int RemindConfigID) throws SchedulerException {

		JobDetail job = newJob(Job.class).withIdentity(RemindConfigID + "",
				RemindConfigID + "").build();
		job.getJobDataMap().put("ID", RemindConfigID + "");

		Date runDay = timestamp;

		// Trigger trigger = newTrigger()
		// .withIdentity(RemindConfigID + "", RemindConfigID + "")
		// .startAt(runDay)
		// .withSchedule(
		// simpleSchedule().withIntervalInSeconds(timeType)
		// .repeatForever()).build();

		Trigger trigger;
		if (timeType == 0) {
			trigger = newTrigger()
					.withIdentity(RemindConfigID + "", RemindConfigID + "")
					.startAt(runDay)
					.withSchedule(
							simpleSchedule().withIntervalInSeconds(
									getTypeSecond(timeType)).withRepeatCount(1))
					.build();
		} else {
			trigger = newTrigger()
					.withIdentity(RemindConfigID + "", RemindConfigID + "")
					.startAt(runDay)
					.withSchedule(
							simpleSchedule().withIntervalInSeconds(
									getTypeSecond(timeType)).repeatForever())
					.build();
		}

		Scheduler scheduler = SchedulerUtil.getScheduler();
		scheduler.scheduleJob(job, trigger);
	}

	/**
	 * 更新触发器
	 * 
	 * @param timeType
	 *            时间类型 0仅一次，1为每秒，2为每分鐘，3为每小时，4为每天，5为每周，6为每月
	 * @param timestamp
	 *            时间戳
	 * @param RemindConfigID
	 *            提醒表的ID
	 * @throws SchedulerException
	 */
	public static void updateTrigger(int timeType, Timestamp timestamp,
			int RemindConfigID) throws SchedulerException {
		Scheduler scheduler = SchedulerUtil.getScheduler();

		String strID = String.valueOf(RemindConfigID);
		Trigger oldTrigger = scheduler.getTrigger(TriggerKey.triggerKey(strID,
				strID));

		TriggerBuilder tb = oldTrigger.getTriggerBuilder();

		Date runDay = timestamp;

		Trigger trigger;
		if (timeType == 0) {
			trigger = newTrigger()
					.withIdentity(RemindConfigID + "", RemindConfigID + "")
					.startAt(runDay)
					.withSchedule(
							simpleSchedule().withIntervalInSeconds(
									getTypeSecond(timeType)).withRepeatCount(1))
					.build();
		} else {
			trigger = newTrigger()
					.withIdentity(RemindConfigID + "", RemindConfigID + "")
					.startAt(runDay)
					.withSchedule(
							simpleSchedule().withIntervalInSeconds(
									getTypeSecond(timeType)).repeatForever())
					.build();
		}

		scheduler.rescheduleJob(oldTrigger.getKey(), trigger);
	}

	/**
	 * 删除作业
	 * 
	 * @param RemindConfigID
	 *            提醒表配置
	 * @throws SchedulerException
	 */
	public static void deletJob(int RemindConfigID) throws SchedulerException {
		Scheduler scheduler = SchedulerUtil.getScheduler();
		String strID = String.valueOf(RemindConfigID);
		scheduler.deleteJob(JobKey.jobKey(strID, strID));
	}

	
}
