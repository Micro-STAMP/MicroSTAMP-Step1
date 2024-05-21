package microstamp.step1.dto;

import lombok.Data;

import java.util.List;

@Data
public class HazardDto {

    private String name;

    private Long projectId;

    private List<Long> lossIds;

    private Long fatherId;

}