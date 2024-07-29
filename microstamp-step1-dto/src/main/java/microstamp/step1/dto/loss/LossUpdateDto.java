package microstamp.step1.dto.loss;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class LossUpdateDto {

    @NotBlank
    private String name;

}
