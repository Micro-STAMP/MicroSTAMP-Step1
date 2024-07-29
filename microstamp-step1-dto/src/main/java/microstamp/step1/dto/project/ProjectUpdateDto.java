package microstamp.step1.dto.project;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class ProjectUpdateDto {

    @NotBlank
    private String name;

    private String description;

    private String url;

    private String type;

}
