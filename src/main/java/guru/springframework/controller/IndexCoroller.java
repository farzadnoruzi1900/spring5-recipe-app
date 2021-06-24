package guru.springframework.controller;

import guru.springframework.module.Catagory;
import guru.springframework.module.UnitOfMeasure;
import guru.springframework.repository.CatagoryRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexCoroller {
   private CatagoryRepository catagoryRepository;
   private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexCoroller(CatagoryRepository catagoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.catagoryRepository = catagoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"/index",""})
    public String getIndex(){
        // in here we get access to those query method and we store them in Optional class again for purpose
        //of not having point to null object and then we easily fetch data from database .
        Optional<Catagory> catagory=catagoryRepository.findByDescription("just for test1");
        Optional<UnitOfMeasure> unitOfMeasure=unitOfMeasureRepository.findByDescription("just for test1");
        System.out.println("cat Id is : "+catagory.get().getId());
        System.out.println("unit id is : "+unitOfMeasure.get().getId());
        return "index";
    }
}
