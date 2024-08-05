package microstamp.step1.dto.assumption;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AssumptionUpdateDto {

    @NotBlank
    private String name;

    @NotBlank
    private String code;
}
