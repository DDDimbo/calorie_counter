package com.winesmoker.calorie.counter.complaint;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter @Setter
@ToString
@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @NotNull
    private Long initiator;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Complaint complaint = (Complaint) o;
        return Objects.equals(id, complaint.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
