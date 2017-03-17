package pitayaa.nail.notification.scheduler;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pitayaa.nail.notification.config.BeanConfiguration;



public class QuartJob {
	public static ApplicationContext applicationContext; 
	public static void activeJob() throws SchedulerException{
		 applicationContext = 
				   new AnnotationConfigApplicationContext(BeanConfiguration.class);
		  JobDetail job = JobBuilder.newJob(AppointmentJob.class).withIdentity("job1", "group1").build();
		  //JobDetail job2 = simpleSchedule().simpleSchedule();
		  
		  // Trigger the job to run now, and then repeat every 40 seconds
		  Trigger trigger = TriggerBuilder.
				  newTrigger().
				  withIdentity("trigger1", "group1").
				  startNow()
				  .withSchedule(simpleSchedule().withIntervalInSeconds(300).repeatForever()).build();
		  // Tell quartz to schedule the job using our trigger
		  Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		  scheduler.start();
		  scheduler.scheduleJob(job, trigger);
	}
	
	public static void main(String[] args) throws SchedulerException {
		
		  JobDetail job = JobBuilder.newJob(AppointmentJob.class).withIdentity("job1", "group1").build();
		  //JobDetail job2 = simpleSchedule().simpleSchedule();
		  
		  // Trigger the job to run now, and then repeat every 40 seconds
		  Trigger trigger = TriggerBuilder.
				  newTrigger().
				  withIdentity("trigger1", "group1").
				  startNow()
				  .withSchedule(simpleSchedule().withIntervalInSeconds(60).repeatForever()).build();
		  // Tell quartz to schedule the job using our trigger
		  Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		  scheduler.start();
		  scheduler.scheduleJob(job, trigger);
		 }
		 private static SimpleScheduleBuilder simpleSchedule() {
		  System.out.println("schedule is running");
		  SimpleScheduleBuilder builder = SimpleScheduleBuilder.repeatHourlyForever();
		  return builder;
		 }

}
