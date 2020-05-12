package com.kbtg.fds.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class CallProcedureRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
 
	public String clearPcbLoanTxnIn(String transDate){
	   StoredProcedureQuery storedProc = entityManager.createStoredProcedureQuery("ClearPcb_loan_Txn_In");
	   
	   // Set the parameters of the stored procedure.
	   storedProc.registerStoredProcedureParameter("transDate", String.class, ParameterMode.IN);
	   storedProc.setParameter("transDate", transDate);
 
	   // Call the stored procedure. 
	   //storedProc.executeUpdate();
	   @SuppressWarnings("unchecked")
	   List<String> resultList = storedProc.getResultList();
 
	   return resultList.get(0);
 
	}
	
	public String clearFclLmtAcArIn(String transDate){
	   StoredProcedureQuery storedProc = entityManager.createStoredProcedureQuery("ClearFcl_lmt_ac_ar_in");

	   // Set the parameters of the stored procedure.
	   storedProc.registerStoredProcedureParameter("transDate", String.class, ParameterMode.IN);
	   storedProc.setParameter("transDate", transDate);
 
	   // Call the stored procedure. 
	   //storedProc.executeUpdate();
	   @SuppressWarnings("unchecked")
	   List<String> resultList = storedProc.getResultList();
 
	   return resultList.get(0);
	 
	}
	
	public String clearMlsLeadLoan(String transDate){
	   StoredProcedureQuery storedProc = entityManager.createStoredProcedureQuery("Clear_Mls_Lead_Loan");
	   
	   // Set the parameters of the stored procedure.
	   storedProc.registerStoredProcedureParameter("transDate", String.class, ParameterMode.IN);
	   storedProc.setParameter("transDate", transDate);
 
	   // Call the stored procedure. 
	   //storedProc.executeUpdate();
	   @SuppressWarnings("unchecked")
	   List<String> resultList = storedProc.getResultList();
 
	   return resultList.get(0);
	 
	}
	
	public String clearFclDrdwnTxnIn(String transDate){
	   StoredProcedureQuery storedProc = entityManager.createStoredProcedureQuery("ClearFcl_drdwn_txn_in");
	   
	   // Set the parameters of the stored procedure.
	   storedProc.registerStoredProcedureParameter("transDate", String.class, ParameterMode.IN);
	   storedProc.setParameter("transDate", transDate);
 
	   // Call the stored procedure. 
	   //storedProc.executeUpdate();
	   @SuppressWarnings("unchecked")
	   List<String> resultList = storedProc.getResultList();
 
	   return resultList.get(0);
	 
	}
	
	public String clearPcb_Lmt_Txn_In(String transDate){
		   StoredProcedureQuery storedProc = entityManager.createStoredProcedureQuery("ClearPcb_Lmt_Txn_In");
		   
		   // Set the parameters of the stored procedure.
		   storedProc.registerStoredProcedureParameter("transDate", String.class, ParameterMode.IN);
		   storedProc.setParameter("transDate", transDate);
	 
		   // Call the stored procedure. 
		   //storedProc.executeUpdate();
		   @SuppressWarnings("unchecked")
		   List<String> resultList = storedProc.getResultList();
	 
		   return resultList.get(0);
		 
		}
	
	public String clearPcb_Account_Txn_In(String transDate){
		   StoredProcedureQuery storedProc = entityManager.createStoredProcedureQuery("ClearPcb_Account_Txn_In");
		   
		   // Set the parameters of the stored procedure.
		   storedProc.registerStoredProcedureParameter("transDate", String.class, ParameterMode.IN);
		   storedProc.setParameter("transDate", transDate);
	 
		   // Call the stored procedure. 
		   //storedProc.executeUpdate();
		   @SuppressWarnings("unchecked")
		   List<String> resultList = storedProc.getResultList();
	 
		   return resultList.get(0);
		 
		}
	
	public String addIndexAccount(String transDate){
		   StoredProcedureQuery storedProc = entityManager.createStoredProcedureQuery("Add_Index_Account");
		   
		   // Set the parameters of the stored procedure.
		   storedProc.registerStoredProcedureParameter("transDate", String.class, ParameterMode.IN);
		   storedProc.setParameter("transDate", transDate);
	 
		   // Call the stored procedure. 
		   //storedProc.executeUpdate();
		   @SuppressWarnings("unchecked")
		   List<String> resultList = storedProc.getResultList();
	 
		   return resultList.get(0);
		 
		}
	
	public String addIndexLimit(String transDate){
		   StoredProcedureQuery storedProc = entityManager.createStoredProcedureQuery("Add_Index_Limit");
		   
		   // Set the parameters of the stored procedure.
		   storedProc.registerStoredProcedureParameter("transDate", String.class, ParameterMode.IN);
		   storedProc.setParameter("transDate", transDate);
	 
		   // Call the stored procedure. 
		   //storedProc.executeUpdate();
		   @SuppressWarnings("unchecked")
		   List<String> resultList = storedProc.getResultList();
	 
		   return resultList.get(0);
		 
		}	
	
//	public String clearKcpsAppFee(String transDate) {
//		StoredProcedureQuery storedProc = entityManager.createStoredProcedureQuery("ClearKcpsAppFee");
//
//		// Set the parameters of the stored procedure.
//		storedProc.registerStoredProcedureParameter("transDate", String.class, ParameterMode.IN);
//		storedProc.setParameter("transDate", transDate);
//
//		// Call the stored procedure.
//		// storedProc.executeUpdate();
//		@SuppressWarnings("unchecked")
//		List<String> resultList = storedProc.getResultList();
//
//		return resultList.get(0);
//
//	}
	
}
