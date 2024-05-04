package com.fighting.stocklink.controller;

import com.fighting.stocklink.config.web.ActionResult;
import com.fighting.stocklink.model.Layout;
import com.fighting.stocklink.service.LayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/layout")
public class LayoutController {
    @Autowired
    private LayoutService layoutService;

    @PostMapping("/")
    public ActionResult<?> createLayout(@RequestBody Layout layout) {
        layoutService.createLayout(layout);
        return ActionResult.success("Layout created successfully");
    }

    @GetMapping("/user/{userId}")
    public ActionResult<List<Layout>> getAllLayouts(@PathVariable int userId) {
        List<Layout> layouts = layoutService.getAllLayoutsByUserId(userId);
        return ActionResult.success(layouts);
    }
}
