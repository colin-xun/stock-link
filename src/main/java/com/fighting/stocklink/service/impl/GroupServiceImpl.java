package com.fighting.stocklink.service.impl;

import com.fighting.stocklink.dao.GroupMapper;
import com.fighting.stocklink.model.Group;
import com.fighting.stocklink.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    public void createGroup(Group group) {
        groupMapper.insert(group);
    }
}
