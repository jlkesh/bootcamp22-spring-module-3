package dev.jlkeesh.springadvanced.task;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TaskRepository {
    private final JdbcTemplate jdbcTemplate;
    private static final String SELECT_TASKS_QUERY = "SELECT * FROM TASKS;";

    public List<Task> getAll() {
        return jdbcTemplate.query(SELECT_TASKS_QUERY,
                BeanPropertyRowMapper.newInstance(Task.class));
    }

}
