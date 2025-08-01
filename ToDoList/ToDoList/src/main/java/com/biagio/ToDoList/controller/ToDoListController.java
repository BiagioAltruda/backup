package com.biagio.ToDoList.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todolist")
public class ToDoListController {

	private Map<Integer,User> tasks = new HashMap<>();
}
