package com.minitasks.tasks.service;

import com.minitasks.tasks.dto.Task;
import com.minitasks.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    public Task createTask(Task taskDetails) {
        return taskRepository.save(taskDetails);
    }

    public Task updateTasks(Long id, Task taskDetails) {
        Task task = getTaskById(id);
        if(task!=null){
            task.setName(taskDetails.getName());
            task.setDescription(taskDetails.getDescription());
            taskRepository.save(task);
        }
        return null;
    }
}
