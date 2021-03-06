package cz.muni.fi.pa165.tireservice.service.config;

import cz.muni.fi.pa165.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.tireservice.service.facade.OrderFacadeImpl;
import cz.muni.fi.pa165.tireservice.sevice.OrderServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses={OrderServiceImpl.class, OrderFacadeImpl.class})
public class ServiceConfiguration {


	@Bean
	public Mapper dozer(){
		DozerBeanMapper dozer = new DozerBeanMapper();
		dozer.addMapping(new DozerCustomConfig());
		return dozer;
	}

	public class DozerCustomConfig extends BeanMappingBuilder {
	    @Override
	    protected void configure() {
//	        mapping(Category.class, CategoryDTO.class);
	    }
	}

}

