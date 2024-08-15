package io.turntabl.FirstJobApp.Job;

import java.util.List;

public interface JobService {
    List<Job> findAll();

    Job createJob(Job job);

    Job retrieveJobById(Long id);

    boolean deleteJobById(Long id);

    Job updateJob(Long id, Job jobUpdates);
}
