package microstamp.step1.converters;

import microstamp.step1.data.SystemGoal;
import microstamp.step1.dto.SystemGoalDto;

public class SystemGoalConverter {

    public static SystemGoalDto toDto(SystemGoal target, Long id){
        boolean targetIsNull = target == null;

        String name = targetIsNull ? null : target.getName();
        Long projectId = targetIsNull ? null : id;

        return new SystemGoalDto(name,projectId);
    }

}
