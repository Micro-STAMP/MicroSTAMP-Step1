package microstamp.step1.dto.systemgoal;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class SystemGoalUpdateDto {

    @NotBlank
    private String name;

    @NotBlank
    private String code;

}
