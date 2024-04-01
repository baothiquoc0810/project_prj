/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import java.util.List;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import modal.OrderTest;

public class PaymentServices {

    private static final String CLIENT_ID = "ATQBGWh_WrFcP4mqXeRmvYekfPLH_ZsVUzkl0z5lCCkSDqPIg52RUyUQMY6kYdEHBdSLXDljqSLzJuQQ";
    private static final String CLIENT_SECRET = "EAHupYUy6v0rpxwMvsnLXkFTDIg0FCLfjMktcaGA6FZulfE5i4zvbSZn2lRvoy4NU0mymSSgu8x_X-3Z";
    private static final String MODE = "sandbox";

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        Payment payment = new Payment().setId(paymentId);
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return payment.execute(apiContext, paymentExecution);
    }

    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        return Payment.get(apiContext, paymentId);
    }

    public String authorizePayment(OrderTest orderDetail) throws PayPalRESTException {

        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectUrls();
        List<Transaction> transactionList = getTransactionInformation(orderDetail);

        Payment requestPayment = new Payment();
        requestPayment.setTransactions(transactionList);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");

        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
        Payment approvedPayment = requestPayment.create(apiContext);

        System.out.println(approvedPayment);

        return getApprovalLink(approvedPayment);
    }

    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;

        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
            }
        }

        return approvalLink;
    }

    private String convertNumberFromGermanToUsFormat(String germanNumber) {
        int usNumber = (Integer.parseInt(germanNumber)/25000);
        String result = Integer.toString(usNumber);
        
        return result;
    }

    private List<Transaction> getTransactionInformation(OrderTest orderDetail) {
        Details details = new Details();
        details.setShipping("0");
        details.setSubtotal(convertNumberFromGermanToUsFormat(orderDetail.getAllPrice()));
        details.setTax("0");

        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal(convertNumberFromGermanToUsFormat(orderDetail.getAllPrice()));
        amount.setDetails(details);
        //System.out.println(amount);

        Item item = new Item();
        item.setCurrency("USD");
        item.setName("ticket");
        item.setPrice(convertNumberFromGermanToUsFormat(orderDetail.getAllPrice()));
        item.setTax("0");
        item.setQuantity("1");

        List<Item> items = new ArrayList<Item>();
        items.add(item);

        ItemList itemList = new ItemList();
        itemList.setItems(items);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("ticket");
        transaction.setItemList(itemList);

        List<Transaction> transactionList = new ArrayList<Transaction>();
        transactionList.add(transaction);

        return transactionList;
    }

    private RedirectUrls getRedirectUrls() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:9999/project/cancel.html");
        redirectUrls.setReturnUrl("http://localhost:9999/project/review_payment");
        return redirectUrls;
    }

    private Payer getPayerInformation() {
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("Bao");
        payerInfo.setLastName("Quoc");
        payerInfo.setLastName("sb-j0bws29984881@personal.example.com");

        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        payer.setPayerInfo(payerInfo);

        return payer;
    }

}
