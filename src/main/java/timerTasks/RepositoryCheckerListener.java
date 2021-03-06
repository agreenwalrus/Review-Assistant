package timerTasks;

import org.quartz.*;
import static org.quartz.TriggerBuilder.newTrigger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Start task which check students` repo.
 */
public class RepositoryCheckerListener implements ServletContextListener {
    private Scheduler scheduler = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContext) {
        try {
            // Setup the Job class and the Job group
            JobDetail job = JobBuilder.newJob(RepositoryCheckerTask.class).withIdentity(
                    "RepositoryJob", "Group1").build();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("RepositoryTrigger", "Group1")
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 1-23 ? * * *"))
                    .forJob(job)
                    .build();

            // Setup the Job and Trigger with Scheduler & schedule jobs
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        }
        catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContext) {
        try {
            scheduler.shutdown();
        }   catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
