package com.fighting.stocklink.controller;

import com.fighting.stocklink.config.web.ActionResult;
import com.fighting.stocklink.model.ExternalLink;
import com.fighting.stocklink.service.ExternalLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/external-link")
public class ExternalLinkController {

    @Autowired
    private ExternalLinkService externalLinkService;

    @GetMapping("/system")
    public ActionResult<List<ExternalLink>> getSystemExternalLinks() {
        List<ExternalLink> systemExternalLinks = externalLinkService.getSystemExternalLinks();
        return ActionResult.success(systemExternalLinks);
    }

    @PostMapping("/")
    public ActionResult<?> addCustomExternalLink(@RequestBody ExternalLink externalLink) {
        externalLinkService.addCustomExternalLink(externalLink);
        return ActionResult.success("Custom external link added successfully");
    }

    // Other methods for getting custom external links, deleting custom external link
}
