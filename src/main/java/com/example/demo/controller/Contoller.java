package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*@Controller
public class Contoller {
    @GetMapping("/")
    public String gatling () {
        String path;
        try {
            path = new String(Files.readAllBytes(Paths.get("target/gatling/lastRun.txt")));
            path.substring(0, path.length() - 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(FileWriter writer = new FileWriter("src/main/resources/static/index.html", false))
        {
            // запись всей строки
            String text = new String(Files.readAllBytes(Paths.get(String.format("/target/gatling/%s", path+"index.html"))));
            writer.write(text);
            // запись по символам
            writer.append('\n');
            writer.append('E');

            writer.flush();
        }
        catch(IOException ex){
        }

        try {
            String text = new String(Files.readAllBytes(Paths.get(String.format("/target/gatling/%s", path+"index.html"))));
            Files.writeString(Path.of(path), text, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            // Handle exception
        }

        Logger logger = LoggerFactory.getLogger(TaskController.class);
        logger.info(String.format(path+"/index.html"));
        return "index.html";
    }
}*/
