package microstamp.step1.dto.hazard;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class HazardUpdateDto {

    @NotBlank
    private String name;

    private List<UUID> lossIds;

    private UUID fatherId;

}