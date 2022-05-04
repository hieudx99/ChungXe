package com.example.chungxe.dao.imp;

import com.example.chungxe.dao.BranchDAO;
import com.example.chungxe.model.Branch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BranchDAOImpTest {

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
    void getListBranch() {
        List<Branch> listBranch = new ArrayList<>();
        listBranch = branchDAO.getListBranch();
        assertEquals(listBranch.size(), 5);
        assertEquals(listBranch.get(0).getName(), "Audi");
    }
}