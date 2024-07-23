package microstamp.step1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AssumptionDto {

    @NotBlank
    private String name;

    private Long projectId;

}
