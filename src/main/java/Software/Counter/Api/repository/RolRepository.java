package Software.Counter.Api.repository;

import Software.Counter.Api.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface RolRepository extends
        JpaRepository<RolEntity, Long>,
        JpaSpecificationExecutor<RolEntity>{}
