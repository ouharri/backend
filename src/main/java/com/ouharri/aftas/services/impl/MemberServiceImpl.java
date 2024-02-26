package com.ouharri.aftas.services.impl;

import com.ouharri.aftas.exceptions.ResourceNotCreatedException;
import com.ouharri.aftas.mapper.MemberMapper;
import com.ouharri.aftas.model.dto.requests.MemberRequest;
import com.ouharri.aftas.model.dto.responses.MemberResponse;
import com.ouharri.aftas.model.entities.Member;
import com.ouharri.aftas.repositories.MemberRepository;
import com.ouharri.aftas.services.spec.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

/**
 * Implementation of the {@link MemberService} interface.
 */
@Slf4j
@Service
@Cacheable("members")
@RequiredArgsConstructor
public class MemberServiceImpl extends _ServiceImp<UUID, MemberRequest, MemberResponse, Member, MemberRepository, MemberMapper> implements MemberService {

    private final PasswordEncoder passwordEncoder;

    @Override
    @Caching(
            evict = {
                    @CacheEvict(
                            key = "#result.get()",
                            allEntries = true,
                            condition = "#result.get() != null"
                    )
            }
    )
    @Transactional
    public Optional<MemberResponse> create(@Valid MemberRequest request) {
        Member member = mapper.toEntityFromRequest(request);
        member.setEnabled(true);
        member.setPassword(passwordEncoder.encode("fjlhfj"));

        if (member.getId() != null && repository.existsById(member.getId()))
            throw new ResourceNotCreatedException("member already exists");

        try {
            Member createdEntity = repository.saveAndFlush(member);
            return Optional.of(mapper.toResponse(createdEntity));
        } catch (Exception e) {
            log.error("Error while creating entity", e);
            throw new ResourceNotCreatedException(e.getMessage());
        }
    }

}
