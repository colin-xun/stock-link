package com.fighting.stocklink.controller;

import com.fighting.stocklink.config.web.ActionResult;
import com.fighting.stocklink.model.Group;
import com.fighting.stocklink.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/")
    public ActionResult<?> createGroup(@RequestBody Group group) {
        groupService.createGroup(group);
        return ActionResult.success("Group created successfully");
    }

    // Other methods for getting all groups, getting group details, updating group, deleting group
}
