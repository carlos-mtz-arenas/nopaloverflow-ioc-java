package mx.nopaloverflow.inversionofcontrol.facades;

import mx.nopaloverflow.inversionofcontrol.dtos.TaskDto;

import java.util.List;

public interface TaskFacade {
    List<TaskDto> getCompletedTasks();
    List<TaskDto> getOpenTasks();
}
