package com.example.chungxe.dao;

import com.example.chungxe.model.Branch;
import com.example.chungxe.model.CarCategory;

import java.util.List;

public interface BranchDAO {
    public Branch getBranchByID(int branchID);
    public List<Branch> getListBranch();
}
