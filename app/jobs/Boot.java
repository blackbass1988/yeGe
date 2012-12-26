package jobs;

import models.Project;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

/**
 * @author blackbass <o.salionov@zmeke.com>
 */
@OnApplicationStart
public class Boot extends Job {

    public void doJob() {
        if (Project.count() == 0) {
            Project project = new Project("Wheeny Slots");
            project.save();
        }
    }
}
