package microstamp.step1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SystemGoalDto {

    @NotBlank
    private String name;

    private Long projectId;

}
