package microstamp.step1.dto.systemsafetyconstraint;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import microstamp.step1.dto.hazard.HazardReadDto;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class SystemSafetyConstraintReadDto {

    @NotNull
    private UUID id;

    @NotBlank
    private String name;

    private List<HazardReadDto> hazards;

}
