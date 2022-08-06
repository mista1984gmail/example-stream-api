package com.mastery.examplestreamapi.repository;

import com.mastery.examplestreamapi.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
