package microstamp.step1.dto.assumption;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class AssumptionUpdateDto {

    @NotBlank
    private String name;

}
