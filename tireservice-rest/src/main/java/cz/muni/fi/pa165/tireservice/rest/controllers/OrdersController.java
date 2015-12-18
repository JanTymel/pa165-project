/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.tireservice.rest.controllers;

import cz.muni.fi.pa165.tireservice.facade.OrderFacade;
import cz.muni.fi.pa165.tireservice.rest.ApiUris;
import cz.muni.fi.pa165.tireservice.dto.OrderDto;
import cz.muni.fi.pa165.tireservice.rest.exceptions.InvalidParameterException;
import cz.muni.fi.pa165.tireservice.rest.exceptions.ResourceNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Michal Kysilko
 */

@RestController
@RequestMapping(ApiUris.ROOT_URI_ORDERS)
public class OrdersController {
    
    @Autowired
    private OrderFacade orderFacade;
    
    /**
     * returns all orders
     * 
     * @return List of OrderDto
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<OrderDto> getAllOrders() {

        return orderFacade.getAllOrders();
    }
    
    /**
     * gets orders by user
     * @param userId 
     * @return List of OrderDto
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "orders_by_user_id/{user_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<OrderDto> getOrdersByUser(@PathVariable("user_id") long userId) throws Exception {

        List<OrderDto> order_list = orderFacade.getOrdersByUser(userId);
        if (order_list == null){
            throw new ResourceNotFoundException();
        }       
        return order_list;

    }

    
    /**
     * gets order by its id
     * @param id 
     * @return OrderDto
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final OrderDto getOrderById(@PathVariable("id") long id) throws Exception {

        OrderDto order = orderFacade.getOrderById(id);
        if (order == null){
            throw new ResourceNotFoundException();
        }       
        return order;

    }
    
    /**
     * sets order status according to param 'action'
     * @param id 
     * @param action - process, cancel or finish
     * @throws InvalidParameterException
     * @returns OrderDto
     */
    @RequestMapping(value = "{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public final OrderDto changeOrderState(@PathVariable("id") long id, @RequestParam(value = "action") String action) throws Exception {

        if (action.equalsIgnoreCase("process")) {
            orderFacade.startProcessingOrder(id);
        } else if (action.equalsIgnoreCase("cancel")) {
            orderFacade.cancelOrder(id);
        } else if (action.equalsIgnoreCase("finish")) {
            orderFacade.finishOrder(id);
        } else {
            throw new InvalidParameterException();
        }  
        
        return orderFacade.getOrderById(id);   
    }
    
    
    
}
