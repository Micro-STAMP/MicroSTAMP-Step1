package microstamp.step1.dto.systemsafetyconstraint;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class SystemSafetyConstraintInsertDto {

    @NotBlank
    private String name;

    @NotNull
    private UUID analysisId;

    private List<UUID> hazardsId;

}
