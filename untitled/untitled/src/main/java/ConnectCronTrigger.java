import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

public class ConnectCronTrigger {
    public static void main(String[] args) throws SchedulerException {
        final JobKey jobKey = new JobKey("Connect");
        final JobDetail job = JobBuilder.newJob(ConnectDb.class).withIdentity(jobKey).build();

        final Trigger trigger = TriggerBuilder.newTrigger().withIdentity( "Connect")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 10 * *  ?")).build();

        final Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        // Listener attached to jobKey
        scheduler.getListenerManager().addJobListener(new TaskListener(), KeyMatcher.keyEquals(jobKey));

        scheduler.start();
        scheduler.scheduleJob(job, trigger);
    }
}
