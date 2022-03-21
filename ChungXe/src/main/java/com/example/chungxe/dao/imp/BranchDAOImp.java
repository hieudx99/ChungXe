package com.example.chungxe.dao.imp;

import com.example.chungxe.dao.BranchDAO;
import com.example.chungxe.dao.CarCategoryDAO;
import com.example.chungxe.dao.DAO;
import com.example.chungxe.model.Branch;
import com.example.chungxe.model.CarCategory;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BranchDAOImp extends DAO implements BranchDAO {

    public BranchDAOImp() {
        super();
    }

    @Override
    public Branch getBranchByID(int branchID) {
        Branch branch = null;
        String sql = "SELECT * FROM tblBranch WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, branchID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                branch = new Branch(id, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return branch;
    }

    @Override
    public List<Branch> getListBranch() {
        List<Branch> listBranch = new ArrayList<>();
        String sql = "SELECT * FROM tblBranch";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Branch branch = Branch.builder()
                        .id(id)
                        .name(name)
                        .build();
                listBranch.add(branch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBranch;
    }
}
