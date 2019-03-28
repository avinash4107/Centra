package com.centraprise.hrmodule.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.centraprise.hrmodule.model.EmployeeCommand;

@Repository
public class EmployeeDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private PlatformTransactionManager transactionManager;

	private static final String delete_by_id = "update employee e set e.active=? where e.emp_id=?";
	private static final String update_by__employee_id = "update employee e set e.emp_name=?,e.gender=?,e.date_of_birth=?"
			+ ",e.marital_status=?,e.password=? where e.emp_id=?";

	private static final String update_employee_address_by_id = "update employee_address a set  a.flat_number=?,a.village=?,"
			+ "a.mandal=?,a.district=?,a.state=?,a.pincode=?,a.country=? where a.emp_id=?";

	private static final String update_assignment_by_id = "update assignment_info info set info.assignment_start_date=?,"
			+ "info.assignment_end_date=?,info.job=?,info.date_of_joining=?,info.years_of_service=?,info.manager=? where info.emp_id=?";

	private static final String update_employee_bank_by_id = "update Bank_details bankInfo set bankInfo.bank_name=?,"
			+ "bankInfo.account_number=?,bankInfo.ifsc_code=? where bankInfo.emp_id=?";

	private static final String update_provident_info_by_id = "update Provident_fund_details  provident set provident.uan_number=?,"
			+ "provident.adhar_number=?,provident.prevoius_employee=?,provident.date_of_leaving=?,provident.start_date=?,"
			+ "provident.end_date=? where provident.emp_id=?";

	public int deleteEmployee(int empId) {
		int count = 0;
		int[] types = new int[2];
		types[0] = Types.BOOLEAN;
		types[1] = Types.INTEGER;
		count = jdbcTemplate.update(delete_by_id, new Object[] { false, empId }, types);
		System.out.println(count);
		return count;
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public int updateEmployee(EmployeeCommand employeeCommand) throws Exception {
		// TransactionDefinition def = new DefaultTransactionDefinition();
		// TransactionStatus status = transactionManager.getTransaction(def);
		int count = 0;
		try {
			count = jdbcTemplate.update(update_by__employee_id,
					new Object[] { employeeCommand.getName(), employeeCommand.getGender(), employeeCommand.getBday(),
							employeeCommand.getMaritalstatus(), employeeCommand.getPassword(),
							employeeCommand.getEmpId() });

			int i = 10 / 0;
			jdbcTemplate.update(update_employee_address_by_id,
					new Object[] { employeeCommand.getDoornum(), employeeCommand.getVlg(), employeeCommand.getMandal(),
							employeeCommand.getDistrict(), employeeCommand.getState(), employeeCommand.getPin(),
							employeeCommand.getCountry(), employeeCommand.getEmpId() });

			jdbcTemplate.update(update_assignment_by_id,
					new Object[] { employeeCommand.getStartdate(), employeeCommand.getEndDate(),
							employeeCommand.getJob(), employeeCommand.getJoindate(), employeeCommand.getService(),
							employeeCommand.getManager(), employeeCommand.getEmpId() });

			jdbcTemplate.update(update_employee_bank_by_id, new Object[] { employeeCommand.getBankname(),
					employeeCommand.getAccountnumber(), employeeCommand.getIfsc(), employeeCommand.getEmpId() });

			jdbcTemplate.update(update_provident_info_by_id,
					new Object[] { employeeCommand.getUan(), employeeCommand.getAdhar(),
							employeeCommand.getPreviousemp(), employeeCommand.getPreviousempleavingdate(),
							employeeCommand.getPreviousempstartdate(), employeeCommand.getPreviousempenddate(),
							employeeCommand.getEmpId() });
			// transactionManager.commit(status);
			return count;
		} catch (Exception e) {
			// transactionManager.rollback(status);
			e.printStackTrace();
			throw new Exception("Database is down ");
		}
	}
}
