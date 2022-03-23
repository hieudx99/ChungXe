package com.example.chungxe.dao;

import com.example.chungxe.model.Branch;
import com.example.chungxe.model.CarCategory;

import java.util.List;

public interface BranchDAO {
    Branch getBranchByID(int branchID);
    List<Branch> getListBranch();
}
