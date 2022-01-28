package com.ndrewcoding.leilao.controller;

import com.ndrewcoding.leilao.dto.NovoLanceDto;
import com.ndrewcoding.leilao.model.Leilao;
import com.ndrewcoding.leilao.service.LanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping(value = "/lances")
public class LanceController {

    private final LanceService lanceService;

    @Autowired
    public LanceController(LanceService lanceService) {
        this.lanceService = lanceService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView novoLance(@Valid @ModelAttribute("lance") NovoLanceDto lanceDto, Errors erros, Principal principal, RedirectAttributes redirectAttributes) {
        Leilao leilao = lanceService.getLeilao(lanceDto.getLeilaoId());

        if (erros.hasErrors()) {
            ModelAndView mv = new ModelAndView("/leilao/show");
            mv.addObject("lance", lanceDto);
            mv.addObject("leilao", leilao);
            return mv;
        }

        if (lanceService.proporLance(lanceDto, principal.getName())) {
            redirectAttributes.addFlashAttribute("message", "Lance adicionado com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Lance invalido!");
        }

        String redirectURL = "redirect:/leiloes/" + lanceDto.getLeilaoId();
        return new ModelAndView(redirectURL);
    }

}
