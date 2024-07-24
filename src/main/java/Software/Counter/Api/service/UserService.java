package Software.Counter.Api.service;

import Software.Counter.Api.dto.UserDto;
import Software.Counter.Api.entity.UserEntity;
import Software.Counter.Api.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserDto create(UserDto dto){

        UserEntity entity = new UserEntity();
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setEmail(dto.getEmail());
        entity.setTelefono(dto.getTelefono());
        entity.setUsuario(dto.getUsuario());
        entity.setPassword("1234");
        entity.setFechaDeCreacion(dto.getFechaDeCreacion());
        entity.setRolId(dto.getRolId());

       entity = repository.save(entity);

       dto.setId(entity.getId());

        return dto;
    }

}
