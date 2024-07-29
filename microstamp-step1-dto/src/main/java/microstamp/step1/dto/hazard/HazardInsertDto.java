package microstamp.step1.dto.hazard;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class HazardInsertDto {

    @NotBlank
    private String name;

    @NotNull
    private UUID projectId;

    private List<UUID> lossIds;

    private UUID fatherId;

}