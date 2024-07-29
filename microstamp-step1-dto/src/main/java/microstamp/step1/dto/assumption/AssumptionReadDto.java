package microstamp.step1.dto.assumption;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class AssumptionReadDto {

    @NotNull
    private UUID id;

    @NotBlank
    private String name;

    public AssumptionReadDto(UUID id, String name){
        this.id = id;
        this.name = name;
    }

}
