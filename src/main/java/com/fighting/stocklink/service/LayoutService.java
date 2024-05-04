package com.fighting.stocklink.service;

import com.fighting.stocklink.model.Layout;

import java.util.List;

public interface LayoutService {
    void createLayout(Layout layout);

    List<Layout> getAllLayoutsByUserId(int userId);
}
