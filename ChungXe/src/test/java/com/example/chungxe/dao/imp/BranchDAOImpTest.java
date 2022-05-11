package com.example.chungxe.dao.imp;

import com.example.chungxe.dao.BranchDAO;
import com.example.chungxe.dao.DAO;
import com.example.chungxe.model.Branch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BranchDAOImpTest {

    @Autowired
    BranchDAO branchDAO;

//    @BeforeEach
//    void setUp() {
//        branchDAO = new BranchDAOImp();
//    }

    @Test
    void getBranchByID() {
        int id = 1;
        Branch branch = branchDAO.getBranchByID(id);
        assertNotNull(branch);
        assertEquals(branch.getId(), id);
        assertEquals(branch.getName(), "Mec");
    }

    @Test
    void givenNonExistBranchIdWhenGetBranchByID_thenThrowException() {
        int id = 6;
        Branch branch = branchDAO.getBranchByID(id);
        Exception exception = assertThrows(SQLException.class, () -> {
            throw new SQLException("Branch not exist!");
        });
        assertEquals(exception.getMessage(), "Branch not exist!");

    }


    @Test
    void getListBranch() {
        List<Branch> listBranch = new ArrayList<>();
        listBranch = branchDAO.getListBranch();
        assertEquals(listBranch.size(), 5);
        assertEquals(listBranch.get(0).getName(), "Audi");
    }
}