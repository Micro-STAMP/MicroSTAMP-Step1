package microstamp.step1.mapper;

import microstamp.step1.entity.Assumption;
import microstamp.step1.dto.assumption.AssumptionInsertDto;
import microstamp.step1.dto.assumption.AssumptionReadDto;

public class AssumptionMapper {

    public static AssumptionReadDto toDto(Assumption assumption){
        return new AssumptionReadDto(assumption.getId(),
                assumption.getName(),
                assumption.getCode());
    }

    public static Assumption toEntity(AssumptionInsertDto assumptionInsertDto){
        return new Assumption(assumptionInsertDto.getName(),
                assumptionInsertDto.getCode(),
                assumptionInsertDto.getAnalysisId());
    }
}
