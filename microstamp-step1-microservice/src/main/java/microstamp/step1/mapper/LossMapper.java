package microstamp.step1.mapper;

import microstamp.step1.data.Loss;
import microstamp.step1.dto.loss.LossReadDto;

public class LossMapper {

    public static LossReadDto toDto(Loss loss){
        return new LossReadDto(loss.getId(),
                loss.getName());
    }
}
