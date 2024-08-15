package io.turntabl.FirstJobApp.Company;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.turntabl.FirstJobApp.Job.Job;
import io.turntabl.FirstJobApp.Review.Review;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;

    @OneToMany(mappedBy = "company")
    private List<Review> reviews;


}
