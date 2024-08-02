package microstamp.step1.dto.loss;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class LossInsertDto {

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @NotNull
    private UUID analysisId;

}
