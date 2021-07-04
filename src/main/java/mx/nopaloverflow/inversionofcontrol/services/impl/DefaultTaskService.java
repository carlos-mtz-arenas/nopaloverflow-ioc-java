package mx.nopaloverflow.inversionofcontrol.services.impl;

import com.google.gson.Gson;
import mx.nopaloverflow.inversionofcontrol.dtos.TaskDto;
import mx.nopaloverflow.inversionofcontrol.exceptions.IOTaskException;
import mx.nopaloverflow.inversionofcontrol.services.TaskService;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

public class DefaultTaskService implements TaskService {
    private static final Logger LOG = Logger.getLogger(DefaultTaskService.class.getName());
    private static final String ENDPOINT = "https://jsonplaceholder.typicode.com/todos";

    @Override
    public List<TaskDto> getAllTasks() throws IOTaskException {
        final var client = HttpClient.newBuilder()
                .build();

        final var request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(ENDPOINT))
                .build();

        try {
            final var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            final var rawJson = response.body();
            final var gson = new Gson();
            return new ArrayList<>(gson.<Collection<TaskDto>>fromJson(rawJson, TaskDto[].class));
        } catch (final IOException | InterruptedException ex) {
            LOG.warning("Error while calling the task's endpoint");
            throw new IOTaskException("Error while calling the task service using http", ex);
        }
    }
}
