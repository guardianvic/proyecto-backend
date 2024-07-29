package Software.Counter.Api.service;

import Software.Counter.Api.dto.UserDto;
import Software.Counter.Api.entity.RolEntity;
import Software.Counter.Api.entity.UserEntity;
import Software.Counter.Api.exception.EmailNotValidException;
import Software.Counter.Api.exception.ResourceNotFoundException;
import Software.Counter.Api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static Software.Counter.Api.dto.UserDto.*;


@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Autowired
    private RolService rolService;

    public boolean validateByEmail(String email){
        UserEntity entity = this.repository.findByEmail(email);
        if (Objects.isNull(entity)){
            return false;
        }

        return true;

    }

    public UserDto create(UserDto dto){
        // no se puede registrar un usuario con un correo registrado
        if(validateByEmail(dto.getEmail())){
            throw new EmailNotValidException();
        }
        // no se puede registrar un usuario sin un rol previamente registrado
        if(!rolService.existRolById(dto.getRolId())){
            throw new ResourceNotFoundException();

        }

        UserEntity entity = new UserEntity();
        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setEmail(dto.getEmail());
        entity.setTelefono(dto.getTelefono());
        entity.setUsuario(dto.getUsuario());
        entity.setPassword("1234");
        entity.setFechaDeCreacion(dto.getFechaDeCreacion());
        RolEntity rolEntity = new RolEntity();
        rolEntity.setId(dto.getRolId());

        entity.setRolEntities(rolEntity);


        //entity.setRolId(dto.getRolId());

       entity = repository.save(entity);

       dto.setId(entity.getId());

        return dto;
    }


    public List<UserDto> findAll() {

        List<UserEntity> entities = this.repository.findAll();
        List<UserDto> dtos = new ArrayList<>();

        for (UserEntity entity : entities) {
            UserDto dto = builder()
                    .id(entity.getId())
                    .nombre(entity.getNombre())
                    .apellido(entity.getApellido())
                    .email(entity.getEmail())
                    .telefono(entity.getTelefono())
                    .usuario(entity.getUsuario())
                    .fechaDeCreacion(entity.getFechaDeCreacion())
                    .rolId(entity.getRolEntities().getId())
                    .titulo(entity.getRolEntities().getTitulo())
                    .build();
            dtos.add(dto);

        }

        return dtos;
    }

    public UserDto getById(Long id){

        UserEntity entity;
        entity = this.repository.findById(id).get();
        UserDto dto = UserDto.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .apellido(entity.getApellido())
                .email(entity.getEmail())
                .telefono(entity.getTelefono())
                .usuario(entity.getUsuario())
                .fechaDeCreacion(entity.getFechaDeCreacion())
                .rolId(Long.valueOf(entity.getRolEntities().getTitulo()))
                .build();

        return dto;
    }


    public boolean deleteById(long id){
        if (id <= 0){
            return false;
        }

       if(!this.repository.findById(id).isPresent()){
            return false;
       }

       this.repository.deleteById(id);
       return true;
    }

    public UserDto updateById(Long id, UserDto newData){
        if (id <= 0){
            return null;
        }
        Optional<UserEntity> optEntity = this.repository.findById(id);

        if(!optEntity.isPresent()){
            return null;
        }
        //ya tiene internamente el id
        UserEntity entity = optEntity.get();

        entity.setNombre(newData.getNombre());
        entity.setApellido(newData.getApellido());
        entity.setEmail(newData.getEmail());
        entity.setTelefono(newData.getTelefono());
        entity.setUsuario(newData.getUsuario());
        entity.setFechaDeCreacion(newData.getFechaDeCreacion());

        RolEntity rolEntity = new RolEntity();
        rolEntity.setId(newData.getRolId());
        entity.setRolEntities(rolEntity);
        //entity.setRolId(newData.getRolId());

        this.repository.save(entity);

        newData.setId(entity.getId());

        return newData;
    }

}
