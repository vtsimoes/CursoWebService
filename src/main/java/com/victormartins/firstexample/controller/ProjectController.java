package com.victormartins.firstexample.controller;

import com.victormartins.firstexample.model.Project;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {
    Logger logger = LoggerFactory.getLogger(ProjectController.class);

    static Project project = Project.builder().id(1).name("Zetta Project").build();
    static Project project2 = Project.builder().id(2).name("Zetta Project 2").build();
    static Project project3 = Project.builder().id(3).name("Zetta Project 3").build();
    public static ArrayList<Project> allProjects = new ArrayList<>(Arrays.asList(project,project2,project3));


    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Project>> getProjects(){
        logger.info("Passou pelo getproject...");
        return ResponseEntity.ok().body(this.allProjects);
    }

    @PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createProject(@RequestBody @Valid Project project){
        ResponseEntity responseEntity;
        if (project != null){
            ProjectController.allProjects.add(project);
            responseEntity = ResponseEntity.ok(project);
            logger.info("Criou o objeto");
        }else{
            logger.info("Erro na criação do objeto");
            responseEntity = ResponseEntity.badRequest().build();
        }
        return responseEntity;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<?> updateProject(@RequestBody Project project, @PathVariable(name="id") Integer id){
        ResponseEntity responseEntity;
        logger.info("Entrou no updateProject");
        if (project != null){
            if( id > ProjectController.allProjects.size()){
                logger.info("Id não existe");
                responseEntity = ResponseEntity.badRequest().build();
            }else{
                //ProjectController.allProjects.add(project);
                Project projectToUpdate = ProjectController.allProjects.get(id-1);
                ProjectController.allProjects.remove(projectToUpdate);
                //ProjectController.allProjects.
                //project.setId();
                projectToUpdate = project;
                ProjectController.allProjects.add(projectToUpdate);
                responseEntity = ResponseEntity.ok(projectToUpdate);
                logger.info("Criou o objeto");
            }

        }else{
            logger.info("Erro na criação do objeto");
            responseEntity = ResponseEntity.badRequest().build();
        }

        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable(name="id") Integer id){
        ResponseEntity responseEntity;
        if(id < ProjectController.allProjects.size()){
            Project projectToDelete = ProjectController.allProjects.get(id-1);
            ProjectController.allProjects.remove(projectToDelete);
            responseEntity = ResponseEntity.ok(projectToDelete);
        }else{
            responseEntity = ResponseEntity.badRequest().build();
        }

        return responseEntity;

    }
}
