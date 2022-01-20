package be.vdab.frida.controllers;

import be.vdab.frida.domain.Saus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.stream.Stream;

@Controller
@RequestMapping("sauzen")
public class SausController {
    private final Saus[] sauzen = {
         new Saus(3, "cocktail",new String[] {"mayonaise", "ketchup", "cognac"}),
         new Saus(6,"mayonaise", new String[] {"ei", "mosterd"}),
         new Saus(7, "mosterd", new String[] {"mosterd", "azijn", "witte wijn"}),
         new Saus(12, "tartare", new String[] {"mayonaise", "augurk", "tabasco"}),
         new Saus(44,"vinaigrette", new String[] {"olijfolie", "mosterd","azijn"})};
    @GetMapping
    public ModelAndView sauzen() {
        return new ModelAndView("sauzen", "sauzen",sauzen);
    }
    @GetMapping("{id}")
    public ModelAndView saus(@PathVariable long id) {
        var modelAndView = new ModelAndView("saus");
        Arrays.stream(sauzen).filter(saus -> saus.getId() == id).findFirst()
                .ifPresent(saus -> modelAndView.addObject(saus));
        return modelAndView;
    }

    private final char[] alfabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    @GetMapping("alfabet")
    public ModelAndView alfabet() {
        return new ModelAndView("sausAlfabet", "alfabet", alfabet);
    }

    private Stream<Saus> sauzenDieBeginnenMet(char letter) {
        return Arrays.stream(sauzen)
                .filter(saus -> saus.getNaam().charAt(0) == letter);
    }

    @GetMapping("alfabet/{letter}")
    public ModelAndView sauzenBeginnendMet(@PathVariable char letter) {
        return new ModelAndView("sausAlfabet", "alfabet", alfabet)
                .addObject("sauzen", sauzenDieBeginnenMet(letter).iterator());
    }
}
