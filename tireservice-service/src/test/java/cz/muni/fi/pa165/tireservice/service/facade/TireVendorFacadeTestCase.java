package cz.muni.fi.pa165.tireservice.service.facade;

import cz.muni.fi.pa165.tireservice.dto.TireVendorDto;
import cz.muni.fi.pa165.tireservice.entity.TireVendor;
import cz.muni.fi.pa165.tireservice.facade.TireVendorFacade;
import cz.muni.fi.pa165.tireservice.sevice.BeanMappingService;
import cz.muni.fi.pa165.tireservice.sevice.TireVendorService;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Jan Tymel
 */
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@RunWith(MockitoJUnitRunner.class)
public class TireVendorFacadeTestCase {

    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private TireVendorService tireVendorService;

    @InjectMocks
    private TireVendorFacade tireVendorFacade = new TireVendorFacadeImpl();

    @BeforeClass
    public void setup() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    private TireVendor vendor1;
    private TireVendor vendor2;

    private TireVendorDto vendorDto1;
    private TireVendorDto vendorDto2;

    private List<TireVendor> vendorList;
    private List<TireVendorDto> vendorDtoList;

    @BeforeMethod
    public void setUpMethod() throws Exception {
        vendor1 = new TireVendor(1l);
        vendor1.setName("Pirelli");

        vendor2 = new TireVendor(2l);
        vendor2.setName("Continental");

        vendorDto1 = new TireVendorDto();
        vendorDto1.setId(1l);
        vendorDto1.setName("Pirelli");

        vendorDto2 = new TireVendorDto();
        vendorDto2.setId(2l);
        vendorDto2.setName("Continental");

        vendorList = new ArrayList<>();
        vendorList.add(vendor1);
        vendorList.add(vendor2);

        vendorDtoList = new ArrayList<>();
        vendorDtoList.add(vendorDto1);
        vendorDtoList.add(vendorDto2);
    }

//    @Test
    public void testGetAllTireVendors() {
        when(tireVendorService.findAll()).thenReturn(vendorList);
        when(beanMappingService.mapTo(Matchers.anyCollection(), Matchers.eq(TireVendorDto.class))).thenReturn(vendorDtoList);
        assertEquals(tireVendorFacade.getAllTireVendors().size(), 2);
    }

//    @Test
    public void testGetTireVendorById() {
        when(tireVendorService.findById(1l)).thenReturn(vendor1);
        when(beanMappingService.mapTo(vendor1, TireVendorDto.class)).thenReturn(vendorDto1);
        assertEquals(tireVendorFacade.getTireVendorById(1l), vendorDto1);
    }

}
