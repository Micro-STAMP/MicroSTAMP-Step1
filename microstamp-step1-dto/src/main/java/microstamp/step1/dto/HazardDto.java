package microstamp.step1.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class HazardDto {

    @NotBlank
    private String name;

    private Long projectId;

    private List<Long> lossIds;

    private Long fatherId;

}