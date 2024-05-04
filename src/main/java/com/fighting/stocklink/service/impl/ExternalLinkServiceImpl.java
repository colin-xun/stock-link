package com.fighting.stocklink.service.impl;

import com.fighting.stocklink.dao.ExternalLinkMapper;
import com.fighting.stocklink.model.ExternalLink;
import com.fighting.stocklink.service.ExternalLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExternalLinkServiceImpl implements ExternalLinkService {
    @Autowired
    private ExternalLinkMapper externalLinkMapper;

    public void addCustomExternalLink(ExternalLink externalLink) {
        externalLinkMapper.insert(externalLink);
    }

    public List<ExternalLink> getSystemExternalLinks() {
        return externalLinkMapper.findByIsSystemDefinedTrue();
    }

}
