package com.interswitch.techquest.billers.sample;

import com.interswitch.techquest.billers.dto.Biller;
import com.interswitch.techquest.billers.dto.BillerResponse;

public class GetBillers extends BaseSample {

    public static void main(String[] args) {

        try {
            BillerResponse billers = billPayment.getBillers();
            if (billers instanceof BillerResponse && billers.getBiller() != null) {

                Biller[] billerArray = billers.getBiller();

                Biller firstBiller = billerArray[0];

                String billerId = firstBiller.getBillerid();

                String billername = firstBiller.getBillername();

                System.out.println("biller id is: " + billerId);
                System.out.println("biller name is " + billername);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
