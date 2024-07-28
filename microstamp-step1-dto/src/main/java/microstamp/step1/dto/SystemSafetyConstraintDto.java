package microstamp.step1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class SystemSafetyConstraintDto {

    @NotBlank
    private String name;

    private UUID projectId;

    private List<UUID> hazardsId;

}
