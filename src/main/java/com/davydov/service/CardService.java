package com.davydov.service;

import com.davydov.repository.ProductRepository;
import org.springframework.stereotype.Service;

import static com.davydov.service.CardService.Command.*;

@Service
public class CardService {

    private ProductRepository repo;

    public CardService(ProductRepository repo) {
        this.repo = repo;
    }

    public void doCommand(String c) {
        if (c.startsWith(GETALL.command)) {
            System.out.println(repo.getAll());
        } else if (c.startsWith(GET.command)) {
            System.out.println(repo.getById(getIdFromCommand(c)));
        } else if (c.startsWith(DELETE.command)) {
            repo.deleteById(getIdFromCommand(c));
        } else if (c.startsWith(ADD.command)) {
            repo.add(getIdFromCommand(c));
        }
    }

    public Long getIdFromCommand(String command) {
        try {
            return Long.parseLong(command.split(" ")[1]);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public enum Command {
        GET("get"), DELETE("delete"), GETALL("getall"), ADD("add");
        public String command;
        Command(String command) {
            this.command = command;
        }
        public String getCommand() {
            return command;
        }
    }
}
