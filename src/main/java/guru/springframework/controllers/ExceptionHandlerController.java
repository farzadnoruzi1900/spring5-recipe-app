package guru.springframework.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {
   /* important thing about testing this exception handling in different classes is that we have to change our mockMvc
    instansiation we should add something that you can see in imageControllerTest file .*/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleFileFormatException(Exception exception){
        log.error(exception.getMessage());
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("exception",exception);
        modelAndView.setViewName("exceptions/400view");
        return modelAndView;
    }
}
