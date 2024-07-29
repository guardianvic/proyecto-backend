package Software.Counter.Api.service;


import Software.Counter.Api.entity.RolEntity;
import Software.Counter.Api.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RolService {

    @Autowired
    private RolRepository repository;

    public boolean existRolById(Long id){
        Optional<RolEntity> optionalRolEntity = this.repository.findById(id);
        return optionalRolEntity.isPresent();

}
}
