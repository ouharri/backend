package com.ouharri.aftas.repositories;

import com.ouharri.aftas.model.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Repository interface for accessing and managing {@link Member} entities in the database.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {

}