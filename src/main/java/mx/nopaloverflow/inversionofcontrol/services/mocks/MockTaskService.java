package mx.nopaloverflow.inversionofcontrol.services.mocks;

import mx.nopaloverflow.inversionofcontrol.dtos.TaskDto;
import mx.nopaloverflow.inversionofcontrol.services.TaskService;

import java.util.Arrays;
import java.util.List;

public class MockTaskService implements TaskService {
    @Override
    public List<TaskDto> getAllTasks() {
        return Arrays.asList(task(1L, 1L, "mocked element 1", false),
                task(2L, 2L, "mocked element 2", true),
                task(3L, 3L, "mocked element 3", false),
                task(2L, 4L, "mocked element 4", true));
    }

    private TaskDto task(final Long userId, final Long id, final String title, final boolean completed) {
        final var dto = new TaskDto();
        dto.setUserId(userId);
        dto.setId(id);
        dto.setTitle(title);
        dto.setCompleted(completed);
        return dto;
    }
}
