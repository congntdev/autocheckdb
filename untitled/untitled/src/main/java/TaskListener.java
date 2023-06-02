import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

import java.util.logging.Logger;

public class TaskListener implements JobListener {
    private static final Logger LOGGER = Logger.getLogger(TaskListener.class.getName());
    @Override
    public String getName() {
        return "Connect Database";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        final String jobName = context.getJobDetail().getKey().toString();
        LOGGER.info("ConnectToBeExecuted: " + jobName + " is starting...");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        LOGGER.info("ConnectExecutionVetoed");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        LOGGER.info("Success!");

        final String jobName = context.getJobDetail().getKey().toString();
        LOGGER.info("Cob : " + jobName + " is finished!!");

        if (!jobException.getMessage().equals("")) {
            LOGGER.warning("Exception thrown by: " + jobName + " Exception: " + jobException.getMessage());
        }
    }
}
