package microstamp.step1.dto.systemgoal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class SystemGoalInsertDto {

    @NotBlank
    private String name;

    @NotNull
    private UUID projectId;

}
