package microstamp.step1.dto.assumption;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class AssumptionInsertDto {

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @NotNull
    private UUID analysisId;

}
