package microstamp.step1.dto.systemsafetyconstraint;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class SystemSafetyConstraintUpdateDto {

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    private List<UUID> hazardsId;

}
