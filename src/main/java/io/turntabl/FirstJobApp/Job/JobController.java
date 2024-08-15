package io.turntabl.FirstJobApp.Job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class JobController {
    JobService jobService;

    // constructor dependency injection
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll(){
        return ResponseEntity.ok(jobService.findAll());
    }

    @PostMapping("/jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        Job createdJob = jobService.createJob(job);
        Long id = createdJob.getId();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()   // get base url from current request
                .path("/{id}")          //append dynamic path
                .buildAndExpand(id)     // substitute value into path
                .toUri();               // convert to uri object
        return ResponseEntity.created(location).body("Job successfully crated!");
    }

    //getJobById  GET /jobs/{id}
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job = jobService.retrieveJobById(id);
        if(job != null)
            return ResponseEntity.ok(job);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // DELETE /jobs/{id}
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        boolean deleted = jobService.deleteJobById(id);
        if(deleted)
            return ResponseEntity.ok("Job deleted successfully");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/jobs/{id}")
    public ResponseEntity<Job> updateJob(@PathVariable Long id, @RequestBody Job job){
        Job updatedJob = jobService.updateJob(id, job);
        if(updatedJob != null)
            return ResponseEntity.ok(job);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
