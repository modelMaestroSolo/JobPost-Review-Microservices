package io.turntabl.FirstJobApp.Job.Impl;

import io.turntabl.FirstJobApp.Job.Job;
import io.turntabl.FirstJobApp.Job.JobService;
import io.turntabl.FirstJobApp.JobRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    // private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job retrieveJobById(Long id) {
       return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        Optional<Job> jobToDelete = jobRepository.findById(id);
        if(jobToDelete.isPresent()) {
            jobRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Job updateJob(Long id, Job jobUpdates) {
        Optional<Job> optionalUpdatedJob = jobRepository.findById(id);
        if(optionalUpdatedJob.isPresent()){
            Job updatedJob = optionalUpdatedJob.get();
            updatedJob.setId(id);
            updatedJob.setDescription(jobUpdates.getDescription());
            updatedJob.setLocation(jobUpdates.getLocation());
            updatedJob.setTitle(jobUpdates.getTitle());
            updatedJob.setMinSalary(jobUpdates.getMinSalary());
            updatedJob.setMaxSalary(jobUpdates.getMaxSalary());
            return jobRepository.save(updatedJob);
        }
        return null;
    }
}