package com.fighting.stocklink.service.impl;

import com.fighting.stocklink.dao.LayoutMapper;
import com.fighting.stocklink.model.Layout;
import com.fighting.stocklink.service.LayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayoutServiceImpl implements LayoutService {
    @Autowired
    private LayoutMapper layoutMapper;

    public void createLayout(Layout layout) {
        layoutMapper.insert(layout);
    }

    public List<Layout> getAllLayoutsByUserId(int userId) {
        return layoutMapper.findByUserId(userId);
    }
}
