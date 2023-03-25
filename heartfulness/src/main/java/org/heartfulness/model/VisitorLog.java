package org.heartfulness.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class VisitorLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private LocalDateTime timeIn;

    @NotNull
    private LocalDateTime timeOut;

    @NotEmpty
    @NotNull
    private String purpose;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "visitorLog")
    List<Promise> promises = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    private Visitor visitor;

}
