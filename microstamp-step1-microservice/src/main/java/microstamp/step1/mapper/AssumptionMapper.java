package microstamp.step1.mapper;

import microstamp.step1.data.Assumption;
import microstamp.step1.dto.assumption.AssumptionInsertDto;
import microstamp.step1.dto.assumption.AssumptionReadDto;

public class AssumptionMapper {

    public static AssumptionReadDto toDto(Assumption assumption){
        return new AssumptionReadDto(assumption.getId(),
                assumption.getName());
    }

    public static Assumption toEntity(AssumptionInsertDto assumptionInsertDto){
        return new Assumption(assumptionInsertDto.getName(),
                assumptionInsertDto.getAnalysisId());
    }
}
