package Software.Counter.Api.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;


@Data
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;

    @NotBlank
    private String nombre;

    @NotNull
    private String apellido;

    @NotBlank
    private String email;

    @NotBlank
    private String telefono;

    @NotBlank
    private String usuario;


    private String password;

    @NotNull
    private Date fechaDeCreacion;

    @NotNull
    private Long rolId;
}
