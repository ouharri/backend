package com.ouharri.aftas.repositories;

import com.ouharri.aftas.model.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
}