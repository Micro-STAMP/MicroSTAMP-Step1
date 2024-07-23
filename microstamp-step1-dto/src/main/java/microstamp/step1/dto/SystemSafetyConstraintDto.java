package microstamp.step1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class SystemSafetyConstraintDto {

    @NotBlank
    private String name;

    private Long projectId;

    private List<Long> hazardsId;

}
