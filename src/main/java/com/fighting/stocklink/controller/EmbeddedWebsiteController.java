package com.fighting.stocklink.controller;

import com.fighting.stocklink.config.web.ActionResult;
import com.fighting.stocklink.model.EmbeddedWebsite;
import com.fighting.stocklink.service.EmbeddedWebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 嵌入网站模型
 */
@RestController
@RequestMapping("/website")
public class EmbeddedWebsiteController {

    @Autowired
    private EmbeddedWebsiteService embeddedWebsiteService;

    @PostMapping("/create")
    public ActionResult<?> embedWebsite(@RequestBody EmbeddedWebsite website) {
        embeddedWebsiteService.embedWebsite(website);
        return ActionResult.success("Website embedded successfully");
    }

    // Other methods for updating and deleting embedded website
}
