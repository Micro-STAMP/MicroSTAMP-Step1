package microstamp.step1.dto.hazard;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import microstamp.step1.dto.loss.LossReadDto;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class HazardReadDto {

    @NotNull
    private UUID id;

    @NotBlank
    private String name;

    private List<LossReadDto> losses;

    private HazardReadDto father;

}