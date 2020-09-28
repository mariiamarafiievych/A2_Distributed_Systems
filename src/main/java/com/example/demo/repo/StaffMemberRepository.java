package com.example.demo.repo;

import com.example.demo.entities.StaffMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StaffMemberRepository extends JpaRepository<StaffMember, UUID> {
}
