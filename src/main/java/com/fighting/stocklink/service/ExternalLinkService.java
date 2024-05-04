package com.fighting.stocklink.service;

import com.fighting.stocklink.model.ExternalLink;

import java.util.List;

public interface ExternalLinkService {
    List<ExternalLink> getSystemExternalLinks();

    void addCustomExternalLink(ExternalLink externalLink);
}
