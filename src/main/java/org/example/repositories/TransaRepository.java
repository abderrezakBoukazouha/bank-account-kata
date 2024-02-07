package org.example.repositories;

import org.example.entities.Transa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransaRepository extends JpaRepository<Transa, UUID> {
}
