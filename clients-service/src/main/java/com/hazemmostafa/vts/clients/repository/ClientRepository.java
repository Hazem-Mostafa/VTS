package com.hazemmostafa.vts.clients.repository;

import com.hazemmostafa.vts.clients.domain.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends BaseRepository<Client, Long> {
}
