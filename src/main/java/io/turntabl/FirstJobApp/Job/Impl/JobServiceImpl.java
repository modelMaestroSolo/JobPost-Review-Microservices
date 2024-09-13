package io.turntabl.FirstJobApp.Job.Impl;

import io.turntabl.FirstJobApp.Company.CompanyService;
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
    CompanyService companyService;


    public JobServiceImpl(JobRepository jobRepository, CompanyService companyService) {
        this.jobRepository = jobRepository;
        this.companyService = companyService;
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
        Optional<Job> jobToUpdateOptional = jobRepository.findById(id);
        if(jobToUpdateOptional.isPresent()){
            Job jobToUpdate = jobToUpdateOptional.get();
//            jobUpdates.setId(jobUpdates.getId());
            jobToUpdate.setDescription(jobUpdates.getDescription());
            jobToUpdate.setLocation(jobUpdates.getLocation());
            jobToUpdate.setTitle(jobUpdates.getTitle());
            jobToUpdate.setMinSalary(jobUpdates.getMinSalary());
            jobToUpdate.setMaxSalary(jobUpdates.getMaxSalary());
//            jobToUpdate.setCompany(jobUpdates.getCompany());
            return jobRepository.save(jobToUpdate);
        }
        return null;
    }
}