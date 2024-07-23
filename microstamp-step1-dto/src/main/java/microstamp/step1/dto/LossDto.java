package microstamp.step1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LossDto {

    @NotBlank
    private String name;

    private Long projectId;

}
