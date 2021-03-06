package timerTasks;

import checker.RepositoryChecker;
import exceptions.CheckException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import resources.Hibernate.HibernateShell;
import resources.Hibernate.HibernateShellQueryException;

/**
 * Task for checking students` repositories.
 */
public class RepositoryCheckerTask implements Job {

    public RepositoryCheckerTask() {
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            RepositoryChecker.checkForCommitsInGroups(HibernateShell.getGroupKeeper());
        }
        catch (HibernateShellQueryException e){
            throw new JobExecutionException(e);
        } catch (CheckException e) {
            throw new JobExecutionException(e);
        }
    }
}