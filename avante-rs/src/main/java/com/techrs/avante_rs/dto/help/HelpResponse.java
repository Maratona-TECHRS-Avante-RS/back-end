package com.techrs.avante_rs.dto.help;

import java.time.LocalDateTime;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class HelpResponse {
    private Long idChamado;
    private Integer numeroVoluntarios;
    private LocalDateTime criadoEm;
    private String ajuda;
    private String urgencia;
    private String expiracao;
    private String voluntarios;

}
