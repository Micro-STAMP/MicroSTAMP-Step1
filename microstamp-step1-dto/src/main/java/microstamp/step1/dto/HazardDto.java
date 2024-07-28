package microstamp.step1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
public class HazardDto {

    @NotBlank
    private String name;

    private UUID projectId;

    private List<UUID> lossIds;

    private UUID fatherId;

}