package com.squad15.exmed.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Table(name = "tb_usuario")
@Entity(name = "Usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "login")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    private String login;
    private String senha;
    @Email
    private String email;
    private String codigoIndicacao; // Adicione o campo para o código de indicação
    private String nome;
    private String sexo;
    private Date dataNascimento;
    private int idade;
    @CPF
    private String cpf;
    @Embedded
    private Endereco endereco;
    private int saldoExmedcoin;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    // Adicione uma lista de indicações feitas por este usuário
    @OneToMany(mappedBy = "indicador")
    private List<Indicacao> indicacoesFeitas;

    // Adicione uma lista de indicações recebidas por este usuário
    @OneToMany(mappedBy = "indicado")
    private List<Indicacao> indicacoesRecebidas;

    // Outros campos e métodos

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public void fazerIndicacao(Usuario usuarioIndicado) {
        Indicacao indicacao = new Indicacao();
        indicacao.setIndicador(this);
        indicacao.setIndicado(usuarioIndicado);
        indicacao.setDataIndicacao(new Date());
        if (indicacoesFeitas == null) {
            indicacoesFeitas = new ArrayList<>();
        }
        indicacoesFeitas.add(indicacao);

        this.saldoExmedcoin += 100;
    }
}
