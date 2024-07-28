package microstamp.step1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class ProjectDto {

    @NotBlank
    private String name;

    private String description;

    private String url;

    private String type;

    private UUID userId;

}
