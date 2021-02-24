package com.hazemmostafa.vts.vaccines.repository;

import com.hazemmostafa.vts.vaccines.domain.Vaccine;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends BaseRepository<Vaccine, Long> {
}
