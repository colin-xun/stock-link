package com.fighting.stocklink.controller;

import com.fighting.stocklink.context.UserContext;
import com.fighting.stocklink.model.LinkVO;
import com.fighting.stocklink.model.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/link")
public class LinkController {

    public Result<LinkVO> getLinks() {
//        UserContext
        return null;
    }

}
