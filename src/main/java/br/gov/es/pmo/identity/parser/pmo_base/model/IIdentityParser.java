/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.gov.es.pmo.identity.parser.pmo_base.model;

import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 *
 * @author gean.carneiro
 * 
 * @param <ID> o tipo do id/sub utilizado pra identificar o usuario
 * 
 */
public interface IIdentityParser<ID> {
    
    /**
     * retorna o identificador do usuario, pode ser id,
     * sub, cpf, o que a api usar pra identificar
     * 
     * @param user usuario retornado pelo user-info
     * @param token token de autenticação
     * @return id/sub do usuário
     */
    public ID getId(OAuth2User user, String token);
    
    /**
     * retorna o nome do usuario
     * 
     * @param user usuario retornado pelo user-info
     * @param token token de autenticação
     * @return nome do usuário
     */    
    public String getNome(OAuth2User user, String token);
    
    /**
     * retorna o email do usuario
     * 
     * @param user usuario retornado pelo user-info
     * @param token token de autenticação
     * @return nome do usuário
     */    
    public String getEmail(OAuth2User user, String token);
    
    /**
     * retorna uma propriedade especifica do usuario
     * 
     * @param user usuario retornado pelo user-info
     * @param token token de autenticação
     * @param attr atributo que deseja recuperar do usuario
     * @return 
     */    
    default Object getAttribute(OAuth2User user, String token, String attr) {
        return user.getAttribute(attr);
    }

    
}
