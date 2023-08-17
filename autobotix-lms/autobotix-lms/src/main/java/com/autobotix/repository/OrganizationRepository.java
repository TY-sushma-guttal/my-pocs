package com.autobotix.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.autobotix.entity.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, String> {

	Optional<Organization> findByEmail(String orgId);

	Optional<Organization> findByContactNumber(String organizationContactNo);

}
