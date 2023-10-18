/**
 * Configuração de Fuso Horário do Spring Framework.
 *
 * Esta classe é responsável por configurar o fuso horário padrão do sistema para 'America/Sao_Paulo'
 * usando a anotação {@link jakarta.annotation.PostConstruct} durante o processo de inicialização da aplicação Spring.
 * Isso garante que todas as datas e horas no aplicativo sigam o fuso horário de São Paulo.
 */
package com.fabio.api.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class SpringTimezoneConfig {

    /**
     * Configura o fuso horário padrão para 'America/Sao_Paulo' durante a inicialização da aplicação Spring.
     */
    @PostConstruct
    public void timezoneConfig() {
        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
    }
}
