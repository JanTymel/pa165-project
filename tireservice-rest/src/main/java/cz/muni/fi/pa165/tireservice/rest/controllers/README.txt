/**
 * README file  
 * shows how to test REST api via CURL commands
 * @author Michal Kysilko
 */

curl http://localhost:8080/pa165/rest/orders
    -- returns all orders

curl http://localhost:8080/pa165/rest/orders/orders_by_user_id/1
    -- returns orders of user with id 1

curl http://localhost:8080/pa165/rest/orders/1
    -- returns order with id 1

curl http://localhost:8080/pa165/rest/orders/1?action=cancel
    -- sets the state of order with id 1 to CANCELED