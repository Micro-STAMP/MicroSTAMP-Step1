package microstamp.step1.mapper;

import microstamp.step1.entity.Loss;
import microstamp.step1.dto.loss.LossInsertDto;
import microstamp.step1.dto.loss.LossReadDto;

public class LossMapper {

    public static LossReadDto toDto(Loss loss){
        return new LossReadDto(loss.getId(),
                loss.getName(),
                loss.getCode());
    }

    public static Loss toEntity(LossInsertDto lossInsertDto){
        return new Loss(lossInsertDto.getName(),
                lossInsertDto.getCode(),
                lossInsertDto.getAnalysisId());
    }
}
