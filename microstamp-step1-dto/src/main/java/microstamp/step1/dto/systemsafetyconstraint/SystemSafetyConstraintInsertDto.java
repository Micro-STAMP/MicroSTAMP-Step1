package microstamp.step1.dto.systemsafetyconstraint;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class SystemSafetyConstraintInsertDto {

    @NotBlank
    private String name;

    @NotNull
    private UUID projectId;

    private List<UUID> hazardsId;

}
