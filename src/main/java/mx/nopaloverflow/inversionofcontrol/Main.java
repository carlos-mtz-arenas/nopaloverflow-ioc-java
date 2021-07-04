package mx.nopaloverflow.inversionofcontrol;

import mx.nopaloverflow.inversionofcontrol.dtos.TaskDto;
import mx.nopaloverflow.inversionofcontrol.facades.impl.DefaultTaskFacade;
import mx.nopaloverflow.inversionofcontrol.services.impl.DefaultTaskService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(final String[] args) {

        // configure the logger
        Logger.getGlobal().setLevel(Level.ALL);

        // manually resolve the dependencies
        final var taskService = new DefaultTaskService();
        final var taskFacade = new DefaultTaskFacade(taskService);

        // perform the calculations
        final var openTasks = taskFacade.getOpenTasks();
        final var completedTasks = taskFacade.getCompletedTasks();

        LOG.info(String.format("Open tasks [%s]", collectTasks(openTasks)));
        LOG.info(String.format("Completed tasks [%s]", collectTasks(completedTasks)));
    }

    private static String collectTasks(final List<TaskDto> tasks) {
        return tasks.stream()
                .map(t -> t.getTitle() + " " + t.getCompleted())
                .collect(Collectors.joining(","));
    }
}
