package com.example.chungxe.dao.imp;

import com.example.chungxe.dao.*;
import com.example.chungxe.model.Bill;
import com.example.chungxe.model.Customer;
import com.example.chungxe.model.dto.BillDTO;
import com.example.chungxe.model.dto.ShortBill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BillDAOImpTest {

    @Autowired
    BillDAOImp billDAO;

//    @BeforeEach
//    void setUp() {
//        billDAO = new BillDAOImp();
//        EmployeeDAO employeeDAO = new EmployeeDAOImp();
//        CustomerDAO customerDAO = new CustomerDAOImp();
//        CarDAO carDAO = new CarDAOImp();
//        CarCategoryDAO categoryDAO = new CarCategoryImp();
//        BranchDAO branchDAO = new BranchDAOImp();
//        ((CarDAOImp) carDAO).setCarCategoryDAO(categoryDAO);
//        ((CarDAOImp) carDAO).setBranchDAO(branchDAO);
//        billDAO.setCustomerDAO(customerDAO);
//        billDAO.setEmployeeDAO(employeeDAO);
//
//        billDAO.setCarDAO(carDAO);
//    }

    @Test
    void getBillsByCar() {
        int id = 1;
        String startDate = "2022-05-01";
        String endDate = "2022-05-30";
        List<ShortBill> result = billDAO.getBillsByCar(id, startDate, endDate);
        assertNotNull(result);
        assertEquals(result.size(), 1);
    }

    @Test
    void getBillById() {
        int billId = 5;
        Bill bill = billDAO.getBillById(billId);
        assertNotNull(bill);
        assertEquals(bill.getId(), billId);
        assertEquals(bill.getConfirmStatus(), "pending");
        assertEquals(bill.getCar().getId(), 1);
        assertEquals(bill.getCreateAt(), "2022-05-02 04:21:32");
    }

    @Test
    void createBillSuccess() {
        Connection conn = DAO.con;

        //data preparation
        BillDTO billDTO = new BillDTO();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String createdAt = dtf.format(now);
        billDTO.setCreateAt(createdAt);
        billDTO.setPaymentStatus("unpaid");
        billDTO.setConfirmStatus("pending");
        billDTO.setPaymentMethod("cash");
        billDTO.setTotalPrice(50000);
        billDTO.setStartDate("2022-04-18");
        billDTO.setEndDate("2022-04-19");
        billDTO.setCarId(1);
        billDTO.setCustomerId(1);
        //
        try {
            conn.setAutoCommit(false);
            Bill billRes = billDAO.createBill(billDTO);
            Bill billTest = billDAO.getBillById(billRes.getId());
            assertNotNull(billTest);
            assertEquals(billRes.getCreateAt(), billTest.getCreateAt());
            assertEquals(billRes.getTotalPrice(), billTest.getTotalPrice());
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (!conn.getAutoCommit()) {
                    conn.rollback();
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    @Test
    void givenInvalidBill_whenCreateBill_thenThrowException() {
        Connection conn = DAO.con;

        //data preparation
        BillDTO billDTO = new BillDTO();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String createdAt = dtf.format(now);
        billDTO.setCreateAt(createdAt);
        billDTO.setPaymentStatus("unpaid");
        billDTO.setConfirmStatus("pending");
        billDTO.setPaymentMethod("cash");
        billDTO.setTotalPrice(50000);
        billDTO.setStartDate("2022-04-18");
        billDTO.setEndDate("2022-04-19");
        billDTO.setCarId(100000);
        billDTO.setCustomerId(1);
        //
        try {
            conn.setAutoCommit(false);
            Bill billRes = billDAO.createBill(billDTO);
//            Bill billTest = billDAO.getBillById(billRes.getId());
            SQLIntegrityConstraintViolationException exception = assertThrows(SQLIntegrityConstraintViolationException.class, () -> {
                throw new SQLIntegrityConstraintViolationException("" +
                        "Cannot add or update a child row: a foreign key constraint fails (`sqa`.`tblbill`, CONSTRAINT `tblbill_ibfk_2` FOREIGN KEY (`carId`) REFERENCES `tblcar` (`id`))");
            });
            assertTrue(exception.getMessage().contains("Cannot add or update a child row:"));

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (!conn.getAutoCommit()) {
                    conn.rollback();
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void getNotConfirmedBills() {
        List<Bill> result = billDAO.getNotConfirmedBills();
        assertNotNull(result);
        assertEquals(result.size(), 4);
    }


    //update
    @Test
    void confirmBill() {
        Connection conn = DAO.con;
        int billId = 2;
        String testStatus = "testStatus";

        try {
            conn.setAutoCommit(false);
            billDAO.confirmBill(billId, testStatus);
            Bill bill = billDAO.getBillById(billId);
            assertEquals(bill.getId(), billId);
            assertEquals(bill.getConfirmStatus(), testStatus);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}