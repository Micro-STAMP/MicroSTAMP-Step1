package microstamp.step1.mapper;

import microstamp.step1.data.SystemGoal;
import microstamp.step1.dto.systemgoal.SystemGoalReadDto;

public class SystemGoalMapper {

    public static SystemGoalReadDto toDto(SystemGoal systemGoal){
        return new SystemGoalReadDto(systemGoal.getId(),
                systemGoal.getName());
    }
}
