package Software.Counter.Api.service;

import Software.Counter.Api.dto.UserDto;
import Software.Counter.Api.entity.UserEntity;
import Software.Counter.Api.repository.UserRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



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


    public List<UserDto> findAll() {

        List<UserEntity> entities = this.repository.findAll();
        List<UserDto> dtos = new ArrayList<>();

        for (UserEntity entity : entities) {
            UserDto dto = UserDto.builder()
                    .id(entity.getId())
                    .nombre(entity.getNombre())
                    .apellido(entity.getApellido())
                    .email(entity.getEmail())
                    .telefono(entity.getTelefono())
                    .usuario(entity.getUsuario())
                    .fechaDeCreacion(entity.getFechaDeCreacion())
                    .rolId(entity.getRolId())
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
                .rolId(entity.getRolId())
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
        entity.setRolId(newData.getRolId());

        this.repository.save(entity);

        newData.setId(entity.getId());

        return newData;
    }

}
