package com.fighting.stocklink.controller;

import com.fighting.stocklink.config.web.ActionResult;
import com.fighting.stocklink.model.LinkVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/link")
public class LinkController {

    public ActionResult<LinkVO> getLinks() {
//        UserContext
        return null;
    }

}
