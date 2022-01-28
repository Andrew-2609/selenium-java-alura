package com.ndrewcoding.leilao.service;

import com.ndrewcoding.leilao.dto.NovoLanceDto;
import com.ndrewcoding.leilao.model.Lance;
import com.ndrewcoding.leilao.model.Leilao;
import com.ndrewcoding.leilao.model.Usuario;
import com.ndrewcoding.leilao.repositories.LanceRepository;
import com.ndrewcoding.leilao.repositories.LeilaoRepository;
import com.ndrewcoding.leilao.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanceService {

    private final LanceRepository lanceRepository;
    private final UsuarioRepository usuarioRepository;
    private final LeilaoRepository leilaoRepository;

    @Autowired
    public LanceService(LanceRepository lanceRepository, UsuarioRepository usuarioRepository, LeilaoRepository leilaoRepository) {
        this.lanceRepository = lanceRepository;
        this.usuarioRepository = usuarioRepository;
        this.leilaoRepository = leilaoRepository;
    }

    public boolean proporLance(NovoLanceDto lanceDto, String nomeUsuario) {

        Usuario usuario = usuarioRepository.getUserByUsername(nomeUsuario);
        Lance lance = lanceDto.toLance(usuario);

        Leilao leilao = this.getLeilao(lanceDto.getLeilaoId());

        if (leilao.propor(lance)) {
            lanceRepository.save(lance);
            return true;
        }

        return false;
    }

    public Leilao getLeilao(Long leilaoId) {
        return leilaoRepository.getOne(leilaoId);
    }

}