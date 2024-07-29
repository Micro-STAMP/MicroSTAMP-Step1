package microstamp.step1.dto.systemgoal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class SystemGoalReadDto {

    @NotNull
    private UUID id;

    @NotBlank
    private String name;

    public SystemGoalReadDto(UUID id, String name){
        this.id = id;
        this.name = name;
    }
}
