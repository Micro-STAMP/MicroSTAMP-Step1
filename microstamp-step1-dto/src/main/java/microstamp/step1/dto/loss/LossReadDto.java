package microstamp.step1.dto.loss;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class LossReadDto {

    @NotNull
    private UUID id;

    @NotBlank
    private String name;

    public LossReadDto(UUID id, String name){
        this.id = id;
        this.name = name;
    }

}
