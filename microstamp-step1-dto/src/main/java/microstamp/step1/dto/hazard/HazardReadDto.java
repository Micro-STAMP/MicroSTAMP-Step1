package microstamp.step1.dto.hazard;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import microstamp.step1.dto.loss.LossReadDto;

import java.util.List;
import java.util.UUID;

@Data
public class HazardReadDto {

    @NotNull
    private UUID id;

    @NotBlank
    private String name;

    private List<LossReadDto> losses;

    private HazardReadDto father;

    public HazardReadDto(UUID id, String name, List<LossReadDto> losses, HazardReadDto father){
        this.id = id;
        this.name = name;
        this.losses = losses;
        this.father = father;
    }

}