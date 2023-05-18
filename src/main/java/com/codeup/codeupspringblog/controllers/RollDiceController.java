package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RollDiceController {
    @GetMapping("/roll-dice")
    public String showForm() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String showForm(@PathVariable int n, Model model) {
        model.addAttribute("userGuess", "Your guess is: " + n);
        int randomInt = (int)Math.floor(Math.random() * (6 - 1 + 1) + 1);
        model.addAttribute("randNum", "The random number is: " + randomInt);
        if (n == randomInt) {
            model.addAttribute("correctIncorrect", "Correct guess!");
        } else {
            model.addAttribute("correctIncorrect", "Incorrect guess!");
        }
        return "roll-dice";
    }

}
