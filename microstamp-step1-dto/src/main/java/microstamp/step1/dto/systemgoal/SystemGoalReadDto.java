package microstamp.step1.dto.systemgoal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class SystemGoalReadDto {

    @NotNull
    private UUID id;

    @NotBlank
    private String name;

}
