package com.fighting.stocklink.service.impl;

import com.fighting.stocklink.dao.EmbeddedWebsiteMapper;
import com.fighting.stocklink.model.EmbeddedWebsite;
import com.fighting.stocklink.service.EmbeddedWebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmbeddedWebsiteServiceImpl implements EmbeddedWebsiteService {
    @Autowired
    private EmbeddedWebsiteMapper embeddedWebsiteMapper;

    public void embedWebsite(EmbeddedWebsite website) {
        embeddedWebsiteMapper.insert(website);
    }
}
