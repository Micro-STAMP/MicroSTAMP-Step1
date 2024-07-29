package microstamp.step1.dto.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ProjectInsertDto {

    @NotBlank
    private String name;

    private String description;

    private String url;

    private String type;

    @NotNull
    private UUID userId;

}
