package microstamp.step1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class AssumptionDto {

    @NotBlank
    private String name;

    private UUID projectId;

}
