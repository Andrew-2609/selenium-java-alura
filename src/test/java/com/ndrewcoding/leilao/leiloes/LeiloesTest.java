package com.ndrewcoding.leilao.leiloes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class LeiloesTest {

    private LeiloesPage leiloesPage;

    @BeforeEach
    void setUp() {
        this.leiloesPage = new LeiloesPage();
    }

    @AfterEach
    void teardown() {
        leiloesPage.fechar();
    }

}
