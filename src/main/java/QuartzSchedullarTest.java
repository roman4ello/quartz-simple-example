import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.TimeZone;

import static org.quartz.CronScheduleBuilder.weeklyOnDayAndHourAndMinute;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzSchedullarTest {

    public static void main(String[] args) throws InterruptedException, SchedulerException {

        JobDetail job = newJob(MyJob.class)
                .withIdentity("job1", "group1")
                .build();

        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(weeklyOnDayAndHourAndMinute(DateBuilder.THURSDAY, 14, 00)
                        .inTimeZone(TimeZone.getTimeZone("Europe/Kiev"))
                )
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }

    public class MyJob implements Job {

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            System.err.println("Hello World!  MyJob is executing.");
        }
    }
}
