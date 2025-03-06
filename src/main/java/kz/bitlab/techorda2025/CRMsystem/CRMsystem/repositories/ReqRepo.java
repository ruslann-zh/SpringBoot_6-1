package kz.bitlab.techorda2025.CRMsystem.CRMsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ReqRepo extends JpaRepository<ApplicationRequest, Long> {
}