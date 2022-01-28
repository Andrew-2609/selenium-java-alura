package com.ndrewcoding.leilao.controller;

import com.ndrewcoding.leilao.dto.NovoLanceDto;
import com.ndrewcoding.leilao.dto.NovoLeilaoDto;
import com.ndrewcoding.leilao.model.Leilao;
import com.ndrewcoding.leilao.model.Usuario;
import com.ndrewcoding.leilao.repositories.LeilaoRepository;
import com.ndrewcoding.leilao.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/leiloes")
public class LeilaoController {

    private final LeilaoRepository leilaoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public LeilaoController(LeilaoRepository leilaoRepository, UsuarioRepository usuarioRepository) {
        this.leilaoRepository = leilaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public ModelAndView index(Principal principal) {
        ModelAndView mv = new ModelAndView("leilao/index");
        mv.addObject("leiloes", leilaoRepository.findAll());
        mv.addObject("usuarioLogado", principal);
        return mv;
    }

    @GetMapping("/{id}/form")
    public ModelAndView form(@PathVariable("id") Long id, Principal principal) {
        Leilao leilao = leilaoRepository.getOne(id);
        NovoLeilaoDto form = new NovoLeilaoDto(leilao);

        ModelAndView mv = new ModelAndView("leilao/form");
        mv.addObject("usuario", principal.getName());
        mv.addObject("leilao", form);
        return mv;
    }

    @PostMapping
    public ModelAndView saveOrUpdate(@Valid @ModelAttribute("leilao") NovoLeilaoDto leilaoForm, Errors errors, RedirectAttributes attr, Principal principal) {
        if (errors.hasErrors()) {
            ModelAndView mv = new ModelAndView("/leilao/form");
            mv.addObject("leilao", leilaoForm);
            mv.addObject("usuario", principal.getName());
            return mv;
        }

        Usuario usuario = usuarioRepository.getUserByUsername(principal.getName());
        Leilao leilao = leilaoForm.toLeilao();
        leilao.setUsuario(usuario);

        leilaoRepository.save(leilao);

        attr.addFlashAttribute("message", "Leilão salvo com sucesso");

        return new ModelAndView("redirect:/leiloes");
    }

    @GetMapping("/new")
    public ModelAndView newLeilao(Principal principal) {
        ModelAndView mv = new ModelAndView("leilao/form");
        mv.addObject("usuario", principal.getName());
        mv.addObject("leilao", new NovoLeilaoDto());
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable Long id, Principal principal) {
        ModelAndView mv = new ModelAndView("leilao/show");
        mv.addObject("usuario", principal.getName());
        mv.addObject("leilao", leilaoRepository.getOne(id));
        mv.addObject("lance", new NovoLanceDto());
        return mv;
    }

}