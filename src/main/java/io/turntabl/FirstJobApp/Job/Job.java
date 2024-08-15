package io.turntabl.FirstJobApp.Job;

import io.turntabl.FirstJobApp.Company.Company;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name="jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String description;
    String minSalary;
    String maxSalary;
    String location;

    @ManyToOne  // not using cascade = Cascade.Type.PERSIST, because only registered companies
    // should be able to post a job. jobs with unregistered companies lead to
            // referential integrity constraint violation
    Company company;

}
