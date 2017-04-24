package com.interswitch.techquest.billers.sample;

import com.interswitch.techquest.billers.dto.BillerResponse;
import com.interswitch.techquest.billers.dto.CategoryResponse;
import com.interswitch.techquest.billers.dto.PaymentItemResponse;
import com.interswitch.techquest.billers.dto.PaymentResponse;
import com.interswitch.techquest.billers.dto.TransactionStatusResponse;

/**
 * 
 * @author 
 * An all in one sample is meant to show all the requests used at once.
 * The flow is 
 * 
 * 1. Get All Categorys.
 * 2. Select a biller from any category.
 * 3. Get all payment item codes for that biller selected from Number 2 Step.
 * 4. Make Payment
 * 5. Query the status of a transaction
 *
 */
public class AllInOneSample extends BaseSample {
    
    public static void main(String[] args) {
        
        String customerId = "07030241757";// (mandatory) customerId

        String amount = "500"; // (mandatory) amount is in minor.
        
        String paymentCode = "40201";
        
        try{
            
            // 1. Get All Categorys
            CategoryResponse categoryResponse = billPayment.getCategorys();
            
            
            
            String categoryId = categoryResponse != null ? categoryResponse.getCategorys()[0].getCategoryid() : null;
            
            if(categoryId == null) throw new Exception("CateogryId cannot be null");
            
            
            
            
            // 2. Get Billers given a categoryId
            BillerResponse billersResponse = billPayment.getCategoryBillers(categoryId);
            
            String billerId = billersResponse!=null ? billersResponse.getBiller()[0].getBillerid() : null;
            
            if(billerId == null) throw new Exception("Biller Id cannot be null");
            
            
            
            
            // 3. Get All PaymentItems for billerId
            PaymentItemResponse paymentItemResponse = billPayment.getBillerPaymentItems(billerId);
            
            String paymentItemCode = paymentItemResponse != null ? paymentItemResponse.getPaymentitems()[0].getPaymentCode() : null;
            
            if(paymentItemCode == null) throw new Exception("Payment Item Response cannot be is null");
            
            String requestReference = "test" + String.valueOf((int)(100000000*Math.random()));//(mandatory)
            
            
            
            // 4. Make a payment to a paymentcode
            PaymentResponse paymentResponse =  billPayment.makePayment(amount, customerId, paymentCode, requestReference);
            
            
            
            
            //5. Query the status of a transaction
            TransactionStatusResponse transactionStatus = billPayment.getTransactionStatus(requestReference);
            
            String status = transactionStatus.getStatus();
            
            System.out.println("status: "+status);
        }
        catch(Exception ex) {
            
            ex.printStackTrace();
        }
        
    }
}
