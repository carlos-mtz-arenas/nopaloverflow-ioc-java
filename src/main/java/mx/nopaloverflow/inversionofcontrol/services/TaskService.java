package mx.nopaloverflow.inversionofcontrol.services;

import mx.nopaloverflow.inversionofcontrol.dtos.TaskDto;
import mx.nopaloverflow.inversionofcontrol.exceptions.IOTaskException;

import java.util.List;

public interface TaskService {

    List<TaskDto> getAllTasks() throws IOTaskException;
}
