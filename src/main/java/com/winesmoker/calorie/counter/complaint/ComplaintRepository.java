package com.winesmoker.calorie.counter.complaint;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    @Override
    <S extends Complaint> S save(S entity);
}
