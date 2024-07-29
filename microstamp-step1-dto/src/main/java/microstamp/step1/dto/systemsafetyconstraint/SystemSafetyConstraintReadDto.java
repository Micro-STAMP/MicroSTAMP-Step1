package microstamp.step1.dto.systemsafetyconstraint;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import microstamp.step1.dto.hazard.HazardReadDto;

import java.util.List;
import java.util.UUID;

@Data
public class SystemSafetyConstraintReadDto {

    @NotNull
    private UUID id;

    @NotBlank
    private String name;

    private List<HazardReadDto> hazards;

    public SystemSafetyConstraintReadDto(UUID id, String name, List<HazardReadDto> hazards) {
        this.id = id;
        this.name = name;
        this.hazards = hazards;
    }
}
