/*
 * Here comes the text of your license
 * Each line should be prefixed with  * 
 */
package com.kalsym.pandaGo.deliveryservice;

import com.kalsym.pandaGo.deliveryservice.models.daos.DeliveryOrder;

/**
 *
 * @author user
 */
public class QueryOrderResult {
    public int providerId;
    public DeliveryOrder orderFound;
    public boolean isSuccess;
}
