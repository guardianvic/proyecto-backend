package Software.Counter.Api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ServerResponseDataDto {

    private  String message;
    private int status;
    private Object data;
}
