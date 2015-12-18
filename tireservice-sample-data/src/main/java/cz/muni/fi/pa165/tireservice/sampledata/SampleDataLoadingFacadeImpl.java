package cz.muni.fi.pa165.tireservice.sampledata;

import cz.muni.fi.pa165.tireservice.entity.Order;
import cz.muni.fi.pa165.tireservice.entity.Service;
import cz.muni.fi.pa165.tireservice.entity.Tire;
import cz.muni.fi.pa165.tireservice.entity.TireVendor;
import cz.muni.fi.pa165.tireservice.entity.User;
import cz.muni.fi.pa165.tireservice.enums.CarType;
import cz.muni.fi.pa165.tireservice.enums.OrderState;
import cz.muni.fi.pa165.tireservice.sevice.OrderService;
import cz.muni.fi.pa165.tireservice.sevice.ServiceService;
import cz.muni.fi.pa165.tireservice.sevice.TireService;
import cz.muni.fi.pa165.tireservice.sevice.TireVendorService;
import cz.muni.fi.pa165.tireservice.sevice.UserService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private TireVendorService tireVendorService;

    @Autowired
    private TireService tireService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ServiceService serviceService;

    @Override
    public void loadData() throws IOException {
        TireVendor barum = tireVendor("Barum");
        TireVendor pirelli = tireVendor("Pirelli");
        TireVendor continental = tireVendor("Continental");
        

        Service oilReplacement = service("Engine oil replacement", new BigDecimal("350.00"));
        Service tireBalancing = service("Tire balancing", new BigDecimal("560.00"));
        Service waxing = service("Waxing", new BigDecimal("400"));

        User pepa = user("Josef Novák", "Botanická 68a 60200 Brno", "776665443", false);
        User franta = user("František Nejezchleba", "Falešná 123 12200 Praha", "543234789", false);
        User mirek = user("Miroslav Skočdopole", "Vymyšlená 77 80022 Ostrava", "543234729", false);
        User admin = user("Admin Adminovič", "Božetěchova 2 60200 Brno", "722333444", true);

        Tire polarisBarum = tire("Polaris", 13, 130, new BigDecimal("880.00"), "70T", barum, CarType.PASSENGER);
        Tire polarisPirelli = tire("Polaris", 13, 130, new BigDecimal("899.00"), "70T", pirelli, CarType.PASSENGER);
        Tire brilantis = tire("Brillantis", 14, 120, new BigDecimal("1480.00"), "73T", continental, CarType.VAN);
        Tire tire1 = tire("Guma", 15, 140, new BigDecimal("2000.00"), "75T", pirelli, CarType.VAN);
        Tire tire2 = tire("Pneumatika", 21, 180, new BigDecimal("12000.00"), "75T", barum, CarType.PASSENGER);

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(2015, 1, 1);
        Date date1 = cal.getTime();
        cal.set(2015, 4, 6);
        Date date2 = cal.getTime();
        cal.set(2014, 11, 6);
        Date date3 = cal.getTime();

        Order order1 = order(polarisBarum, oilReplacement, OrderState.NEW, pepa, date1, CarType.PASSENGER);
        Order order2 = order(brilantis, tireBalancing, OrderState.IN_PROGRESS, admin, date1, CarType.PASSENGER);
        Order order3 = order(polarisPirelli, waxing, OrderState.COMPLETED, franta, date3, CarType.PASSENGER);
        Order order4 = order(tire1, null, OrderState.COMPLETED, mirek, date2, CarType.VAN);
        //Order order5 = order(tire2, oilReplacement, OrderState.CANCELLED, mirek, date2, CarType.VAN);

    }

    private TireVendor tireVendor(String name) {
        TireVendor tireVendor = new TireVendor();
        tireVendor.setName(name);

        tireVendorService.create(tireVendor);
        return tireVendor;
    }

    private Service service(String name, BigDecimal price) {
        Service service = new Service();
        service.setName(name);
        service.setPrice(price);

        serviceService.create(service);

        return service;
    }

    private User user(String name, String address, String phone, boolean admin) {
        User user = new User();
        user.setAddress(address);
        user.setIsAdmin(admin);
        user.setName(name);
        user.setPhone(phone);

        userService.create(user);

        return user;
    }

    private Tire tire(String name, int diameter, int width, BigDecimal price, String speedIndex, TireVendor tireVendor, CarType type) {
        Tire tire = new Tire();

        tire.setName(name);
        tire.setWidth(width);
        tire.setPrice(price);
        tire.setSpeedIndex(speedIndex);
        tire.setTireVendor(tireVendor);
        tire.setCarType(type);
        tire.setDiameter(diameter);

        tireService.createTire(tire);

        return tire;
    }

    private Order order(Tire tire, Service service, OrderState state, User customer, Date created, CarType type) {
        Order order = new Order();

        order.setCarType(type);
        order.setCreated(created);
        order.setCustomer(customer);
        order.setState(state);

        order.addTire(tire);

        if (service != null) {
            order.addService(service);
        }

        orderService.createOrder(order);

        return order;
    }
}
