package mx.nopaloverflow.inversionofcontrol.facades.impl;

import mx.nopaloverflow.inversionofcontrol.dtos.TaskDto;
import mx.nopaloverflow.inversionofcontrol.exceptions.IOTaskException;
import mx.nopaloverflow.inversionofcontrol.facades.TaskFacade;
import mx.nopaloverflow.inversionofcontrol.services.TaskService;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class DefaultTaskFacade implements TaskFacade {
    private static final Logger LOG = Logger.getLogger(DefaultTaskFacade.class.getName());

    private TaskService taskService;

    public DefaultTaskFacade(final TaskService taskService) {
        this.taskService = taskService;
    }

    private void validateTasks(final List<TaskDto> tasks) {
        assert tasks != null : "Tasks can not be null";
    }

    private List<TaskDto> fetchTasks() {
        try {
            return getTaskService().getAllTasks();
        } catch (IOTaskException e) {
            LOG.log(Level.SEVERE, "Unable to call service");
            return Collections.emptyList();
        }
    }

    @Override
    public List<TaskDto> getCompletedTasks() {
        LOG.log(Level.FINE, "fetching the tasks");
        final List<TaskDto> tasks = fetchTasks();

        validateTasks(tasks);
        LOG.log(Level.FINE, String.format("got tasks [%d]", tasks.size()));

        return tasks.stream()
                .filter(t -> Boolean.TRUE.equals(t.getCompleted()))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskDto> getOpenTasks() {
        LOG.log(Level.FINE, "fetching the tasks");
        final List<TaskDto> tasks = fetchTasks();

        validateTasks(tasks);
        LOG.log(Level.FINE, String.format("got tasks [%d]", tasks.size()));

        return tasks.stream()
                .filter(t -> t.getCompleted() == null || Boolean.FALSE.equals(t.getCompleted()))
                .collect(Collectors.toList());
    }

    public TaskService getTaskService() {
        return taskService;
    }
}
