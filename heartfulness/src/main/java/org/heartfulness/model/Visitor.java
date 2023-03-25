package org.heartfulness.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    @Size(min = 10, message = "Please enter valid mobile number")
    @Column(unique=true)
    private String mobileNumber;

    private String HFNid;

    @NotEmpty
    @NotNull
    private String FirstName;

    @NotEmpty
    @NotNull
    private String MiddleName;

    @NotEmpty
    @NotNull
    private String LastName;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "visitor")
    List<VisitorLog> logs = new ArrayList<>();
}
